package ar.edu.um.ingenieria.programacion2.generics;

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

		Gen<Punto> punto = new Gen<Punto>(new Punto(2d, 3d));
		punto.mostrarTipo();
		punto.sumar1();
		punto.mostrarTipo();
		
		Gen<Persona> persona = new Gen<Persona>(new Persona("Juan", "Perez", 10));
		persona.mostrarTipo();
		persona.sumar1();
		persona.mostrarTipo();

//		Gen<Integer> entero = new Gen<Integer>(30);
//		entero.mostrarTipo();
//		
//		Gen<BigDecimal> value = new Gen<BigDecimal>(BigDecimal.ONE);
//		value.mostrarTipo();
//		
//		Gen<String> cadena = new Gen<String>("Hola mundo");
//		cadena.mostrarTipo();
//		
//		Gen<Punto> punto = new Gen<Punto>(new Punto(2d, 3d));
//		punto.mostrarTipo();
	}
}
