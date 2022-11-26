package ar.edu.um.programacion2.micro_productos.repository;

import ar.edu.um.programacion2.micro_productos.domain.Distribuidor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Distribuidor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DistribuidorRepository extends JpaRepository<Distribuidor, Long>, JpaSpecificationExecutor<Distribuidor> {}
