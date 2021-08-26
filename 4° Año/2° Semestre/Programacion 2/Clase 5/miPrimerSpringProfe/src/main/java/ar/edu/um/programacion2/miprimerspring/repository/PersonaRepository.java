/**
 * 
 */
package ar.edu.um.programacion2.miprimerspring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import ar.edu.um.programacion2.miprimerspring.model.Persona;

/**
 * @author daniel
 *
 */
@Component
public class PersonaRepository {

	private Map<Long, Persona> personas = new HashMap<Long, Persona>();

	public List<Persona> findAll() {
		return new ArrayList<Persona>(personas.values());
	}

	public Optional<Persona> findByDocumento(Long documento) {
		return Optional.ofNullable(personas.get(documento));
	}

	public Persona add(Persona persona) {
		personas.put(persona.getDocumento(), persona);
		return personas.get(persona.getDocumento());
	}

	public Persona update(Persona newpersona, Long documento) {
		Persona persona = this.findByDocumento(documento).get();
		persona.setNombre(newpersona.getNombre());
		return personas.get(persona.getDocumento());
	}

	public void deleteByDocumento(Long documento) {
		personas.remove(documento);
	}

}
