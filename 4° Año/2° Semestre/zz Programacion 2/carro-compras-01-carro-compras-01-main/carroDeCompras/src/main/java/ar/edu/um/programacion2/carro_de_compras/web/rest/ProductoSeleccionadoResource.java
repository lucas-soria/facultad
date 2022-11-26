package ar.edu.um.programacion2.carro_de_compras.web.rest;

import ar.edu.um.programacion2.carro_de_compras.repository.ProductoSeleccionadoRepository;
import ar.edu.um.programacion2.carro_de_compras.security.AuthoritiesConstants;
import ar.edu.um.programacion2.carro_de_compras.service.ProductoSeleccionadoService;
import ar.edu.um.programacion2.carro_de_compras.service.dto.ProductoSeleccionadoDTO;
import ar.edu.um.programacion2.carro_de_compras.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ar.edu.um.programacion2.carro_de_compras.domain.ProductoSeleccionado}.
 */
@RestController
@RequestMapping("/api")
public class ProductoSeleccionadoResource {

    private final Logger log = LoggerFactory.getLogger(ProductoSeleccionadoResource.class);

    private static final String ENTITY_NAME = "productoSeleccionado";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductoSeleccionadoService productoSeleccionadoService;

    private final ProductoSeleccionadoRepository productoSeleccionadoRepository;

    public ProductoSeleccionadoResource(
        ProductoSeleccionadoService productoSeleccionadoService,
        ProductoSeleccionadoRepository productoSeleccionadoRepository
    ) {
        this.productoSeleccionadoService = productoSeleccionadoService;
        this.productoSeleccionadoRepository = productoSeleccionadoRepository;
    }

    /**
     * {@code POST  /producto-seleccionados} : Create a new productoSeleccionado.
     *
     * @param productoSeleccionadoDTO the productoSeleccionadoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productoSeleccionadoDTO, or with status {@code 400 (Bad Request)} if the productoSeleccionado has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/producto-seleccionados")
    public ResponseEntity<ProductoSeleccionadoDTO> createProductoSeleccionado(@RequestBody ProductoSeleccionadoDTO productoSeleccionadoDTO)
        throws URISyntaxException {
        log.debug("REST request to save ProductoSeleccionado : {}", productoSeleccionadoDTO);
        if (productoSeleccionadoDTO.getId() != null) {
            throw new BadRequestAlertException("A new productoSeleccionado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductoSeleccionadoDTO result;
        try {
            result = productoSeleccionadoService.addProductoSeleccionado(productoSeleccionadoDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity
            .created(new URI("/api/producto-seleccionados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /producto-seleccionados/:id} : Updates an existing productoSeleccionado.
     *
     * @param id the id of the productoSeleccionadoDTO to save.
     * @param productoSeleccionadoDTO the productoSeleccionadoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productoSeleccionadoDTO,
     * or with status {@code 400 (Bad Request)} if the productoSeleccionadoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productoSeleccionadoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/producto-seleccionados/{id}")
    public ResponseEntity<ProductoSeleccionadoDTO> updateProductoSeleccionado(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductoSeleccionadoDTO productoSeleccionadoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProductoSeleccionado : {}, {}", id, productoSeleccionadoDTO);
        if (productoSeleccionadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productoSeleccionadoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productoSeleccionadoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        try {
            productoSeleccionadoService.checkProductoExiste(productoSeleccionadoDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        ProductoSeleccionadoDTO result = productoSeleccionadoService.save(productoSeleccionadoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productoSeleccionadoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /producto-seleccionados/:id} : Partial updates given fields of an existing productoSeleccionado, field will ignore if it is null
     *
     * @param id the id of the productoSeleccionadoDTO to save.
     * @param productoSeleccionadoDTO the productoSeleccionadoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productoSeleccionadoDTO,
     * or with status {@code 400 (Bad Request)} if the productoSeleccionadoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the productoSeleccionadoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the productoSeleccionadoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/producto-seleccionados/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProductoSeleccionadoDTO> partialUpdateProductoSeleccionado(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductoSeleccionadoDTO productoSeleccionadoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProductoSeleccionado partially : {}, {}", id, productoSeleccionadoDTO);
        if (productoSeleccionadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productoSeleccionadoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productoSeleccionadoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        if (productoSeleccionadoDTO.getProducto() != null) {
            try {
                productoSeleccionadoService.checkProductoExiste(productoSeleccionadoDTO);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        Optional<ProductoSeleccionadoDTO> result = productoSeleccionadoService.partialUpdate(productoSeleccionadoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productoSeleccionadoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /producto-seleccionados} : get all the productoSeleccionados.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productoSeleccionados in body.
     */
    @GetMapping("/producto-seleccionados")
    public List<ProductoSeleccionadoDTO> getAllProductoSeleccionados() {
        log.debug("REST request to get all ProductoSeleccionados");
        return productoSeleccionadoService.findAll();
    }

    /**
     * {@code GET  /producto-seleccionados/:id} : get the "id" productoSeleccionado.
     *
     * @param id the id of the productoSeleccionadoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productoSeleccionadoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/producto-seleccionados/{id}")
    public ResponseEntity<ProductoSeleccionadoDTO> getProductoSeleccionado(@PathVariable Long id) {
        log.debug("REST request to get ProductoSeleccionado : {}", id);
        Optional<ProductoSeleccionadoDTO> productoSeleccionadoDTO = productoSeleccionadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productoSeleccionadoDTO);
    }

    /**
     * {@code DELETE  /producto-seleccionados/:id} : delete the "id" productoSeleccionado.
     *
     * @param id the id of the productoSeleccionadoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/producto-seleccionados/{id}")
    public ResponseEntity<Void> deleteProductoSeleccionado(@PathVariable Long id) {
        log.debug("REST request to delete ProductoSeleccionado : {}", id);
        productoSeleccionadoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }


// ---------------------------------------------------------------------------------------------------------------------

    /**
     * {@code GET  /producto-seleccionados/current} : get all the productoSeleccionados of current cliente and current carro.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productoSeleccionados in body.
     */
    @GetMapping("/producto-seleccionados/current")
    public List<ProductoSeleccionadoDTO> getAllProductoSeleccionadosByClienteIsCurrentUserAndCarroIsNotFinalizado() {
        log.debug("REST request to get all ProductoSeleccionados");
        return productoSeleccionadoService.findAllProductoSeleccionadosByClienteIsCurrentUserAndCarroIsNotFinalizado();
    }

    /**
     * {@code GET  /producto-seleccionados/current/:id} : get all the productoSeleccionados of current cliente by carro.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productoSeleccionados in body.
     */
    @GetMapping("/producto-seleccionados/current/{id}")
    public List<ProductoSeleccionadoDTO> getAllProductoSeleccionadosByClienteIsCurrentUserAndByCarro(@PathVariable Long id) {
        log.debug("REST request to get all ProductoSeleccionados");
        return productoSeleccionadoService.findAllProductoSeleccionadosByClienteIsCurrentUserAndByCarro(id);
    }

    /**
     * {@code GET  /producto-seleccionados/carro/:id} : get all the productoSeleccionados by carro.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productoSeleccionados in body.
     */
    @GetMapping("/producto-seleccionados/carro/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<ProductoSeleccionadoDTO> getAllProductoSeleccionadosByCarro(@PathVariable Long id) {
        log.debug("REST request to get all ProductoSeleccionados");
        return productoSeleccionadoService.findAllProductoSeleccionadosByCarro(id);
    }


}
