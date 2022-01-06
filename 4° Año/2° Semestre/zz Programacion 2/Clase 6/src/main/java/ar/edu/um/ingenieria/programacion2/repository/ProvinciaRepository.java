package ar.edu.um.ingenieria.programacion2.repository;

import ar.edu.um.ingenieria.programacion2.domain.Provincia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Provincia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {}
