package ar.edu.um.programacion2.micro_carro_de_compras.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.edu.um.programacion2.micro_carro_de_compras.IntegrationTest;
import ar.edu.um.programacion2.micro_carro_de_compras.domain.ProductoComprado;
import ar.edu.um.programacion2.micro_carro_de_compras.domain.Venta;
import ar.edu.um.programacion2.micro_carro_de_compras.repository.ProductoCompradoRepository;
import ar.edu.um.programacion2.micro_carro_de_compras.service.criteria.ProductoCompradoCriteria;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.ProductoCompradoDTO;
import ar.edu.um.programacion2.micro_carro_de_compras.service.mapper.ProductoCompradoMapper;
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
 * Integration tests for the {@link ProductoCompradoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProductoCompradoResourceIT {

    private static final Long DEFAULT_PRODUCTO = 1L;
    private static final Long UPDATED_PRODUCTO = 2L;
    private static final Long SMALLER_PRODUCTO = 1L - 1L;

    private static final Float DEFAULT_PRECIO = 1F;
    private static final Float UPDATED_PRECIO = 2F;
    private static final Float SMALLER_PRECIO = 1F - 1F;

    private static final Integer DEFAULT_CANTIDAD = 1;
    private static final Integer UPDATED_CANTIDAD = 2;
    private static final Integer SMALLER_CANTIDAD = 1 - 1;

    private static final String ENTITY_API_URL = "/api/producto-comprados";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProductoCompradoRepository productoCompradoRepository;

    @Autowired
    private ProductoCompradoMapper productoCompradoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductoCompradoMockMvc;

    private ProductoComprado productoComprado;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductoComprado createEntity(EntityManager em) {
        ProductoComprado productoComprado = new ProductoComprado()
            .producto(DEFAULT_PRODUCTO)
            .precio(DEFAULT_PRECIO)
            .cantidad(DEFAULT_CANTIDAD);
        return productoComprado;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductoComprado createUpdatedEntity(EntityManager em) {
        ProductoComprado productoComprado = new ProductoComprado()
            .producto(UPDATED_PRODUCTO)
            .precio(UPDATED_PRECIO)
            .cantidad(UPDATED_CANTIDAD);
        return productoComprado;
    }

    @BeforeEach
    public void initTest() {
        productoComprado = createEntity(em);
    }

    @Test
    @Transactional
    void createProductoComprado() throws Exception {
        int databaseSizeBeforeCreate = productoCompradoRepository.findAll().size();
        // Create the ProductoComprado
        ProductoCompradoDTO productoCompradoDTO = productoCompradoMapper.toDto(productoComprado);
        restProductoCompradoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productoCompradoDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProductoComprado in the database
        List<ProductoComprado> productoCompradoList = productoCompradoRepository.findAll();
        assertThat(productoCompradoList).hasSize(databaseSizeBeforeCreate + 1);
        ProductoComprado testProductoComprado = productoCompradoList.get(productoCompradoList.size() - 1);
        assertThat(testProductoComprado.getProducto()).isEqualTo(DEFAULT_PRODUCTO);
        assertThat(testProductoComprado.getPrecio()).isEqualTo(DEFAULT_PRECIO);
        assertThat(testProductoComprado.getCantidad()).isEqualTo(DEFAULT_CANTIDAD);
    }

    @Test
    @Transactional
    void createProductoCompradoWithExistingId() throws Exception {
        // Create the ProductoComprado with an existing ID
        productoComprado.setId(1L);
        ProductoCompradoDTO productoCompradoDTO = productoCompradoMapper.toDto(productoComprado);

        int databaseSizeBeforeCreate = productoCompradoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductoCompradoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productoCompradoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductoComprado in the database
        List<ProductoComprado> productoCompradoList = productoCompradoRepository.findAll();
        assertThat(productoCompradoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProductoComprados() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList
        restProductoCompradoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productoComprado.getId().intValue())))
            .andExpect(jsonPath("$.[*].producto").value(hasItem(DEFAULT_PRODUCTO.intValue())))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.doubleValue())))
            .andExpect(jsonPath("$.[*].cantidad").value(hasItem(DEFAULT_CANTIDAD)));
    }

    @Test
    @Transactional
    void getProductoComprado() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get the productoComprado
        restProductoCompradoMockMvc
            .perform(get(ENTITY_API_URL_ID, productoComprado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productoComprado.getId().intValue()))
            .andExpect(jsonPath("$.producto").value(DEFAULT_PRODUCTO.intValue()))
            .andExpect(jsonPath("$.precio").value(DEFAULT_PRECIO.doubleValue()))
            .andExpect(jsonPath("$.cantidad").value(DEFAULT_CANTIDAD));
    }

    @Test
    @Transactional
    void getProductoCompradosByIdFiltering() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        Long id = productoComprado.getId();

        defaultProductoCompradoShouldBeFound("id.equals=" + id);
        defaultProductoCompradoShouldNotBeFound("id.notEquals=" + id);

        defaultProductoCompradoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultProductoCompradoShouldNotBeFound("id.greaterThan=" + id);

        defaultProductoCompradoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultProductoCompradoShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByProductoIsEqualToSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where producto equals to DEFAULT_PRODUCTO
        defaultProductoCompradoShouldBeFound("producto.equals=" + DEFAULT_PRODUCTO);

        // Get all the productoCompradoList where producto equals to UPDATED_PRODUCTO
        defaultProductoCompradoShouldNotBeFound("producto.equals=" + UPDATED_PRODUCTO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByProductoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where producto not equals to DEFAULT_PRODUCTO
        defaultProductoCompradoShouldNotBeFound("producto.notEquals=" + DEFAULT_PRODUCTO);

        // Get all the productoCompradoList where producto not equals to UPDATED_PRODUCTO
        defaultProductoCompradoShouldBeFound("producto.notEquals=" + UPDATED_PRODUCTO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByProductoIsInShouldWork() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where producto in DEFAULT_PRODUCTO or UPDATED_PRODUCTO
        defaultProductoCompradoShouldBeFound("producto.in=" + DEFAULT_PRODUCTO + "," + UPDATED_PRODUCTO);

        // Get all the productoCompradoList where producto equals to UPDATED_PRODUCTO
        defaultProductoCompradoShouldNotBeFound("producto.in=" + UPDATED_PRODUCTO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByProductoIsNullOrNotNull() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where producto is not null
        defaultProductoCompradoShouldBeFound("producto.specified=true");

        // Get all the productoCompradoList where producto is null
        defaultProductoCompradoShouldNotBeFound("producto.specified=false");
    }

    @Test
    @Transactional
    void getAllProductoCompradosByProductoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where producto is greater than or equal to DEFAULT_PRODUCTO
        defaultProductoCompradoShouldBeFound("producto.greaterThanOrEqual=" + DEFAULT_PRODUCTO);

        // Get all the productoCompradoList where producto is greater than or equal to UPDATED_PRODUCTO
        defaultProductoCompradoShouldNotBeFound("producto.greaterThanOrEqual=" + UPDATED_PRODUCTO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByProductoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where producto is less than or equal to DEFAULT_PRODUCTO
        defaultProductoCompradoShouldBeFound("producto.lessThanOrEqual=" + DEFAULT_PRODUCTO);

        // Get all the productoCompradoList where producto is less than or equal to SMALLER_PRODUCTO
        defaultProductoCompradoShouldNotBeFound("producto.lessThanOrEqual=" + SMALLER_PRODUCTO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByProductoIsLessThanSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where producto is less than DEFAULT_PRODUCTO
        defaultProductoCompradoShouldNotBeFound("producto.lessThan=" + DEFAULT_PRODUCTO);

        // Get all the productoCompradoList where producto is less than UPDATED_PRODUCTO
        defaultProductoCompradoShouldBeFound("producto.lessThan=" + UPDATED_PRODUCTO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByProductoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where producto is greater than DEFAULT_PRODUCTO
        defaultProductoCompradoShouldNotBeFound("producto.greaterThan=" + DEFAULT_PRODUCTO);

        // Get all the productoCompradoList where producto is greater than SMALLER_PRODUCTO
        defaultProductoCompradoShouldBeFound("producto.greaterThan=" + SMALLER_PRODUCTO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByPrecioIsEqualToSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where precio equals to DEFAULT_PRECIO
        defaultProductoCompradoShouldBeFound("precio.equals=" + DEFAULT_PRECIO);

        // Get all the productoCompradoList where precio equals to UPDATED_PRECIO
        defaultProductoCompradoShouldNotBeFound("precio.equals=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByPrecioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where precio not equals to DEFAULT_PRECIO
        defaultProductoCompradoShouldNotBeFound("precio.notEquals=" + DEFAULT_PRECIO);

        // Get all the productoCompradoList where precio not equals to UPDATED_PRECIO
        defaultProductoCompradoShouldBeFound("precio.notEquals=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByPrecioIsInShouldWork() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where precio in DEFAULT_PRECIO or UPDATED_PRECIO
        defaultProductoCompradoShouldBeFound("precio.in=" + DEFAULT_PRECIO + "," + UPDATED_PRECIO);

        // Get all the productoCompradoList where precio equals to UPDATED_PRECIO
        defaultProductoCompradoShouldNotBeFound("precio.in=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByPrecioIsNullOrNotNull() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where precio is not null
        defaultProductoCompradoShouldBeFound("precio.specified=true");

        // Get all the productoCompradoList where precio is null
        defaultProductoCompradoShouldNotBeFound("precio.specified=false");
    }

    @Test
    @Transactional
    void getAllProductoCompradosByPrecioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where precio is greater than or equal to DEFAULT_PRECIO
        defaultProductoCompradoShouldBeFound("precio.greaterThanOrEqual=" + DEFAULT_PRECIO);

        // Get all the productoCompradoList where precio is greater than or equal to UPDATED_PRECIO
        defaultProductoCompradoShouldNotBeFound("precio.greaterThanOrEqual=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByPrecioIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where precio is less than or equal to DEFAULT_PRECIO
        defaultProductoCompradoShouldBeFound("precio.lessThanOrEqual=" + DEFAULT_PRECIO);

        // Get all the productoCompradoList where precio is less than or equal to SMALLER_PRECIO
        defaultProductoCompradoShouldNotBeFound("precio.lessThanOrEqual=" + SMALLER_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByPrecioIsLessThanSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where precio is less than DEFAULT_PRECIO
        defaultProductoCompradoShouldNotBeFound("precio.lessThan=" + DEFAULT_PRECIO);

        // Get all the productoCompradoList where precio is less than UPDATED_PRECIO
        defaultProductoCompradoShouldBeFound("precio.lessThan=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByPrecioIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where precio is greater than DEFAULT_PRECIO
        defaultProductoCompradoShouldNotBeFound("precio.greaterThan=" + DEFAULT_PRECIO);

        // Get all the productoCompradoList where precio is greater than SMALLER_PRECIO
        defaultProductoCompradoShouldBeFound("precio.greaterThan=" + SMALLER_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByCantidadIsEqualToSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where cantidad equals to DEFAULT_CANTIDAD
        defaultProductoCompradoShouldBeFound("cantidad.equals=" + DEFAULT_CANTIDAD);

        // Get all the productoCompradoList where cantidad equals to UPDATED_CANTIDAD
        defaultProductoCompradoShouldNotBeFound("cantidad.equals=" + UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByCantidadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where cantidad not equals to DEFAULT_CANTIDAD
        defaultProductoCompradoShouldNotBeFound("cantidad.notEquals=" + DEFAULT_CANTIDAD);

        // Get all the productoCompradoList where cantidad not equals to UPDATED_CANTIDAD
        defaultProductoCompradoShouldBeFound("cantidad.notEquals=" + UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByCantidadIsInShouldWork() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where cantidad in DEFAULT_CANTIDAD or UPDATED_CANTIDAD
        defaultProductoCompradoShouldBeFound("cantidad.in=" + DEFAULT_CANTIDAD + "," + UPDATED_CANTIDAD);

        // Get all the productoCompradoList where cantidad equals to UPDATED_CANTIDAD
        defaultProductoCompradoShouldNotBeFound("cantidad.in=" + UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByCantidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where cantidad is not null
        defaultProductoCompradoShouldBeFound("cantidad.specified=true");

        // Get all the productoCompradoList where cantidad is null
        defaultProductoCompradoShouldNotBeFound("cantidad.specified=false");
    }

    @Test
    @Transactional
    void getAllProductoCompradosByCantidadIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where cantidad is greater than or equal to DEFAULT_CANTIDAD
        defaultProductoCompradoShouldBeFound("cantidad.greaterThanOrEqual=" + DEFAULT_CANTIDAD);

        // Get all the productoCompradoList where cantidad is greater than or equal to UPDATED_CANTIDAD
        defaultProductoCompradoShouldNotBeFound("cantidad.greaterThanOrEqual=" + UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByCantidadIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where cantidad is less than or equal to DEFAULT_CANTIDAD
        defaultProductoCompradoShouldBeFound("cantidad.lessThanOrEqual=" + DEFAULT_CANTIDAD);

        // Get all the productoCompradoList where cantidad is less than or equal to SMALLER_CANTIDAD
        defaultProductoCompradoShouldNotBeFound("cantidad.lessThanOrEqual=" + SMALLER_CANTIDAD);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByCantidadIsLessThanSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where cantidad is less than DEFAULT_CANTIDAD
        defaultProductoCompradoShouldNotBeFound("cantidad.lessThan=" + DEFAULT_CANTIDAD);

        // Get all the productoCompradoList where cantidad is less than UPDATED_CANTIDAD
        defaultProductoCompradoShouldBeFound("cantidad.lessThan=" + UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByCantidadIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        // Get all the productoCompradoList where cantidad is greater than DEFAULT_CANTIDAD
        defaultProductoCompradoShouldNotBeFound("cantidad.greaterThan=" + DEFAULT_CANTIDAD);

        // Get all the productoCompradoList where cantidad is greater than SMALLER_CANTIDAD
        defaultProductoCompradoShouldBeFound("cantidad.greaterThan=" + SMALLER_CANTIDAD);
    }

    @Test
    @Transactional
    void getAllProductoCompradosByVentaIsEqualToSomething() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);
        Venta venta;
        if (TestUtil.findAll(em, Venta.class).isEmpty()) {
            venta = VentaResourceIT.createEntity(em);
            em.persist(venta);
            em.flush();
        } else {
            venta = TestUtil.findAll(em, Venta.class).get(0);
        }
        em.persist(venta);
        em.flush();
        productoComprado.setVenta(venta);
        productoCompradoRepository.saveAndFlush(productoComprado);
        Long ventaId = venta.getId();

        // Get all the productoCompradoList where venta equals to ventaId
        defaultProductoCompradoShouldBeFound("ventaId.equals=" + ventaId);

        // Get all the productoCompradoList where venta equals to (ventaId + 1)
        defaultProductoCompradoShouldNotBeFound("ventaId.equals=" + (ventaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultProductoCompradoShouldBeFound(String filter) throws Exception {
        restProductoCompradoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productoComprado.getId().intValue())))
            .andExpect(jsonPath("$.[*].producto").value(hasItem(DEFAULT_PRODUCTO.intValue())))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.doubleValue())))
            .andExpect(jsonPath("$.[*].cantidad").value(hasItem(DEFAULT_CANTIDAD)));

        // Check, that the count call also returns 1
        restProductoCompradoMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultProductoCompradoShouldNotBeFound(String filter) throws Exception {
        restProductoCompradoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProductoCompradoMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingProductoComprado() throws Exception {
        // Get the productoComprado
        restProductoCompradoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProductoComprado() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        int databaseSizeBeforeUpdate = productoCompradoRepository.findAll().size();

        // Update the productoComprado
        ProductoComprado updatedProductoComprado = productoCompradoRepository.findById(productoComprado.getId()).get();
        // Disconnect from session so that the updates on updatedProductoComprado are not directly saved in db
        em.detach(updatedProductoComprado);
        updatedProductoComprado.producto(UPDATED_PRODUCTO).precio(UPDATED_PRECIO).cantidad(UPDATED_CANTIDAD);
        ProductoCompradoDTO productoCompradoDTO = productoCompradoMapper.toDto(updatedProductoComprado);

        restProductoCompradoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productoCompradoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productoCompradoDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProductoComprado in the database
        List<ProductoComprado> productoCompradoList = productoCompradoRepository.findAll();
        assertThat(productoCompradoList).hasSize(databaseSizeBeforeUpdate);
        ProductoComprado testProductoComprado = productoCompradoList.get(productoCompradoList.size() - 1);
        assertThat(testProductoComprado.getProducto()).isEqualTo(UPDATED_PRODUCTO);
        assertThat(testProductoComprado.getPrecio()).isEqualTo(UPDATED_PRECIO);
        assertThat(testProductoComprado.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    void putNonExistingProductoComprado() throws Exception {
        int databaseSizeBeforeUpdate = productoCompradoRepository.findAll().size();
        productoComprado.setId(count.incrementAndGet());

        // Create the ProductoComprado
        ProductoCompradoDTO productoCompradoDTO = productoCompradoMapper.toDto(productoComprado);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductoCompradoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productoCompradoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productoCompradoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductoComprado in the database
        List<ProductoComprado> productoCompradoList = productoCompradoRepository.findAll();
        assertThat(productoCompradoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProductoComprado() throws Exception {
        int databaseSizeBeforeUpdate = productoCompradoRepository.findAll().size();
        productoComprado.setId(count.incrementAndGet());

        // Create the ProductoComprado
        ProductoCompradoDTO productoCompradoDTO = productoCompradoMapper.toDto(productoComprado);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoCompradoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productoCompradoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductoComprado in the database
        List<ProductoComprado> productoCompradoList = productoCompradoRepository.findAll();
        assertThat(productoCompradoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProductoComprado() throws Exception {
        int databaseSizeBeforeUpdate = productoCompradoRepository.findAll().size();
        productoComprado.setId(count.incrementAndGet());

        // Create the ProductoComprado
        ProductoCompradoDTO productoCompradoDTO = productoCompradoMapper.toDto(productoComprado);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoCompradoMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productoCompradoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductoComprado in the database
        List<ProductoComprado> productoCompradoList = productoCompradoRepository.findAll();
        assertThat(productoCompradoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProductoCompradoWithPatch() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        int databaseSizeBeforeUpdate = productoCompradoRepository.findAll().size();

        // Update the productoComprado using partial update
        ProductoComprado partialUpdatedProductoComprado = new ProductoComprado();
        partialUpdatedProductoComprado.setId(productoComprado.getId());

        partialUpdatedProductoComprado.precio(UPDATED_PRECIO).cantidad(UPDATED_CANTIDAD);

        restProductoCompradoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductoComprado.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductoComprado))
            )
            .andExpect(status().isOk());

        // Validate the ProductoComprado in the database
        List<ProductoComprado> productoCompradoList = productoCompradoRepository.findAll();
        assertThat(productoCompradoList).hasSize(databaseSizeBeforeUpdate);
        ProductoComprado testProductoComprado = productoCompradoList.get(productoCompradoList.size() - 1);
        assertThat(testProductoComprado.getProducto()).isEqualTo(DEFAULT_PRODUCTO);
        assertThat(testProductoComprado.getPrecio()).isEqualTo(UPDATED_PRECIO);
        assertThat(testProductoComprado.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    void fullUpdateProductoCompradoWithPatch() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        int databaseSizeBeforeUpdate = productoCompradoRepository.findAll().size();

        // Update the productoComprado using partial update
        ProductoComprado partialUpdatedProductoComprado = new ProductoComprado();
        partialUpdatedProductoComprado.setId(productoComprado.getId());

        partialUpdatedProductoComprado.producto(UPDATED_PRODUCTO).precio(UPDATED_PRECIO).cantidad(UPDATED_CANTIDAD);

        restProductoCompradoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductoComprado.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductoComprado))
            )
            .andExpect(status().isOk());

        // Validate the ProductoComprado in the database
        List<ProductoComprado> productoCompradoList = productoCompradoRepository.findAll();
        assertThat(productoCompradoList).hasSize(databaseSizeBeforeUpdate);
        ProductoComprado testProductoComprado = productoCompradoList.get(productoCompradoList.size() - 1);
        assertThat(testProductoComprado.getProducto()).isEqualTo(UPDATED_PRODUCTO);
        assertThat(testProductoComprado.getPrecio()).isEqualTo(UPDATED_PRECIO);
        assertThat(testProductoComprado.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    void patchNonExistingProductoComprado() throws Exception {
        int databaseSizeBeforeUpdate = productoCompradoRepository.findAll().size();
        productoComprado.setId(count.incrementAndGet());

        // Create the ProductoComprado
        ProductoCompradoDTO productoCompradoDTO = productoCompradoMapper.toDto(productoComprado);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductoCompradoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, productoCompradoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productoCompradoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductoComprado in the database
        List<ProductoComprado> productoCompradoList = productoCompradoRepository.findAll();
        assertThat(productoCompradoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProductoComprado() throws Exception {
        int databaseSizeBeforeUpdate = productoCompradoRepository.findAll().size();
        productoComprado.setId(count.incrementAndGet());

        // Create the ProductoComprado
        ProductoCompradoDTO productoCompradoDTO = productoCompradoMapper.toDto(productoComprado);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoCompradoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productoCompradoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductoComprado in the database
        List<ProductoComprado> productoCompradoList = productoCompradoRepository.findAll();
        assertThat(productoCompradoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProductoComprado() throws Exception {
        int databaseSizeBeforeUpdate = productoCompradoRepository.findAll().size();
        productoComprado.setId(count.incrementAndGet());

        // Create the ProductoComprado
        ProductoCompradoDTO productoCompradoDTO = productoCompradoMapper.toDto(productoComprado);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoCompradoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productoCompradoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductoComprado in the database
        List<ProductoComprado> productoCompradoList = productoCompradoRepository.findAll();
        assertThat(productoCompradoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProductoComprado() throws Exception {
        // Initialize the database
        productoCompradoRepository.saveAndFlush(productoComprado);

        int databaseSizeBeforeDelete = productoCompradoRepository.findAll().size();

        // Delete the productoComprado
        restProductoCompradoMockMvc
            .perform(delete(ENTITY_API_URL_ID, productoComprado.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductoComprado> productoCompradoList = productoCompradoRepository.findAll();
        assertThat(productoCompradoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
