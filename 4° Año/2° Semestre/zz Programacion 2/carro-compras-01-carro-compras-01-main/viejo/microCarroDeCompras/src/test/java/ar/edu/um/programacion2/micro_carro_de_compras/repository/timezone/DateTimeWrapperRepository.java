package ar.edu.um.programacion2.micro_carro_de_compras.repository.timezone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the {@link DateTimeWrapper} entity.
 */
@Repository
public interface DateTimeWrapperRepository extends JpaRepository<DateTimeWrapper, Long> {}
