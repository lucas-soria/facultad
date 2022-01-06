/**
 * 
 */
package ar.edu.um.programacion2.miprimerspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.programacion2.miprimerspring.exception.PersonaNotFoundException;
import ar.edu.um.programacion2.miprimerspring.model.Persona;
import ar.edu.um.programacion2.miprimerspring.repository.PersonaRepository;

/**
 * @author daniel
 *
 */
@Service
public class PersonaService {

	@Autowired
	private PersonaRepository repository;

	public List<Persona> findAll() {
		return repository.findAll();
	}

	public Persona findByDocumento(Long documento) {
		return repository.findByDocumento(documento).orElseThrow(() -> new PersonaNotFoundException(documento));
	}

	public Persona add(Persona persona) {
		return repository.add(persona);
	}

	public Persona update(Persona persona, Long documento) {
		return repository.update(persona, documento);
	}

	public void deleteByDocumento(Long documento) {
		repository.deleteByDocumento(documento);
	}

}
