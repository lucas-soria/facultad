package ar.edu.um.programacion2.micro_productos.web.rest;

import ar.edu.um.programacion2.micro_productos.repository.DistribuidorRepository;
import ar.edu.um.programacion2.micro_productos.service.DistribuidorQueryService;
import ar.edu.um.programacion2.micro_productos.service.DistribuidorService;
import ar.edu.um.programacion2.micro_productos.service.criteria.DistribuidorCriteria;
import ar.edu.um.programacion2.micro_productos.service.dto.DistribuidorDTO;
import ar.edu.um.programacion2.micro_productos.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link ar.edu.um.programacion2.micro_productos.domain.Distribuidor}.
 */
@RestController
@RequestMapping("/api")
public class DistribuidorResource {

    private final Logger log = LoggerFactory.getLogger(DistribuidorResource.class);

    private static final String ENTITY_NAME = "microProductosDistribuidor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DistribuidorService distribuidorService;

    private final DistribuidorRepository distribuidorRepository;

    private final DistribuidorQueryService distribuidorQueryService;

    public DistribuidorResource(
        DistribuidorService distribuidorService,
        DistribuidorRepository distribuidorRepository,
        DistribuidorQueryService distribuidorQueryService
    ) {
        this.distribuidorService = distribuidorService;
        this.distribuidorRepository = distribuidorRepository;
        this.distribuidorQueryService = distribuidorQueryService;
    }

    /**
     * {@code POST  /distribuidores} : Create a new distribuidor.
     *
     * @param distribuidorDTO the distribuidorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new distribuidorDTO, or with status {@code 400 (Bad Request)} if the distribuidor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/distribuidores")
    public ResponseEntity<DistribuidorDTO> createDistribuidor(@RequestBody DistribuidorDTO distribuidorDTO) throws URISyntaxException {
        log.debug("REST request to save Distribuidor : {}", distribuidorDTO);
        if (distribuidorDTO.getId() != null) {
            throw new BadRequestAlertException("A new distribuidor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DistribuidorDTO result = distribuidorService.preSave(distribuidorDTO);
        return ResponseEntity
            .created(new URI("/api/distribuidores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /distribuidores/:id} : Updates an existing distribuidor.
     *
     * @param id the id of the distribuidorDTO to save.
     * @param distribuidorDTO the distribuidorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated distribuidorDTO,
     * or with status {@code 400 (Bad Request)} if the distribuidorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the distribuidorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/distribuidores/{id}")
    public ResponseEntity<DistribuidorDTO> updateDistribuidor(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DistribuidorDTO distribuidorDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Distribuidor : {}, {}", id, distribuidorDTO);
        if (distribuidorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, distribuidorDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!distribuidorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DistribuidorDTO result = distribuidorService.save(distribuidorDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, distribuidorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /distribuidores/:id} : Partial updates given fields of an existing distribuidor, field will ignore if it is null
     *
     * @param id the id of the distribuidorDTO to save.
     * @param distribuidorDTO the distribuidorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated distribuidorDTO,
     * or with status {@code 400 (Bad Request)} if the distribuidorDTO is not valid,
     * or with status {@code 404 (Not Found)} if the distribuidorDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the distribuidorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/distribuidores/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DistribuidorDTO> partialUpdateDistribuidor(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DistribuidorDTO distribuidorDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Distribuidor partially : {}, {}", id, distribuidorDTO);
        if (distribuidorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, distribuidorDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!distribuidorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DistribuidorDTO> result = distribuidorService.partialUpdate(distribuidorDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, distribuidorDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /distribuidores} : get all the distribuidors.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of distribuidors in body.
     */
    @GetMapping("/distribuidores")
    public ResponseEntity<List<DistribuidorDTO>> getAllDistribuidors(DistribuidorCriteria criteria) {
        log.debug("REST request to get Distribuidors by criteria: {}", criteria);
        List<DistribuidorDTO> entityList = distribuidorQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /distribuidores/count} : count all the distribuidors.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/distribuidores/count")
    public ResponseEntity<Long> countDistribuidors(DistribuidorCriteria criteria) {
        log.debug("REST request to count Distribuidors by criteria: {}", criteria);
        return ResponseEntity.ok().body(distribuidorQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /distribuidores/:id} : get the "id" distribuidor.
     *
     * @param id the id of the distribuidorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the distribuidorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/distribuidores/{id}")
    public ResponseEntity<DistribuidorDTO> getDistribuidor(@PathVariable Long id) {
        log.debug("REST request to get Distribuidor : {}", id);
        Optional<DistribuidorDTO> distribuidorDTO = distribuidorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(distribuidorDTO);
    }

    /**
     * {@code DELETE  /distribuidores/:id} : delete the "id" distribuidor.
     *
     * @param id the id of the distribuidorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/distribuidores/{id}")
    public ResponseEntity<Void> deleteDistribuidor(@PathVariable Long id) {
        log.debug("REST request to delete Distribuidor : {}", id);
        distribuidorService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
