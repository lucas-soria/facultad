

/**
 * @author Lucas
 * @version 1.0
 * @created 09-Sep-2020 12:48:01
 */
public class Persona {

	protected String apellido;
	protected int edad;
	protected String nombre;
	public Domicilio aUnDomicilio;
	public Auto m_Auto;

	public Persona(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param carne
	 * @param vegetales
	 */
	public void comer(String carne, int vegetales){

	}

	public abstract int estudiar();

}