package ar.edu.um.programacion2.carro_de_compras.repository;

import ar.edu.um.programacion2.carro_de_compras.domain.CarroCompra;
import ar.edu.um.programacion2.carro_de_compras.domain.ProductoSeleccionado;
import ar.edu.um.programacion2.carro_de_compras.service.dto.CarroCompraDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the ProductoSeleccionado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductoSeleccionadoRepository extends JpaRepository<ProductoSeleccionado, Long> {

    @Query("SELECT productoSeleccionado FROM ProductoSeleccionado productoSeleccionado WHERE productoSeleccionado.producto = :producto and productoSeleccionado.carro = :carro")
    Optional<ProductoSeleccionado> findByProductoAndCarro(@Param("producto") Long producto, @Param("carro")CarroCompra carroCompra);

    @Query("SELECT productoSeleccionado FROM ProductoSeleccionado productoSeleccionado WHERE productoSeleccionado.carro = :carro")
    List<ProductoSeleccionado> findByCarro(@Param("carro") CarroCompra carroCompra);

}
