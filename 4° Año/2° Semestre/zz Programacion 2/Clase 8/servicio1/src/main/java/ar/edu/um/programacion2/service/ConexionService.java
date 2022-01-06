package ar.edu.um.programacion2.service;

import ar.edu.um.programacion2.config.ConexionConfiguracion;
import ar.edu.um.programacion2.domain.Provincia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ConexionService {

    private final Logger log = LoggerFactory.getLogger(ConexionService.class);

    private final ConexionConfiguracion conexionConfiguracion = new ConexionConfiguracion();

    public String prueba() {
        return "Prueba";
    }

    // Consumir un servicio "externo"
    public List<Provincia> obtenerProvincias(){
        String url = this.conexionConfiguracion.getUrl();
        String token = this.conexionConfiguracion.getToken();
        MediaType mediaType = MediaType.APPLICATION_JSON;


        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(mediaType);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Provincia[]> resultado = null;
        try {
            resultado = template.exchange(url, HttpMethod.GET, entity, Provincia[].class);
        } catch (Exception ex) {
            log.debug(ex.getMessage());
        }
        List<Provincia> provincias = Arrays.asList(resultado.getBody());
        return provincias;
    }

}
