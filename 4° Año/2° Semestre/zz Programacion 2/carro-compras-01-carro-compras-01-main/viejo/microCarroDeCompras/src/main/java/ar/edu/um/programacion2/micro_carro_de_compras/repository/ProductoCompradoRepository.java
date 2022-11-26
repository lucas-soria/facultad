package ar.edu.um.programacion2.micro_carro_de_compras.repository;

import ar.edu.um.programacion2.micro_carro_de_compras.domain.ProductoComprado;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProductoComprado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductoCompradoRepository extends JpaRepository<ProductoComprado, Long>, JpaSpecificationExecutor<ProductoComprado> {}
