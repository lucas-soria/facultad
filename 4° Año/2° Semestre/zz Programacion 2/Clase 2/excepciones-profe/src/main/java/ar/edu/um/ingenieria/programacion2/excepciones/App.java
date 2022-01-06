package ar.edu.um.ingenieria.programacion2.excepciones;

import java.util.ArrayList;
import java.util.List;

import ar.edu.um.ingenieria.programacion2.excepciones.exception.SoloMenoresDeEdadException;
import ar.edu.um.ingenieria.programacion2.excepciones.model.Persona;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		App app = new App();
		app.run();
	}

	private void run() {
		this.molestar();
	}

	private void molestar() {
		List<Persona> personas = new ArrayList<Persona>();
		personas.add(new Persona("Jorge Luis", "Borges", "261-4242424", 70));

		try {
			for (Persona p : personas) {
				new AgarradaDeLosPelos().verifyAge(p);
				System.out.println(p);
			}
		} catch (SoloMenoresDeEdadException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

}
