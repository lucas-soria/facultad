package ar.edu.um.programacion2.micro_productos;

import ar.edu.um.programacion2.micro_productos.MicroProductosApp;
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
@SpringBootTest(classes = MicroProductosApp.class)
public @interface IntegrationTest {
}
