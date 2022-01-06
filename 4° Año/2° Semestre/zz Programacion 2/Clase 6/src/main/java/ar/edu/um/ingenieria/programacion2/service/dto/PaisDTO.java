package ar.edu.um.ingenieria.programacion2.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ar.edu.um.ingenieria.programacion2.domain.Pais} entity.
 */
public class PaisDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String continente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaisDTO)) {
            return false;
        }

        PaisDTO paisDTO = (PaisDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paisDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaisDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", continente='" + getContinente() + "'" +
            "}";
    }
}
