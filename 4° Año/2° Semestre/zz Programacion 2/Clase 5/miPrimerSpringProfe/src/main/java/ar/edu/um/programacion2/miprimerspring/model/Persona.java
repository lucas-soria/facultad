/**
 * 
 */
package ar.edu.um.programacion2.miprimerspring.model;

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
public class Persona {

	private Long documento;
	private String nombre;

}
