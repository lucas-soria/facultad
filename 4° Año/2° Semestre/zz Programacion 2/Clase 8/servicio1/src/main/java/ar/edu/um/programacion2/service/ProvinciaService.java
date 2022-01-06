package ar.edu.um.programacion2.service;

import ar.edu.um.programacion2.domain.Provincia;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Provincia}.
 */
public interface ProvinciaService {
    /**
     * Save a provincia.
     *
     * @param provincia the entity to save.
     * @return the persisted entity.
     */
    Provincia save(Provincia provincia);

    /**
     * Partially updates a provincia.
     *
     * @param provincia the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Provincia> partialUpdate(Provincia provincia);

    /**
     * Get all the provincias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Provincia> findAll(Pageable pageable);

    /**
     * Get the "id" provincia.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Provincia> findOne(Long id);

    /**
     * Delete the "id" provincia.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
