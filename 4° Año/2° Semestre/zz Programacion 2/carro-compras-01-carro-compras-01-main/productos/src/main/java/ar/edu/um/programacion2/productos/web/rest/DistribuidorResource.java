package ar.edu.um.programacion2.productos.web.rest;

import ar.edu.um.programacion2.productos.repository.DistribuidorRepository;
import ar.edu.um.programacion2.productos.security.AuthoritiesConstants;
import ar.edu.um.programacion2.productos.service.DistribuidorService;
import ar.edu.um.programacion2.productos.service.dto.DistribuidorDTO;
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
 * REST controller for managing {@link ar.edu.um.programacion2.productos.domain.Distribuidor}.
 */
@RestController
@RequestMapping("/api")
public class DistribuidorResource {

    private final Logger log = LoggerFactory.getLogger(DistribuidorResource.class);

    private static final String ENTITY_NAME = "distribuidor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DistribuidorService distribuidorService;

    private final DistribuidorRepository distribuidorRepository;

    public DistribuidorResource(DistribuidorService distribuidorService, DistribuidorRepository distribuidorRepository) {
        this.distribuidorService = distribuidorService;
        this.distribuidorRepository = distribuidorRepository;
    }

    /**
     * {@code POST  /distribuidores} : Create a new distribuidor.
     *
     * @param distribuidorDTO the distribuidorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new distribuidorDTO, or with status {@code 400 (Bad Request)} if the distribuidor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/distribuidores")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
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
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
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

        DistribuidorDTO result = distribuidorService.preSave(distribuidorDTO);
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
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
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
     * {@code GET  /open/distribuidores} : get all the distribuidors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of distribuidors in body.
     */
    @GetMapping("/open/distribuidores")
    public List<DistribuidorDTO> getAllDistribuidors() {
        log.debug("REST request to get all Distribuidors");
        return distribuidorService.findAll();
    }

    /**
     * {@code GET  /open/distribuidores/:id} : get the "id" distribuidor.
     *
     * @param id the id of the distribuidorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the distribuidorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/open/distribuidores/{id}")
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
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteDistribuidor(@PathVariable Long id) {
        log.debug("REST request to delete Distribuidor : {}", id);
        distribuidorService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
