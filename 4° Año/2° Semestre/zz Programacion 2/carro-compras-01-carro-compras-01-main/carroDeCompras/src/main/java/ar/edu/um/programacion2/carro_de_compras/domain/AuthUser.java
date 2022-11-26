package ar.edu.um.programacion2.carro_de_compras.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {

    private String username;

    private String password;

    private Boolean rememberMe;

}
