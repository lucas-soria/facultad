package ar.edu.um.programacion2.micro_carro_de_compras.web.rest;

import ar.edu.um.programacion2.micro_carro_de_compras.repository.CarroCompraRepository;
import ar.edu.um.programacion2.micro_carro_de_compras.service.CarroCompraQueryService;
import ar.edu.um.programacion2.micro_carro_de_compras.service.CarroCompraService;
import ar.edu.um.programacion2.micro_carro_de_compras.service.criteria.CarroCompraCriteria;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.CarroCompraDTO;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ar.edu.um.programacion2.micro_carro_de_compras.domain.CarroCompra}.
 */
@RestController
@RequestMapping("/api")
public class CarroCompraResource {

    private final Logger log = LoggerFactory.getLogger(CarroCompraResource.class);

    private static final String ENTITY_NAME = "microCarroDeComprasCarroCompra";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarroCompraService carroCompraService;

    private final CarroCompraRepository carroCompraRepository;

    private final CarroCompraQueryService carroCompraQueryService;

    public CarroCompraResource(
        CarroCompraService carroCompraService,
        CarroCompraRepository carroCompraRepository,
        CarroCompraQueryService carroCompraQueryService
    ) {
        this.carroCompraService = carroCompraService;
        this.carroCompraRepository = carroCompraRepository;
        this.carroCompraQueryService = carroCompraQueryService;
    }

    /**
     * {@code POST  /carro-compras} : Create a new carroCompra.
     *
     * @param carroCompraDTO the carroCompraDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carroCompraDTO, or with status {@code 400 (Bad Request)} if the carroCompra has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/carro-compras")
    public ResponseEntity<CarroCompraDTO> createCarroCompra(@RequestBody CarroCompraDTO carroCompraDTO) throws URISyntaxException {
        log.debug("REST request to save CarroCompra : {}", carroCompraDTO);
        if (carroCompraDTO.getId() != null) {
            throw new BadRequestAlertException("A new carroCompra cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarroCompraDTO result = carroCompraService.preSave(carroCompraDTO);
        return ResponseEntity
            .created(new URI("/api/carro-compras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /carro-compras/:id} : Updates an existing carroCompra.
     *
     * @param id the id of the carroCompraDTO to save.
     * @param carroCompraDTO the carroCompraDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carroCompraDTO,
     * or with status {@code 400 (Bad Request)} if the carroCompraDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carroCompraDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/carro-compras/{id}")
    public ResponseEntity<CarroCompraDTO> updateCarroCompra(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CarroCompraDTO carroCompraDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CarroCompra : {}, {}", id, carroCompraDTO);
        if (carroCompraDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carroCompraDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carroCompraRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CarroCompraDTO result = carroCompraService.save(carroCompraDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carroCompraDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /carro-compras/:id} : Partial updates given fields of an existing carroCompra, field will ignore if it is null
     *
     * @param id the id of the carroCompraDTO to save.
     * @param carroCompraDTO the carroCompraDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carroCompraDTO,
     * or with status {@code 400 (Bad Request)} if the carroCompraDTO is not valid,
     * or with status {@code 404 (Not Found)} if the carroCompraDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the carroCompraDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/carro-compras/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CarroCompraDTO> partialUpdateCarroCompra(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CarroCompraDTO carroCompraDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CarroCompra partially : {}, {}", id, carroCompraDTO);
        if (carroCompraDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carroCompraDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carroCompraRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CarroCompraDTO> result = carroCompraService.partialUpdate(carroCompraDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carroCompraDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /carro-compras} : get all the carroCompras.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carroCompras in body.
     */
    @GetMapping("/carro-compras")
    public ResponseEntity<List<CarroCompraDTO>> getAllCarroCompras(CarroCompraCriteria criteria) {
        log.debug("REST request to get CarroCompras by criteria: {}", criteria);
        List<CarroCompraDTO> entityList = carroCompraQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /carro-compras/count} : count all the carroCompras.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/carro-compras/count")
    public ResponseEntity<Long> countCarroCompras(CarroCompraCriteria criteria) {
        log.debug("REST request to count CarroCompras by criteria: {}", criteria);
        return ResponseEntity.ok().body(carroCompraQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /carro-compras/:id} : get the "id" carroCompra.
     *
     * @param id the id of the carroCompraDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carroCompraDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/carro-compras/{id}")
    public ResponseEntity<CarroCompraDTO> getCarroCompra(@PathVariable Long id) {
        log.debug("REST request to get CarroCompra : {}", id);
        Optional<CarroCompraDTO> carroCompraDTO = carroCompraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carroCompraDTO);
    }

    /**
     * {@code DELETE  /carro-compras/:id} : delete the "id" carroCompra.
     *
     * @param id the id of the carroCompraDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/carro-compras/{id}")
    public ResponseEntity<Void> deleteCarroCompra(@PathVariable Long id) {
        log.debug("REST request to delete CarroCompra : {}", id);
        carroCompraService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }


// ---------------------------------------------------------------------------------------------------------------------


    @GetMapping("/carro-compras/cliente/")
    public ResponseEntity<CarroCompraDTO> getCarroCompraNotFinalizadoByCliente() {
        log.debug("REST request to get CarroCompra activo by Cliente");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Optional<CarroCompraDTO> carroCompraDTO = carroCompraService.findByClienteAndNotFinalizado(username);
        return ResponseUtil.wrapOrNotFound(carroCompraDTO);
    }


}
