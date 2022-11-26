package ar.edu.um.programacion2.carro_de_compras.service;

import ar.edu.um.programacion2.carro_de_compras.domain.CarroCompra;
import ar.edu.um.programacion2.carro_de_compras.repository.CarroCompraRepository;
import ar.edu.um.programacion2.carro_de_compras.repository.ProductoSeleccionadoRepository;
import ar.edu.um.programacion2.carro_de_compras.service.dto.CarroCompraDTO;
import ar.edu.um.programacion2.carro_de_compras.service.dto.UserDTO;
import ar.edu.um.programacion2.carro_de_compras.service.mapper.CarroCompraMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CarroCompra}.
 */
@Service
@Transactional
public class CarroCompraService {

    private final Logger log = LoggerFactory.getLogger(CarroCompraService.class);

    private final CarroCompraRepository carroCompraRepository;

    private final CarroCompraMapper carroCompraMapper;

    public CarroCompraService(CarroCompraRepository carroCompraRepository, CarroCompraMapper carroCompraMapper) {
        this.carroCompraRepository = carroCompraRepository;
        this.carroCompraMapper = carroCompraMapper;
    }

    /**
     * Save a carroCompra.
     *
     * @param carroCompraDTO the entity to save.
     * @return the persisted entity.
     */
    public CarroCompraDTO save(CarroCompraDTO carroCompraDTO) {
        log.debug("Request to save CarroCompra : {}", carroCompraDTO);
        CarroCompra carroCompra = carroCompraMapper.toEntity(carroCompraDTO);
        carroCompra = carroCompraRepository.save(carroCompra);
        return carroCompraMapper.toDto(carroCompra);
    }

    /**
     * Partially update a carroCompra.
     *
     * @param carroCompraDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CarroCompraDTO> partialUpdate(CarroCompraDTO carroCompraDTO) {
        log.debug("Request to partially update CarroCompra : {}", carroCompraDTO);

        return carroCompraRepository
            .findById(carroCompraDTO.getId())
            .map(existingCarroCompra -> {
                carroCompraMapper.partialUpdate(existingCarroCompra, carroCompraDTO);

                return existingCarroCompra;
            })
            .map(carroCompraRepository::save)
            .map(carroCompraMapper::toDto);
    }

    /**
     * Get all the carroCompras.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CarroCompraDTO> findAll() {
        log.debug("Request to get all CarroCompras");
        return carroCompraRepository.findAll().stream().map(carroCompraMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one carroCompra by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CarroCompraDTO> findOne(Long id) {
        log.debug("Request to get CarroCompra : {}", id);
        return carroCompraRepository.findById(id).map(carroCompraMapper::toDto);
    }

    /**
     * Delete the carroCompra by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CarroCompra : {}", id);
        CarroCompraDTO carroCompraDTO = carroCompraRepository.findById(id).map(carroCompraMapper::toDto).get();
        if (!carroCompraDTO.getFinalizado()) {
            carroCompraDTO.setFinalizado(true);
            this.partialUpdate(carroCompraDTO);
        }
    }


// ---------------------------------------------------------------------------------------------------------------------


    @Autowired
    UserService userService;

    @Autowired
    ProductoSeleccionadoRepository productoSeleccionadoRepository;

    @Autowired
    VentaService ventaService;


    /**
     * set default values before saving a carro compra.
     *
     * @param carroCompraDTO the entity to save.
     * @return the persisted entity.
     */
    public CarroCompraDTO preSave(CarroCompraDTO carroCompraDTO) {
        log.debug("Setting defaults of producto: {}", carroCompraDTO);
        if (carroCompraDTO.getFinalizado() == null) {
            carroCompraDTO.setFinalizado(false);
        }
        if (carroCompraDTO.getVendido() == null) {
            carroCompraDTO.setVendido(false);
        }
        this.checkUniqueActive(carroCompraDTO);
        if (carroCompraDTO.getTotal() == null) {
            carroCompraDTO.setTotal(0F);
        }
        return this.save(carroCompraDTO);
    }

    public void checkUniqueActive(CarroCompraDTO carroCompraDTO) {
        if (!carroCompraDTO.getFinalizado() && !carroCompraDTO.getVendido()) {
            Optional<CarroCompraDTO> carroBuscado = carroCompraRepository.findByClienteAndFinalizado(carroCompraDTO.getCliente().getId(), false).map(carroCompraMapper::toDto);
            if (!carroBuscado.isEmpty()) {
                CarroCompraDTO carro = carroBuscado.get();
                carro.setFinalizado(true);
                this.partialUpdate(carro);
            }
        }
    }

    public CarroCompraDTO findByClienteIsCurrentUserAndNotFinalizado() {
        Optional<CarroCompraDTO> carroBuscado = carroCompraRepository.findByClienteIsCurrentUserAndFinalizado(false).map(carroCompraMapper::toDto);
        if (carroBuscado.isEmpty()) {
            CarroCompraDTO carroVacio = new CarroCompraDTO();
            carroVacio.setCliente(new UserDTO(userService.getUserWithAuthorities().get()));
            return this.preSave(carroVacio);
        }
        return carroBuscado.get();
    }

    /**
     * get all carroCompra of current cliente
     * @return list of carroCompra
     */
    public List<CarroCompraDTO> findAllByClienteIsCurrentUser() {
        return carroCompraRepository.findByClienteIsCurrentUser().stream().map(carroCompraMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * get all carroCompra of cliente by id
     * @return list of carroCompra
     */
    public List<CarroCompraDTO> findAllByCliente(Long id) {
        return carroCompraRepository.findByCliente(id).stream().map(carroCompraMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Updates the Total of all the carros
     * @param carroCompraDTOList
     * @return a list of all
     */
    public List<CarroCompraDTO> updateTotal(List<CarroCompraDTO> carroCompraDTOList) {
        carroCompraDTOList.forEach((carroCompraDTO) -> {
            var wrapper = new Object(){ Float ordinal = 0F; };
            CarroCompra carroCompra = carroCompraRepository.getById(carroCompraDTO.getId());
            productoSeleccionadoRepository.findByCarro(carroCompra).forEach((productoSeleccionado) -> wrapper.ordinal += productoSeleccionado.getPrecio() * productoSeleccionado.getCantidad());
            carroCompraDTO.setTotal(wrapper.ordinal);
            this.partialUpdate(carroCompraDTO);
        });
        return carroCompraDTOList;
    }

    /**
     * Creates a new Venta if CarroCompra.vendido is true
     * @param carroCompraDTO
     */
    public void performVenta(CarroCompraDTO carroCompraDTO) {
        carroCompraDTO.setFinalizado(true);
        Optional<CarroCompraDTO> carroCompraDTOOptional = partialUpdate(carroCompraDTO);
        ventaService.performVenta(carroCompraDTOOptional.get());
    }

}
