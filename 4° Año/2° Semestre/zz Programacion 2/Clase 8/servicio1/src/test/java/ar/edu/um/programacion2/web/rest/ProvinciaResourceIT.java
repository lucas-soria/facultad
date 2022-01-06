package ar.edu.um.programacion2.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.edu.um.programacion2.IntegrationTest;
import ar.edu.um.programacion2.domain.Provincia;
import ar.edu.um.programacion2.repository.ProvinciaRepository;
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
 * Integration tests for the {@link ProvinciaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProvinciaResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_POSTAL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/provincias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProvinciaMockMvc;

    private Provincia provincia;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Provincia createEntity(EntityManager em) {
        Provincia provincia = new Provincia().nombre(DEFAULT_NOMBRE).codigoPostal(DEFAULT_CODIGO_POSTAL);
        return provincia;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Provincia createUpdatedEntity(EntityManager em) {
        Provincia provincia = new Provincia().nombre(UPDATED_NOMBRE).codigoPostal(UPDATED_CODIGO_POSTAL);
        return provincia;
    }

    @BeforeEach
    public void initTest() {
        provincia = createEntity(em);
    }

    @Test
    @Transactional
    void createProvincia() throws Exception {
        int databaseSizeBeforeCreate = provinciaRepository.findAll().size();
        // Create the Provincia
        restProvinciaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(provincia)))
            .andExpect(status().isCreated());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeCreate + 1);
        Provincia testProvincia = provinciaList.get(provinciaList.size() - 1);
        assertThat(testProvincia.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testProvincia.getCodigoPostal()).isEqualTo(DEFAULT_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void createProvinciaWithExistingId() throws Exception {
        // Create the Provincia with an existing ID
        provincia.setId(1L);

        int databaseSizeBeforeCreate = provinciaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProvinciaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(provincia)))
            .andExpect(status().isBadRequest());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProvincias() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get all the provinciaList
        restProvinciaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(provincia.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].codigoPostal").value(hasItem(DEFAULT_CODIGO_POSTAL)));
    }

    @Test
    @Transactional
    void getProvincia() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        // Get the provincia
        restProvinciaMockMvc
            .perform(get(ENTITY_API_URL_ID, provincia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(provincia.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.codigoPostal").value(DEFAULT_CODIGO_POSTAL));
    }

    @Test
    @Transactional
    void getNonExistingProvincia() throws Exception {
        // Get the provincia
        restProvinciaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewProvincia() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();

        // Update the provincia
        Provincia updatedProvincia = provinciaRepository.findById(provincia.getId()).get();
        // Disconnect from session so that the updates on updatedProvincia are not directly saved in db
        em.detach(updatedProvincia);
        updatedProvincia.nombre(UPDATED_NOMBRE).codigoPostal(UPDATED_CODIGO_POSTAL);

        restProvinciaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProvincia.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedProvincia))
            )
            .andExpect(status().isOk());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate);
        Provincia testProvincia = provinciaList.get(provinciaList.size() - 1);
        assertThat(testProvincia.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testProvincia.getCodigoPostal()).isEqualTo(UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void putNonExistingProvincia() throws Exception {
        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();
        provincia.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProvinciaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, provincia.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(provincia))
            )
            .andExpect(status().isBadRequest());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProvincia() throws Exception {
        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();
        provincia.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProvinciaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(provincia))
            )
            .andExpect(status().isBadRequest());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProvincia() throws Exception {
        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();
        provincia.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProvinciaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(provincia)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProvinciaWithPatch() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();

        // Update the provincia using partial update
        Provincia partialUpdatedProvincia = new Provincia();
        partialUpdatedProvincia.setId(provincia.getId());

        restProvinciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProvincia.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProvincia))
            )
            .andExpect(status().isOk());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate);
        Provincia testProvincia = provinciaList.get(provinciaList.size() - 1);
        assertThat(testProvincia.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testProvincia.getCodigoPostal()).isEqualTo(DEFAULT_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void fullUpdateProvinciaWithPatch() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();

        // Update the provincia using partial update
        Provincia partialUpdatedProvincia = new Provincia();
        partialUpdatedProvincia.setId(provincia.getId());

        partialUpdatedProvincia.nombre(UPDATED_NOMBRE).codigoPostal(UPDATED_CODIGO_POSTAL);

        restProvinciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProvincia.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProvincia))
            )
            .andExpect(status().isOk());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate);
        Provincia testProvincia = provinciaList.get(provinciaList.size() - 1);
        assertThat(testProvincia.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testProvincia.getCodigoPostal()).isEqualTo(UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    void patchNonExistingProvincia() throws Exception {
        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();
        provincia.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProvinciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, provincia.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(provincia))
            )
            .andExpect(status().isBadRequest());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProvincia() throws Exception {
        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();
        provincia.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProvinciaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(provincia))
            )
            .andExpect(status().isBadRequest());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProvincia() throws Exception {
        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();
        provincia.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProvinciaMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(provincia))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Provincia in the database
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProvincia() throws Exception {
        // Initialize the database
        provinciaRepository.saveAndFlush(provincia);

        int databaseSizeBeforeDelete = provinciaRepository.findAll().size();

        // Delete the provincia
        restProvinciaMockMvc
            .perform(delete(ENTITY_API_URL_ID, provincia.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
