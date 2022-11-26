package ar.edu.um.programacion2.carro_de_compras.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ar.edu.um.programacion2.carro_de_compras.domain.ProductoSeleccionado} entity.
 */
public class ProductoSeleccionadoDTO implements Serializable {

    private Long id;

    private Long producto;

    private Float precio;

    private Integer cantidad;

    private CarroCompraDTO carro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProducto() {
        return producto;
    }

    public void setProducto(Long producto) {
        this.producto = producto;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public CarroCompraDTO getCarro() {
        return carro;
    }

    public void setCarro(CarroCompraDTO carro) {
        this.carro = carro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductoSeleccionadoDTO)) {
            return false;
        }

        ProductoSeleccionadoDTO productoSeleccionadoDTO = (ProductoSeleccionadoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productoSeleccionadoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductoSeleccionadoDTO{" +
            "id=" + getId() +
            ", producto=" + getProducto() +
            ", precio=" + getPrecio() +
            ", cantidad=" + getCantidad() +
            ", carro=" + getCarro() +
            "}";
    }
}
