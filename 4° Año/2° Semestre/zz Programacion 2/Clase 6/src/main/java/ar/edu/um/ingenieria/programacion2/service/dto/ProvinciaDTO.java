package ar.edu.um.ingenieria.programacion2.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ar.edu.um.ingenieria.programacion2.domain.Provincia} entity.
 */
public class ProvinciaDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String codigoPostal;

    private PaisDTO pais;

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

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public PaisDTO getPais() {
        return pais;
    }

    public void setPais(PaisDTO pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProvinciaDTO)) {
            return false;
        }

        ProvinciaDTO provinciaDTO = (ProvinciaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, provinciaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProvinciaDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", codigoPostal='" + getCodigoPostal() + "'" +
            ", pais=" + getPais() +
            "}";
    }
}
