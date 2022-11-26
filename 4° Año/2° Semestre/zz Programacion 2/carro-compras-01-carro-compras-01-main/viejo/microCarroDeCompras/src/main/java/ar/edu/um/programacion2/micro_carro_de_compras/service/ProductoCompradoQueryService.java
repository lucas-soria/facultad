package ar.edu.um.programacion2.micro_carro_de_compras.service;

import ar.edu.um.programacion2.micro_carro_de_compras.domain.*; // for static metamodels
import ar.edu.um.programacion2.micro_carro_de_compras.domain.ProductoComprado;
import ar.edu.um.programacion2.micro_carro_de_compras.repository.ProductoCompradoRepository;
import ar.edu.um.programacion2.micro_carro_de_compras.service.criteria.ProductoCompradoCriteria;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.ProductoCompradoDTO;
import ar.edu.um.programacion2.micro_carro_de_compras.service.mapper.ProductoCompradoMapper;
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
 * Service for executing complex queries for {@link ProductoComprado} entities in the database.
 * The main input is a {@link ProductoCompradoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProductoCompradoDTO} or a {@link Page} of {@link ProductoCompradoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProductoCompradoQueryService extends QueryService<ProductoComprado> {

    private final Logger log = LoggerFactory.getLogger(ProductoCompradoQueryService.class);

    private final ProductoCompradoRepository productoCompradoRepository;

    private final ProductoCompradoMapper productoCompradoMapper;

    public ProductoCompradoQueryService(
        ProductoCompradoRepository productoCompradoRepository,
        ProductoCompradoMapper productoCompradoMapper
    ) {
        this.productoCompradoRepository = productoCompradoRepository;
        this.productoCompradoMapper = productoCompradoMapper;
    }

    /**
     * Return a {@link List} of {@link ProductoCompradoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProductoCompradoDTO> findByCriteria(ProductoCompradoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ProductoComprado> specification = createSpecification(criteria);
        return productoCompradoMapper.toDto(productoCompradoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProductoCompradoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductoCompradoDTO> findByCriteria(ProductoCompradoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ProductoComprado> specification = createSpecification(criteria);
        return productoCompradoRepository.findAll(specification, page).map(productoCompradoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProductoCompradoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ProductoComprado> specification = createSpecification(criteria);
        return productoCompradoRepository.count(specification);
    }

    /**
     * Function to convert {@link ProductoCompradoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ProductoComprado> createSpecification(ProductoCompradoCriteria criteria) {
        Specification<ProductoComprado> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ProductoComprado_.id));
            }
            if (criteria.getProducto() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProducto(), ProductoComprado_.producto));
            }
            if (criteria.getPrecio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrecio(), ProductoComprado_.precio));
            }
            if (criteria.getCantidad() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCantidad(), ProductoComprado_.cantidad));
            }
            if (criteria.getVentaId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getVentaId(), root -> root.join(ProductoComprado_.venta, JoinType.LEFT).get(Venta_.id))
                    );
            }
        }
        return specification;
    }
}
