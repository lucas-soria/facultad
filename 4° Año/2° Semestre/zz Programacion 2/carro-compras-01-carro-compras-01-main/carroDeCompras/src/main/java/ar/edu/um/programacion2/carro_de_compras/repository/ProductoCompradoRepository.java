package ar.edu.um.programacion2.carro_de_compras.repository;

import ar.edu.um.programacion2.carro_de_compras.domain.ProductoComprado;
import ar.edu.um.programacion2.carro_de_compras.domain.Venta;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the ProductoComprado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductoCompradoRepository extends JpaRepository<ProductoComprado, Long> {

    @Query("SELECT productoComprado FROM ProductoComprado productoComprado WHERE productoComprado.venta = :venta")
    List<ProductoComprado> findByVenta(@Param("venta") Venta venta);

}
