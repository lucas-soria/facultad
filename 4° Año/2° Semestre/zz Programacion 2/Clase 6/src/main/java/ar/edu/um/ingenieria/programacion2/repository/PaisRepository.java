package ar.edu.um.ingenieria.programacion2.repository;

import ar.edu.um.ingenieria.programacion2.domain.Pais;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Pais entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaisRepository extends JpaRepository<Pais, Long>, JpaSpecificationExecutor<Pais> {}
