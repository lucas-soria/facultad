package ar.edu.um.programacion2.micro_carro_de_compras.service;

import ar.edu.um.programacion2.gateway.domain.User;
import ar.edu.um.programacion2.gateway.service.UserService;
import ar.edu.um.programacion2.micro_carro_de_compras.domain.CarroCompra;
import ar.edu.um.programacion2.micro_carro_de_compras.repository.CarroCompraRepository;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.CarroCompraDTO;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.ProductoSeleccionadoDTO;
import ar.edu.um.programacion2.micro_carro_de_compras.service.mapper.CarroCompraMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import javax.management.modelmbean.ModelMBeanNotificationBroadcaster;

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
        Optional<CarroCompraDTO> carroCompraDTOOptional = carroCompraRepository.findById(id).map(carroCompraMapper::toDto);
        if (!carroCompraDTOOptional.isEmpty()) {
            CarroCompraDTO carroCompraDTO = carroCompraDTOOptional.get();
            if (carroCompraDTO.getFinalizado() == true) {
                carroCompraRepository.deleteById(id);
            }
            carroCompraDTO.setFinalizado(true);
            this.partialUpdate(carroCompraDTO);
        }
        carroCompraRepository.deleteById(id);
    }


// ---------------------------------------------------------------------------------------------------------------------

    @Autowired
    UserService userService;

    public Optional<CarroCompraDTO> findByClienteAndNotFinalizado(String username) {
        CarroCompra carroCompraEjemplo = new CarroCompra();
        Mono<User> user = userService.getUserWithAuthoritiesByLogin(username);
        System.out.println("\n\n" + user + "\n\n");
        carroCompraEjemplo.setCliente(username);
        carroCompraEjemplo.finalizado(false);
        Example<CarroCompra> carro = Example.of(carroCompraEjemplo);
        Optional<CarroCompraDTO> carroBuscado = carroCompraRepository.findOne(carro).map(carroCompraMapper::toDto);
        if (carroBuscado.isEmpty()) {
            CarroCompraDTO carroVacio = new CarroCompraDTO();
            carroVacio.setCliente(username);
            return Optional.of(this.preSave(carroVacio));
        }
        return carroBuscado;
    }

    //@Autowired
    //DistribuidorRepository distribuidorRepository;


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
        if (carroCompraDTO.getTotal() == null) {
            carroCompraDTO.setTotal(0F);
        }
        return this.save(carroCompraDTO);
    }

    /*public Boolean checkDistribuidor(DistribuidorDTO distribuidorDTO) {
        return distribuidorRepository.existsById(distribuidorDTO.getId());
    }*/


}
