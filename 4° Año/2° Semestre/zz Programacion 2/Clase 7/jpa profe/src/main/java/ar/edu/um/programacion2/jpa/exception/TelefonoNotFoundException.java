/**
 * 
 */
package ar.edu.um.programacion2.jpa.exception;

/**
 * @author daniel
 *
 */
public class TelefonoNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7191314444551603662L;

	public TelefonoNotFoundException(Long telefonoId) {
		super("Cannot find Telefono " + telefonoId);
	}

}
