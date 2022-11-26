package ar.edu.um.programacion2.carro_de_compras.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
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

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "total")
    private Float total;

    @JsonIgnoreProperties(value = { "cliente" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private CarroCompra carro;

    @ManyToOne
    private User cliente;

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

    public User getCliente() {
        return this.cliente;
    }

    public void setCliente(User user) {
        this.cliente = user;
    }

    public Venta cliente(User user) {
        this.setCliente(user);
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
            ", fecha='" + getFecha() + "'" +
            ", total=" + getTotal() +
            "}";
    }
}
