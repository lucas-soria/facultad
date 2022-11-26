package ar.edu.um.programacion2.micro_carro_de_compras.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.edu.um.programacion2.micro_carro_de_compras.IntegrationTest;
import ar.edu.um.programacion2.micro_carro_de_compras.domain.CarroCompra;
import ar.edu.um.programacion2.micro_carro_de_compras.domain.ProductoSeleccionado;
import ar.edu.um.programacion2.micro_carro_de_compras.repository.CarroCompraRepository;
import ar.edu.um.programacion2.micro_carro_de_compras.service.criteria.CarroCompraCriteria;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.CarroCompraDTO;
import ar.edu.um.programacion2.micro_carro_de_compras.service.mapper.CarroCompraMapper;
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
 * Integration tests for the {@link CarroCompraResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CarroCompraResourceIT {

    private static final String DEFAULT_CLIENTE = "AAAAAAAAAA";
    private static final String UPDATED_CLIENTE = "BBBBBBBBBB";

    private static final Float DEFAULT_TOTAL = 1F;
    private static final Float UPDATED_TOTAL = 2F;
    private static final Float SMALLER_TOTAL = 1F - 1F;

    private static final Boolean DEFAULT_FINALIZADO = false;
    private static final Boolean UPDATED_FINALIZADO = true;

    private static final Boolean DEFAULT_VENDIDO = false;
    private static final Boolean UPDATED_VENDIDO = true;

    private static final String ENTITY_API_URL = "/api/carro-compras";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CarroCompraRepository carroCompraRepository;

    @Autowired
    private CarroCompraMapper carroCompraMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCarroCompraMockMvc;

    private CarroCompra carroCompra;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CarroCompra createEntity(EntityManager em) {
        CarroCompra carroCompra = new CarroCompra()
            .cliente(DEFAULT_CLIENTE)
            .total(DEFAULT_TOTAL)
            .finalizado(DEFAULT_FINALIZADO)
            .vendido(DEFAULT_VENDIDO);
        return carroCompra;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CarroCompra createUpdatedEntity(EntityManager em) {
        CarroCompra carroCompra = new CarroCompra()
            .cliente(UPDATED_CLIENTE)
            .total(UPDATED_TOTAL)
            .finalizado(UPDATED_FINALIZADO)
            .vendido(UPDATED_VENDIDO);
        return carroCompra;
    }

    @BeforeEach
    public void initTest() {
        carroCompra = createEntity(em);
    }

    @Test
    @Transactional
    void createCarroCompra() throws Exception {
        int databaseSizeBeforeCreate = carroCompraRepository.findAll().size();
        // Create the CarroCompra
        CarroCompraDTO carroCompraDTO = carroCompraMapper.toDto(carroCompra);
        restCarroCompraMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(carroCompraDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CarroCompra in the database
        List<CarroCompra> carroCompraList = carroCompraRepository.findAll();
        assertThat(carroCompraList).hasSize(databaseSizeBeforeCreate + 1);
        CarroCompra testCarroCompra = carroCompraList.get(carroCompraList.size() - 1);
        assertThat(testCarroCompra.getCliente()).isEqualTo(DEFAULT_CLIENTE);
        assertThat(testCarroCompra.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testCarroCompra.getFinalizado()).isEqualTo(DEFAULT_FINALIZADO);
        assertThat(testCarroCompra.getVendido()).isEqualTo(DEFAULT_VENDIDO);
    }

    @Test
    @Transactional
    void createCarroCompraWithExistingId() throws Exception {
        // Create the CarroCompra with an existing ID
        carroCompra.setId(1L);
        CarroCompraDTO carroCompraDTO = carroCompraMapper.toDto(carroCompra);

        int databaseSizeBeforeCreate = carroCompraRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCarroCompraMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(carroCompraDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CarroCompra in the database
        List<CarroCompra> carroCompraList = carroCompraRepository.findAll();
        assertThat(carroCompraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCarroCompras() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList
        restCarroCompraMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(carroCompra.getId().intValue())))
            .andExpect(jsonPath("$.[*].cliente").value(hasItem(DEFAULT_CLIENTE)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].finalizado").value(hasItem(DEFAULT_FINALIZADO.booleanValue())))
            .andExpect(jsonPath("$.[*].vendido").value(hasItem(DEFAULT_VENDIDO.booleanValue())));
    }

    @Test
    @Transactional
    void getCarroCompra() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get the carroCompra
        restCarroCompraMockMvc
            .perform(get(ENTITY_API_URL_ID, carroCompra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(carroCompra.getId().intValue()))
            .andExpect(jsonPath("$.cliente").value(DEFAULT_CLIENTE))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.finalizado").value(DEFAULT_FINALIZADO.booleanValue()))
            .andExpect(jsonPath("$.vendido").value(DEFAULT_VENDIDO.booleanValue()));
    }

    @Test
    @Transactional
    void getCarroComprasByIdFiltering() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        Long id = carroCompra.getId();

        defaultCarroCompraShouldBeFound("id.equals=" + id);
        defaultCarroCompraShouldNotBeFound("id.notEquals=" + id);

        defaultCarroCompraShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCarroCompraShouldNotBeFound("id.greaterThan=" + id);

        defaultCarroCompraShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCarroCompraShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllCarroComprasByClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where cliente equals to DEFAULT_CLIENTE
        defaultCarroCompraShouldBeFound("cliente.equals=" + DEFAULT_CLIENTE);

        // Get all the carroCompraList where cliente equals to UPDATED_CLIENTE
        defaultCarroCompraShouldNotBeFound("cliente.equals=" + UPDATED_CLIENTE);
    }

    @Test
    @Transactional
    void getAllCarroComprasByClienteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where cliente not equals to DEFAULT_CLIENTE
        defaultCarroCompraShouldNotBeFound("cliente.notEquals=" + DEFAULT_CLIENTE);

        // Get all the carroCompraList where cliente not equals to UPDATED_CLIENTE
        defaultCarroCompraShouldBeFound("cliente.notEquals=" + UPDATED_CLIENTE);
    }

    @Test
    @Transactional
    void getAllCarroComprasByClienteIsInShouldWork() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where cliente in DEFAULT_CLIENTE or UPDATED_CLIENTE
        defaultCarroCompraShouldBeFound("cliente.in=" + DEFAULT_CLIENTE + "," + UPDATED_CLIENTE);

        // Get all the carroCompraList where cliente equals to UPDATED_CLIENTE
        defaultCarroCompraShouldNotBeFound("cliente.in=" + UPDATED_CLIENTE);
    }

    @Test
    @Transactional
    void getAllCarroComprasByClienteIsNullOrNotNull() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where cliente is not null
        defaultCarroCompraShouldBeFound("cliente.specified=true");

        // Get all the carroCompraList where cliente is null
        defaultCarroCompraShouldNotBeFound("cliente.specified=false");
    }

    @Test
    @Transactional
    void getAllCarroComprasByClienteContainsSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where cliente contains DEFAULT_CLIENTE
        defaultCarroCompraShouldBeFound("cliente.contains=" + DEFAULT_CLIENTE);

        // Get all the carroCompraList where cliente contains UPDATED_CLIENTE
        defaultCarroCompraShouldNotBeFound("cliente.contains=" + UPDATED_CLIENTE);
    }

    @Test
    @Transactional
    void getAllCarroComprasByClienteNotContainsSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where cliente does not contain DEFAULT_CLIENTE
        defaultCarroCompraShouldNotBeFound("cliente.doesNotContain=" + DEFAULT_CLIENTE);

        // Get all the carroCompraList where cliente does not contain UPDATED_CLIENTE
        defaultCarroCompraShouldBeFound("cliente.doesNotContain=" + UPDATED_CLIENTE);
    }

    @Test
    @Transactional
    void getAllCarroComprasByTotalIsEqualToSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where total equals to DEFAULT_TOTAL
        defaultCarroCompraShouldBeFound("total.equals=" + DEFAULT_TOTAL);

        // Get all the carroCompraList where total equals to UPDATED_TOTAL
        defaultCarroCompraShouldNotBeFound("total.equals=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllCarroComprasByTotalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where total not equals to DEFAULT_TOTAL
        defaultCarroCompraShouldNotBeFound("total.notEquals=" + DEFAULT_TOTAL);

        // Get all the carroCompraList where total not equals to UPDATED_TOTAL
        defaultCarroCompraShouldBeFound("total.notEquals=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllCarroComprasByTotalIsInShouldWork() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where total in DEFAULT_TOTAL or UPDATED_TOTAL
        defaultCarroCompraShouldBeFound("total.in=" + DEFAULT_TOTAL + "," + UPDATED_TOTAL);

        // Get all the carroCompraList where total equals to UPDATED_TOTAL
        defaultCarroCompraShouldNotBeFound("total.in=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllCarroComprasByTotalIsNullOrNotNull() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where total is not null
        defaultCarroCompraShouldBeFound("total.specified=true");

        // Get all the carroCompraList where total is null
        defaultCarroCompraShouldNotBeFound("total.specified=false");
    }

    @Test
    @Transactional
    void getAllCarroComprasByTotalIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where total is greater than or equal to DEFAULT_TOTAL
        defaultCarroCompraShouldBeFound("total.greaterThanOrEqual=" + DEFAULT_TOTAL);

        // Get all the carroCompraList where total is greater than or equal to UPDATED_TOTAL
        defaultCarroCompraShouldNotBeFound("total.greaterThanOrEqual=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllCarroComprasByTotalIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where total is less than or equal to DEFAULT_TOTAL
        defaultCarroCompraShouldBeFound("total.lessThanOrEqual=" + DEFAULT_TOTAL);

        // Get all the carroCompraList where total is less than or equal to SMALLER_TOTAL
        defaultCarroCompraShouldNotBeFound("total.lessThanOrEqual=" + SMALLER_TOTAL);
    }

    @Test
    @Transactional
    void getAllCarroComprasByTotalIsLessThanSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where total is less than DEFAULT_TOTAL
        defaultCarroCompraShouldNotBeFound("total.lessThan=" + DEFAULT_TOTAL);

        // Get all the carroCompraList where total is less than UPDATED_TOTAL
        defaultCarroCompraShouldBeFound("total.lessThan=" + UPDATED_TOTAL);
    }

    @Test
    @Transactional
    void getAllCarroComprasByTotalIsGreaterThanSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where total is greater than DEFAULT_TOTAL
        defaultCarroCompraShouldNotBeFound("total.greaterThan=" + DEFAULT_TOTAL);

        // Get all the carroCompraList where total is greater than SMALLER_TOTAL
        defaultCarroCompraShouldBeFound("total.greaterThan=" + SMALLER_TOTAL);
    }

    @Test
    @Transactional
    void getAllCarroComprasByFinalizadoIsEqualToSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where finalizado equals to DEFAULT_FINALIZADO
        defaultCarroCompraShouldBeFound("finalizado.equals=" + DEFAULT_FINALIZADO);

        // Get all the carroCompraList where finalizado equals to UPDATED_FINALIZADO
        defaultCarroCompraShouldNotBeFound("finalizado.equals=" + UPDATED_FINALIZADO);
    }

    @Test
    @Transactional
    void getAllCarroComprasByFinalizadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where finalizado not equals to DEFAULT_FINALIZADO
        defaultCarroCompraShouldNotBeFound("finalizado.notEquals=" + DEFAULT_FINALIZADO);

        // Get all the carroCompraList where finalizado not equals to UPDATED_FINALIZADO
        defaultCarroCompraShouldBeFound("finalizado.notEquals=" + UPDATED_FINALIZADO);
    }

    @Test
    @Transactional
    void getAllCarroComprasByFinalizadoIsInShouldWork() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where finalizado in DEFAULT_FINALIZADO or UPDATED_FINALIZADO
        defaultCarroCompraShouldBeFound("finalizado.in=" + DEFAULT_FINALIZADO + "," + UPDATED_FINALIZADO);

        // Get all the carroCompraList where finalizado equals to UPDATED_FINALIZADO
        defaultCarroCompraShouldNotBeFound("finalizado.in=" + UPDATED_FINALIZADO);
    }

    @Test
    @Transactional
    void getAllCarroComprasByFinalizadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where finalizado is not null
        defaultCarroCompraShouldBeFound("finalizado.specified=true");

        // Get all the carroCompraList where finalizado is null
        defaultCarroCompraShouldNotBeFound("finalizado.specified=false");
    }

    @Test
    @Transactional
    void getAllCarroComprasByVendidoIsEqualToSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where vendido equals to DEFAULT_VENDIDO
        defaultCarroCompraShouldBeFound("vendido.equals=" + DEFAULT_VENDIDO);

        // Get all the carroCompraList where vendido equals to UPDATED_VENDIDO
        defaultCarroCompraShouldNotBeFound("vendido.equals=" + UPDATED_VENDIDO);
    }

    @Test
    @Transactional
    void getAllCarroComprasByVendidoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where vendido not equals to DEFAULT_VENDIDO
        defaultCarroCompraShouldNotBeFound("vendido.notEquals=" + DEFAULT_VENDIDO);

        // Get all the carroCompraList where vendido not equals to UPDATED_VENDIDO
        defaultCarroCompraShouldBeFound("vendido.notEquals=" + UPDATED_VENDIDO);
    }

    @Test
    @Transactional
    void getAllCarroComprasByVendidoIsInShouldWork() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where vendido in DEFAULT_VENDIDO or UPDATED_VENDIDO
        defaultCarroCompraShouldBeFound("vendido.in=" + DEFAULT_VENDIDO + "," + UPDATED_VENDIDO);

        // Get all the carroCompraList where vendido equals to UPDATED_VENDIDO
        defaultCarroCompraShouldNotBeFound("vendido.in=" + UPDATED_VENDIDO);
    }

    @Test
    @Transactional
    void getAllCarroComprasByVendidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        // Get all the carroCompraList where vendido is not null
        defaultCarroCompraShouldBeFound("vendido.specified=true");

        // Get all the carroCompraList where vendido is null
        defaultCarroCompraShouldNotBeFound("vendido.specified=false");
    }

    @Test
    @Transactional
    void getAllCarroComprasByProductoSeleccionadoIsEqualToSomething() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);
        ProductoSeleccionado productoSeleccionado;
        if (TestUtil.findAll(em, ProductoSeleccionado.class).isEmpty()) {
            productoSeleccionado = ProductoSeleccionadoResourceIT.createEntity(em);
            em.persist(productoSeleccionado);
            em.flush();
        } else {
            productoSeleccionado = TestUtil.findAll(em, ProductoSeleccionado.class).get(0);
        }
        em.persist(productoSeleccionado);
        em.flush();
        carroCompra.addProductoSeleccionado(productoSeleccionado);
        carroCompraRepository.saveAndFlush(carroCompra);
        Long productoSeleccionadoId = productoSeleccionado.getId();

        // Get all the carroCompraList where productoSeleccionado equals to productoSeleccionadoId
        defaultCarroCompraShouldBeFound("productoSeleccionadoId.equals=" + productoSeleccionadoId);

        // Get all the carroCompraList where productoSeleccionado equals to (productoSeleccionadoId + 1)
        defaultCarroCompraShouldNotBeFound("productoSeleccionadoId.equals=" + (productoSeleccionadoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCarroCompraShouldBeFound(String filter) throws Exception {
        restCarroCompraMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(carroCompra.getId().intValue())))
            .andExpect(jsonPath("$.[*].cliente").value(hasItem(DEFAULT_CLIENTE)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].finalizado").value(hasItem(DEFAULT_FINALIZADO.booleanValue())))
            .andExpect(jsonPath("$.[*].vendido").value(hasItem(DEFAULT_VENDIDO.booleanValue())));

        // Check, that the count call also returns 1
        restCarroCompraMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCarroCompraShouldNotBeFound(String filter) throws Exception {
        restCarroCompraMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCarroCompraMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCarroCompra() throws Exception {
        // Get the carroCompra
        restCarroCompraMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCarroCompra() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        int databaseSizeBeforeUpdate = carroCompraRepository.findAll().size();

        // Update the carroCompra
        CarroCompra updatedCarroCompra = carroCompraRepository.findById(carroCompra.getId()).get();
        // Disconnect from session so that the updates on updatedCarroCompra are not directly saved in db
        em.detach(updatedCarroCompra);
        updatedCarroCompra.cliente(UPDATED_CLIENTE).total(UPDATED_TOTAL).finalizado(UPDATED_FINALIZADO).vendido(UPDATED_VENDIDO);
        CarroCompraDTO carroCompraDTO = carroCompraMapper.toDto(updatedCarroCompra);

        restCarroCompraMockMvc
            .perform(
                put(ENTITY_API_URL_ID, carroCompraDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(carroCompraDTO))
            )
            .andExpect(status().isOk());

        // Validate the CarroCompra in the database
        List<CarroCompra> carroCompraList = carroCompraRepository.findAll();
        assertThat(carroCompraList).hasSize(databaseSizeBeforeUpdate);
        CarroCompra testCarroCompra = carroCompraList.get(carroCompraList.size() - 1);
        assertThat(testCarroCompra.getCliente()).isEqualTo(UPDATED_CLIENTE);
        assertThat(testCarroCompra.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testCarroCompra.getFinalizado()).isEqualTo(UPDATED_FINALIZADO);
        assertThat(testCarroCompra.getVendido()).isEqualTo(UPDATED_VENDIDO);
    }

    @Test
    @Transactional
    void putNonExistingCarroCompra() throws Exception {
        int databaseSizeBeforeUpdate = carroCompraRepository.findAll().size();
        carroCompra.setId(count.incrementAndGet());

        // Create the CarroCompra
        CarroCompraDTO carroCompraDTO = carroCompraMapper.toDto(carroCompra);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCarroCompraMockMvc
            .perform(
                put(ENTITY_API_URL_ID, carroCompraDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(carroCompraDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CarroCompra in the database
        List<CarroCompra> carroCompraList = carroCompraRepository.findAll();
        assertThat(carroCompraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCarroCompra() throws Exception {
        int databaseSizeBeforeUpdate = carroCompraRepository.findAll().size();
        carroCompra.setId(count.incrementAndGet());

        // Create the CarroCompra
        CarroCompraDTO carroCompraDTO = carroCompraMapper.toDto(carroCompra);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCarroCompraMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(carroCompraDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CarroCompra in the database
        List<CarroCompra> carroCompraList = carroCompraRepository.findAll();
        assertThat(carroCompraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCarroCompra() throws Exception {
        int databaseSizeBeforeUpdate = carroCompraRepository.findAll().size();
        carroCompra.setId(count.incrementAndGet());

        // Create the CarroCompra
        CarroCompraDTO carroCompraDTO = carroCompraMapper.toDto(carroCompra);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCarroCompraMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(carroCompraDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CarroCompra in the database
        List<CarroCompra> carroCompraList = carroCompraRepository.findAll();
        assertThat(carroCompraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCarroCompraWithPatch() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        int databaseSizeBeforeUpdate = carroCompraRepository.findAll().size();

        // Update the carroCompra using partial update
        CarroCompra partialUpdatedCarroCompra = new CarroCompra();
        partialUpdatedCarroCompra.setId(carroCompra.getId());

        partialUpdatedCarroCompra.cliente(UPDATED_CLIENTE).finalizado(UPDATED_FINALIZADO).vendido(UPDATED_VENDIDO);

        restCarroCompraMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCarroCompra.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCarroCompra))
            )
            .andExpect(status().isOk());

        // Validate the CarroCompra in the database
        List<CarroCompra> carroCompraList = carroCompraRepository.findAll();
        assertThat(carroCompraList).hasSize(databaseSizeBeforeUpdate);
        CarroCompra testCarroCompra = carroCompraList.get(carroCompraList.size() - 1);
        assertThat(testCarroCompra.getCliente()).isEqualTo(UPDATED_CLIENTE);
        assertThat(testCarroCompra.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testCarroCompra.getFinalizado()).isEqualTo(UPDATED_FINALIZADO);
        assertThat(testCarroCompra.getVendido()).isEqualTo(UPDATED_VENDIDO);
    }

    @Test
    @Transactional
    void fullUpdateCarroCompraWithPatch() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        int databaseSizeBeforeUpdate = carroCompraRepository.findAll().size();

        // Update the carroCompra using partial update
        CarroCompra partialUpdatedCarroCompra = new CarroCompra();
        partialUpdatedCarroCompra.setId(carroCompra.getId());

        partialUpdatedCarroCompra.cliente(UPDATED_CLIENTE).total(UPDATED_TOTAL).finalizado(UPDATED_FINALIZADO).vendido(UPDATED_VENDIDO);

        restCarroCompraMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCarroCompra.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCarroCompra))
            )
            .andExpect(status().isOk());

        // Validate the CarroCompra in the database
        List<CarroCompra> carroCompraList = carroCompraRepository.findAll();
        assertThat(carroCompraList).hasSize(databaseSizeBeforeUpdate);
        CarroCompra testCarroCompra = carroCompraList.get(carroCompraList.size() - 1);
        assertThat(testCarroCompra.getCliente()).isEqualTo(UPDATED_CLIENTE);
        assertThat(testCarroCompra.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testCarroCompra.getFinalizado()).isEqualTo(UPDATED_FINALIZADO);
        assertThat(testCarroCompra.getVendido()).isEqualTo(UPDATED_VENDIDO);
    }

    @Test
    @Transactional
    void patchNonExistingCarroCompra() throws Exception {
        int databaseSizeBeforeUpdate = carroCompraRepository.findAll().size();
        carroCompra.setId(count.incrementAndGet());

        // Create the CarroCompra
        CarroCompraDTO carroCompraDTO = carroCompraMapper.toDto(carroCompra);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCarroCompraMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, carroCompraDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(carroCompraDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CarroCompra in the database
        List<CarroCompra> carroCompraList = carroCompraRepository.findAll();
        assertThat(carroCompraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCarroCompra() throws Exception {
        int databaseSizeBeforeUpdate = carroCompraRepository.findAll().size();
        carroCompra.setId(count.incrementAndGet());

        // Create the CarroCompra
        CarroCompraDTO carroCompraDTO = carroCompraMapper.toDto(carroCompra);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCarroCompraMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(carroCompraDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CarroCompra in the database
        List<CarroCompra> carroCompraList = carroCompraRepository.findAll();
        assertThat(carroCompraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCarroCompra() throws Exception {
        int databaseSizeBeforeUpdate = carroCompraRepository.findAll().size();
        carroCompra.setId(count.incrementAndGet());

        // Create the CarroCompra
        CarroCompraDTO carroCompraDTO = carroCompraMapper.toDto(carroCompra);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCarroCompraMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(carroCompraDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CarroCompra in the database
        List<CarroCompra> carroCompraList = carroCompraRepository.findAll();
        assertThat(carroCompraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCarroCompra() throws Exception {
        // Initialize the database
        carroCompraRepository.saveAndFlush(carroCompra);

        int databaseSizeBeforeDelete = carroCompraRepository.findAll().size();

        // Delete the carroCompra
        restCarroCompraMockMvc
            .perform(delete(ENTITY_API_URL_ID, carroCompra.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CarroCompra> carroCompraList = carroCompraRepository.findAll();
        assertThat(carroCompraList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
