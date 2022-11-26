package ar.edu.um.programacion2.micro_carro_de_compras.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProductoSeleccionado.
 */
@Entity
@Table(name = "productos_seleccionados")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductoSeleccionado implements Serializable {

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
    @JsonIgnoreProperties(value = { "productoSeleccionados" }, allowSetters = true)
    private CarroCompra carro;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProductoSeleccionado id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProducto() {
        return this.producto;
    }

    public ProductoSeleccionado producto(Long producto) {
        this.setProducto(producto);
        return this;
    }

    public void setProducto(Long producto) {
        this.producto = producto;
    }

    public Float getPrecio() {
        return this.precio;
    }

    public ProductoSeleccionado precio(Float precio) {
        this.setPrecio(precio);
        return this;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public ProductoSeleccionado cantidad(Integer cantidad) {
        this.setCantidad(cantidad);
        return this;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public CarroCompra getCarro() {
        return this.carro;
    }

    public void setCarro(CarroCompra carroCompra) {
        this.carro = carroCompra;
    }

    public ProductoSeleccionado carro(CarroCompra carroCompra) {
        this.setCarro(carroCompra);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductoSeleccionado)) {
            return false;
        }
        return id != null && id.equals(((ProductoSeleccionado) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductoSeleccionado{" +
            "id=" + getId() +
            ", producto=" + getProducto() +
            ", precio=" + getPrecio() +
            ", cantidad=" + getCantidad() +
            "}";
    }
}
