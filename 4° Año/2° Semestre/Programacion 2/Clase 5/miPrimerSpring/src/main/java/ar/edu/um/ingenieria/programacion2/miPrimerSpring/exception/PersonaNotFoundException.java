package ar.edu.um.ingenieria.programacion2.miPrimerSpring.exception;

public class PersonaNotFoundException extends RuntimeException{

    public PersonaNotFoundException(Long documento) {
        super("Can not find persona: " + documento);
    }

}
