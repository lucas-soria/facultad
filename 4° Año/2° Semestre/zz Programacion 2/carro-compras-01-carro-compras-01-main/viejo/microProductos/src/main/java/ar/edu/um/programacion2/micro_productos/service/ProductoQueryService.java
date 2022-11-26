package ar.edu.um.programacion2.micro_productos.service;

import ar.edu.um.programacion2.micro_productos.domain.*; // for static metamodels
import ar.edu.um.programacion2.micro_productos.domain.Producto;
import ar.edu.um.programacion2.micro_productos.repository.ProductoRepository;
import ar.edu.um.programacion2.micro_productos.service.criteria.ProductoCriteria;
import ar.edu.um.programacion2.micro_productos.service.dto.ProductoDTO;
import ar.edu.um.programacion2.micro_productos.service.mapper.ProductoMapper;
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
 * Service for executing complex queries for {@link Producto} entities in the database.
 * The main input is a {@link ProductoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProductoDTO} or a {@link Page} of {@link ProductoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProductoQueryService extends QueryService<Producto> {

    private final Logger log = LoggerFactory.getLogger(ProductoQueryService.class);

    private final ProductoRepository productoRepository;

    private final ProductoMapper productoMapper;

    public ProductoQueryService(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    /**
     * Return a {@link List} of {@link ProductoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProductoDTO> findByCriteria(ProductoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Producto> specification = createSpecification(criteria);
        return productoMapper.toDto(productoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProductoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductoDTO> findByCriteria(ProductoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Producto> specification = createSpecification(criteria);
        return productoRepository.findAll(specification, page).map(productoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProductoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Producto> specification = createSpecification(criteria);
        return productoRepository.count(specification);
    }

    /**
     * Function to convert {@link ProductoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Producto> createSpecification(ProductoCriteria criteria) {
        Specification<Producto> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Producto_.id));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), Producto_.nombre));
            }
            if (criteria.getDescripcion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescripcion(), Producto_.descripcion));
            }
            if (criteria.getPrecio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrecio(), Producto_.precio));
            }
            if (criteria.getCantidadVendidos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCantidadVendidos(), Producto_.cantidadVendidos));
            }
            if (criteria.getHabilitado() != null) {
                specification = specification.and(buildSpecification(criteria.getHabilitado(), Producto_.habilitado));
            }
            if (criteria.getDistribuidorId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getDistribuidorId(),
                            root -> root.join(Producto_.distribuidor, JoinType.LEFT).get(Distribuidor_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
