package ar.edu.um.programacion2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConexionConfiguracion {

    @Value("${parametros.endpoint.token}")
    protected String token;

    @Value("${parametros.endpoint.url}")
    protected String url;

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

}
