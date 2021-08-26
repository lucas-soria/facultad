/**
 * 
 */
package ar.edu.um.programacion2.jpa.exception;

/**
 * @author daniel
 *
 */
public class PersonaNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 451469513733022917L;

	public PersonaNotFoundException(Long personaId) {
		super("Cannot find Persona " + personaId);
	}

	public PersonaNotFoundException(String documento) {
		super("Cannot find Persona (documento) " + documento);
	}

	public PersonaNotFoundException(Long personaId, String documento) {
		super("Cannot find Persona (personaId, documento) " + personaId + "/" + documento);
	}

}
