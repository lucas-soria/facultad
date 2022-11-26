package ar.edu.um.programacion2.productos.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ar.edu.um.programacion2.productos.domain.Distribuidor} entity.
 */
public class DistribuidorDTO implements Serializable {

    private Long id;

    private String nombre;

    private String descripcion;

    private Boolean habilitado;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DistribuidorDTO)) {
            return false;
        }

        DistribuidorDTO distribuidorDTO = (DistribuidorDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, distribuidorDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DistribuidorDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", habilitado='" + getHabilitado() + "'" +
            "}";
    }
}
