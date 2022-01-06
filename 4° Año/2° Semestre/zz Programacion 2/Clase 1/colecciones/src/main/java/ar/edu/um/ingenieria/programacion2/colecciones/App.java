package ar.edu.um.ingenieria.programacion2.colecciones;

import java.util.ArrayList;
import java.util.List;

import ar.edu.um.ingenieria.programacion2.colecciones.interfaces.IFigura;
import ar.edu.um.ingenieria.programacion2.colecciones.model.Cuadrado;
import ar.edu.um.ingenieria.programacion2.colecciones.model.Triangulo;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {
    	
    	List<IFigura> figuras = new ArrayList<IFigura>();
        
        figuras.add(new Cuadrado());
        figuras.add(new Triangulo());
        
        for (IFigura figura : figuras) {
        	System.out.println(figuras.toString());
        	figura.muestraPuntos();
        }
    }
}
