/**
 * 
 */
package ar.edu.um.programacion2.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.programacion2.jpa.model.Telefono;
import ar.edu.um.programacion2.jpa.service.TelefonoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/api/telefono")
public class TelefonoController {

	@Autowired
	private TelefonoService service;

	@GetMapping("/{telefonoId}")
	public ResponseEntity<Telefono> findByTelefonoId(@PathVariable Long telefonoId) {
		return new ResponseEntity<Telefono>(service.findByTelefonoId(telefonoId), HttpStatus.OK);
	}
}
