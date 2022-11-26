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
 * Criteria class for the {@link ar.edu.um.programacion2.micro_carro_de_compras.domain.CarroCompra} entity. This class is used
 * in {@link ar.edu.um.programacion2.micro_carro_de_compras.web.rest.CarroCompraResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /carro-compras?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CarroCompraCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter cliente;

    private FloatFilter total;

    private BooleanFilter finalizado;

    private BooleanFilter vendido;

    private LongFilter productoSeleccionadoId;

    private Boolean distinct;

    public CarroCompraCriteria() {}

    public CarroCompraCriteria(CarroCompraCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.cliente = other.cliente == null ? null : other.cliente.copy();
        this.total = other.total == null ? null : other.total.copy();
        this.finalizado = other.finalizado == null ? null : other.finalizado.copy();
        this.vendido = other.vendido == null ? null : other.vendido.copy();
        this.productoSeleccionadoId = other.productoSeleccionadoId == null ? null : other.productoSeleccionadoId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CarroCompraCriteria copy() {
        return new CarroCompraCriteria(this);
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

    public BooleanFilter getFinalizado() {
        return finalizado;
    }

    public BooleanFilter finalizado() {
        if (finalizado == null) {
            finalizado = new BooleanFilter();
        }
        return finalizado;
    }

    public void setFinalizado(BooleanFilter finalizado) {
        this.finalizado = finalizado;
    }

    public BooleanFilter getVendido() {
        return vendido;
    }

    public BooleanFilter vendido() {
        if (vendido == null) {
            vendido = new BooleanFilter();
        }
        return vendido;
    }

    public void setVendido(BooleanFilter vendido) {
        this.vendido = vendido;
    }

    public LongFilter getProductoSeleccionadoId() {
        return productoSeleccionadoId;
    }

    public LongFilter productoSeleccionadoId() {
        if (productoSeleccionadoId == null) {
            productoSeleccionadoId = new LongFilter();
        }
        return productoSeleccionadoId;
    }

    public void setProductoSeleccionadoId(LongFilter productoSeleccionadoId) {
        this.productoSeleccionadoId = productoSeleccionadoId;
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
        final CarroCompraCriteria that = (CarroCompraCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(cliente, that.cliente) &&
            Objects.equals(total, that.total) &&
            Objects.equals(finalizado, that.finalizado) &&
            Objects.equals(vendido, that.vendido) &&
            Objects.equals(productoSeleccionadoId, that.productoSeleccionadoId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, total, finalizado, vendido, productoSeleccionadoId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarroCompraCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (cliente != null ? "cliente=" + cliente + ", " : "") +
            (total != null ? "total=" + total + ", " : "") +
            (finalizado != null ? "finalizado=" + finalizado + ", " : "") +
            (vendido != null ? "vendido=" + vendido + ", " : "") +
            (productoSeleccionadoId != null ? "productoSeleccionadoId=" + productoSeleccionadoId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
