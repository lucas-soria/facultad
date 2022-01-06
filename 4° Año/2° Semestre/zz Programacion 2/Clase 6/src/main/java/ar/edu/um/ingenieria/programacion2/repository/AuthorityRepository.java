package ar.edu.um.ingenieria.programacion2.repository;

import ar.edu.um.ingenieria.programacion2.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
