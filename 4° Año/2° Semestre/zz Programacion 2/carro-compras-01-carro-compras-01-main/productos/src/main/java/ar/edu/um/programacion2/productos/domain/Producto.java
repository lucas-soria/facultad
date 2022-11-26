package ar.edu.um.programacion2.productos.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Producto.
 */
@Entity
@Table(name = "productos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Float precio;

    @Column(name = "cantidad_vendidos")
    private Integer cantidadVendidos;

    @Column(name = "habilitado")
    private Boolean habilitado;

    @Column(name = "imagen")
    private String imagen;

    @ManyToOne
    private Distribuidor distribuidor;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Producto id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Producto nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Producto descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return this.precio;
    }

    public Producto precio(Float precio) {
        this.setPrecio(precio);
        return this;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getCantidadVendidos() {
        return this.cantidadVendidos;
    }

    public Producto cantidadVendidos(Integer cantidadVendidos) {
        this.setCantidadVendidos(cantidadVendidos);
        return this;
    }

    public void setCantidadVendidos(Integer cantidadVendidos) {
        this.cantidadVendidos = cantidadVendidos;
    }

    public Boolean getHabilitado() {
        return this.habilitado;
    }

    public Producto habilitado(Boolean habilitado) {
        this.setHabilitado(habilitado);
        return this;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getImagen() {
        return this.imagen;
    }

    public Producto imagen(String imagen) {
        this.setImagen(imagen);
        return this;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Distribuidor getDistribuidor() {
        return this.distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public Producto distribuidor(Distribuidor distribuidor) {
        this.setDistribuidor(distribuidor);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Producto)) {
            return false;
        }
        return id != null && id.equals(((Producto) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Producto{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", precio=" + getPrecio() +
            ", cantidadVendidos=" + getCantidadVendidos() +
            ", habilitado='" + getHabilitado() + "'" +
            ", imagen='" + getImagen() + "'" +
            "}";
    }
}
