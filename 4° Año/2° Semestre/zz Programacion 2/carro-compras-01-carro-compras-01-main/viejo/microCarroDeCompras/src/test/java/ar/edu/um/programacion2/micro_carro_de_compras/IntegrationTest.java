package ar.edu.um.programacion2.micro_carro_de_compras;

import ar.edu.um.programacion2.micro_carro_de_compras.MicroCarroDeComprasApp;
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
@SpringBootTest(classes = MicroCarroDeComprasApp.class)
public @interface IntegrationTest {
}
