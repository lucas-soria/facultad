package ar.edu.um.programacion2.carro_de_compras.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.edu.um.programacion2.carro_de_compras.IntegrationTest;
import ar.edu.um.programacion2.carro_de_compras.domain.ProductoComprado;
import ar.edu.um.programacion2.carro_de_compras.repository.ProductoCompradoRepository;
import ar.edu.um.programacion2.carro_de_compras.service.dto.ProductoCompradoDTO;
import ar.edu.um.programacion2.carro_de_compras.service.mapper.ProductoCompradoMapper;
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

    private static final Float DEFAULT_PRECIO = 1F;
    private static final Float UPDATED_PRECIO = 2F;

    private static final Integer DEFAULT_CANTIDAD = 1;
    private static final Integer UPDATED_CANTIDAD = 2;

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
