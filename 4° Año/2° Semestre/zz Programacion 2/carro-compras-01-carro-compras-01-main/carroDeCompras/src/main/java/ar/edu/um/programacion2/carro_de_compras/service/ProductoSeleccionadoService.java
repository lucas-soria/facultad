package ar.edu.um.programacion2.carro_de_compras.service;

import ar.edu.um.programacion2.carro_de_compras.domain.CarroCompra;
import ar.edu.um.programacion2.carro_de_compras.domain.ProductoSeleccionado;
import ar.edu.um.programacion2.carro_de_compras.repository.CarroCompraRepository;
import ar.edu.um.programacion2.carro_de_compras.repository.ProductoSeleccionadoRepository;
import ar.edu.um.programacion2.carro_de_compras.service.dto.CarroCompraDTO;
import ar.edu.um.programacion2.carro_de_compras.service.dto.ProductoSeleccionadoDTO;
import ar.edu.um.programacion2.carro_de_compras.service.mapper.ProductoSeleccionadoMapper;

import java.util.*;
import java.util.stream.Collectors;

import ar.edu.um.programacion2.carro_de_compras.service.dto.ProductoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;

/**
 * Service Implementation for managing {@link ProductoSeleccionado}.
 */
@Service
@Transactional
public class ProductoSeleccionadoService {

    private final Logger log = LoggerFactory.getLogger(ProductoSeleccionadoService.class);

    private final ProductoSeleccionadoRepository productoSeleccionadoRepository;

    private final ProductoSeleccionadoMapper productoSeleccionadoMapper;

    public ProductoSeleccionadoService(
        ProductoSeleccionadoRepository productoSeleccionadoRepository,
        ProductoSeleccionadoMapper productoSeleccionadoMapper
    ) {
        this.productoSeleccionadoRepository = productoSeleccionadoRepository;
        this.productoSeleccionadoMapper = productoSeleccionadoMapper;
    }

    /**
     * Save a productoSeleccionado.
     *
     * @param productoSeleccionadoDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductoSeleccionadoDTO save(ProductoSeleccionadoDTO productoSeleccionadoDTO) {
        log.debug("Request to save ProductoSeleccionado : {}", productoSeleccionadoDTO);
        ProductoSeleccionado productoSeleccionado = productoSeleccionadoMapper.toEntity(productoSeleccionadoDTO);
        productoSeleccionado = productoSeleccionadoRepository.save(productoSeleccionado);
        return productoSeleccionadoMapper.toDto(productoSeleccionado);
    }

    /**
     * Partially update a productoSeleccionado.
     *
     * @param productoSeleccionadoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProductoSeleccionadoDTO> partialUpdate(ProductoSeleccionadoDTO productoSeleccionadoDTO) {
        log.debug("Request to partially update ProductoSeleccionado : {}", productoSeleccionadoDTO);

        return productoSeleccionadoRepository
            .findById(productoSeleccionadoDTO.getId())
            .map(existingProductoSeleccionado -> {
                productoSeleccionadoMapper.partialUpdate(existingProductoSeleccionado, productoSeleccionadoDTO);

                return existingProductoSeleccionado;
            })
            .map(productoSeleccionadoRepository::save)
            .map(productoSeleccionadoMapper::toDto);
    }

    /**
     * Get all the productoSeleccionados.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProductoSeleccionadoDTO> findAll() {
        log.debug("Request to get all ProductoSeleccionados");
        return productoSeleccionadoRepository
            .findAll()
            .stream()
            .map(productoSeleccionadoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one productoSeleccionado by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProductoSeleccionadoDTO> findOne(Long id) {
        log.debug("Request to get ProductoSeleccionado : {}", id);
        return productoSeleccionadoRepository.findById(id).map(productoSeleccionadoMapper::toDto);
    }

    /**
     * Delete the productoSeleccionado by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProductoSeleccionado : {}", id);
        ProductoSeleccionadoDTO productoSeleccionadoDTO = productoSeleccionadoRepository.findById(id).map(productoSeleccionadoMapper::toDto).get();
        if (productoSeleccionadoDTO.getCantidad() != 0) {
            productoSeleccionadoDTO.setCantidad(0);
            this.partialUpdate(productoSeleccionadoDTO);
        }
    }


// ---------------------------------------------------------------------------------------------------------------------

    @Autowired
    CarroCompraService carroCompraService;

    @Autowired
    CarroCompraRepository carroCompraRepository;

    @Resource
    ConexionService conexionService;

    /**
     * Add productoSeleccionado to current carro.
     *
     * @param productoSeleccionadoDTO the producto seleccionado.
     * @return the entity.
     */
    public ProductoSeleccionadoDTO addProductoSeleccionado(ProductoSeleccionadoDTO productoSeleccionadoDTO) {
        if (productoSeleccionadoDTO.getCantidad() == null) {
            productoSeleccionadoDTO.setCantidad(1);
        }
        Long idProducto = productoSeleccionadoDTO.getProducto();
        ProductoDTO productoDTO;
        try {
            productoDTO = conexionService.sendHttpRequestForEntity("/open/productos/" + idProducto.toString(), ProductoDTO.class);
        } catch (ResponseStatusException responseStatusException) {
            throw responseStatusException;
        }
        CarroCompraDTO carroActual = carroCompraService.findByClienteIsCurrentUserAndNotFinalizado();
        CarroCompra carroActualEntity = carroCompraRepository.findById(carroActual.getId()).get();
        Optional<ProductoSeleccionadoDTO> productoSeleccionadoDTOExistente = productoSeleccionadoRepository.findByProductoAndCarro(idProducto, carroActualEntity).map(productoSeleccionadoMapper::toDto);
        if (productoSeleccionadoDTOExistente.isEmpty()) {
            productoSeleccionadoDTO.setPrecio(productoDTO.getPrecio());
            productoSeleccionadoDTO.setCarro(carroActual);
            return this.save(productoSeleccionadoDTO);
        }
        ProductoSeleccionadoDTO productoActualizado = productoSeleccionadoDTOExistente.get();
        productoActualizado.setCantidad(productoActualizado.getCantidad() + productoSeleccionadoDTO.getCantidad());
        productoActualizado.setPrecio(productoDTO.getPrecio());
        return this.partialUpdate(productoActualizado).get();
    }

    /**
     * Get all productoSeleccionado from the current cliente
     *
     * @return list of entities
     */
    public List<ProductoSeleccionadoDTO> findAllProductoSeleccionadosByClienteIsCurrentUserAndCarroIsNotFinalizado() {
        return updatePrecio(productoSeleccionadoRepository
            .findByCarro(carroCompraRepository.findById(carroCompraService.findByClienteIsCurrentUserAndNotFinalizado().getId()).get())
            .stream().map(productoSeleccionadoMapper::toDto).collect(Collectors.toCollection(LinkedList::new))
        );
    }

    /**
     * Updates the Total of all the carros
     * @param productoSeleccionadoDTOList list of productos seleccionados
     * @return a list of all productoSeleccionado with precio updated
     */
    public List<ProductoSeleccionadoDTO> updatePrecio(List<ProductoSeleccionadoDTO> productoSeleccionadoDTOList) {
        productoSeleccionadoDTOList.forEach((productoSeleccionadoDTO) -> {
            try {
                ProductoDTO productoDTO = conexionService.sendHttpRequestForEntity("/open/productos/" + productoSeleccionadoDTO.getProducto(), ProductoDTO.class);
                productoSeleccionadoDTO.setPrecio(productoDTO.getPrecio());
            } catch (Exception e) {
                productoSeleccionadoDTO.setCantidad(0);
            } finally {
                this.partialUpdate(productoSeleccionadoDTO);
            }
        });
        return productoSeleccionadoDTOList;
    }

    public List<ProductoSeleccionadoDTO> findAllProductoSeleccionadosByClienteIsCurrentUserAndByCarro(Long id) {
        Optional<CarroCompra> carroCompra = carroCompraRepository.findByClienteIsCurrentUserAndCarro(id);
        if (!carroCompra.isEmpty()) {
            return productoSeleccionadoRepository.findByCarro(carroCompra.get()).stream().map(productoSeleccionadoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        }
        return new ArrayList<>();
    }

    public List<ProductoSeleccionadoDTO> findAllProductoSeleccionadosByCarro(Long id) {
        CarroCompra carroCompra = carroCompraRepository.getById(id);
        return productoSeleccionadoRepository.findByCarro(carroCompra).stream().map(productoSeleccionadoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    public void checkProductoExiste(ProductoSeleccionadoDTO productoSeleccionadoDTO) {
        conexionService.sendHttpRequestForEntity("/open/productos/" + productoSeleccionadoDTO.getProducto(), ProductoDTO.class);
    }


}
