package ar.edu.um.ingenieria.programacion2;

import ar.edu.um.ingenieria.programacion2.Servicio1App;
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
@SpringBootTest(classes = Servicio1App.class)
public @interface IntegrationTest {
}
