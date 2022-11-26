package ar.edu.um.programacion2.carro_de_compras.service;

import ar.edu.um.programacion2.carro_de_compras.domain.ProductoComprado;
import ar.edu.um.programacion2.carro_de_compras.domain.Venta;
import ar.edu.um.programacion2.carro_de_compras.repository.*;
import ar.edu.um.programacion2.carro_de_compras.service.dto.ProductoCompradoDTO;
import ar.edu.um.programacion2.carro_de_compras.service.dto.ProductoDTO;
import ar.edu.um.programacion2.carro_de_compras.service.dto.VentaDTO;
import ar.edu.um.programacion2.carro_de_compras.service.mapper.ProductoCompradoMapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ar.edu.um.programacion2.carro_de_compras.service.mapper.ProductoSeleccionadoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Service Implementation for managing {@link ProductoComprado}.
 */
@Service
@Transactional
public class ProductoCompradoService {

    private final Logger log = LoggerFactory.getLogger(ProductoCompradoService.class);

    private final ProductoCompradoRepository productoCompradoRepository;

    private final ProductoCompradoMapper productoCompradoMapper;

    public ProductoCompradoService(ProductoCompradoRepository productoCompradoRepository, ProductoCompradoMapper productoCompradoMapper) {
        this.productoCompradoRepository = productoCompradoRepository;
        this.productoCompradoMapper = productoCompradoMapper;
    }

    /**
     * Save a productoComprado.
     *
     * @param productoCompradoDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductoCompradoDTO save(ProductoCompradoDTO productoCompradoDTO) {
        log.debug("Request to save ProductoComprado : {}", productoCompradoDTO);
        ProductoComprado productoComprado = productoCompradoMapper.toEntity(productoCompradoDTO);
        productoComprado = productoCompradoRepository.save(productoComprado);
        return productoCompradoMapper.toDto(productoComprado);
    }

    /**
     * Partially update a productoComprado.
     *
     * @param productoCompradoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProductoCompradoDTO> partialUpdate(ProductoCompradoDTO productoCompradoDTO) {
        log.debug("Request to partially update ProductoComprado : {}", productoCompradoDTO);

        return productoCompradoRepository
            .findById(productoCompradoDTO.getId())
            .map(existingProductoComprado -> {
                productoCompradoMapper.partialUpdate(existingProductoComprado, productoCompradoDTO);

                return existingProductoComprado;
            })
            .map(productoCompradoRepository::save)
            .map(productoCompradoMapper::toDto);
    }

    /**
     * Get all the productoComprados.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProductoCompradoDTO> findAll() {
        log.debug("Request to get all ProductoComprados");
        return productoCompradoRepository
            .findAll()
            .stream()
            .map(productoCompradoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one productoComprado by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProductoCompradoDTO> findOne(Long id) {
        log.debug("Request to get ProductoComprado : {}", id);
        return productoCompradoRepository.findById(id).map(productoCompradoMapper::toDto);
    }

    /**
     * Delete the productoComprado by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProductoComprado : {}", id);
        productoCompradoRepository.deleteById(id);
    }


// ---------------------------------------------------------------------------------------------------------------------


    @Autowired
    CarroCompraRepository carroCompraRepository;

    @Autowired
    ProductoSeleccionadoService productoSeleccionadoService;

    @Autowired
    ProductoSeleccionadoRepository productoSeleccionadoRepository;

    @Autowired
    ProductoSeleccionadoMapper productoSeleccionadoMapper;

    @Autowired
    VentaRepository ventaRepository;

    @Resource
    ConexionService conexionService;

    public void performVenta(VentaDTO ventaDTO) {
        conexionService.getJWT();
        productoSeleccionadoRepository.findByCarro(
            carroCompraRepository.getById(ventaDTO.getCarro().getId()))
            .stream().map(productoSeleccionadoMapper::toDto).collect(Collectors.toCollection(LinkedList::new))
            .forEach( (productoSeleccionadoDTO) -> {
                try {
                    ProductoDTO productoDTO = conexionService.sendHttpRequestForEntity("/open/productos/" + productoSeleccionadoDTO.getProducto().toString(), ProductoDTO.class);
                    if (productoDTO.getHabilitado()) {
                        String cantidadVendidoString = "{\"id\": " + productoDTO.getId() + ", \"cantidadVendidos\": " + (productoDTO.getCantidadVendidos() + productoSeleccionadoDTO.getCantidad()) + "}";
                        conexionService.sendHttpRequestToPatchEntity("/productos/" + productoSeleccionadoDTO.getProducto().toString(), cantidadVendidoString, ProductoDTO.class);
                        ProductoCompradoDTO productoCompradoDTO = CustomMapper.fromSeleccionadoToComprado(productoSeleccionadoDTO);
                        productoCompradoDTO.setVenta(ventaDTO);
                        this.save(productoCompradoDTO);
                    }
                } catch (Exception e) {
                    productoSeleccionadoDTO.setCantidad(0);
                    productoSeleccionadoService.partialUpdate(productoSeleccionadoDTO);
                }
            });
    }

    public List<ProductoCompradoDTO> findAllProductoCompradoByClienteIsCurrentUserAndByVenta(Long id) {
        Optional<Venta> venta = ventaRepository.findByClienteIsCurrentUserAndVenta(id);
        if (!venta.isEmpty()) {
            return productoCompradoRepository.findByVenta(venta.get()).stream().map(productoCompradoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        }
        return new ArrayList<>();
    }

    public List<ProductoCompradoDTO> findAllProductoCompradoByVenta(Long id) {
        Venta venta = ventaRepository.getById(id);
        return productoCompradoRepository.findByVenta(venta).stream().map(productoCompradoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    public void deleteByVenta(Long id) {
        this.findAllProductoCompradoByVenta(id).forEach((productoCompradoDTO -> delete(productoCompradoDTO.getId())));
    }


}
