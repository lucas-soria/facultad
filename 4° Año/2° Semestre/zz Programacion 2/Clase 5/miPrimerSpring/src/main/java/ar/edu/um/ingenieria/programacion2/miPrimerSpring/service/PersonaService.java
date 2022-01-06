package ar.edu.um.ingenieria.programacion2.miPrimerSpring.service;

import ar.edu.um.ingenieria.programacion2.miPrimerSpring.exception.PersonaNotFoundException;
import ar.edu.um.ingenieria.programacion2.miPrimerSpring.model.Persona;
import ar.edu.um.ingenieria.programacion2.miPrimerSpring.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository repository;

    public List<Persona> findAll() {
        return this.repository.findAll();
    }

    public Persona findByDocumento(Long documento) {
        return this.repository.findByDocumento(documento).orElseThrow(() -> new PersonaNotFoundException(documento));
    }

    public Persona add(Persona persona) {
        return this.repository.add(persona);
    }

    public Persona update(Persona persona, Long documento) {
        return this.repository.update(persona, documento);
    }

    public void deleteByDocumento(Long documento) {
        this.repository.deleteByDocumento(documento);
    }
}
