package ar.edu.um.programacion2.micro_productos.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link ar.edu.um.programacion2.micro_productos.domain.Producto} entity. This class is used
 * in {@link ar.edu.um.programacion2.micro_productos.web.rest.ProductoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /productos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProductoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombre;

    private StringFilter descripcion;

    private FloatFilter precio;

    private IntegerFilter cantidadVendidos;

    private BooleanFilter habilitado;

    private LongFilter distribuidorId;

    private Boolean distinct;

    public ProductoCriteria() {}

    public ProductoCriteria(ProductoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.descripcion = other.descripcion == null ? null : other.descripcion.copy();
        this.precio = other.precio == null ? null : other.precio.copy();
        this.cantidadVendidos = other.cantidadVendidos == null ? null : other.cantidadVendidos.copy();
        this.habilitado = other.habilitado == null ? null : other.habilitado.copy();
        this.distribuidorId = other.distribuidorId == null ? null : other.distribuidorId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ProductoCriteria copy() {
        return new ProductoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNombre() {
        return nombre;
    }

    public StringFilter nombre() {
        if (nombre == null) {
            nombre = new StringFilter();
        }
        return nombre;
    }

    public void setNombre(StringFilter nombre) {
        this.nombre = nombre;
    }

    public StringFilter getDescripcion() {
        return descripcion;
    }

    public StringFilter descripcion() {
        if (descripcion == null) {
            descripcion = new StringFilter();
        }
        return descripcion;
    }

    public void setDescripcion(StringFilter descripcion) {
        this.descripcion = descripcion;
    }

    public FloatFilter getPrecio() {
        return precio;
    }

    public FloatFilter precio() {
        if (precio == null) {
            precio = new FloatFilter();
        }
        return precio;
    }

    public void setPrecio(FloatFilter precio) {
        this.precio = precio;
    }

    public IntegerFilter getCantidadVendidos() {
        return cantidadVendidos;
    }

    public IntegerFilter cantidadVendidos() {
        if (cantidadVendidos == null) {
            cantidadVendidos = new IntegerFilter();
        }
        return cantidadVendidos;
    }

    public void setCantidadVendidos(IntegerFilter cantidadVendidos) {
        this.cantidadVendidos = cantidadVendidos;
    }

    public BooleanFilter getHabilitado() {
        return habilitado;
    }

    public BooleanFilter habilitado() {
        if (habilitado == null) {
            habilitado = new BooleanFilter();
        }
        return habilitado;
    }

    public void setHabilitado(BooleanFilter habilitado) {
        this.habilitado = habilitado;
    }

    public LongFilter getDistribuidorId() {
        return distribuidorId;
    }

    public LongFilter distribuidorId() {
        if (distribuidorId == null) {
            distribuidorId = new LongFilter();
        }
        return distribuidorId;
    }

    public void setDistribuidorId(LongFilter distribuidorId) {
        this.distribuidorId = distribuidorId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProductoCriteria that = (ProductoCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(descripcion, that.descripcion) &&
            Objects.equals(precio, that.precio) &&
            Objects.equals(cantidadVendidos, that.cantidadVendidos) &&
            Objects.equals(habilitado, that.habilitado) &&
            Objects.equals(distribuidorId, that.distribuidorId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, precio, cantidadVendidos, habilitado, distribuidorId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductoCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (nombre != null ? "nombre=" + nombre + ", " : "") +
            (descripcion != null ? "descripcion=" + descripcion + ", " : "") +
            (precio != null ? "precio=" + precio + ", " : "") +
            (cantidadVendidos != null ? "cantidadVendidos=" + cantidadVendidos + ", " : "") +
            (habilitado != null ? "habilitado=" + habilitado + ", " : "") +
            (distribuidorId != null ? "distribuidorId=" + distribuidorId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
