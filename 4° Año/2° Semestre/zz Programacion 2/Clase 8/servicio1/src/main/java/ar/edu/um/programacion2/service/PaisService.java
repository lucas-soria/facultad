package ar.edu.um.programacion2.service;

import ar.edu.um.programacion2.domain.Pais;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Pais}.
 */
public interface PaisService {
    /**
     * Save a pais.
     *
     * @param pais the entity to save.
     * @return the persisted entity.
     */
    Pais save(Pais pais);

    /**
     * Partially updates a pais.
     *
     * @param pais the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Pais> partialUpdate(Pais pais);

    /**
     * Get all the pais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Pais> findAll(Pageable pageable);

    /**
     * Get the "id" pais.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Pais> findOne(Long id);

    /**
     * Delete the "id" pais.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
