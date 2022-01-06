/**
 * 
 */
package ar.edu.um.ingenieria.programacion2.colecciones.model;

import java.util.ArrayList;
import java.util.List;

import ar.edu.um.ingenieria.programacion2.colecciones.interfaces.IFigura;
import lombok.Data;

/**
 * @author daniel
 *
 */
@Data
public class Cuadrado implements IFigura {

	private List<Punto> vertices = new ArrayList<Punto>();

	public Cuadrado() {
		vertices.add(new Punto(10, 10));
		vertices.add(new Punto(20, 10));
		vertices.add(new Punto(20, 20));
		vertices.add(new Punto(20, 10));
	}

	@Override
	public void muestraPuntos() {
		for (Punto punto : vertices) {
			System.out.println(punto.toString());
		}
	}

}
