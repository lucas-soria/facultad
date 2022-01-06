package ar.edu.um.ingenieria.programacion2.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Pais.
 */
@Entity
@Table(name = "pais")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "continente", nullable = false)
    private String continente;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pais id(Long id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Pais nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContinente() {
        return this.continente;
    }

    public Pais continente(String continente) {
        this.continente = continente;
        return this;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pais)) {
            return false;
        }
        return id != null && id.equals(((Pais) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pais{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", continente='" + getContinente() + "'" +
            "}";
    }
}
