/**
 * 
 */
package ar.edu.um.programacion2.miprimerspring.exception;

/**
 * @author daniel
 *
 */
public class PersonaNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7113083411153317230L;

	public PersonaNotFoundException(Long documento) {
		super("Cannot find Persona " + documento);
	}

}
