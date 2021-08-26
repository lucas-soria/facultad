/**
 * 
 */
package ar.edu.um.programacion2.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Telefono {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long telefonoId;
	
	@NotNull
	@Size(max = 50)
	private String tipo = "";
	
	@NotNull
	private String numero = "";
	
	@JoinColumn(name = "persona_id")
	@ManyToOne
	private Persona persona;
}
