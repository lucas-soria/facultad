package ar.edu.um.programacion2.micro_carro_de_compras.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CarroCompra.
 */
@Entity
@Table(name = "carros_compras")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CarroCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cliente")
    private String cliente;

    @Column(name = "total")
    private Float total;

    @Column(name = "finalizado")
    private Boolean finalizado;

    @Column(name = "vendido")
    private Boolean vendido;

    @OneToMany(mappedBy = "carro")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "carro" }, allowSetters = true)
    private Set<ProductoSeleccionado> productoSeleccionados = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CarroCompra id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return this.cliente;
    }

    public CarroCompra cliente(String cliente) {
        this.setCliente(cliente);
        return this;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Float getTotal() {
        return this.total;
    }

    public CarroCompra total(Float total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Boolean getFinalizado() {
        return this.finalizado;
    }

    public CarroCompra finalizado(Boolean finalizado) {
        this.setFinalizado(finalizado);
        return this;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Boolean getVendido() {
        return this.vendido;
    }

    public CarroCompra vendido(Boolean vendido) {
        this.setVendido(vendido);
        return this;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }

    public Set<ProductoSeleccionado> getProductoSeleccionados() {
        return this.productoSeleccionados;
    }

    public void setProductoSeleccionados(Set<ProductoSeleccionado> productoSeleccionados) {
        if (this.productoSeleccionados != null) {
            this.productoSeleccionados.forEach(i -> i.setCarro(null));
        }
        if (productoSeleccionados != null) {
            productoSeleccionados.forEach(i -> i.setCarro(this));
        }
        this.productoSeleccionados = productoSeleccionados;
    }

    public CarroCompra productoSeleccionados(Set<ProductoSeleccionado> productoSeleccionados) {
        this.setProductoSeleccionados(productoSeleccionados);
        return this;
    }

    public CarroCompra addProductoSeleccionado(ProductoSeleccionado productoSeleccionado) {
        this.productoSeleccionados.add(productoSeleccionado);
        productoSeleccionado.setCarro(this);
        return this;
    }

    public CarroCompra removeProductoSeleccionado(ProductoSeleccionado productoSeleccionado) {
        this.productoSeleccionados.remove(productoSeleccionado);
        productoSeleccionado.setCarro(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarroCompra)) {
            return false;
        }
        return id != null && id.equals(((CarroCompra) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarroCompra{" +
            "id=" + getId() +
            ", cliente='" + getCliente() + "'" +
            ", total=" + getTotal() +
            ", finalizado='" + getFinalizado() + "'" +
            ", vendido='" + getVendido() + "'" +
            "}";
    }
}
