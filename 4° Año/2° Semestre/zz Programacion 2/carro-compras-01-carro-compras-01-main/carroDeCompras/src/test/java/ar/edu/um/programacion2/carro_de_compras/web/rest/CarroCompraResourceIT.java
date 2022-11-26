package ar.edu.um.programacion2.carro_de_compras.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.edu.um.programacion2.carro_de_compras.IntegrationTest;
import ar.edu.um.programacion2.carro_de_compras.domain.CarroCompra;
import ar.edu.um.programacion2.carro_de_compras.repository.CarroCompraRepository;
import ar.edu.um.programacion2.carro_de_compras.service.dto.CarroCompraDTO;
import ar.edu.um.programacion2.carro_de_compras.service.mapper.CarroCompraMapper;
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

    private static final Float DEFAULT_TOTAL = 1F;
    private static final Float UPDATED_TOTAL = 2F;

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
        CarroCompra carroCompra = new CarroCompra().total(DEFAULT_TOTAL).finalizado(DEFAULT_FINALIZADO).vendido(DEFAULT_VENDIDO);
        return carroCompra;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CarroCompra createUpdatedEntity(EntityManager em) {
        CarroCompra carroCompra = new CarroCompra().total(UPDATED_TOTAL).finalizado(UPDATED_FINALIZADO).vendido(UPDATED_VENDIDO);
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
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.finalizado").value(DEFAULT_FINALIZADO.booleanValue()))
            .andExpect(jsonPath("$.vendido").value(DEFAULT_VENDIDO.booleanValue()));
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
        updatedCarroCompra.total(UPDATED_TOTAL).finalizado(UPDATED_FINALIZADO).vendido(UPDATED_VENDIDO);
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

        partialUpdatedCarroCompra.total(UPDATED_TOTAL).vendido(UPDATED_VENDIDO);

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
        assertThat(testCarroCompra.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testCarroCompra.getFinalizado()).isEqualTo(DEFAULT_FINALIZADO);
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

        partialUpdatedCarroCompra.total(UPDATED_TOTAL).finalizado(UPDATED_FINALIZADO).vendido(UPDATED_VENDIDO);

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
