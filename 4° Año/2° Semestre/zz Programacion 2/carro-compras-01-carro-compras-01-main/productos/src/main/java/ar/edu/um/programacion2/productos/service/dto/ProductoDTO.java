package ar.edu.um.programacion2.productos.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ar.edu.um.programacion2.productos.domain.Producto} entity.
 */
public class ProductoDTO implements Serializable {

    private Long id;

    private String nombre;

    private String descripcion;

    private Float precio;

    private Integer cantidadVendidos;

    private Boolean habilitado;

    private String imagen;

    private DistribuidorDTO distribuidor;

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

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getCantidadVendidos() {
        return cantidadVendidos;
    }

    public void setCantidadVendidos(Integer cantidadVendidos) {
        this.cantidadVendidos = cantidadVendidos;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public DistribuidorDTO getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(DistribuidorDTO distribuidor) {
        this.distribuidor = distribuidor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductoDTO)) {
            return false;
        }

        ProductoDTO productoDTO = (ProductoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductoDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", precio=" + getPrecio() +
            ", cantidadVendidos=" + getCantidadVendidos() +
            ", habilitado='" + getHabilitado() + "'" +
            ", imagen='" + getImagen() + "'" +
            ", distribuidor=" + getDistribuidor() +
            "}";
    }
}
