package ar.edu.um.programacion2.micro_carro_de_compras.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.edu.um.programacion2.micro_carro_de_compras.IntegrationTest;
import ar.edu.um.programacion2.micro_carro_de_compras.domain.CarroCompra;
import ar.edu.um.programacion2.micro_carro_de_compras.domain.ProductoComprado;
import ar.edu.um.programacion2.micro_carro_de_compras.domain.Venta;
import ar.edu.um.programacion2.micro_carro_de_compras.repository.VentaRepository;
import ar.edu.um.programacion2.micro_carro_de_compras.service.criteria.VentaCriteria;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.VentaDTO;
import ar.edu.um.programacion2.micro_carro_de_compras.service.mapper.VentaMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link VentaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VentaResourceIT {

    private static final String DEFAULT_CLIENTE = "AAAAAAAAAA";
    private static final String UPDATED_CLIENTE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA = LocalDate.ofEpochDay(-1L);

    private static final Float DEFAULT_TOTAL = 1F;
    private static final Float UPDATED_TOTAL = 2F;
    private static final Float SMALLER_TOTAL = 1F - 1F;

    private static final String ENTITY_API_URL = "/api/ventas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private VentaMapper ventaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVentaMockMvc;

    private Venta venta;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Venta createEntity(EntityManager em) {
        Venta venta = new Venta().cliente(DEFAULT_CLIENTE).fecha(DEFAULT_FECHA).total(DEFAULT_TOTAL);
        return venta;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Venta createUpdatedEntity(EntityManager em) {
        Venta venta = new Venta().cliente(UPDATED_CLIENTE).fecha(UPDATED_FECHA).total(UPDATED_TOTAL);
        return venta;
    }

    @BeforeEach
    public void initTest() {
        venta = createEntity(em);
    }

    @Test
    @Transactional
    void createVenta() throws Exception {
        int databaseSizeBeforeCreate = ventaRepository.findAll().size();
        // Create the Venta
        VentaDTO ventaDTO = ventaMapper.toDto(venta);
        restVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ventaDTO)))
            .andExpect(status().isCreated());

        // Validate the Venta in the database
        List<Venta> ventaList = ventaRepository.findAll();
        assertThat(ventaList).hasSize(databaseSizeBeforeCreate + 1);
        Venta testVenta = ventaList.get(ventaList.size() - 1);
        assertThat(testVenta.getCliente()).isEqualTo(DEFAULT_CLIENTE);
        assertThat(testVenta.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testVenta.getTotal()).isEqualTo(DEFAULT_TOTAL);
    }

    @Test
    @Transactional
    void createVentaWithExistingId() throws Exception {
        // Create the Venta with an existing ID
        venta.setId(1L);
        VentaDTO ventaDTO = ventaMapper.toDto(venta);

        int databaseSizeBeforeCreate = ventaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVentaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ventaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Venta in the database
        List<Venta> ventaList = ventaRepository.findAll();
        assertThat(ventaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVentas() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList
        restVentaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(venta.getId().intValue())))
            .andExpect(jsonPath("$.[*].cliente").value(hasItem(DEFAULT_CLIENTE)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())));
    }

    @Test
    @Transactional
    void getVenta() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get the venta
        restVentaMockMvc
            .perform(get(ENTITY_API_URL_ID, venta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(venta.getId().intValue()))
            .andExpect(jsonPath("$.cliente").value(DEFAULT_CLIENTE))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.doubleValue()));
    }

    @Test
    @Transactional
    void getVentasByIdFiltering() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        Long id = venta.getId();

        defaultVentaShouldBeFound("id.equals=" + id);
        defaultVentaShouldNotBeFound("id.notEquals=" + id);

        defaultVentaShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultVentaShouldNotBeFound("id.greaterThan=" + id);

        defaultVentaShouldBeFound("id.lessThanOrEqual=" + id);
        defaultVentaShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllVentasByClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where cliente equals to DEFAULT_CLIENTE
        defaultVentaShouldBeFound("cliente.equals=" + DEFAULT_CLIENTE);

        // Get all the ventaList where cliente equals to UPDATED_CLIENTE
        defaultVentaShouldNotBeFound("cliente.equals=" + UPDATED_CLIENTE);
    }

    @Test
    @Transactional
    void getAllVentasByClienteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where cliente not equals to DEFAULT_CLIENTE
        defaultVentaShouldNotBeFound("cliente.notEquals=" + DEFAULT_CLIENTE);

        // Get all the ventaList where cliente not equals to UPDATED_CLIENTE
        defaultVentaShouldBeFound("cliente.notEquals=" + UPDATED_CLIENTE);
    }

    @Test
    @Transactional
    void getAllVentasByClienteIsInShouldWork() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where cliente in DEFAULT_CLIENTE or UPDATED_CLIENTE
        defaultVentaShouldBeFound("cliente.in=" + DEFAULT_CLIENTE + "," + UPDATED_CLIENTE);

        // Get all the ventaList where cliente equals to UPDATED_CLIENTE
        defaultVentaShouldNotBeFound("cliente.in=" + UPDATED_CLIENTE);
    }

    @Test
    @Transactional
    void getAllVentasByClienteIsNullOrNotNull() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where cliente is not null
        defaultVentaShouldBeFound("cliente.specified=true");

        // Get all the ventaList where cliente is null
        defaultVentaShouldNotBeFound("cliente.specified=false");
    }

    @Test
    @Transactional
    void getAllVentasByClienteContainsSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where cliente contains DEFAULT_CLIENTE
        defaultVentaShouldBeFound("cliente.contains=" + DEFAULT_CLIENTE);

        // Get all the ventaList where cliente contains UPDATED_CLIENTE
        defaultVentaShouldNotBeFound("cliente.contains=" + UPDATED_CLIENTE);
    }

    @Test
    @Transactional
    void getAllVentasByClienteNotContainsSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where cliente does not contain DEFAULT_CLIENTE
        defaultVentaShouldNotBeFound("cliente.doesNotContain=" + DEFAULT_CLIENTE);

        // Get all the ventaList where cliente does not contain UPDATED_CLIENTE
        defaultVentaShouldBeFound("cliente.doesNotContain=" + UPDATED_CLIENTE);
    }

    @Test
    @Transactional
    void getAllVentasByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where fecha equals to DEFAULT_FECHA
        defaultVentaShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the ventaList where fecha equals to UPDATED_FECHA
        defaultVentaShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    void getAllVentasByFechaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where fecha not equals to DEFAULT_FECHA
        defaultVentaShouldNotBeFound("fecha.notEquals=" + DEFAULT_FECHA);

        // Get all the ventaList where fecha not equals to UPDATED_FECHA
        defaultVentaShouldBeFound("fecha.notEquals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    void getAllVentasByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultVentaShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the ventaList where fecha equals to UPDATED_FECHA
        defaultVentaShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    void getAllVentasByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where fecha is not null
        defaultVentaShouldBeFound("fecha.specified=true");

        // Get all the ventaList where fecha is null
        defaultVentaShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    void getAllVentasByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where fecha is greater than or equal to DEFAULT_FECHA
        defaultVentaShouldBeFound("fecha.greaterThanOrEqual=" + DEFAULT_FECHA);

        // Get all the ventaList where fecha is greater than or equal to UPDATED_FECHA
        defaultVentaShouldNotBeFound("fecha.greaterThanOrEqual=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    void getAllVentasByFechaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where fecha is less than or equal to DEFAULT_FECHA
        defaultVentaShouldBeFound("fecha.lessThanOrEqual=" + DEFAULT_FECHA);

        // Get all the ventaList where fecha is less than or equal to SMALLER_FECHA
        defaultVentaShouldNotBeFound("fecha.lessThanOrEqual=" + SMALLER_FECHA);
    }

    @Test
    @Transactional
    void getAllVentasByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where fecha is less than DEFAULT_FECHA
        defaultVentaShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the ventaList where fecha is less than UPDATED_FECHA
        defaultVentaShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    void getAllVentasByFechaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where fecha is greater than DEFAULT_FECHA
        defaultVentaShouldNotBeFound("fecha.greaterThan=" + DEFAULT_FECHA);

        // Get all the ventaList where fecha is greater than SMALLER_FECHA
        defaultVentaShouldBeFound("fecha.greaterThan=" + SMALLER_FECHA);
    }

    @Test
    @Transactional
    void getAllVentasByTotalIsEqualToSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where total equals to DEFAULT_TOTAL
        defaultVentaShouldBeFound("total.equals=" + DEFAULT_TOTAL);

        // Get all the ventaList where total equals to UPDATED_TOTAL
        defaultVentaShouldNotBeFound("total.equals=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllVentasByTotalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where total not equals to DEFAULT_TOTAL
        defaultVentaShouldNotBeFound("total.notEquals=" + DEFAULT_TOTAL);

        // Get all the ventaList where total not equals to UPDATED_TOTAL
        defaultVentaShouldBeFound("total.notEquals=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllVentasByTotalIsInShouldWork() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where total in DEFAULT_TOTAL or UPDATED_TOTAL
        defaultVentaShouldBeFound("total.in=" + DEFAULT_TOTAL + "," + UPDATED_TOTAL);

        // Get all the ventaList where total equals to UPDATED_TOTAL
        defaultVentaShouldNotBeFound("total.in=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllVentasByTotalIsNullOrNotNull() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where total is not null
        defaultVentaShouldBeFound("total.specified=true");

        // Get all the ventaList where total is null
        defaultVentaShouldNotBeFound("total.specified=false");
    }

    @Test
    @Transactional
    void getAllVentasByTotalIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where total is greater than or equal to DEFAULT_TOTAL
        defaultVentaShouldBeFound("total.greaterThanOrEqual=" + DEFAULT_TOTAL);

        // Get all the ventaList where total is greater than or equal to UPDATED_TOTAL
        defaultVentaShouldNotBeFound("total.greaterThanOrEqual=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllVentasByTotalIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where total is less than or equal to DEFAULT_TOTAL
        defaultVentaShouldBeFound("total.lessThanOrEqual=" + DEFAULT_TOTAL);

        // Get all the ventaList where total is less than or equal to SMALLER_TOTAL
        defaultVentaShouldNotBeFound("total.lessThanOrEqual=" + SMALLER_TOTAL);
    }

    @Test
    @Transactional
    void getAllVentasByTotalIsLessThanSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where total is less than DEFAULT_TOTAL
        defaultVentaShouldNotBeFound("total.lessThan=" + DEFAULT_TOTAL);

        // Get all the ventaList where total is less than UPDATED_TOTAL
        defaultVentaShouldBeFound("total.lessThan=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllVentasByTotalIsGreaterThanSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        // Get all the ventaList where total is greater than DEFAULT_TOTAL
        defaultVentaShouldNotBeFound("total.greaterThan=" + DEFAULT_TOTAL);

        // Get all the ventaList where total is greater than SMALLER_TOTAL
        defaultVentaShouldBeFound("total.greaterThan=" + SMALLER_TOTAL);
    }

    @Test
    @Transactional
    void getAllVentasByCarroIsEqualToSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);
        CarroCompra carro;
        if (TestUtil.findAll(em, CarroCompra.class).isEmpty()) {
            carro = CarroCompraResourceIT.createEntity(em);
            em.persist(carro);
            em.flush();
        } else {
            carro = TestUtil.findAll(em, CarroCompra.class).get(0);
        }
        em.persist(carro);
        em.flush();
        venta.setCarro(carro);
        ventaRepository.saveAndFlush(venta);
        Long carroId = carro.getId();

        // Get all the ventaList where carro equals to carroId
        defaultVentaShouldBeFound("carroId.equals=" + carroId);

        // Get all the ventaList where carro equals to (carroId + 1)
        defaultVentaShouldNotBeFound("carroId.equals=" + (carroId + 1));
    }

    @Test
    @Transactional
    void getAllVentasByProductoCompradoIsEqualToSomething() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);
        ProductoComprado productoComprado;
        if (TestUtil.findAll(em, ProductoComprado.class).isEmpty()) {
            productoComprado = ProductoCompradoResourceIT.createEntity(em);
            em.persist(productoComprado);
            em.flush();
        } else {
            productoComprado = TestUtil.findAll(em, ProductoComprado.class).get(0);
        }
        em.persist(productoComprado);
        em.flush();
        venta.addProductoComprado(productoComprado);
        ventaRepository.saveAndFlush(venta);
        Long productoCompradoId = productoComprado.getId();

        // Get all the ventaList where productoComprado equals to productoCompradoId
        defaultVentaShouldBeFound("productoCompradoId.equals=" + productoCompradoId);

        // Get all the ventaList where productoComprado equals to (productoCompradoId + 1)
        defaultVentaShouldNotBeFound("productoCompradoId.equals=" + (productoCompradoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultVentaShouldBeFound(String filter) throws Exception {
        restVentaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(venta.getId().intValue())))
            .andExpect(jsonPath("$.[*].cliente").value(hasItem(DEFAULT_CLIENTE)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())));

        // Check, that the count call also returns 1
        restVentaMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultVentaShouldNotBeFound(String filter) throws Exception {
        restVentaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restVentaMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingVenta() throws Exception {
        // Get the venta
        restVentaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewVenta() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        int databaseSizeBeforeUpdate = ventaRepository.findAll().size();

        // Update the venta
        Venta updatedVenta = ventaRepository.findById(venta.getId()).get();
        // Disconnect from session so that the updates on updatedVenta are not directly saved in db
        em.detach(updatedVenta);
        updatedVenta.cliente(UPDATED_CLIENTE).fecha(UPDATED_FECHA).total(UPDATED_TOTAL);
        VentaDTO ventaDTO = ventaMapper.toDto(updatedVenta);

        restVentaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ventaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ventaDTO))
            )
            .andExpect(status().isOk());

        // Validate the Venta in the database
        List<Venta> ventaList = ventaRepository.findAll();
        assertThat(ventaList).hasSize(databaseSizeBeforeUpdate);
        Venta testVenta = ventaList.get(ventaList.size() - 1);
        assertThat(testVenta.getCliente()).isEqualTo(UPDATED_CLIENTE);
        assertThat(testVenta.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testVenta.getTotal()).isEqualTo(UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void putNonExistingVenta() throws Exception {
        int databaseSizeBeforeUpdate = ventaRepository.findAll().size();
        venta.setId(count.incrementAndGet());

        // Create the Venta
        VentaDTO ventaDTO = ventaMapper.toDto(venta);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVentaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ventaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ventaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Venta in the database
        List<Venta> ventaList = ventaRepository.findAll();
        assertThat(ventaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVenta() throws Exception {
        int databaseSizeBeforeUpdate = ventaRepository.findAll().size();
        venta.setId(count.incrementAndGet());

        // Create the Venta
        VentaDTO ventaDTO = ventaMapper.toDto(venta);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVentaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ventaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Venta in the database
        List<Venta> ventaList = ventaRepository.findAll();
        assertThat(ventaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVenta() throws Exception {
        int databaseSizeBeforeUpdate = ventaRepository.findAll().size();
        venta.setId(count.incrementAndGet());

        // Create the Venta
        VentaDTO ventaDTO = ventaMapper.toDto(venta);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVentaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ventaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Venta in the database
        List<Venta> ventaList = ventaRepository.findAll();
        assertThat(ventaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVentaWithPatch() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        int databaseSizeBeforeUpdate = ventaRepository.findAll().size();

        // Update the venta using partial update
        Venta partialUpdatedVenta = new Venta();
        partialUpdatedVenta.setId(venta.getId());

        partialUpdatedVenta.fecha(UPDATED_FECHA);

        restVentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVenta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVenta))
            )
            .andExpect(status().isOk());

        // Validate the Venta in the database
        List<Venta> ventaList = ventaRepository.findAll();
        assertThat(ventaList).hasSize(databaseSizeBeforeUpdate);
        Venta testVenta = ventaList.get(ventaList.size() - 1);
        assertThat(testVenta.getCliente()).isEqualTo(DEFAULT_CLIENTE);
        assertThat(testVenta.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testVenta.getTotal()).isEqualTo(DEFAULT_TOTAL);
    }

    @Test
    @Transactional
    void fullUpdateVentaWithPatch() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        int databaseSizeBeforeUpdate = ventaRepository.findAll().size();

        // Update the venta using partial update
        Venta partialUpdatedVenta = new Venta();
        partialUpdatedVenta.setId(venta.getId());

        partialUpdatedVenta.cliente(UPDATED_CLIENTE).fecha(UPDATED_FECHA).total(UPDATED_TOTAL);

        restVentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVenta.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVenta))
            )
            .andExpect(status().isOk());

        // Validate the Venta in the database
        List<Venta> ventaList = ventaRepository.findAll();
        assertThat(ventaList).hasSize(databaseSizeBeforeUpdate);
        Venta testVenta = ventaList.get(ventaList.size() - 1);
        assertThat(testVenta.getCliente()).isEqualTo(UPDATED_CLIENTE);
        assertThat(testVenta.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testVenta.getTotal()).isEqualTo(UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void patchNonExistingVenta() throws Exception {
        int databaseSizeBeforeUpdate = ventaRepository.findAll().size();
        venta.setId(count.incrementAndGet());

        // Create the Venta
        VentaDTO ventaDTO = ventaMapper.toDto(venta);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ventaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ventaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Venta in the database
        List<Venta> ventaList = ventaRepository.findAll();
        assertThat(ventaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVenta() throws Exception {
        int databaseSizeBeforeUpdate = ventaRepository.findAll().size();
        venta.setId(count.incrementAndGet());

        // Create the Venta
        VentaDTO ventaDTO = ventaMapper.toDto(venta);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVentaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ventaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Venta in the database
        List<Venta> ventaList = ventaRepository.findAll();
        assertThat(ventaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVenta() throws Exception {
        int databaseSizeBeforeUpdate = ventaRepository.findAll().size();
        venta.setId(count.incrementAndGet());

        // Create the Venta
        VentaDTO ventaDTO = ventaMapper.toDto(venta);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVentaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ventaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Venta in the database
        List<Venta> ventaList = ventaRepository.findAll();
        assertThat(ventaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVenta() throws Exception {
        // Initialize the database
        ventaRepository.saveAndFlush(venta);

        int databaseSizeBeforeDelete = ventaRepository.findAll().size();

        // Delete the venta
        restVentaMockMvc
            .perform(delete(ENTITY_API_URL_ID, venta.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Venta> ventaList = ventaRepository.findAll();
        assertThat(ventaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
