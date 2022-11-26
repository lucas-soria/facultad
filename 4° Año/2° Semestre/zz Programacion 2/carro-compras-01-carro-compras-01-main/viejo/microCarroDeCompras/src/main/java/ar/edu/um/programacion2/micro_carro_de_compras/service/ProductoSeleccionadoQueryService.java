package ar.edu.um.programacion2.micro_carro_de_compras.service;

import ar.edu.um.programacion2.micro_carro_de_compras.domain.*; // for static metamodels
import ar.edu.um.programacion2.micro_carro_de_compras.domain.ProductoSeleccionado;
import ar.edu.um.programacion2.micro_carro_de_compras.repository.ProductoSeleccionadoRepository;
import ar.edu.um.programacion2.micro_carro_de_compras.service.criteria.ProductoSeleccionadoCriteria;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.ProductoSeleccionadoDTO;
import ar.edu.um.programacion2.micro_carro_de_compras.service.mapper.ProductoSeleccionadoMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link ProductoSeleccionado} entities in the database.
 * The main input is a {@link ProductoSeleccionadoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProductoSeleccionadoDTO} or a {@link Page} of {@link ProductoSeleccionadoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProductoSeleccionadoQueryService extends QueryService<ProductoSeleccionado> {

    private final Logger log = LoggerFactory.getLogger(ProductoSeleccionadoQueryService.class);

    private final ProductoSeleccionadoRepository productoSeleccionadoRepository;

    private final ProductoSeleccionadoMapper productoSeleccionadoMapper;

    public ProductoSeleccionadoQueryService(
        ProductoSeleccionadoRepository productoSeleccionadoRepository,
        ProductoSeleccionadoMapper productoSeleccionadoMapper
    ) {
        this.productoSeleccionadoRepository = productoSeleccionadoRepository;
        this.productoSeleccionadoMapper = productoSeleccionadoMapper;
    }

    /**
     * Return a {@link List} of {@link ProductoSeleccionadoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProductoSeleccionadoDTO> findByCriteria(ProductoSeleccionadoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ProductoSeleccionado> specification = createSpecification(criteria);
        return productoSeleccionadoMapper.toDto(productoSeleccionadoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProductoSeleccionadoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductoSeleccionadoDTO> findByCriteria(ProductoSeleccionadoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ProductoSeleccionado> specification = createSpecification(criteria);
        return productoSeleccionadoRepository.findAll(specification, page).map(productoSeleccionadoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProductoSeleccionadoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ProductoSeleccionado> specification = createSpecification(criteria);
        return productoSeleccionadoRepository.count(specification);
    }

    /**
     * Function to convert {@link ProductoSeleccionadoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ProductoSeleccionado> createSpecification(ProductoSeleccionadoCriteria criteria) {
        Specification<ProductoSeleccionado> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ProductoSeleccionado_.id));
            }
            if (criteria.getProducto() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProducto(), ProductoSeleccionado_.producto));
            }
            if (criteria.getPrecio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrecio(), ProductoSeleccionado_.precio));
            }
            if (criteria.getCantidad() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCantidad(), ProductoSeleccionado_.cantidad));
            }
            if (criteria.getCarroId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getCarroId(),
                            root -> root.join(ProductoSeleccionado_.carro, JoinType.LEFT).get(CarroCompra_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
