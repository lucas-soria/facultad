package ar.edu.um.programacion2.micro_carro_de_compras.repository;

import ar.edu.um.programacion2.micro_carro_de_compras.domain.CarroCompra;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the CarroCompra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarroCompraRepository extends JpaRepository<CarroCompra, Long>, JpaSpecificationExecutor<CarroCompra> {}
