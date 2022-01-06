package ar.edu.um.ingenieria.programacion2.service.criteria;

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
 * Criteria class for the {@link ar.edu.um.ingenieria.programacion2.domain.Pais} entity. This class is used
 * in {@link ar.edu.um.ingenieria.programacion2.web.rest.PaisResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pais?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PaisCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombre;

    private StringFilter continente;

    public PaisCriteria() {}

    public PaisCriteria(PaisCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.continente = other.continente == null ? null : other.continente.copy();
    }

    @Override
    public PaisCriteria copy() {
        return new PaisCriteria(this);
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

    public StringFilter getContinente() {
        return continente;
    }

    public StringFilter continente() {
        if (continente == null) {
            continente = new StringFilter();
        }
        return continente;
    }

    public void setContinente(StringFilter continente) {
        this.continente = continente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PaisCriteria that = (PaisCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(continente, that.continente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, continente);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaisCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (nombre != null ? "nombre=" + nombre + ", " : "") +
            (continente != null ? "continente=" + continente + ", " : "") +
            "}";
    }
}
