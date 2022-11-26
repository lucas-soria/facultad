package ar.edu.um.programacion2.micro_carro_de_compras.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProductoComprado.
 */
@Entity
@Table(name = "productos_comprados")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductoComprado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "producto")
    private Long producto;

    @Column(name = "precio")
    private Float precio;

    @Column(name = "cantidad")
    private Integer cantidad;

    @ManyToOne
    @JsonIgnoreProperties(value = { "carro", "productoComprados" }, allowSetters = true)
    private Venta venta;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProductoComprado id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProducto() {
        return this.producto;
    }

    public ProductoComprado producto(Long producto) {
        this.setProducto(producto);
        return this;
    }

    public void setProducto(Long producto) {
        this.producto = producto;
    }

    public Float getPrecio() {
        return this.precio;
    }

    public ProductoComprado precio(Float precio) {
        this.setPrecio(precio);
        return this;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public ProductoComprado cantidad(Integer cantidad) {
        this.setCantidad(cantidad);
        return this;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Venta getVenta() {
        return this.venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public ProductoComprado venta(Venta venta) {
        this.setVenta(venta);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductoComprado)) {
            return false;
        }
        return id != null && id.equals(((ProductoComprado) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductoComprado{" +
            "id=" + getId() +
            ", producto=" + getProducto() +
            ", precio=" + getPrecio() +
            ", cantidad=" + getCantidad() +
            "}";
    }
}
