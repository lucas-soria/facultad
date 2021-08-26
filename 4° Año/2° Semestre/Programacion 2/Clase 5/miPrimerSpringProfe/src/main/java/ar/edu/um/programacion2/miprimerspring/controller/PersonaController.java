/**
 * 
 */
package ar.edu.um.programacion2.miprimerspring.controller;

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

import ar.edu.um.programacion2.miprimerspring.model.Persona;
import ar.edu.um.programacion2.miprimerspring.service.PersonaService;

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

	@GetMapping("/{documento}")
	public ResponseEntity<Persona> findByDocumento(@PathVariable Long documento) {
		return new ResponseEntity<Persona>(service.findByDocumento(documento), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Persona> add(@RequestBody Persona persona) {
		return new ResponseEntity<Persona>(service.add(persona), HttpStatus.OK);
	}

	@PutMapping("/{documento}")
	public ResponseEntity<Persona> update(@RequestBody Persona persona, @PathVariable Long documento) {
		return new ResponseEntity<Persona>(service.update(persona, documento), HttpStatus.OK);
	}

	@DeleteMapping("/{documento}")
	public ResponseEntity<Void> deleteByDocumento(@PathVariable Long documento) {
		service.deleteByDocumento(documento);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
