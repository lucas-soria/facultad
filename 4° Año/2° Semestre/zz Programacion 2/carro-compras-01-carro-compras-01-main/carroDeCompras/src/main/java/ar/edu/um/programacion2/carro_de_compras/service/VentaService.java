package ar.edu.um.programacion2.carro_de_compras.service;

import ar.edu.um.programacion2.carro_de_compras.domain.Venta;
import ar.edu.um.programacion2.carro_de_compras.repository.CustomMapper;
import ar.edu.um.programacion2.carro_de_compras.repository.VentaRepository;
import ar.edu.um.programacion2.carro_de_compras.service.dto.CarroCompraDTO;
import ar.edu.um.programacion2.carro_de_compras.service.dto.VentaDTO;
import ar.edu.um.programacion2.carro_de_compras.service.mapper.VentaMapper;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Venta}.
 */
@Service
@Transactional
public class VentaService {

    private final Logger log = LoggerFactory.getLogger(VentaService.class);

    private final VentaRepository ventaRepository;

    private final VentaMapper ventaMapper;

    public VentaService(VentaRepository ventaRepository, VentaMapper ventaMapper) {
        this.ventaRepository = ventaRepository;
        this.ventaMapper = ventaMapper;
    }

    /**
     * Save a venta.
     *
     * @param ventaDTO the entity to save.
     * @return the persisted entity.
     */
    public VentaDTO save(VentaDTO ventaDTO) {
        log.debug("Request to save Venta : {}", ventaDTO);
        Venta venta = ventaMapper.toEntity(ventaDTO);
        venta = ventaRepository.save(venta);
        return ventaMapper.toDto(venta);
    }

    /**
     * Partially update a venta.
     *
     * @param ventaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<VentaDTO> partialUpdate(VentaDTO ventaDTO) {
        log.debug("Request to partially update Venta : {}", ventaDTO);

        return ventaRepository
            .findById(ventaDTO.getId())
            .map(existingVenta -> {
                ventaMapper.partialUpdate(existingVenta, ventaDTO);

                return existingVenta;
            })
            .map(ventaRepository::save)
            .map(ventaMapper::toDto);
    }

    /**
     * Get all the ventas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<VentaDTO> findAll() {
        log.debug("Request to get all Ventas");
        return ventaRepository.findAll().stream().map(ventaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one venta by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VentaDTO> findOne(Long id) {
        log.debug("Request to get Venta : {}", id);
        return ventaRepository.findById(id).map(ventaMapper::toDto);
    }

    /**
     * Delete the venta by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Venta : {}", id);
        productoCompradoService.deleteByVenta(id);
        ventaRepository.deleteById(id);
    }


// ---------------------------------------------------------------------------------------------------------------------



    @Autowired
    ProductoCompradoService productoCompradoService;

    @Autowired
    CarroCompraService carroCompraService;

    public VentaDTO performVenta(CarroCompraDTO carroCompraDTO) {
        if (!carroCompraDTO.getFinalizado() || !carroCompraDTO.getVendido()) {
            carroCompraDTO.setFinalizado(true);
            carroCompraDTO.setVendido(true);
            carroCompraService.partialUpdate(carroCompraDTO);
        }
        VentaDTO ventaDTOrealizada = save(CustomMapper.fromCarroToVenta(carroCompraDTO));
        productoCompradoService.performVenta(ventaDTOrealizada);
        return ventaDTOrealizada;
    }

    /**
     * get all Venta of current cliente
     * @return list of VentaDTO
     */
    public List<VentaDTO> findAllByClienteIsCurrentUser() {
        return ventaRepository.findByClienteIsCurrentUser().stream().map(ventaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * get all Venta of cliente by id
     * @return list of VentaDTO
     */
    public List<VentaDTO> findAllByCliente(Long id) {
        return ventaRepository.findByCliente(id).stream().map(ventaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * get all Venta of the last 30 days
     * @return list of VentaDTO
     */
    public List<VentaDTO> findAllOfPastThirtyDays() {
        LocalDate fechaInicio;
        if (LocalDate.now().getMonthValue() != 1) {
            fechaInicio = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue()-1, LocalDate.now().getDayOfMonth());
        } else {
            fechaInicio = LocalDate.of(LocalDate.now().getYear()-1, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        }
        return ventaRepository.findByFechaInicioAndFechaFin(fechaInicio, LocalDate.now()).stream().map(ventaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * get all Venta of this month
     * @return list of VentaDTO
     */
    public List<VentaDTO> findAllByCurrentMonth() {
        LocalDate fechaInicio = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 1);
        return ventaRepository.findByFechaInicioAndFechaFin(fechaInicio, LocalDate.now()).stream().map(ventaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * get all Venta of the last year
     * @return list of VentaDTO
     */
    public List<VentaDTO> findAllByPastYear() {
        LocalDate fechaInicio = LocalDate.of(LocalDate.now().getYear()-1, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        return ventaRepository.findByFechaInicioAndFechaFin(fechaInicio, LocalDate.now()).stream().map(ventaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * get all Venta between dates
     * @return list of VentaDTO
     */
    public List<VentaDTO> findAllBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return ventaRepository.findByFechaInicioAndFechaFin(fechaInicio, fechaFin).stream().map(ventaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }


}
