package ar.edu.um.ingenieria.programacion2.web.rest;

import ar.edu.um.ingenieria.programacion2.repository.PaisRepository;
import ar.edu.um.ingenieria.programacion2.service.PaisQueryService;
import ar.edu.um.ingenieria.programacion2.service.PaisService;
import ar.edu.um.ingenieria.programacion2.service.criteria.PaisCriteria;
import ar.edu.um.ingenieria.programacion2.service.dto.PaisDTO;
import ar.edu.um.ingenieria.programacion2.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ar.edu.um.ingenieria.programacion2.domain.Pais}.
 */
@RestController
@RequestMapping("/api")
public class PaisResource {

    private final Logger log = LoggerFactory.getLogger(PaisResource.class);

    private static final String ENTITY_NAME = "pais";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaisService paisService;

    private final PaisRepository paisRepository;

    private final PaisQueryService paisQueryService;

    public PaisResource(PaisService paisService, PaisRepository paisRepository, PaisQueryService paisQueryService) {
        this.paisService = paisService;
        this.paisRepository = paisRepository;
        this.paisQueryService = paisQueryService;
    }

    /**
     * {@code POST  /pais} : Create a new pais.
     *
     * @param paisDTO the paisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paisDTO, or with status {@code 400 (Bad Request)} if the pais has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pais")
    public ResponseEntity<PaisDTO> createPais(@Valid @RequestBody PaisDTO paisDTO) throws URISyntaxException {
        log.debug("REST request to save Pais : {}", paisDTO);
        if (paisDTO.getId() != null) {
            throw new BadRequestAlertException("A new pais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaisDTO result = paisService.save(paisDTO);
        return ResponseEntity
            .created(new URI("/api/pais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pais/:id} : Updates an existing pais.
     *
     * @param id the id of the paisDTO to save.
     * @param paisDTO the paisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paisDTO,
     * or with status {@code 400 (Bad Request)} if the paisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pais/{id}")
    public ResponseEntity<PaisDTO> updatePais(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PaisDTO paisDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Pais : {}, {}", id, paisDTO);
        if (paisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paisDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PaisDTO result = paisService.save(paisDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pais/:id} : Partial updates given fields of an existing pais, field will ignore if it is null
     *
     * @param id the id of the paisDTO to save.
     * @param paisDTO the paisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paisDTO,
     * or with status {@code 400 (Bad Request)} if the paisDTO is not valid,
     * or with status {@code 404 (Not Found)} if the paisDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the paisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pais/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<PaisDTO> partialUpdatePais(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PaisDTO paisDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Pais partially : {}, {}", id, paisDTO);
        if (paisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paisDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PaisDTO> result = paisService.partialUpdate(paisDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paisDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /pais} : get all the pais.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pais in body.
     */
    @GetMapping("/pais")
    public ResponseEntity<List<PaisDTO>> getAllPais(PaisCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Pais by criteria: {}", criteria);
        Page<PaisDTO> page = paisQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pais/count} : count all the pais.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/pais/count")
    public ResponseEntity<Long> countPais(PaisCriteria criteria) {
        log.debug("REST request to count Pais by criteria: {}", criteria);
        return ResponseEntity.ok().body(paisQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pais/:id} : get the "id" pais.
     *
     * @param id the id of the paisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pais/{id}")
    public ResponseEntity<PaisDTO> getPais(@PathVariable Long id) {
        log.debug("REST request to get Pais : {}", id);
        Optional<PaisDTO> paisDTO = paisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paisDTO);
    }

    /**
     * {@code DELETE  /pais/:id} : delete the "id" pais.
     *
     * @param id the id of the paisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pais/{id}")
    public ResponseEntity<Void> deletePais(@PathVariable Long id) {
        log.debug("REST request to delete Pais : {}", id);
        paisService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
