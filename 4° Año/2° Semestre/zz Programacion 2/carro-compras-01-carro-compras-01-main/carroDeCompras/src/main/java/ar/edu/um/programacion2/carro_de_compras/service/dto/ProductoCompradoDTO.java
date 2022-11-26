package ar.edu.um.programacion2.carro_de_compras.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ar.edu.um.programacion2.carro_de_compras.domain.ProductoComprado} entity.
 */
public class ProductoCompradoDTO implements Serializable {

    private Long id;

    private Long producto;

    private Float precio;

    private Integer cantidad;

    private VentaDTO venta;

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

    public VentaDTO getVenta() {
        return venta;
    }

    public void setVenta(VentaDTO venta) {
        this.venta = venta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductoCompradoDTO)) {
            return false;
        }

        ProductoCompradoDTO productoCompradoDTO = (ProductoCompradoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productoCompradoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductoCompradoDTO{" +
            "id=" + getId() +
            ", producto=" + getProducto() +
            ", precio=" + getPrecio() +
            ", cantidad=" + getCantidad() +
            ", venta=" + getVenta() +
            "}";
    }
}
