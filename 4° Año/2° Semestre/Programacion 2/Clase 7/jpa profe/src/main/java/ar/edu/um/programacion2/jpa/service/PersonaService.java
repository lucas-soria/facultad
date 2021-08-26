/**
 * 
 */
package ar.edu.um.programacion2.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.programacion2.jpa.exception.PersonaNotFoundException;
import ar.edu.um.programacion2.jpa.model.Persona;
import ar.edu.um.programacion2.jpa.repository.IPersonaRepository;

/**
 * @author daniel
 *
 */
@Service
public class PersonaService {

	@Autowired
	private IPersonaRepository repository;

	public List<Persona> findAll() {
		return repository.findAll();
	}

	public Persona findByPersonaId(Long personaId) {
		return repository.findByPersonaId(personaId).orElseThrow(() -> new PersonaNotFoundException(personaId));
	}

	public Persona findByDocumento(String documento) {
		return repository.findByDocumento(documento).orElseThrow(() -> new PersonaNotFoundException(documento));
	}

	public Persona findByCombinado(Long personaId, String documento) {
		return repository.findByPersonaIdAndDocumento(personaId, documento)
				.orElseThrow(() -> new PersonaNotFoundException(personaId, documento));
	}

	public List<Persona> saveAll(List<Persona> personas) {
		return repository.saveAll(personas);
	}

	public Persona add(Persona persona) {
		persona = repository.save(persona);
		return persona;
	}

	public Persona update(Persona newpersona, Long personaId) {
		return repository.findByPersonaId(personaId).map(persona -> {
			persona = new Persona(personaId, newpersona.getDocumento(), newpersona.getNombre(),
					newpersona.getApellido());
			persona = repository.save(persona);
			return persona;
		}).orElseThrow(() -> new PersonaNotFoundException(personaId));
	}

	@Transactional
	public void deleteByPersonaId(Long personaId) {
		repository.deleteByPersonaId(personaId);
	}

}
