package ar.edu.um.programacion2.micro_productos.service;

import ar.edu.um.programacion2.micro_productos.domain.Producto;
import ar.edu.um.programacion2.micro_productos.repository.DistribuidorRepository;
import ar.edu.um.programacion2.micro_productos.repository.ProductoRepository;
import ar.edu.um.programacion2.micro_productos.service.dto.DistribuidorDTO;
import ar.edu.um.programacion2.micro_productos.service.dto.ProductoDTO;
import ar.edu.um.programacion2.micro_productos.service.mapper.DistribuidorMapper;
import ar.edu.um.programacion2.micro_productos.service.mapper.ProductoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ar.edu.um.programacion2.micro_productos.web.rest.DistribuidorResource;
import ar.edu.um.programacion2.micro_productos.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Producto}.
 */
@Service
@Transactional
public class ProductoService {

    private final Logger log = LoggerFactory.getLogger(ProductoService.class);

    private final ProductoRepository productoRepository;

    private final ProductoMapper productoMapper;

    public ProductoService(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    /**
     * Save a producto.
     *
     * @param productoDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductoDTO save(ProductoDTO productoDTO) {
        log.debug("Request to save Producto : {}", productoDTO);
        Producto producto = productoMapper.toEntity(productoDTO);
        producto = productoRepository.save(producto);
        return productoMapper.toDto(producto);
    }

    /**
     * Partially update a producto.
     *
     * @param productoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProductoDTO> partialUpdate(ProductoDTO productoDTO) {
        log.debug("Request to partially update Producto : {}", productoDTO);
        // check distribuidor
        return productoRepository
            .findById(productoDTO.getId())
            .map(existingProducto -> {
                productoMapper.partialUpdate(existingProducto, productoDTO);

                return existingProducto;
            })
            .map(productoRepository::save)
            .map(productoMapper::toDto);
    }

    /**
     * Get all the productos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProductoDTO> findAll() {
        log.debug("Request to get all Productos");
        return productoRepository.findAll().stream().map(productoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one producto by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProductoDTO> findOne(Long id) {
        log.debug("Request to get Producto : {}", id);
        return productoRepository.findById(id).map(productoMapper::toDto);
    }

    /**
     * Delete the producto by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Producto : {}", id);
        Optional<ProductoDTO> productoDTOOptional = productoRepository.findById(id).map(productoMapper::toDto);
        if (!productoDTOOptional.isEmpty()) {
            ProductoDTO productoDTO = productoDTOOptional.get();
            if (productoDTO.getHabilitado() == false) {
                distribuidorRepository.deleteById(id);
            }
            productoDTO.setHabilitado(false);
            this.partialUpdate(productoDTO);
        }
        productoRepository.deleteById(id);
    }


// ---------------------------------------------------------------------------------------------------------------------


    @Autowired
    DistribuidorRepository distribuidorRepository;

    public Boolean checkDistribuidor(DistribuidorDTO distribuidorDTO) {
        return distribuidorRepository.existsById(distribuidorDTO.getId());
    }

    /**
     * set default values before saving a producto.
     *
     * @param productoDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductoDTO preSave(ProductoDTO productoDTO) {
        log.debug("Setting defaults of producto: {}", productoDTO);
        if (productoDTO.getHabilitado() == null) {
            productoDTO.setHabilitado(true);
        }
        if (productoDTO.getCantidadVendidos() == null) {
            productoDTO.setCantidadVendidos(0);
        }
        return this.save(productoDTO);
    }


}
