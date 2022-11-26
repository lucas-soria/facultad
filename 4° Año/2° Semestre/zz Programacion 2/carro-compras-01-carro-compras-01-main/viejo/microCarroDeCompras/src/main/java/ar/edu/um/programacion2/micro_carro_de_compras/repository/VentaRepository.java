package ar.edu.um.programacion2.micro_carro_de_compras.repository;

import ar.edu.um.programacion2.micro_carro_de_compras.domain.Venta;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Venta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>, JpaSpecificationExecutor<Venta> {}
