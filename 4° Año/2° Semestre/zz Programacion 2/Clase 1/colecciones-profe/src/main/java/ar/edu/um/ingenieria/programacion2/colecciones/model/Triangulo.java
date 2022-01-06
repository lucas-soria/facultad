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
public class Triangulo implements IFigura {

	private List<Punto> vertices = new ArrayList<Punto>();

	public Triangulo() {
		vertices.add(new Punto(10, 10));
		vertices.add(new Punto(20, 10));
		vertices.add(new Punto(15, 0));
	}

	@Override
	public void muestraPuntos() {
		for (Punto punto : vertices) {
			System.out.println(punto.toString());
		}
	}

}
