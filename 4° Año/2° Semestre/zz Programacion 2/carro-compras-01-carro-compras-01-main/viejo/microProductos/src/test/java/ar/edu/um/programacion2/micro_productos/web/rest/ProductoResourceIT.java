package ar.edu.um.programacion2.micro_productos.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.edu.um.programacion2.micro_productos.IntegrationTest;
import ar.edu.um.programacion2.micro_productos.domain.Distribuidor;
import ar.edu.um.programacion2.micro_productos.domain.Producto;
import ar.edu.um.programacion2.micro_productos.repository.ProductoRepository;
import ar.edu.um.programacion2.micro_productos.service.criteria.ProductoCriteria;
import ar.edu.um.programacion2.micro_productos.service.dto.ProductoDTO;
import ar.edu.um.programacion2.micro_productos.service.mapper.ProductoMapper;
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
 * Integration tests for the {@link ProductoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProductoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Float DEFAULT_PRECIO = 1F;
    private static final Float UPDATED_PRECIO = 2F;
    private static final Float SMALLER_PRECIO = 1F - 1F;

    private static final Integer DEFAULT_CANTIDAD_VENDIDOS = 1;
    private static final Integer UPDATED_CANTIDAD_VENDIDOS = 2;
    private static final Integer SMALLER_CANTIDAD_VENDIDOS = 1 - 1;

    private static final Boolean DEFAULT_HABILITADO = false;
    private static final Boolean UPDATED_HABILITADO = true;

    private static final String ENTITY_API_URL = "/api/productos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductoMockMvc;

    private Producto producto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Producto createEntity(EntityManager em) {
        Producto producto = new Producto()
            .nombre(DEFAULT_NOMBRE)
            .descripcion(DEFAULT_DESCRIPCION)
            .precio(DEFAULT_PRECIO)
            .cantidadVendidos(DEFAULT_CANTIDAD_VENDIDOS)
            .habilitado(DEFAULT_HABILITADO);
        return producto;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Producto createUpdatedEntity(EntityManager em) {
        Producto producto = new Producto()
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .precio(UPDATED_PRECIO)
            .cantidadVendidos(UPDATED_CANTIDAD_VENDIDOS)
            .habilitado(UPDATED_HABILITADO);
        return producto;
    }

    @BeforeEach
    public void initTest() {
        producto = createEntity(em);
    }

    @Test
    @Transactional
    void createProducto() throws Exception {
        int databaseSizeBeforeCreate = productoRepository.findAll().size();
        // Create the Producto
        ProductoDTO productoDTO = productoMapper.toDto(producto);
        restProductoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productoDTO)))
            .andExpect(status().isCreated());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeCreate + 1);
        Producto testProducto = productoList.get(productoList.size() - 1);
        assertThat(testProducto.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testProducto.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testProducto.getPrecio()).isEqualTo(DEFAULT_PRECIO);
        assertThat(testProducto.getCantidadVendidos()).isEqualTo(DEFAULT_CANTIDAD_VENDIDOS);
        assertThat(testProducto.getHabilitado()).isEqualTo(DEFAULT_HABILITADO);
    }

    @Test
    @Transactional
    void createProductoWithExistingId() throws Exception {
        // Create the Producto with an existing ID
        producto.setId(1L);
        ProductoDTO productoDTO = productoMapper.toDto(producto);

        int databaseSizeBeforeCreate = productoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProductos() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList
        restProductoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(producto.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.doubleValue())))
            .andExpect(jsonPath("$.[*].cantidadVendidos").value(hasItem(DEFAULT_CANTIDAD_VENDIDOS)))
            .andExpect(jsonPath("$.[*].habilitado").value(hasItem(DEFAULT_HABILITADO.booleanValue())));
    }

    @Test
    @Transactional
    void getProducto() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get the producto
        restProductoMockMvc
            .perform(get(ENTITY_API_URL_ID, producto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(producto.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.precio").value(DEFAULT_PRECIO.doubleValue()))
            .andExpect(jsonPath("$.cantidadVendidos").value(DEFAULT_CANTIDAD_VENDIDOS))
            .andExpect(jsonPath("$.habilitado").value(DEFAULT_HABILITADO.booleanValue()));
    }

    @Test
    @Transactional
    void getProductosByIdFiltering() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        Long id = producto.getId();

        defaultProductoShouldBeFound("id.equals=" + id);
        defaultProductoShouldNotBeFound("id.notEquals=" + id);

        defaultProductoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultProductoShouldNotBeFound("id.greaterThan=" + id);

        defaultProductoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultProductoShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllProductosByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where nombre equals to DEFAULT_NOMBRE
        defaultProductoShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the productoList where nombre equals to UPDATED_NOMBRE
        defaultProductoShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    void getAllProductosByNombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where nombre not equals to DEFAULT_NOMBRE
        defaultProductoShouldNotBeFound("nombre.notEquals=" + DEFAULT_NOMBRE);

        // Get all the productoList where nombre not equals to UPDATED_NOMBRE
        defaultProductoShouldBeFound("nombre.notEquals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    void getAllProductosByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultProductoShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the productoList where nombre equals to UPDATED_NOMBRE
        defaultProductoShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    void getAllProductosByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where nombre is not null
        defaultProductoShouldBeFound("nombre.specified=true");

        // Get all the productoList where nombre is null
        defaultProductoShouldNotBeFound("nombre.specified=false");
    }

    @Test
    @Transactional
    void getAllProductosByNombreContainsSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where nombre contains DEFAULT_NOMBRE
        defaultProductoShouldBeFound("nombre.contains=" + DEFAULT_NOMBRE);

        // Get all the productoList where nombre contains UPDATED_NOMBRE
        defaultProductoShouldNotBeFound("nombre.contains=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    void getAllProductosByNombreNotContainsSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where nombre does not contain DEFAULT_NOMBRE
        defaultProductoShouldNotBeFound("nombre.doesNotContain=" + DEFAULT_NOMBRE);

        // Get all the productoList where nombre does not contain UPDATED_NOMBRE
        defaultProductoShouldBeFound("nombre.doesNotContain=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    void getAllProductosByDescripcionIsEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where descripcion equals to DEFAULT_DESCRIPCION
        defaultProductoShouldBeFound("descripcion.equals=" + DEFAULT_DESCRIPCION);

        // Get all the productoList where descripcion equals to UPDATED_DESCRIPCION
        defaultProductoShouldNotBeFound("descripcion.equals=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    void getAllProductosByDescripcionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where descripcion not equals to DEFAULT_DESCRIPCION
        defaultProductoShouldNotBeFound("descripcion.notEquals=" + DEFAULT_DESCRIPCION);

        // Get all the productoList where descripcion not equals to UPDATED_DESCRIPCION
        defaultProductoShouldBeFound("descripcion.notEquals=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    void getAllProductosByDescripcionIsInShouldWork() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where descripcion in DEFAULT_DESCRIPCION or UPDATED_DESCRIPCION
        defaultProductoShouldBeFound("descripcion.in=" + DEFAULT_DESCRIPCION + "," + UPDATED_DESCRIPCION);

        // Get all the productoList where descripcion equals to UPDATED_DESCRIPCION
        defaultProductoShouldNotBeFound("descripcion.in=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    void getAllProductosByDescripcionIsNullOrNotNull() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where descripcion is not null
        defaultProductoShouldBeFound("descripcion.specified=true");

        // Get all the productoList where descripcion is null
        defaultProductoShouldNotBeFound("descripcion.specified=false");
    }

    @Test
    @Transactional
    void getAllProductosByDescripcionContainsSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where descripcion contains DEFAULT_DESCRIPCION
        defaultProductoShouldBeFound("descripcion.contains=" + DEFAULT_DESCRIPCION);

        // Get all the productoList where descripcion contains UPDATED_DESCRIPCION
        defaultProductoShouldNotBeFound("descripcion.contains=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    void getAllProductosByDescripcionNotContainsSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where descripcion does not contain DEFAULT_DESCRIPCION
        defaultProductoShouldNotBeFound("descripcion.doesNotContain=" + DEFAULT_DESCRIPCION);

        // Get all the productoList where descripcion does not contain UPDATED_DESCRIPCION
        defaultProductoShouldBeFound("descripcion.doesNotContain=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    void getAllProductosByPrecioIsEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where precio equals to DEFAULT_PRECIO
        defaultProductoShouldBeFound("precio.equals=" + DEFAULT_PRECIO);

        // Get all the productoList where precio equals to UPDATED_PRECIO
        defaultProductoShouldNotBeFound("precio.equals=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductosByPrecioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where precio not equals to DEFAULT_PRECIO
        defaultProductoShouldNotBeFound("precio.notEquals=" + DEFAULT_PRECIO);

        // Get all the productoList where precio not equals to UPDATED_PRECIO
        defaultProductoShouldBeFound("precio.notEquals=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductosByPrecioIsInShouldWork() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where precio in DEFAULT_PRECIO or UPDATED_PRECIO
        defaultProductoShouldBeFound("precio.in=" + DEFAULT_PRECIO + "," + UPDATED_PRECIO);

        // Get all the productoList where precio equals to UPDATED_PRECIO
        defaultProductoShouldNotBeFound("precio.in=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductosByPrecioIsNullOrNotNull() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where precio is not null
        defaultProductoShouldBeFound("precio.specified=true");

        // Get all the productoList where precio is null
        defaultProductoShouldNotBeFound("precio.specified=false");
    }

    @Test
    @Transactional
    void getAllProductosByPrecioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where precio is greater than or equal to DEFAULT_PRECIO
        defaultProductoShouldBeFound("precio.greaterThanOrEqual=" + DEFAULT_PRECIO);

        // Get all the productoList where precio is greater than or equal to UPDATED_PRECIO
        defaultProductoShouldNotBeFound("precio.greaterThanOrEqual=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductosByPrecioIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where precio is less than or equal to DEFAULT_PRECIO
        defaultProductoShouldBeFound("precio.lessThanOrEqual=" + DEFAULT_PRECIO);

        // Get all the productoList where precio is less than or equal to SMALLER_PRECIO
        defaultProductoShouldNotBeFound("precio.lessThanOrEqual=" + SMALLER_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductosByPrecioIsLessThanSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where precio is less than DEFAULT_PRECIO
        defaultProductoShouldNotBeFound("precio.lessThan=" + DEFAULT_PRECIO);

        // Get all the productoList where precio is less than UPDATED_PRECIO
        defaultProductoShouldBeFound("precio.lessThan=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductosByPrecioIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where precio is greater than DEFAULT_PRECIO
        defaultProductoShouldNotBeFound("precio.greaterThan=" + DEFAULT_PRECIO);

        // Get all the productoList where precio is greater than SMALLER_PRECIO
        defaultProductoShouldBeFound("precio.greaterThan=" + SMALLER_PRECIO);
    }

    @Test
    @Transactional
    void getAllProductosByCantidadVendidosIsEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where cantidadVendidos equals to DEFAULT_CANTIDAD_VENDIDOS
        defaultProductoShouldBeFound("cantidadVendidos.equals=" + DEFAULT_CANTIDAD_VENDIDOS);

        // Get all the productoList where cantidadVendidos equals to UPDATED_CANTIDAD_VENDIDOS
        defaultProductoShouldNotBeFound("cantidadVendidos.equals=" + UPDATED_CANTIDAD_VENDIDOS);
    }

    @Test
    @Transactional
    void getAllProductosByCantidadVendidosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where cantidadVendidos not equals to DEFAULT_CANTIDAD_VENDIDOS
        defaultProductoShouldNotBeFound("cantidadVendidos.notEquals=" + DEFAULT_CANTIDAD_VENDIDOS);

        // Get all the productoList where cantidadVendidos not equals to UPDATED_CANTIDAD_VENDIDOS
        defaultProductoShouldBeFound("cantidadVendidos.notEquals=" + UPDATED_CANTIDAD_VENDIDOS);
    }

    @Test
    @Transactional
    void getAllProductosByCantidadVendidosIsInShouldWork() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where cantidadVendidos in DEFAULT_CANTIDAD_VENDIDOS or UPDATED_CANTIDAD_VENDIDOS
        defaultProductoShouldBeFound("cantidadVendidos.in=" + DEFAULT_CANTIDAD_VENDIDOS + "," + UPDATED_CANTIDAD_VENDIDOS);

        // Get all the productoList where cantidadVendidos equals to UPDATED_CANTIDAD_VENDIDOS
        defaultProductoShouldNotBeFound("cantidadVendidos.in=" + UPDATED_CANTIDAD_VENDIDOS);
    }

    @Test
    @Transactional
    void getAllProductosByCantidadVendidosIsNullOrNotNull() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where cantidadVendidos is not null
        defaultProductoShouldBeFound("cantidadVendidos.specified=true");

        // Get all the productoList where cantidadVendidos is null
        defaultProductoShouldNotBeFound("cantidadVendidos.specified=false");
    }

    @Test
    @Transactional
    void getAllProductosByCantidadVendidosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where cantidadVendidos is greater than or equal to DEFAULT_CANTIDAD_VENDIDOS
        defaultProductoShouldBeFound("cantidadVendidos.greaterThanOrEqual=" + DEFAULT_CANTIDAD_VENDIDOS);

        // Get all the productoList where cantidadVendidos is greater than or equal to UPDATED_CANTIDAD_VENDIDOS
        defaultProductoShouldNotBeFound("cantidadVendidos.greaterThanOrEqual=" + UPDATED_CANTIDAD_VENDIDOS);
    }

    @Test
    @Transactional
    void getAllProductosByCantidadVendidosIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where cantidadVendidos is less than or equal to DEFAULT_CANTIDAD_VENDIDOS
        defaultProductoShouldBeFound("cantidadVendidos.lessThanOrEqual=" + DEFAULT_CANTIDAD_VENDIDOS);

        // Get all the productoList where cantidadVendidos is less than or equal to SMALLER_CANTIDAD_VENDIDOS
        defaultProductoShouldNotBeFound("cantidadVendidos.lessThanOrEqual=" + SMALLER_CANTIDAD_VENDIDOS);
    }

    @Test
    @Transactional
    void getAllProductosByCantidadVendidosIsLessThanSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where cantidadVendidos is less than DEFAULT_CANTIDAD_VENDIDOS
        defaultProductoShouldNotBeFound("cantidadVendidos.lessThan=" + DEFAULT_CANTIDAD_VENDIDOS);

        // Get all the productoList where cantidadVendidos is less than UPDATED_CANTIDAD_VENDIDOS
        defaultProductoShouldBeFound("cantidadVendidos.lessThan=" + UPDATED_CANTIDAD_VENDIDOS);
    }

    @Test
    @Transactional
    void getAllProductosByCantidadVendidosIsGreaterThanSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where cantidadVendidos is greater than DEFAULT_CANTIDAD_VENDIDOS
        defaultProductoShouldNotBeFound("cantidadVendidos.greaterThan=" + DEFAULT_CANTIDAD_VENDIDOS);

        // Get all the productoList where cantidadVendidos is greater than SMALLER_CANTIDAD_VENDIDOS
        defaultProductoShouldBeFound("cantidadVendidos.greaterThan=" + SMALLER_CANTIDAD_VENDIDOS);
    }

    @Test
    @Transactional
    void getAllProductosByHabilitadoIsEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where habilitado equals to DEFAULT_HABILITADO
        defaultProductoShouldBeFound("habilitado.equals=" + DEFAULT_HABILITADO);

        // Get all the productoList where habilitado equals to UPDATED_HABILITADO
        defaultProductoShouldNotBeFound("habilitado.equals=" + UPDATED_HABILITADO);
    }

    @Test
    @Transactional
    void getAllProductosByHabilitadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where habilitado not equals to DEFAULT_HABILITADO
        defaultProductoShouldNotBeFound("habilitado.notEquals=" + DEFAULT_HABILITADO);

        // Get all the productoList where habilitado not equals to UPDATED_HABILITADO
        defaultProductoShouldBeFound("habilitado.notEquals=" + UPDATED_HABILITADO);
    }

    @Test
    @Transactional
    void getAllProductosByHabilitadoIsInShouldWork() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where habilitado in DEFAULT_HABILITADO or UPDATED_HABILITADO
        defaultProductoShouldBeFound("habilitado.in=" + DEFAULT_HABILITADO + "," + UPDATED_HABILITADO);

        // Get all the productoList where habilitado equals to UPDATED_HABILITADO
        defaultProductoShouldNotBeFound("habilitado.in=" + UPDATED_HABILITADO);
    }

    @Test
    @Transactional
    void getAllProductosByHabilitadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        // Get all the productoList where habilitado is not null
        defaultProductoShouldBeFound("habilitado.specified=true");

        // Get all the productoList where habilitado is null
        defaultProductoShouldNotBeFound("habilitado.specified=false");
    }

    @Test
    @Transactional
    void getAllProductosByDistribuidorIsEqualToSomething() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);
        Distribuidor distribuidor;
        if (TestUtil.findAll(em, Distribuidor.class).isEmpty()) {
            distribuidor = DistribuidorResourceIT.createEntity(em);
            em.persist(distribuidor);
            em.flush();
        } else {
            distribuidor = TestUtil.findAll(em, Distribuidor.class).get(0);
        }
        em.persist(distribuidor);
        em.flush();
        producto.setDistribuidor(distribuidor);
        productoRepository.saveAndFlush(producto);
        Long distribuidorId = distribuidor.getId();

        // Get all the productoList where distribuidor equals to distribuidorId
        defaultProductoShouldBeFound("distribuidorId.equals=" + distribuidorId);

        // Get all the productoList where distribuidor equals to (distribuidorId + 1)
        defaultProductoShouldNotBeFound("distribuidorId.equals=" + (distribuidorId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultProductoShouldBeFound(String filter) throws Exception {
        restProductoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(producto.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.doubleValue())))
            .andExpect(jsonPath("$.[*].cantidadVendidos").value(hasItem(DEFAULT_CANTIDAD_VENDIDOS)))
            .andExpect(jsonPath("$.[*].habilitado").value(hasItem(DEFAULT_HABILITADO.booleanValue())));

        // Check, that the count call also returns 1
        restProductoMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultProductoShouldNotBeFound(String filter) throws Exception {
        restProductoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProductoMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingProducto() throws Exception {
        // Get the producto
        restProductoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProducto() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        int databaseSizeBeforeUpdate = productoRepository.findAll().size();

        // Update the producto
        Producto updatedProducto = productoRepository.findById(producto.getId()).get();
        // Disconnect from session so that the updates on updatedProducto are not directly saved in db
        em.detach(updatedProducto);
        updatedProducto
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .precio(UPDATED_PRECIO)
            .cantidadVendidos(UPDATED_CANTIDAD_VENDIDOS)
            .habilitado(UPDATED_HABILITADO);
        ProductoDTO productoDTO = productoMapper.toDto(updatedProducto);

        restProductoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productoDTO))
            )
            .andExpect(status().isOk());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
        Producto testProducto = productoList.get(productoList.size() - 1);
        assertThat(testProducto.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testProducto.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testProducto.getPrecio()).isEqualTo(UPDATED_PRECIO);
        assertThat(testProducto.getCantidadVendidos()).isEqualTo(UPDATED_CANTIDAD_VENDIDOS);
        assertThat(testProducto.getHabilitado()).isEqualTo(UPDATED_HABILITADO);
    }

    @Test
    @Transactional
    void putNonExistingProducto() throws Exception {
        int databaseSizeBeforeUpdate = productoRepository.findAll().size();
        producto.setId(count.incrementAndGet());

        // Create the Producto
        ProductoDTO productoDTO = productoMapper.toDto(producto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProducto() throws Exception {
        int databaseSizeBeforeUpdate = productoRepository.findAll().size();
        producto.setId(count.incrementAndGet());

        // Create the Producto
        ProductoDTO productoDTO = productoMapper.toDto(producto);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProducto() throws Exception {
        int databaseSizeBeforeUpdate = productoRepository.findAll().size();
        producto.setId(count.incrementAndGet());

        // Create the Producto
        ProductoDTO productoDTO = productoMapper.toDto(producto);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProductoWithPatch() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        int databaseSizeBeforeUpdate = productoRepository.findAll().size();

        // Update the producto using partial update
        Producto partialUpdatedProducto = new Producto();
        partialUpdatedProducto.setId(producto.getId());

        partialUpdatedProducto.nombre(UPDATED_NOMBRE).descripcion(UPDATED_DESCRIPCION).habilitado(UPDATED_HABILITADO);

        restProductoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProducto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProducto))
            )
            .andExpect(status().isOk());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
        Producto testProducto = productoList.get(productoList.size() - 1);
        assertThat(testProducto.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testProducto.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testProducto.getPrecio()).isEqualTo(DEFAULT_PRECIO);
        assertThat(testProducto.getCantidadVendidos()).isEqualTo(DEFAULT_CANTIDAD_VENDIDOS);
        assertThat(testProducto.getHabilitado()).isEqualTo(UPDATED_HABILITADO);
    }

    @Test
    @Transactional
    void fullUpdateProductoWithPatch() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        int databaseSizeBeforeUpdate = productoRepository.findAll().size();

        // Update the producto using partial update
        Producto partialUpdatedProducto = new Producto();
        partialUpdatedProducto.setId(producto.getId());

        partialUpdatedProducto
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .precio(UPDATED_PRECIO)
            .cantidadVendidos(UPDATED_CANTIDAD_VENDIDOS)
            .habilitado(UPDATED_HABILITADO);

        restProductoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProducto.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProducto))
            )
            .andExpect(status().isOk());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
        Producto testProducto = productoList.get(productoList.size() - 1);
        assertThat(testProducto.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testProducto.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testProducto.getPrecio()).isEqualTo(UPDATED_PRECIO);
        assertThat(testProducto.getCantidadVendidos()).isEqualTo(UPDATED_CANTIDAD_VENDIDOS);
        assertThat(testProducto.getHabilitado()).isEqualTo(UPDATED_HABILITADO);
    }

    @Test
    @Transactional
    void patchNonExistingProducto() throws Exception {
        int databaseSizeBeforeUpdate = productoRepository.findAll().size();
        producto.setId(count.incrementAndGet());

        // Create the Producto
        ProductoDTO productoDTO = productoMapper.toDto(producto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, productoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProducto() throws Exception {
        int databaseSizeBeforeUpdate = productoRepository.findAll().size();
        producto.setId(count.incrementAndGet());

        // Create the Producto
        ProductoDTO productoDTO = productoMapper.toDto(producto);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProducto() throws Exception {
        int databaseSizeBeforeUpdate = productoRepository.findAll().size();
        producto.setId(count.incrementAndGet());

        // Create the Producto
        ProductoDTO productoDTO = productoMapper.toDto(producto);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(productoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Producto in the database
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProducto() throws Exception {
        // Initialize the database
        productoRepository.saveAndFlush(producto);

        int databaseSizeBeforeDelete = productoRepository.findAll().size();

        // Delete the producto
        restProductoMockMvc
            .perform(delete(ENTITY_API_URL_ID, producto.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Producto> productoList = productoRepository.findAll();
        assertThat(productoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
