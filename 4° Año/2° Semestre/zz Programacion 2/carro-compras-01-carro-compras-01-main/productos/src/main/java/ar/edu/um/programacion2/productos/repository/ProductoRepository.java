package ar.edu.um.programacion2.productos.repository;

import ar.edu.um.programacion2.productos.domain.Producto;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Producto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT producto FROM Producto producto WHERE producto.habilitado = :status")
    List<Producto> findAllAndHabilitado(@Param("status") Boolean status);

    @Query("SELECT producto FROM Producto producto WHERE producto.distribuidor.id = :id")
    List<Producto> findAllByDistribuidor(@Param("id") Long id);

}
