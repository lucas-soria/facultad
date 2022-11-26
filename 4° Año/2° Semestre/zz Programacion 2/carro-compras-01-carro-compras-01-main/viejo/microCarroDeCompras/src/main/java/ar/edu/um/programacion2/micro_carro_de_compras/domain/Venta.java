package ar.edu.um.programacion2.micro_carro_de_compras.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Venta.
 */
@Entity
@Table(name = "ventas")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cliente")
    private String cliente;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "total")
    private Float total;

    @JsonIgnoreProperties(value = { "productoSeleccionados" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private CarroCompra carro;

    @OneToMany(mappedBy = "venta")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "venta" }, allowSetters = true)
    private Set<ProductoComprado> productoComprados = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Venta id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return this.cliente;
    }

    public Venta cliente(String cliente) {
        this.setCliente(cliente);
        return this;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public Venta fecha(LocalDate fecha) {
        this.setFecha(fecha);
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Float getTotal() {
        return this.total;
    }

    public Venta total(Float total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public CarroCompra getCarro() {
        return this.carro;
    }

    public void setCarro(CarroCompra carroCompra) {
        this.carro = carroCompra;
    }

    public Venta carro(CarroCompra carroCompra) {
        this.setCarro(carroCompra);
        return this;
    }

    public Set<ProductoComprado> getProductoComprados() {
        return this.productoComprados;
    }

    public void setProductoComprados(Set<ProductoComprado> productoComprados) {
        if (this.productoComprados != null) {
            this.productoComprados.forEach(i -> i.setVenta(null));
        }
        if (productoComprados != null) {
            productoComprados.forEach(i -> i.setVenta(this));
        }
        this.productoComprados = productoComprados;
    }

    public Venta productoComprados(Set<ProductoComprado> productoComprados) {
        this.setProductoComprados(productoComprados);
        return this;
    }

    public Venta addProductoComprado(ProductoComprado productoComprado) {
        this.productoComprados.add(productoComprado);
        productoComprado.setVenta(this);
        return this;
    }

    public Venta removeProductoComprado(ProductoComprado productoComprado) {
        this.productoComprados.remove(productoComprado);
        productoComprado.setVenta(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Venta)) {
            return false;
        }
        return id != null && id.equals(((Venta) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Venta{" +
            "id=" + getId() +
            ", cliente='" + getCliente() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", total=" + getTotal() +
            "}";
    }
}
