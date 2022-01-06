/**
 * 
 */
package ar.edu.um.ingenieria.programacion2.generics;

/**
 * @author daniel
 *
 */
public class Gen<T extends Sumador> {

	private T ob;

	public Gen(T o) {
		this.ob = o;
	}

	public T getOb() {
		return ob;
	}

	public void mostrarTipo() {
		System.out.println("El tipo de T es: " + ob.getClass().getName());
		System.out.println("El valor es: " + ob.toString());
	}

	public void sumar1() {
		ob.sumar1();
	}

}
