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
public class Persona implements Sumador {

	private String nombre;
	private String apellido;
	private Integer edad;
	@Override
	public void sumar1() {
		this.edad++;
	}
	
}
