package ar.edu.um.programacion2.carro_de_compras.web.rest;

import ar.edu.um.programacion2.carro_de_compras.repository.ProductoCompradoRepository;
import ar.edu.um.programacion2.carro_de_compras.security.AuthoritiesConstants;
import ar.edu.um.programacion2.carro_de_compras.service.ProductoCompradoService;
import ar.edu.um.programacion2.carro_de_compras.service.dto.ProductoCompradoDTO;
import ar.edu.um.programacion2.carro_de_compras.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link ar.edu.um.programacion2.carro_de_compras.domain.ProductoComprado}.
 */
@RestController
@RequestMapping("/api")
public class ProductoCompradoResource {

    private final Logger log = LoggerFactory.getLogger(ProductoCompradoResource.class);

    private static final String ENTITY_NAME = "productoComprado";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductoCompradoService productoCompradoService;

    private final ProductoCompradoRepository productoCompradoRepository;

    public ProductoCompradoResource(
        ProductoCompradoService productoCompradoService,
        ProductoCompradoRepository productoCompradoRepository
    ) {
        this.productoCompradoService = productoCompradoService;
        this.productoCompradoRepository = productoCompradoRepository;
    }

    /**
     * {@code POST  /producto-comprados} : Create a new productoComprado.
     *
     * @param productoCompradoDTO the productoCompradoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productoCompradoDTO, or with status {@code 400 (Bad Request)} if the productoComprado has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/producto-comprados")
    public ResponseEntity<ProductoCompradoDTO> createProductoComprado(@RequestBody ProductoCompradoDTO productoCompradoDTO)
        throws URISyntaxException {
        log.debug("REST request to save ProductoComprado : {}", productoCompradoDTO);
        if (productoCompradoDTO.getId() != null) {
            throw new BadRequestAlertException("A new productoComprado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductoCompradoDTO result = productoCompradoService.save(productoCompradoDTO);
        return ResponseEntity
            .created(new URI("/api/producto-comprados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /producto-comprados/:id} : Updates an existing productoComprado.
     *
     * @param id the id of the productoCompradoDTO to save.
     * @param productoCompradoDTO the productoCompradoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productoCompradoDTO,
     * or with status {@code 400 (Bad Request)} if the productoCompradoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productoCompradoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/producto-comprados/{id}")
    public ResponseEntity<ProductoCompradoDTO> updateProductoComprado(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductoCompradoDTO productoCompradoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProductoComprado : {}, {}", id, productoCompradoDTO);
        if (productoCompradoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productoCompradoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productoCompradoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProductoCompradoDTO result = productoCompradoService.save(productoCompradoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productoCompradoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /producto-comprados/:id} : Partial updates given fields of an existing productoComprado, field will ignore if it is null
     *
     * @param id the id of the productoCompradoDTO to save.
     * @param productoCompradoDTO the productoCompradoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productoCompradoDTO,
     * or with status {@code 400 (Bad Request)} if the productoCompradoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the productoCompradoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the productoCompradoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/producto-comprados/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProductoCompradoDTO> partialUpdateProductoComprado(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductoCompradoDTO productoCompradoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProductoComprado partially : {}, {}", id, productoCompradoDTO);
        if (productoCompradoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productoCompradoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productoCompradoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProductoCompradoDTO> result = productoCompradoService.partialUpdate(productoCompradoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productoCompradoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /producto-comprados} : get all the productoComprados.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productoComprados in body.
     */
    @GetMapping("/producto-comprados")
    public List<ProductoCompradoDTO> getAllProductoComprados() {
        log.debug("REST request to get all ProductoComprados");
        return productoCompradoService.findAll();
    }

    /**
     * {@code GET  /producto-comprados/:id} : get the "id" productoComprado.
     *
     * @param id the id of the productoCompradoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productoCompradoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/producto-comprados/{id}")
    public ResponseEntity<ProductoCompradoDTO> getProductoComprado(@PathVariable Long id) {
        log.debug("REST request to get ProductoComprado : {}", id);
        Optional<ProductoCompradoDTO> productoCompradoDTO = productoCompradoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productoCompradoDTO);
    }

    /**
     * {@code DELETE  /producto-comprados/:id} : delete the "id" productoComprado.
     *
     * @param id the id of the productoCompradoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/producto-comprados/{id}")
    public ResponseEntity<Void> deleteProductoComprado(@PathVariable Long id) {
        log.debug("REST request to delete ProductoComprado : {}", id);
        productoCompradoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }


// ---------------------------------------------------------------------------------------------------------------------


    /**
     * {@code GET  /producto-comprados/current/:id} : get all the productoComprado of current cliente by venta.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productoComprados in body.
     */
    @GetMapping("/producto-comprados/current/{id}")
    public List<ProductoCompradoDTO> getAllProductoCompradoByClienteIsCurrentUserAndByCarro(@PathVariable Long id) {
        log.debug("REST request to get all ProductoComprado of current cliente by venta : {}", id);
        return productoCompradoService.findAllProductoCompradoByClienteIsCurrentUserAndByVenta(id);
    }

    /**
     * {@code GET  /producto-comprados/venta/:id} : get all the productoComprado by venta.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productoSeleccionados in body.
     */
    @GetMapping("/producto-comprados/venta/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<ProductoCompradoDTO> getAllProductoCompradoByCarro(@PathVariable Long id) {
        log.debug("REST request to get all ProductoSeleccionados");
        return productoCompradoService.findAllProductoCompradoByVenta(id);
    }


}
