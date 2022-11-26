package ar.edu.um.programacion2.carro_de_compras.domain;

import java.io.Serializable;
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

    @Column(name = "total")
    private Float total;

    @Column(name = "finalizado")
    private Boolean finalizado;

    @Column(name = "vendido")
    private Boolean vendido;

    @ManyToOne
    private User cliente;

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

    public User getCliente() {
        return this.cliente;
    }

    public void setCliente(User user) {
        this.cliente = user;
    }

    public CarroCompra cliente(User user) {
        this.setCliente(user);
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
            ", total=" + getTotal() +
            ", finalizado='" + getFinalizado() + "'" +
            ", vendido='" + getVendido() + "'" +
            "}";
    }
}
