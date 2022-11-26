package ar.edu.um.programacion2.micro_carro_de_compras.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link ar.edu.um.programacion2.micro_carro_de_compras.domain.Venta} entity. This class is used
 * in {@link ar.edu.um.programacion2.micro_carro_de_compras.web.rest.VentaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ventas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VentaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter cliente;

    private LocalDateFilter fecha;

    private FloatFilter total;

    private LongFilter carroId;

    private LongFilter productoCompradoId;

    private Boolean distinct;

    public VentaCriteria() {}

    public VentaCriteria(VentaCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.cliente = other.cliente == null ? null : other.cliente.copy();
        this.fecha = other.fecha == null ? null : other.fecha.copy();
        this.total = other.total == null ? null : other.total.copy();
        this.carroId = other.carroId == null ? null : other.carroId.copy();
        this.productoCompradoId = other.productoCompradoId == null ? null : other.productoCompradoId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public VentaCriteria copy() {
        return new VentaCriteria(this);
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

    public StringFilter getCliente() {
        return cliente;
    }

    public StringFilter cliente() {
        if (cliente == null) {
            cliente = new StringFilter();
        }
        return cliente;
    }

    public void setCliente(StringFilter cliente) {
        this.cliente = cliente;
    }

    public LocalDateFilter getFecha() {
        return fecha;
    }

    public LocalDateFilter fecha() {
        if (fecha == null) {
            fecha = new LocalDateFilter();
        }
        return fecha;
    }

    public void setFecha(LocalDateFilter fecha) {
        this.fecha = fecha;
    }

    public FloatFilter getTotal() {
        return total;
    }

    public FloatFilter total() {
        if (total == null) {
            total = new FloatFilter();
        }
        return total;
    }

    public void setTotal(FloatFilter total) {
        this.total = total;
    }

    public LongFilter getCarroId() {
        return carroId;
    }

    public LongFilter carroId() {
        if (carroId == null) {
            carroId = new LongFilter();
        }
        return carroId;
    }

    public void setCarroId(LongFilter carroId) {
        this.carroId = carroId;
    }

    public LongFilter getProductoCompradoId() {
        return productoCompradoId;
    }

    public LongFilter productoCompradoId() {
        if (productoCompradoId == null) {
            productoCompradoId = new LongFilter();
        }
        return productoCompradoId;
    }

    public void setProductoCompradoId(LongFilter productoCompradoId) {
        this.productoCompradoId = productoCompradoId;
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
        final VentaCriteria that = (VentaCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(cliente, that.cliente) &&
            Objects.equals(fecha, that.fecha) &&
            Objects.equals(total, that.total) &&
            Objects.equals(carroId, that.carroId) &&
            Objects.equals(productoCompradoId, that.productoCompradoId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, fecha, total, carroId, productoCompradoId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VentaCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (cliente != null ? "cliente=" + cliente + ", " : "") +
            (fecha != null ? "fecha=" + fecha + ", " : "") +
            (total != null ? "total=" + total + ", " : "") +
            (carroId != null ? "carroId=" + carroId + ", " : "") +
            (productoCompradoId != null ? "productoCompradoId=" + productoCompradoId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
