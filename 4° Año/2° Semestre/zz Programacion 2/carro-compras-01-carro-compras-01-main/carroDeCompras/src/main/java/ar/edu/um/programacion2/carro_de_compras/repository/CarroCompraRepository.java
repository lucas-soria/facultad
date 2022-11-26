package ar.edu.um.programacion2.carro_de_compras.repository;

import ar.edu.um.programacion2.carro_de_compras.domain.CarroCompra;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CarroCompra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarroCompraRepository extends JpaRepository<CarroCompra, Long> {

    @Query("select carroCompra from CarroCompra carroCompra where carroCompra.cliente.login = ?#{principal.username}")
    List<CarroCompra> findByClienteIsCurrentUser();

    @Query("select carroCompra from CarroCompra carroCompra where carroCompra.cliente.id = :id")
    List<CarroCompra> findByCliente(@Param("id") Long id);

    @Query("SELECT carroCompra FROM CarroCompra carroCompra WHERE carroCompra.cliente.login = ?#{principal.username} and carroCompra.finalizado = :status")
    Optional<CarroCompra> findByClienteIsCurrentUserAndFinalizado(@Param("status") Boolean status);

    @Query("SELECT carroCompra FROM CarroCompra carroCompra WHERE carroCompra.cliente.login = ?#{principal.username} and carroCompra.id = :id")
    Optional<CarroCompra> findByClienteIsCurrentUserAndCarro(@Param("id") Long id);

    @Query("SELECT carroCompra FROM CarroCompra carroCompra WHERE carroCompra.cliente.id = :id and carroCompra.finalizado = :status")
    Optional<CarroCompra> findByClienteAndFinalizado(@Param("id") Long id, @Param("status") Boolean status);

}
