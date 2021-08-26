/**
 * 
 */
package ar.edu.um.programacion2.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.um.programacion2.jpa.model.Persona;

/**
 * @author daniel
 *
 */
@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long> {

	public Optional<Persona> findByPersonaId(Long personaId);

	public Optional<Persona> findByDocumento(String documento);

	public Optional<Persona> findByPersonaIdAndDocumento(Long personaId, String documento);

	public void deleteByPersonaId(Long personaId);

}
