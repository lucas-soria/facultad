package ar.edu.um.ingenieria.programacion2.miPrimerSpring.controller;

import ar.edu.um.ingenieria.programacion2.miPrimerSpring.model.Persona;
import ar.edu.um.ingenieria.programacion2.miPrimerSpring.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
