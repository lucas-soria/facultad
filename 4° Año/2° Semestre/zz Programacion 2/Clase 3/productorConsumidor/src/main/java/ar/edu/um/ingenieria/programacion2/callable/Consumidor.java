package ar.edu.um.ingenieria.programacion2.callable;

import ar.edu.um.ingenieria.programacion2.producto.Producto;

import java.util.concurrent.Callable;

public class Consumidor<T> implements  Callable<String> {

    final private String nombre;
    final private Long delay;
    final private Producto<T> listaProductos;

    public Consumidor(String nombre, Long delay, Producto<T> listaProductos) {
        this.nombre = nombre;
        this.delay = delay;
        this.listaProductos = listaProductos;
    }

    public String call() throws InterruptedException {
        System.out.println("\u001B[32m" + this.nombre + " esta empezando a consumir de: " + System.identityHashCode(this.listaProductos) + "\u001B[0m");
        Thread.sleep(this.delay);
        T ultimo = this.listaProductos.sacarUltimo(this.nombre);
        return "\u001B[32m" + this.nombre + " consume el producto: " + ultimo + "\u001B[0m";
    }

}
