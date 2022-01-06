/**
 * 
 */
package ar.edu.um.ingenieria.programacion2.excepciones;

import ar.edu.um.ingenieria.programacion2.excepciones.exception.SoloMenoresDeEdadException;
import ar.edu.um.ingenieria.programacion2.excepciones.model.Persona;

/**
 * @author daniel
 *
 */
public class AgarradaDeLosPelos {

	public void verifyAge(Persona p) throws SoloMenoresDeEdadException {
		if (p.getEdad() > 18) {
			throw new SoloMenoresDeEdadException(p.getEdad());
		}
	}

}
