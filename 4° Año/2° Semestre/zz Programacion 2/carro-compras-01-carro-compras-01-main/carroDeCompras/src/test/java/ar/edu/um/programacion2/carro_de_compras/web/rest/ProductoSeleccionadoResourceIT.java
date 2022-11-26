package ar.edu.um.programacion2.carro_de_compras.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.edu.um.programacion2.carro_de_compras.IntegrationTest;
import ar.edu.um.programacion2.carro_de_compras.domain.ProductoSeleccionado;
import ar.edu.um.programacion2.carro_de_compras.repository.ProductoSeleccionadoRepository;
import ar.edu.um.programacion2.carro_de_compras.service.dto.ProductoSeleccionadoDTO;
import ar.edu.um.programacion2.carro_de_compras.service.mapper.ProductoSeleccionadoMapper;
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
 * Integration tests for the {@link ProductoSeleccionadoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProductoSeleccionadoResourceIT {

    private static final Long DEFAULT_PRODUCTO = 1L;
    private static final Long UPDATED_PRODUCTO = 2L;

    private static final Float DEFAULT_PRECIO = 1F;
    private static final Float UPDATED_PRECIO = 2F;

    private static final Integer DEFAULT_CANTIDAD = 1;
    private static final Integer UPDATED_CANTIDAD = 2;

    private static final String ENTITY_API_URL = "/api/producto-seleccionados";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProductoSeleccionadoRepository productoSeleccionadoRepository;

    @Autowired
    private ProductoSeleccionadoMapper productoSeleccionadoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductoSeleccionadoMockMvc;

    private ProductoSeleccionado productoSeleccionado;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductoSeleccionado createEntity(EntityManager em) {
        ProductoSeleccionado productoSeleccionado = new ProductoSeleccionado()
            .producto(DEFAULT_PRODUCTO)
            .precio(DEFAULT_PRECIO)
            .cantidad(DEFAULT_CANTIDAD);
        return productoSeleccionado;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductoSeleccionado createUpdatedEntity(EntityManager em) {
        ProductoSeleccionado productoSeleccionado = new ProductoSeleccionado()
            .producto(UPDATED_PRODUCTO)
            .precio(UPDATED_PRECIO)
            .cantidad(UPDATED_CANTIDAD);
        return productoSeleccionado;
    }

    @BeforeEach
    public void initTest() {
        productoSeleccionado = createEntity(em);
    }

    @Test
    @Transactional
    void createProductoSeleccionado() throws Exception {
        int databaseSizeBeforeCreate = productoSeleccionadoRepository.findAll().size();
        // Create the ProductoSeleccionado
        ProductoSeleccionadoDTO productoSeleccionadoDTO = productoSeleccionadoMapper.toDto(productoSeleccionado);
        restProductoSeleccionadoMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productoSeleccionadoDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ProductoSeleccionado in the database
        List<ProductoSeleccionado> productoSeleccionadoList = productoSeleccionadoRepository.findAll();
        assertThat(productoSeleccionadoList).hasSize(databaseSizeBeforeCreate + 1);
        ProductoSeleccionado testProductoSeleccionado = productoSeleccionadoList.get(productoSeleccionadoList.size() - 1);
        assertThat(testProductoSeleccionado.getProducto()).isEqualTo(DEFAULT_PRODUCTO);
        assertThat(testProductoSeleccionado.getPrecio()).isEqualTo(DEFAULT_PRECIO);
        assertThat(testProductoSeleccionado.getCantidad()).isEqualTo(DEFAULT_CANTIDAD);
    }

    @Test
    @Transactional
    void createProductoSeleccionadoWithExistingId() throws Exception {
        // Create the ProductoSeleccionado with an existing ID
        productoSeleccionado.setId(1L);
        ProductoSeleccionadoDTO productoSeleccionadoDTO = productoSeleccionadoMapper.toDto(productoSeleccionado);

        int databaseSizeBeforeCreate = productoSeleccionadoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductoSeleccionadoMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productoSeleccionadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductoSeleccionado in the database
        List<ProductoSeleccionado> productoSeleccionadoList = productoSeleccionadoRepository.findAll();
        assertThat(productoSeleccionadoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProductoSeleccionados() throws Exception {
        // Initialize the database
        productoSeleccionadoRepository.saveAndFlush(productoSeleccionado);

        // Get all the productoSeleccionadoList
        restProductoSeleccionadoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productoSeleccionado.getId().intValue())))
            .andExpect(jsonPath("$.[*].producto").value(hasItem(DEFAULT_PRODUCTO.intValue())))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.doubleValue())))
            .andExpect(jsonPath("$.[*].cantidad").value(hasItem(DEFAULT_CANTIDAD)));
    }

    @Test
    @Transactional
    void getProductoSeleccionado() throws Exception {
        // Initialize the database
        productoSeleccionadoRepository.saveAndFlush(productoSeleccionado);

        // Get the productoSeleccionado
        restProductoSeleccionadoMockMvc
            .perform(get(ENTITY_API_URL_ID, productoSeleccionado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productoSeleccionado.getId().intValue()))
            .andExpect(jsonPath("$.producto").value(DEFAULT_PRODUCTO.intValue()))
            .andExpect(jsonPath("$.precio").value(DEFAULT_PRECIO.doubleValue()))
            .andExpect(jsonPath("$.cantidad").value(DEFAULT_CANTIDAD));
    }

    @Test
    @Transactional
    void getNonExistingProductoSeleccionado() throws Exception {
        // Get the productoSeleccionado
        restProductoSeleccionadoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProductoSeleccionado() throws Exception {
        // Initialize the database
        productoSeleccionadoRepository.saveAndFlush(productoSeleccionado);

        int databaseSizeBeforeUpdate = productoSeleccionadoRepository.findAll().size();

        // Update the productoSeleccionado
        ProductoSeleccionado updatedProductoSeleccionado = productoSeleccionadoRepository.findById(productoSeleccionado.getId()).get();
        // Disconnect from session so that the updates on updatedProductoSeleccionado are not directly saved in db
        em.detach(updatedProductoSeleccionado);
        updatedProductoSeleccionado.producto(UPDATED_PRODUCTO).precio(UPDATED_PRECIO).cantidad(UPDATED_CANTIDAD);
        ProductoSeleccionadoDTO productoSeleccionadoDTO = productoSeleccionadoMapper.toDto(updatedProductoSeleccionado);

        restProductoSeleccionadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productoSeleccionadoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productoSeleccionadoDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProductoSeleccionado in the database
        List<ProductoSeleccionado> productoSeleccionadoList = productoSeleccionadoRepository.findAll();
        assertThat(productoSeleccionadoList).hasSize(databaseSizeBeforeUpdate);
        ProductoSeleccionado testProductoSeleccionado = productoSeleccionadoList.get(productoSeleccionadoList.size() - 1);
        assertThat(testProductoSeleccionado.getProducto()).isEqualTo(UPDATED_PRODUCTO);
        assertThat(testProductoSeleccionado.getPrecio()).isEqualTo(UPDATED_PRECIO);
        assertThat(testProductoSeleccionado.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    void putNonExistingProductoSeleccionado() throws Exception {
        int databaseSizeBeforeUpdate = productoSeleccionadoRepository.findAll().size();
        productoSeleccionado.setId(count.incrementAndGet());

        // Create the ProductoSeleccionado
        ProductoSeleccionadoDTO productoSeleccionadoDTO = productoSeleccionadoMapper.toDto(productoSeleccionado);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductoSeleccionadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productoSeleccionadoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productoSeleccionadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductoSeleccionado in the database
        List<ProductoSeleccionado> productoSeleccionadoList = productoSeleccionadoRepository.findAll();
        assertThat(productoSeleccionadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProductoSeleccionado() throws Exception {
        int databaseSizeBeforeUpdate = productoSeleccionadoRepository.findAll().size();
        productoSeleccionado.setId(count.incrementAndGet());

        // Create the ProductoSeleccionado
        ProductoSeleccionadoDTO productoSeleccionadoDTO = productoSeleccionadoMapper.toDto(productoSeleccionado);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoSeleccionadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productoSeleccionadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductoSeleccionado in the database
        List<ProductoSeleccionado> productoSeleccionadoList = productoSeleccionadoRepository.findAll();
        assertThat(productoSeleccionadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProductoSeleccionado() throws Exception {
        int databaseSizeBeforeUpdate = productoSeleccionadoRepository.findAll().size();
        productoSeleccionado.setId(count.incrementAndGet());

        // Create the ProductoSeleccionado
        ProductoSeleccionadoDTO productoSeleccionadoDTO = productoSeleccionadoMapper.toDto(productoSeleccionado);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoSeleccionadoMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productoSeleccionadoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductoSeleccionado in the database
        List<ProductoSeleccionado> productoSeleccionadoList = productoSeleccionadoRepository.findAll();
        assertThat(productoSeleccionadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProductoSeleccionadoWithPatch() throws Exception {
        // Initialize the database
        productoSeleccionadoRepository.saveAndFlush(productoSeleccionado);

        int databaseSizeBeforeUpdate = productoSeleccionadoRepository.findAll().size();

        // Update the productoSeleccionado using partial update
        ProductoSeleccionado partialUpdatedProductoSeleccionado = new ProductoSeleccionado();
        partialUpdatedProductoSeleccionado.setId(productoSeleccionado.getId());

        partialUpdatedProductoSeleccionado.cantidad(UPDATED_CANTIDAD);

        restProductoSeleccionadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductoSeleccionado.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductoSeleccionado))
            )
            .andExpect(status().isOk());

        // Validate the ProductoSeleccionado in the database
        List<ProductoSeleccionado> productoSeleccionadoList = productoSeleccionadoRepository.findAll();
        assertThat(productoSeleccionadoList).hasSize(databaseSizeBeforeUpdate);
        ProductoSeleccionado testProductoSeleccionado = productoSeleccionadoList.get(productoSeleccionadoList.size() - 1);
        assertThat(testProductoSeleccionado.getProducto()).isEqualTo(DEFAULT_PRODUCTO);
        assertThat(testProductoSeleccionado.getPrecio()).isEqualTo(DEFAULT_PRECIO);
        assertThat(testProductoSeleccionado.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    void fullUpdateProductoSeleccionadoWithPatch() throws Exception {
        // Initialize the database
        productoSeleccionadoRepository.saveAndFlush(productoSeleccionado);

        int databaseSizeBeforeUpdate = productoSeleccionadoRepository.findAll().size();

        // Update the productoSeleccionado using partial update
        ProductoSeleccionado partialUpdatedProductoSeleccionado = new ProductoSeleccionado();
        partialUpdatedProductoSeleccionado.setId(productoSeleccionado.getId());

        partialUpdatedProductoSeleccionado.producto(UPDATED_PRODUCTO).precio(UPDATED_PRECIO).cantidad(UPDATED_CANTIDAD);

        restProductoSeleccionadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductoSeleccionado.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductoSeleccionado))
            )
            .andExpect(status().isOk());

        // Validate the ProductoSeleccionado in the database
        List<ProductoSeleccionado> productoSeleccionadoList = productoSeleccionadoRepository.findAll();
        assertThat(productoSeleccionadoList).hasSize(databaseSizeBeforeUpdate);
        ProductoSeleccionado testProductoSeleccionado = productoSeleccionadoList.get(productoSeleccionadoList.size() - 1);
        assertThat(testProductoSeleccionado.getProducto()).isEqualTo(UPDATED_PRODUCTO);
        assertThat(testProductoSeleccionado.getPrecio()).isEqualTo(UPDATED_PRECIO);
        assertThat(testProductoSeleccionado.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    void patchNonExistingProductoSeleccionado() throws Exception {
        int databaseSizeBeforeUpdate = productoSeleccionadoRepository.findAll().size();
        productoSeleccionado.setId(count.incrementAndGet());

        // Create the ProductoSeleccionado
        ProductoSeleccionadoDTO productoSeleccionadoDTO = productoSeleccionadoMapper.toDto(productoSeleccionado);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductoSeleccionadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, productoSeleccionadoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productoSeleccionadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductoSeleccionado in the database
        List<ProductoSeleccionado> productoSeleccionadoList = productoSeleccionadoRepository.findAll();
        assertThat(productoSeleccionadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProductoSeleccionado() throws Exception {
        int databaseSizeBeforeUpdate = productoSeleccionadoRepository.findAll().size();
        productoSeleccionado.setId(count.incrementAndGet());

        // Create the ProductoSeleccionado
        ProductoSeleccionadoDTO productoSeleccionadoDTO = productoSeleccionadoMapper.toDto(productoSeleccionado);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoSeleccionadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productoSeleccionadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductoSeleccionado in the database
        List<ProductoSeleccionado> productoSeleccionadoList = productoSeleccionadoRepository.findAll();
        assertThat(productoSeleccionadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProductoSeleccionado() throws Exception {
        int databaseSizeBeforeUpdate = productoSeleccionadoRepository.findAll().size();
        productoSeleccionado.setId(count.incrementAndGet());

        // Create the ProductoSeleccionado
        ProductoSeleccionadoDTO productoSeleccionadoDTO = productoSeleccionadoMapper.toDto(productoSeleccionado);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductoSeleccionadoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productoSeleccionadoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductoSeleccionado in the database
        List<ProductoSeleccionado> productoSeleccionadoList = productoSeleccionadoRepository.findAll();
        assertThat(productoSeleccionadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProductoSeleccionado() throws Exception {
        // Initialize the database
        productoSeleccionadoRepository.saveAndFlush(productoSeleccionado);

        int databaseSizeBeforeDelete = productoSeleccionadoRepository.findAll().size();

        // Delete the productoSeleccionado
        restProductoSeleccionadoMockMvc
            .perform(delete(ENTITY_API_URL_ID, productoSeleccionado.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductoSeleccionado> productoSeleccionadoList = productoSeleccionadoRepository.findAll();
        assertThat(productoSeleccionadoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
