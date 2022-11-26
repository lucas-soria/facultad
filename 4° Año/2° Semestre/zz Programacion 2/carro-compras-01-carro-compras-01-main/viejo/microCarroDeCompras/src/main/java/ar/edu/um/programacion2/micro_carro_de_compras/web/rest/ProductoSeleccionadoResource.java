package ar.edu.um.programacion2.micro_carro_de_compras.web.rest;

import ar.edu.um.programacion2.micro_carro_de_compras.repository.ProductoSeleccionadoRepository;
import ar.edu.um.programacion2.micro_carro_de_compras.service.ProductoSeleccionadoQueryService;
import ar.edu.um.programacion2.micro_carro_de_compras.service.ProductoSeleccionadoService;
import ar.edu.um.programacion2.micro_carro_de_compras.service.criteria.ProductoSeleccionadoCriteria;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.ProductoSeleccionadoDTO;
import ar.edu.um.programacion2.micro_carro_de_compras.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ar.edu.um.programacion2.micro_carro_de_compras.domain.ProductoSeleccionado}.
 */
@RestController
@RequestMapping("/api")
public class ProductoSeleccionadoResource {

    private final Logger log = LoggerFactory.getLogger(ProductoSeleccionadoResource.class);

    private static final String ENTITY_NAME = "microCarroDeComprasProductoSeleccionado";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductoSeleccionadoService productoSeleccionadoService;

    private final ProductoSeleccionadoRepository productoSeleccionadoRepository;

    private final ProductoSeleccionadoQueryService productoSeleccionadoQueryService;

    public ProductoSeleccionadoResource(
        ProductoSeleccionadoService productoSeleccionadoService,
        ProductoSeleccionadoRepository productoSeleccionadoRepository,
        ProductoSeleccionadoQueryService productoSeleccionadoQueryService
    ) {
        this.productoSeleccionadoService = productoSeleccionadoService;
        this.productoSeleccionadoRepository = productoSeleccionadoRepository;
        this.productoSeleccionadoQueryService = productoSeleccionadoQueryService;
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
        ProductoSeleccionadoDTO result = productoSeleccionadoService.addProductoSeleccionado(productoSeleccionadoDTO);
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

        Optional<ProductoSeleccionadoDTO> result = productoSeleccionadoService.partialUpdate(productoSeleccionadoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productoSeleccionadoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /producto-seleccionados} : get all the productoSeleccionados.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productoSeleccionados in body.
     */
    @GetMapping("/producto-seleccionados")
    public ResponseEntity<List<ProductoSeleccionadoDTO>> getAllProductoSeleccionados(ProductoSeleccionadoCriteria criteria) {
        log.debug("REST request to get ProductoSeleccionados by criteria: {}", criteria);
        List<ProductoSeleccionadoDTO> entityList = productoSeleccionadoQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /producto-seleccionados/count} : count all the productoSeleccionados.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/producto-seleccionados/count")
    public ResponseEntity<Long> countProductoSeleccionados(ProductoSeleccionadoCriteria criteria) {
        log.debug("REST request to count ProductoSeleccionados by criteria: {}", criteria);
        return ResponseEntity.ok().body(productoSeleccionadoQueryService.countByCriteria(criteria));
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
}
