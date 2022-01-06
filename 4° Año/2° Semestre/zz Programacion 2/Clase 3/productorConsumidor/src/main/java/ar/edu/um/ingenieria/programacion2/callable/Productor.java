package ar.edu.um.ingenieria.programacion2.callable;

import ar.edu.um.ingenieria.programacion2.producto.Producto;

import java.util.concurrent.Callable;

public class Productor<T> implements Callable<String> {

    final private String nombre;
    final private T producto;
    final private Long delay;
    final private Producto<T> listaProductos;

    public Productor(String nombre, T producto, Long delay, Producto<T> listaProductos) {
        this.nombre = nombre;
        this.producto = producto;
        this.delay = delay;
        this.listaProductos = listaProductos;
    }

    public String call() throws InterruptedException {
        System.out.println("\u001B[31m" + this.nombre + " esta empezando a producir: " + this.producto + "\u001B[0m");
        Thread.sleep(this.delay);
        this.listaProductos.agregar(this.producto, this.nombre);
        return "\u001B[31m" + this.nombre + " termino el producto: " + this.producto + "\u001B[0m";
    }

}
