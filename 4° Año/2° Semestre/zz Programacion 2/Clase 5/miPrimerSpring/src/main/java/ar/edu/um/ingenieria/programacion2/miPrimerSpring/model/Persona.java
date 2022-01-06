package ar.edu.um.ingenieria.programacion2.miPrimerSpring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    private Long documento;
    private String nombre;

}
