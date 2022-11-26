package ar.edu.um.programacion2.micro_carro_de_compras.service.criteria;

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
 * Criteria class for the {@link ar.edu.um.programacion2.micro_carro_de_compras.domain.ProductoComprado} entity. This class is used
 * in {@link ar.edu.um.programacion2.micro_carro_de_compras.web.rest.ProductoCompradoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /producto-comprados?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProductoCompradoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter producto;

    private FloatFilter precio;

    private IntegerFilter cantidad;

    private LongFilter ventaId;

    private Boolean distinct;

    public ProductoCompradoCriteria() {}

    public ProductoCompradoCriteria(ProductoCompradoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.producto = other.producto == null ? null : other.producto.copy();
        this.precio = other.precio == null ? null : other.precio.copy();
        this.cantidad = other.cantidad == null ? null : other.cantidad.copy();
        this.ventaId = other.ventaId == null ? null : other.ventaId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ProductoCompradoCriteria copy() {
        return new ProductoCompradoCriteria(this);
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

    public LongFilter getProducto() {
        return producto;
    }

    public LongFilter producto() {
        if (producto == null) {
            producto = new LongFilter();
        }
        return producto;
    }

    public void setProducto(LongFilter producto) {
        this.producto = producto;
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

    public IntegerFilter getCantidad() {
        return cantidad;
    }

    public IntegerFilter cantidad() {
        if (cantidad == null) {
            cantidad = new IntegerFilter();
        }
        return cantidad;
    }

    public void setCantidad(IntegerFilter cantidad) {
        this.cantidad = cantidad;
    }

    public LongFilter getVentaId() {
        return ventaId;
    }

    public LongFilter ventaId() {
        if (ventaId == null) {
            ventaId = new LongFilter();
        }
        return ventaId;
    }

    public void setVentaId(LongFilter ventaId) {
        this.ventaId = ventaId;
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
        final ProductoCompradoCriteria that = (ProductoCompradoCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(producto, that.producto) &&
            Objects.equals(precio, that.precio) &&
            Objects.equals(cantidad, that.cantidad) &&
            Objects.equals(ventaId, that.ventaId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, producto, precio, cantidad, ventaId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductoCompradoCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (producto != null ? "producto=" + producto + ", " : "") +
            (precio != null ? "precio=" + precio + ", " : "") +
            (cantidad != null ? "cantidad=" + cantidad + ", " : "") +
            (ventaId != null ? "ventaId=" + ventaId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
