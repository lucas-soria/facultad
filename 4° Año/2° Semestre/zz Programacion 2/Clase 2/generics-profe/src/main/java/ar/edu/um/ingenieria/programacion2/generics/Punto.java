/**
 * 
 */
package ar.edu.um.ingenieria.programacion2.generics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Punto implements Sumador {

	private Double x;
	private Double y;
	
	@Override
	public void sumar1() {
		x = x + 1;
		y = y + 1;
	}

}
