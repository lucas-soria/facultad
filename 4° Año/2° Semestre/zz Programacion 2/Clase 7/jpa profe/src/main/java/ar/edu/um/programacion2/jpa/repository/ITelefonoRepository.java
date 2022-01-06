/**
 * 
 */
package ar.edu.um.programacion2.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.um.programacion2.jpa.model.Telefono;

/**
 * @author daniel
 *
 */
@Repository
public interface ITelefonoRepository extends JpaRepository<Telefono, Long> {

	public Optional<Telefono> findByTelefonoId(Long telefonoId);

}
