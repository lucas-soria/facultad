/**
 * 
 */
package ar.edu.um.programacion2.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.programacion2.jpa.model.Persona;
import ar.edu.um.programacion2.jpa.service.PersonaService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/api/persona")
public class PersonaController {

	@Autowired
	private PersonaService service;

	@GetMapping("/")
	public ResponseEntity<List<Persona>> findAll() {
		return new ResponseEntity<List<Persona>>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{personaId}")
	public ResponseEntity<Persona> findByPersonaId(@PathVariable Long personaId) {
		return new ResponseEntity<Persona>(service.findByPersonaId(personaId), HttpStatus.OK);
	}

	@GetMapping("/documento/{documento}")
	public ResponseEntity<Persona> findByDocumento(@PathVariable String documento) {
		return new ResponseEntity<Persona>(service.findByDocumento(documento), HttpStatus.OK);
	}

	@GetMapping("/combinado/{personaId}/{documento}")
	public ResponseEntity<Persona> findByCombinado(@PathVariable Long personaId, @PathVariable String documento) {
		return new ResponseEntity<Persona>(service.findByCombinado(personaId, documento), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Persona> add(@RequestBody Persona persona) {
		return new ResponseEntity<Persona>(service.add(persona), HttpStatus.OK);
	}

	@PutMapping("/{personaId}")
	public ResponseEntity<Persona> update(@RequestBody Persona persona, @PathVariable Long personaId) {
		return new ResponseEntity<Persona>(service.update(persona, personaId), HttpStatus.OK);
	}

	@DeleteMapping("/{personaId}")
	public ResponseEntity<Void> delete(@PathVariable Long personaId) {
		service.deleteByPersonaId(personaId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
