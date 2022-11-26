package ar.edu.um.programacion2.micro_productos.service;

import ar.edu.um.programacion2.micro_productos.domain.*; // for static metamodels
import ar.edu.um.programacion2.micro_productos.domain.Distribuidor;
import ar.edu.um.programacion2.micro_productos.repository.DistribuidorRepository;
import ar.edu.um.programacion2.micro_productos.service.criteria.DistribuidorCriteria;
import ar.edu.um.programacion2.micro_productos.service.dto.DistribuidorDTO;
import ar.edu.um.programacion2.micro_productos.service.mapper.DistribuidorMapper;
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
 * Service for executing complex queries for {@link Distribuidor} entities in the database.
 * The main input is a {@link DistribuidorCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DistribuidorDTO} or a {@link Page} of {@link DistribuidorDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DistribuidorQueryService extends QueryService<Distribuidor> {

    private final Logger log = LoggerFactory.getLogger(DistribuidorQueryService.class);

    private final DistribuidorRepository distribuidorRepository;

    private final DistribuidorMapper distribuidorMapper;

    public DistribuidorQueryService(DistribuidorRepository distribuidorRepository, DistribuidorMapper distribuidorMapper) {
        this.distribuidorRepository = distribuidorRepository;
        this.distribuidorMapper = distribuidorMapper;
    }

    /**
     * Return a {@link List} of {@link DistribuidorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DistribuidorDTO> findByCriteria(DistribuidorCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Distribuidor> specification = createSpecification(criteria);
        return distribuidorMapper.toDto(distribuidorRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DistribuidorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DistribuidorDTO> findByCriteria(DistribuidorCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Distribuidor> specification = createSpecification(criteria);
        return distribuidorRepository.findAll(specification, page).map(distribuidorMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DistribuidorCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Distribuidor> specification = createSpecification(criteria);
        return distribuidorRepository.count(specification);
    }

    /**
     * Function to convert {@link DistribuidorCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Distribuidor> createSpecification(DistribuidorCriteria criteria) {
        Specification<Distribuidor> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Distribuidor_.id));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), Distribuidor_.nombre));
            }
            if (criteria.getDescripcion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescripcion(), Distribuidor_.descripcion));
            }
            if (criteria.getHabilitado() != null) {
                specification = specification.and(buildSpecification(criteria.getHabilitado(), Distribuidor_.habilitado));
            }
            if (criteria.getProductoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getProductoId(),
                            root -> root.join(Distribuidor_.productos, JoinType.LEFT).get(Producto_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
