package ar.edu.um.programacion2.productos.web.rest;

import ar.edu.um.programacion2.productos.repository.ProductoRepository;
import ar.edu.um.programacion2.productos.security.AuthoritiesConstants;
import ar.edu.um.programacion2.productos.service.ProductoService;
import ar.edu.um.programacion2.productos.service.dto.ProductoDTO;
import ar.edu.um.programacion2.productos.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ar.edu.um.programacion2.productos.domain.Producto}.
 */
@RestController
@RequestMapping("/api")
public class ProductoResource {

    private final Logger log = LoggerFactory.getLogger(ProductoResource.class);

    private static final String ENTITY_NAME = "producto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductoService productoService;

    private final ProductoRepository productoRepository;

    public ProductoResource(ProductoService productoService, ProductoRepository productoRepository) {
        this.productoService = productoService;
        this.productoRepository = productoRepository;
    }

    /**
     * {@code POST  /productos} : Create a new producto.
     *
     * @param productoDTO the productoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productoDTO, or with status {@code 400 (Bad Request)} if the producto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/productos")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) throws URISyntaxException {
        log.debug("REST request to save Producto : {}", productoDTO);
        if (productoDTO.getId() != null) {
            throw new BadRequestAlertException("A new producto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (productoDTO.getDistribuidor() != null) {
            if (!productoService.checkDistribuidor(productoDTO.getDistribuidor())) {
                throw new BadRequestAlertException("Entity not found", "productosDistribuidor", "distribuidorNotFound");
            }
        }
        ProductoDTO result = productoService.preSave(productoDTO);
        return ResponseEntity
            .created(new URI("/api/productos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /productos/:id} : Updates an existing producto.
     *
     * @param id the id of the productoDTO to save.
     * @param productoDTO the productoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productoDTO,
     * or with status {@code 400 (Bad Request)} if the productoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/productos/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<ProductoDTO> updateProducto(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductoDTO productoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Producto : {}, {}", id, productoDTO);
        if (productoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        if (productoDTO.getDistribuidor() != null) {
            if (!productoService.checkDistribuidor(productoDTO.getDistribuidor())) {
                throw new BadRequestAlertException("Entity not found", "microProductosDistribuidor", "distribuidorNotFound");
            }
        }
        ProductoDTO result = productoService.preSave(productoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /productos/:id} : Partial updates given fields of an existing producto, field will ignore if it is null
     *
     * @param id the id of the productoDTO to save.
     * @param productoDTO the productoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productoDTO,
     * or with status {@code 400 (Bad Request)} if the productoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the productoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the productoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/productos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<ProductoDTO> partialUpdateProducto(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductoDTO productoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Producto partially : {}, {}", id, productoDTO);
        if (productoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        if (productoDTO.getDistribuidor() != null) {
            if (!productoService.checkDistribuidor(productoDTO.getDistribuidor())) {
                throw new BadRequestAlertException("Entity not found", "microProductosDistribuidor", "distribuidorNotFound");
            }
        }
        Optional<ProductoDTO> result = productoService.partialUpdate(productoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /open/productos} : get all the productos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productos in body.
     */
    @GetMapping("/open/productos")
    public List<ProductoDTO> getAllProductos() {
        log.debug("REST request to get all Productos");
        return productoService.findAll();
    }

    /**
     * {@code GET  /open/productos/:id} : get the "id" producto.
     *
     * @param id the id of the productoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/open/productos/{id}")
    public ResponseEntity<ProductoDTO> getProducto(@PathVariable Long id) {
        log.debug("REST request to get Producto : {}", id);
        Optional<ProductoDTO> productoDTO = productoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productoDTO);
    }

    /**
     * {@code DELETE  /productos/:id} : delete the "id" producto.
     *
     * @param id the id of the productoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/productos/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        log.debug("REST request to delete Producto : {}", id);
        productoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }


// ---------------------------------------------------------------------------------------------------------------------

    /**
     * {@code GET  /open/productos/habilitados} : get all the productos habilitado = true.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productos in body.
     */
    @GetMapping("/open/productos/habilitados")
    public List<ProductoDTO> getAllProductosHabilitados() {
        log.debug("REST request to get all Productos habilitados");
        return productoService.findAllByHabilitado(true);
    }

    /**
     * {@code GET  /open/productos/deshabilitados} : get all the productos habilitado = false.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productos in body.
     */
    @GetMapping("/open/productos/deshabilitados")
    public List<ProductoDTO> getAllProductosDesabilitados() {
        log.debug("REST request to get all Productos deshabilitados");
        return productoService.findAllByHabilitado(false);
    }

    /**
     *
     * {@code GET /open/productos/distribuidor/:id} : get all the productos of distribuidor
     *
     * @param id the id of a distribuidor
     * @return a list of productos
     */
    @GetMapping("/open/productos/distribuidor/{id}")
    public List<ProductoDTO> getAllProductoByDistribuidor(@PathVariable Long id) {
        log.debug("REST request to get all Productos of Distribuidor : {}", id);
        return productoService.findAllByDistribuidor(id);
    }

}
