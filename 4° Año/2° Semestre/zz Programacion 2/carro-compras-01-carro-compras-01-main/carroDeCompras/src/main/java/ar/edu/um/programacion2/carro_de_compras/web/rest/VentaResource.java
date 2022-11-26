package ar.edu.um.programacion2.carro_de_compras.web.rest;

import ar.edu.um.programacion2.carro_de_compras.repository.VentaRepository;
import ar.edu.um.programacion2.carro_de_compras.security.AuthoritiesConstants;
import ar.edu.um.programacion2.carro_de_compras.service.CarroCompraService;
import ar.edu.um.programacion2.carro_de_compras.service.VentaService;
import ar.edu.um.programacion2.carro_de_compras.service.dto.CarroCompraDTO;
import ar.edu.um.programacion2.carro_de_compras.service.dto.VentaDTO;
import ar.edu.um.programacion2.carro_de_compras.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ar.edu.um.programacion2.carro_de_compras.domain.Venta}.
 */
@RestController
@RequestMapping("/api")
public class VentaResource {

    private final Logger log = LoggerFactory.getLogger(VentaResource.class);

    private static final String ENTITY_NAME = "venta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VentaService ventaService;

    private final VentaRepository ventaRepository;

    @Autowired
    CarroCompraService carroCompraService;

    public VentaResource(VentaService ventaService, VentaRepository ventaRepository) {
        this.ventaService = ventaService;
        this.ventaRepository = ventaRepository;
    }

    /**
     * {@code POST  /ventas} : Create a new venta.
     *
     * @param ventaDTO the ventaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ventaDTO, or with status {@code 400 (Bad Request)} if the venta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ventas")
    public ResponseEntity<VentaDTO> createVenta(@RequestBody VentaDTO ventaDTO) throws URISyntaxException {
        log.debug("REST request to save Venta : {}", ventaDTO);
        if (ventaDTO.getId() != null) {
            throw new BadRequestAlertException("A new venta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarroCompraDTO carroCompraDTO = carroCompraService.findOne(ventaDTO.getCarro().getId()).get();
        VentaDTO result = ventaService.performVenta(carroCompraDTO);
        return ResponseEntity
            .created(new URI("/api/ventas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ventas/:id} : Updates an existing venta.
     *
     * @param id the id of the ventaDTO to save.
     * @param ventaDTO the ventaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ventaDTO,
     * or with status {@code 400 (Bad Request)} if the ventaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ventaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ventas/{id}")
    public ResponseEntity<VentaDTO> updateVenta(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VentaDTO ventaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Venta : {}, {}", id, ventaDTO);
        if (ventaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ventaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ventaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VentaDTO result = ventaService.save(ventaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ventaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ventas/:id} : Partial updates given fields of an existing venta, field will ignore if it is null
     *
     * @param id the id of the ventaDTO to save.
     * @param ventaDTO the ventaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ventaDTO,
     * or with status {@code 400 (Bad Request)} if the ventaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ventaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ventaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ventas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VentaDTO> partialUpdateVenta(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VentaDTO ventaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Venta partially : {}, {}", id, ventaDTO);
        if (ventaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ventaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ventaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VentaDTO> result = ventaService.partialUpdate(ventaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ventaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /ventas} : get all the ventas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ventas in body.
     */
    @GetMapping("/ventas")
    public List<VentaDTO> getAllVentas() {
        log.debug("REST request to get all Ventas");
        return ventaService.findAll();
    }

    /**
     * {@code GET  /ventas/:id} : get the "id" venta.
     *
     * @param id the id of the ventaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ventaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ventas/{id}")
    public ResponseEntity<VentaDTO> getVenta(@PathVariable Long id) {
        log.debug("REST request to get Venta : {}", id);
        Optional<VentaDTO> ventaDTO = ventaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ventaDTO);
    }

    /**
     * {@code DELETE  /ventas/:id} : delete the "id" venta.
     *
     * @param id the id of the ventaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ventas/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        log.debug("REST request to delete Venta : {}", id);
        ventaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }


// ---------------------------------------------------------------------------------------------------------------------


    /**
     * {@code GET  /ventas/current/all} : GET all ventas of current cliente
     * @return Response entity with a lists of venta
     */
    @GetMapping("/ventas/current/all")
    public List<VentaDTO> getAllVentaByCurrentCliente() {
        log.debug("REST request to get all Venta by current Cliente");
        return ventaService.findAllByClienteIsCurrentUser();
    }

    /**
     * {@code GET  /ventas/cliente/:id} : GET all ventas of a cliente by id
     * @param id the id of the cliente
     * @return Response entity with a lists of venta
     */
    @GetMapping("/ventas/cliente/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<VentaDTO> getAllVentaByCliente(@PathVariable Long id) {
        log.debug("REST request to get all Venta of Cliente : {}", id);
        return ventaService.findAllByCliente(id);
    }

    /**
     * {@code GET  /ventas/month/30} : GET all ventas of las 30 days
     *
     * @return Response entity with a lists of venta
     */
    @GetMapping("/ventas/month/30")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<VentaDTO> getAllVentaOfPastThirtyDays() {
        log.debug("REST request to get all Venta of last 30 days");
        return ventaService.findAllOfPastThirtyDays();
    }

    /**
     * {@code GET  /ventas/month/current} : GET all ventas of this month
     *
     * @return Response entity with a lists of venta
     */
    @GetMapping("/ventas/month/current")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<VentaDTO> getAllVentaOfThisMonth() {
        log.debug("REST request to get all Venta of this month");
        return ventaService.findAllByCurrentMonth();
    }

    /**
     * {@code GET  /ventas/year} : GET all ventas of the past year
     *
     * @return Response entity with a lists of venta
     */
    @GetMapping("/ventas/year")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<VentaDTO> getAllVentaOfPastYear() {
        log.debug("REST request to get all Venta of past year");
        return ventaService.findAllByPastYear();
    }

    /**
     * {@code GET  /ventas/:fechaInicio/:fechaFin} : GET all ventas between this dates
     *
     * @return Response entity with a lists of venta
     */
    @GetMapping("/ventas/{fechaInicio}/{fechaFin}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<VentaDTO> getAllVentaOfThisMonth(@PathVariable LocalDate fechaInicio, @PathVariable LocalDate fechaFin) {
        log.debug("REST request to get all Venta between {} AND {}", fechaInicio, fechaFin);
        return ventaService.findAllBetween(fechaInicio, fechaFin);
    }


}
