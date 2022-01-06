package ar.edu.um.programacion2.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.edu.um.programacion2.IntegrationTest;
import ar.edu.um.programacion2.domain.Pais;
import ar.edu.um.programacion2.repository.PaisRepository;
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
 * Integration tests for the {@link PaisResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PaisResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTINENTE = "AAAAAAAAAA";
    private static final String UPDATED_CONTINENTE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pais";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaisMockMvc;

    private Pais pais;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pais createEntity(EntityManager em) {
        Pais pais = new Pais().nombre(DEFAULT_NOMBRE).continente(DEFAULT_CONTINENTE);
        return pais;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pais createUpdatedEntity(EntityManager em) {
        Pais pais = new Pais().nombre(UPDATED_NOMBRE).continente(UPDATED_CONTINENTE);
        return pais;
    }

    @BeforeEach
    public void initTest() {
        pais = createEntity(em);
    }

    @Test
    @Transactional
    void createPais() throws Exception {
        int databaseSizeBeforeCreate = paisRepository.findAll().size();
        // Create the Pais
        restPaisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pais)))
            .andExpect(status().isCreated());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeCreate + 1);
        Pais testPais = paisList.get(paisList.size() - 1);
        assertThat(testPais.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testPais.getContinente()).isEqualTo(DEFAULT_CONTINENTE);
    }

    @Test
    @Transactional
    void createPaisWithExistingId() throws Exception {
        // Create the Pais with an existing ID
        pais.setId(1L);

        int databaseSizeBeforeCreate = paisRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pais)))
            .andExpect(status().isBadRequest());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = paisRepository.findAll().size();
        // set the field null
        pais.setNombre(null);

        // Create the Pais, which fails.

        restPaisMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pais)))
            .andExpect(status().isBadRequest());

        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPais() throws Exception {
        // Initialize the database
        paisRepository.saveAndFlush(pais);

        // Get all the paisList
        restPaisMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pais.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].continente").value(hasItem(DEFAULT_CONTINENTE)));
    }

    @Test
    @Transactional
    void getPais() throws Exception {
        // Initialize the database
        paisRepository.saveAndFlush(pais);

        // Get the pais
        restPaisMockMvc
            .perform(get(ENTITY_API_URL_ID, pais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pais.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.continente").value(DEFAULT_CONTINENTE));
    }

    @Test
    @Transactional
    void getNonExistingPais() throws Exception {
        // Get the pais
        restPaisMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPais() throws Exception {
        // Initialize the database
        paisRepository.saveAndFlush(pais);

        int databaseSizeBeforeUpdate = paisRepository.findAll().size();

        // Update the pais
        Pais updatedPais = paisRepository.findById(pais.getId()).get();
        // Disconnect from session so that the updates on updatedPais are not directly saved in db
        em.detach(updatedPais);
        updatedPais.nombre(UPDATED_NOMBRE).continente(UPDATED_CONTINENTE);

        restPaisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPais.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPais))
            )
            .andExpect(status().isOk());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeUpdate);
        Pais testPais = paisList.get(paisList.size() - 1);
        assertThat(testPais.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testPais.getContinente()).isEqualTo(UPDATED_CONTINENTE);
    }

    @Test
    @Transactional
    void putNonExistingPais() throws Exception {
        int databaseSizeBeforeUpdate = paisRepository.findAll().size();
        pais.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pais.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pais))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPais() throws Exception {
        int databaseSizeBeforeUpdate = paisRepository.findAll().size();
        pais.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pais))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPais() throws Exception {
        int databaseSizeBeforeUpdate = paisRepository.findAll().size();
        pais.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaisMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pais)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePaisWithPatch() throws Exception {
        // Initialize the database
        paisRepository.saveAndFlush(pais);

        int databaseSizeBeforeUpdate = paisRepository.findAll().size();

        // Update the pais using partial update
        Pais partialUpdatedPais = new Pais();
        partialUpdatedPais.setId(pais.getId());

        partialUpdatedPais.continente(UPDATED_CONTINENTE);

        restPaisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPais.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPais))
            )
            .andExpect(status().isOk());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeUpdate);
        Pais testPais = paisList.get(paisList.size() - 1);
        assertThat(testPais.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testPais.getContinente()).isEqualTo(UPDATED_CONTINENTE);
    }

    @Test
    @Transactional
    void fullUpdatePaisWithPatch() throws Exception {
        // Initialize the database
        paisRepository.saveAndFlush(pais);

        int databaseSizeBeforeUpdate = paisRepository.findAll().size();

        // Update the pais using partial update
        Pais partialUpdatedPais = new Pais();
        partialUpdatedPais.setId(pais.getId());

        partialUpdatedPais.nombre(UPDATED_NOMBRE).continente(UPDATED_CONTINENTE);

        restPaisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPais.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPais))
            )
            .andExpect(status().isOk());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeUpdate);
        Pais testPais = paisList.get(paisList.size() - 1);
        assertThat(testPais.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testPais.getContinente()).isEqualTo(UPDATED_CONTINENTE);
    }

    @Test
    @Transactional
    void patchNonExistingPais() throws Exception {
        int databaseSizeBeforeUpdate = paisRepository.findAll().size();
        pais.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pais.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pais))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPais() throws Exception {
        int databaseSizeBeforeUpdate = paisRepository.findAll().size();
        pais.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pais))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPais() throws Exception {
        int databaseSizeBeforeUpdate = paisRepository.findAll().size();
        pais.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaisMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(pais)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Pais in the database
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePais() throws Exception {
        // Initialize the database
        paisRepository.saveAndFlush(pais);

        int databaseSizeBeforeDelete = paisRepository.findAll().size();

        // Delete the pais
        restPaisMockMvc
            .perform(delete(ENTITY_API_URL_ID, pais.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
