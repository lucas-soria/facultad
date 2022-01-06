/**
 * 
 */
package ar.edu.um.ingenieria.programacion2.excepciones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lucas
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
	private String nombre;
	private String apellido;
	private String telefono;
	private Integer edad;
}
