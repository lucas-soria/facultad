package ar.edu.um.programacion2.micro_carro_de_compras.service;

import ar.edu.um.programacion2.micro_carro_de_compras.domain.CarroCompra;
import ar.edu.um.programacion2.micro_carro_de_compras.domain.ProductoSeleccionado;
import ar.edu.um.programacion2.micro_carro_de_compras.repository.ProductoSeleccionadoRepository;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.CarroCompraDTO;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.ProductoSeleccionadoDTO;
import ar.edu.um.programacion2.micro_carro_de_compras.service.mapper.CarroCompraMapper;
import ar.edu.um.programacion2.micro_carro_de_compras.service.mapper.ProductoSeleccionadoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Optional<ProductoSeleccionadoDTO> productoSeleccionadoDTOOptional = productoSeleccionadoRepository.findById(id).map(productoSeleccionadoMapper::toDto);
        if (!productoSeleccionadoDTOOptional.isEmpty()) {
            ProductoSeleccionadoDTO productoSeleccionadoDTO = productoSeleccionadoDTOOptional.get();
            if (productoSeleccionadoDTO.getCantidad() == 0) {
                productoSeleccionadoRepository.deleteById(id);
            }
            productoSeleccionadoDTO.setCantidad(0);
            this.partialUpdate(productoSeleccionadoDTO);
        }
        productoSeleccionadoRepository.deleteById(id);
    }


// ---------------------------------------------------------------------------------------------------------------------

    @Autowired
    private CarroCompraService carroCompraService;

    @Autowired
    private CarroCompraMapper carroCompraMapper;

    /**
     * Add productoSeleccionado to current carro.
     *
     * @param productoSeleccionadoDTO the producto seleccionado.
     * @return the entity.
     */
    public ProductoSeleccionadoDTO addProductoSeleccionado(ProductoSeleccionadoDTO productoSeleccionadoDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        CarroCompraDTO carro = carroCompraService.findByClienteAndNotFinalizado(username).get();
        ProductoSeleccionado productoSeleccionadoExample = new ProductoSeleccionado();
        productoSeleccionadoExample.setProducto(productoSeleccionadoDTO.getProducto());
        productoSeleccionadoExample.setCarro(carroCompraMapper.toEntity(carro));
        Example<ProductoSeleccionado> productoSeleccionado = Example.of(productoSeleccionadoExample);
        Optional<ProductoSeleccionadoDTO> productoBuscado = productoSeleccionadoRepository.findOne(productoSeleccionado).map(productoSeleccionadoMapper::toDto);
        if (productoBuscado.isEmpty()) {
            productoSeleccionadoDTO.setCarro(carro);
            return this.save(productoSeleccionadoDTO);
        }
        ProductoSeleccionadoDTO productoActualizado = productoBuscado.get();
        productoActualizado.setCantidad(productoActualizado.getCantidad() + productoSeleccionadoDTO.getCantidad());
        return this.partialUpdate(productoActualizado).get();
    }


}
