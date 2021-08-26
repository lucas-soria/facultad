package org.example;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Gen<String> algo = new Gen<>("Hola");
        System.out.println(algo.getObject());
        algo.add("Chau");
        System.out.println(algo.getObject());
    }
}
