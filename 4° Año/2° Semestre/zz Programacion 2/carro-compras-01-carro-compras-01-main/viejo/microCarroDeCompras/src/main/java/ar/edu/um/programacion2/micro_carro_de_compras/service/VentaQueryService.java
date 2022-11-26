package ar.edu.um.programacion2.micro_carro_de_compras.service;

import ar.edu.um.programacion2.micro_carro_de_compras.domain.*; // for static metamodels
import ar.edu.um.programacion2.micro_carro_de_compras.domain.Venta;
import ar.edu.um.programacion2.micro_carro_de_compras.repository.VentaRepository;
import ar.edu.um.programacion2.micro_carro_de_compras.service.criteria.VentaCriteria;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.VentaDTO;
import ar.edu.um.programacion2.micro_carro_de_compras.service.mapper.VentaMapper;
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
 * Service for executing complex queries for {@link Venta} entities in the database.
 * The main input is a {@link VentaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VentaDTO} or a {@link Page} of {@link VentaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VentaQueryService extends QueryService<Venta> {

    private final Logger log = LoggerFactory.getLogger(VentaQueryService.class);

    private final VentaRepository ventaRepository;

    private final VentaMapper ventaMapper;

    public VentaQueryService(VentaRepository ventaRepository, VentaMapper ventaMapper) {
        this.ventaRepository = ventaRepository;
        this.ventaMapper = ventaMapper;
    }

    /**
     * Return a {@link List} of {@link VentaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VentaDTO> findByCriteria(VentaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Venta> specification = createSpecification(criteria);
        return ventaMapper.toDto(ventaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VentaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VentaDTO> findByCriteria(VentaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Venta> specification = createSpecification(criteria);
        return ventaRepository.findAll(specification, page).map(ventaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VentaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Venta> specification = createSpecification(criteria);
        return ventaRepository.count(specification);
    }

    /**
     * Function to convert {@link VentaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Venta> createSpecification(VentaCriteria criteria) {
        Specification<Venta> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Venta_.id));
            }
            if (criteria.getCliente() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCliente(), Venta_.cliente));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), Venta_.fecha));
            }
            if (criteria.getTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotal(), Venta_.total));
            }
            if (criteria.getCarroId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCarroId(), root -> root.join(Venta_.carro, JoinType.LEFT).get(CarroCompra_.id))
                    );
            }
            if (criteria.getProductoCompradoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getProductoCompradoId(),
                            root -> root.join(Venta_.productoComprados, JoinType.LEFT).get(ProductoComprado_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
