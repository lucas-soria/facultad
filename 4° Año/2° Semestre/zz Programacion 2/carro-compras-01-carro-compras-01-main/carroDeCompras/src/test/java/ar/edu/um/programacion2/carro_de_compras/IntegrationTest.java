package ar.edu.um.programacion2.carro_de_compras;

import ar.edu.um.programacion2.carro_de_compras.CarroDeComprasApp;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = CarroDeComprasApp.class)
public @interface IntegrationTest {
}
