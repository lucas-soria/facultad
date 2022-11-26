package ar.edu.um.programacion2.carro_de_compras.service;

import ar.edu.um.programacion2.carro_de_compras.config.MicroProductoConfiguration;
import ar.edu.um.programacion2.carro_de_compras.domain.AuthToken;
import ar.edu.um.programacion2.carro_de_compras.domain.AuthUser;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ConexionService {

    private String token;

    @Resource
    MicroProductoConfiguration microProductoConfiguration;

    /**
     * GET a List of an entity from Productos REST API
     * Example of use:
     *      List<ProductoDTO> productoDTOList = conexionService.sendHttpRequestForList("/productos/", new ParameterizedTypeReference<List<ProductoDTO>>() {});
     * @param url Producto's API endpoint
     * @param clazz the class of the list of entities
     * @param <T> the class of the entity
     * @return List<T>
     */
    public <T> List<T> sendHttpRequestForList(String url, ParameterizedTypeReference<List<T>> clazz) {
        url = microProductoConfiguration.getBaseURL() + url;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(this.token);
        HttpEntity<String> requestDataHttpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<T>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestDataHttpEntity, clazz);
        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return responseEntity.getBody();
        }

    /**
     * GET an entity from Productos REST API
     * Example of use:
     *      ProductoDTO productoDTO = conexionService.sendHttpRequestForEntity("/productos/" + idProducto.toString(), ProductoDTO.class);
     * @param url Producto's API endpoint
     * @param clazz the class of the entity
     * @param <T> the class of the entity
     * @return T
     */
    public <T> T sendHttpRequestForEntity(String url, Class<T> clazz) {
        url = microProductoConfiguration.getBaseURL() + url;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(this.token);
        HttpEntity<String> requestDataHttpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestDataHttpEntity, clazz);
        if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return responseEntity.getBody();
    }

    /**
     * PATCH an entity from Productos REST API
     * Example of use:
     *
     * @param url Producto's API endpoint
     * @param entity JSON with the variables you want to modify
     * @param clazz the class of the entity
     * @param <T> the class of the entity
     * @return T
     */
    public <T> T sendHttpRequestToPatchEntity(String url, String entity,Class<T> clazz) {
        url = microProductoConfiguration.getBaseURL() + url;
        RestTemplate restTemplate = new RestTemplate();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate.setRequestFactory(requestFactory);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(this.token);
        HttpEntity<String> requestDataHttpEntity = new HttpEntity<>(entity, httpHeaders);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, requestDataHttpEntity, clazz);
        return responseEntity.getBody();
    }

    /**
     * Gets the JWT in order to communicate with Productos microservice
     */
    public void getJWT() {
        String urlAuthentication = microProductoConfiguration.getBaseURL() + "/authenticate";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        AuthUser authUser = new AuthUser(microProductoConfiguration.getUsername(), microProductoConfiguration.getPassword(), false);
        HttpEntity<AuthUser> authUserHttpEntity = new HttpEntity<>(authUser, httpHeaders);
        ResponseEntity<AuthToken> authTokenResponseEntity = restTemplate.postForEntity(urlAuthentication, authUserHttpEntity, AuthToken.class);
        if(authTokenResponseEntity.getStatusCode() == HttpStatus.OK) {
            this.token = authTokenResponseEntity.getBody().getId_token();
        }
    }

}
