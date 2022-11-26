package ar.edu.um.programacion2.micro_productos.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Distribuidor.
 */
@Entity
@Table(name = "distribuidores")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Distribuidor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "habilitado")
    private Boolean habilitado;

    @OneToMany(mappedBy = "distribuidor")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "distribuidor" }, allowSetters = true)
    private Set<Producto> productos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Distribuidor id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Distribuidor nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Distribuidor descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getHabilitado() {
        return this.habilitado;
    }

    public Distribuidor habilitado(Boolean habilitado) {
        this.setHabilitado(habilitado);
        return this;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Set<Producto> getProductos() {
        return this.productos;
    }

    public void setProductos(Set<Producto> productos) {
        if (this.productos != null) {
            this.productos.forEach(i -> i.setDistribuidor(null));
        }
        if (productos != null) {
            productos.forEach(i -> i.setDistribuidor(this));
        }
        this.productos = productos;
    }

    public Distribuidor productos(Set<Producto> productos) {
        this.setProductos(productos);
        return this;
    }

    public Distribuidor addProducto(Producto producto) {
        this.productos.add(producto);
        producto.setDistribuidor(this);
        return this;
    }

    public Distribuidor removeProducto(Producto producto) {
        this.productos.remove(producto);
        producto.setDistribuidor(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Distribuidor)) {
            return false;
        }
        return id != null && id.equals(((Distribuidor) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Distribuidor{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", habilitado='" + getHabilitado() + "'" +
            "}";
    }
}
