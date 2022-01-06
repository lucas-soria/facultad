package ar.edu.um.ingenieria.programacion2;

import ar.edu.um.ingenieria.programacion2.callable.Consumidor;
import ar.edu.um.ingenieria.programacion2.callable.Productor;
import ar.edu.um.ingenieria.programacion2.producto.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {

        App app = new App();
        app.run();

    }

    public void run() {

        // Creamos una lista de productos de tipo String
        Producto<String> productosString = new Producto<>();

        // Pool de 3 hilos productores
        ExecutorService productoresExecutors = Executors.newFixedThreadPool(3);

        // Lista de los resultados futuros de los hilos
        List<Future<String>> resultadosFuture;
        resultadosFuture = new ArrayList<>();

        // Creamos los productores de Strings
        Productor<String> productorString1 = new Productor<>("Franco", "Mesa", 500L, productosString);
        Productor<String> productorString2 = new Productor<>("Alejandro", "Silla", 150L, productosString);
        Productor<String> productorString3 = new Productor<>("Ignacio", "Mesada", 300L, productosString);

        // Agregamos los resultados de los hilos a la lista de resultados futuros
        resultadosFuture.add(productoresExecutors.submit(productorString1));
        resultadosFuture.add(productoresExecutors.submit(productorString2));
        resultadosFuture.add(productoresExecutors.submit(productorString3));

        // Creamos una lista de productos de tipo Integer
        Producto<Integer> productosInteger = new Producto<>();

        // Creamos los productores de Integers
        Productor<Integer> productorInteger1 = new Productor<>("Joaquin", 1, 500L, productosInteger);
        Productor<Integer> productorInteger2 = new Productor<>("Philipp", 2, 150L, productosInteger);
        Productor<Integer> productorInteger3 = new Productor<>("Lucas", 6, 300L, productosInteger);

        // Agregamos los resultados de los hilos a la lista de resultados futuros
        resultadosFuture.add(productoresExecutors.submit(productorInteger1));
        resultadosFuture.add(productoresExecutors.submit(productorInteger2));
        resultadosFuture.add(productoresExecutors.submit(productorInteger3));

        // Pool de 3 hilos consumidores
        ExecutorService consumidoresExecutors = Executors.newFixedThreadPool(3);

        // Creamos los consumidores de Strings
        Consumidor<String> consumidorString1 = new Consumidor<>("Valentina", 50L, productosString);
        Consumidor<String> consumidorString2 = new Consumidor<>("Mariano", 200L, productosString);

        // Creamos los consumidores de Integers
        Consumidor<Integer> consumidorInteger1 = new Consumidor<>("Juan", 25L, productosInteger);
        Consumidor<Integer> consumidorInteger2 = new Consumidor<>("Agustina", 1500L, productosInteger);

        // Agregamos los resultados de los hilos a la lista de resultados futuros
        resultadosFuture.add(consumidoresExecutors.submit(consumidorString1));
        resultadosFuture.add(consumidoresExecutors.submit(consumidorString2));
        resultadosFuture.add(consumidoresExecutors.submit(consumidorInteger1));
        resultadosFuture.add(consumidoresExecutors.submit(consumidorInteger2));

        // Terminamos los executors de productores y consumidores
        productoresExecutors.shutdown();
        consumidoresExecutors.shutdown();

        // Mostramos los resultados
        for (Future<String> resultado : resultadosFuture) {
            try {
                System.out.println(resultado.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
