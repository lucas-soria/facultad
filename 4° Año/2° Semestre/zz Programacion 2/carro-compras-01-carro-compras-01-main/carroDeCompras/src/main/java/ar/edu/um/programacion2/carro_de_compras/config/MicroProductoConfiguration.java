package ar.edu.um.programacion2.carro_de_compras.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@Data
public class MicroProductoConfiguration {

    @Value("${propiedades.microproductos.url}")
    private String baseURL;

    @Value("${propiedades.microproductos.username}")
    private String username;

    @Value("${propiedades.microproductos.password}")
    private String password;

}
