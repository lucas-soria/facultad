/**
 * 
 */
package ar.edu.um.ingenieria.programacion2.excepciones.exception;

import java.io.IOException;

/**
 * @author daniel
 *
 */
public class SoloMenoresDeEdadException extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2275992387002821923L;
	
	public SoloMenoresDeEdadException(Integer edad) {
		super("Excepcion, no se acepta " + edad + " como edad permitida");
	}

}
