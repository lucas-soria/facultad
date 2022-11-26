package ar.edu.um.programacion2.carro_de_compras.repository;

import ar.edu.um.programacion2.carro_de_compras.domain.Venta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Venta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query("select venta from Venta venta where venta.cliente.login = ?#{principal.username}")
    List<Venta> findByClienteIsCurrentUser();

    @Query("select venta from Venta venta where venta.cliente.id = :id")
    List<Venta> findByCliente(@Param("id") Long id);

    @Query("SELECT venta FROM Venta venta WHERE venta.cliente.login = ?#{principal.username} and venta.id = :id")
    Optional<Venta> findByClienteIsCurrentUserAndVenta(@Param("id") Long id);

    @Query("SELECT venta FROM Venta venta WHERE venta.fecha >= :fechaInicio and venta.fecha <= :fechaFin")
    List<Venta> findByFechaInicioAndFechaFin(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

}
