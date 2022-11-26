package ar.edu.um.programacion2.micro_carro_de_compras.repository;

import ar.edu.um.programacion2.micro_carro_de_compras.domain.ProductoSeleccionado;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProductoSeleccionado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductoSeleccionadoRepository
    extends JpaRepository<ProductoSeleccionado, Long>, JpaSpecificationExecutor<ProductoSeleccionado> {}
