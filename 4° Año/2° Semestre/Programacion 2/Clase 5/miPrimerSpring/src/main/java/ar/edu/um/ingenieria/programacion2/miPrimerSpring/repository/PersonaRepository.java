package ar.edu.um.ingenieria.programacion2.miPrimerSpring.repository;

import ar.edu.um.ingenieria.programacion2.miPrimerSpring.model.Persona;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PersonaRepository {

    private final Map<Long, Persona> personas = new HashMap<>();

    public List<Persona> findAll() {
        return new ArrayList<>(this.personas.values());
    }

    public Optional<Persona> findByDocumento(Long documento) {
        return Optional.ofNullable(this.personas.get(documento));
    }

    public Persona add(Persona persona) {
        this.personas.put(persona.getDocumento(), persona);
        return this.personas.get(persona.getDocumento());
    }

    public Persona update(Persona nuevaPersona, Long documento) {
        Persona persona = this.findByDocumento(documento).get();
        persona.setNombre(nuevaPersona.getNombre());
        return personas.get(persona.getDocumento());
    }

    public void deleteByDocumento(Long documento) {
        this.personas.remove(documento);
    }
}
