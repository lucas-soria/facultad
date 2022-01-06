package ar.edu.um.ingenieria.programacion2.producto;

import java.util.ArrayList;
import java.util.List;

public class Producto<T> {

    final private List<T> productos;

    public Producto() {
        this.productos = new ArrayList<>();
    }

    public synchronized void agregar(T producto, String hilo) {
        System.out.println("\u001B[31m" + hilo + " esta agregando producto: " + producto + "\u001B[0m");
        this.productos.add(producto);
        Integer cantidad = this.cantidad();
        System.out.println("Quedan " + cantidad + " elementos en: " + System.identityHashCode(this));
        this.notify();
    }

    public synchronized T sacarUltimo(String hilo) {
        while (this.cantidad() == 0) {
            System.out.println("\u001B[32m" + hilo + " esta esperando para consumir de: " + System.identityHashCode(this) + "\u001B[0m");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T ultimo = this.productos.remove(this.productos.size() - 1);
        Integer cantidad = this.cantidad();
        System.out.println("\u001B[32m" + hilo + "esta quitando producto: " + ultimo + "\u001B[0m");
        System.out.println("Quedan " + cantidad + " elementos en: " + System.identityHashCode(this));
        return ultimo;
    }

    public synchronized Integer cantidad(){
        return this.productos.size();
    }

}
