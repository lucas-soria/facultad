package ar.edu.um.ingenieria.programacion2.jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Persona {

    @Id
    private Long id;

}
