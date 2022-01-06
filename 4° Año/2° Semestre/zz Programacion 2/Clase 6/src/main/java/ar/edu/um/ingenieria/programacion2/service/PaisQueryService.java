package ar.edu.um.ingenieria.programacion2.service;

import ar.edu.um.ingenieria.programacion2.domain.*; // for static metamodels
import ar.edu.um.ingenieria.programacion2.domain.Pais;
import ar.edu.um.ingenieria.programacion2.repository.PaisRepository;
import ar.edu.um.ingenieria.programacion2.service.criteria.PaisCriteria;
import ar.edu.um.ingenieria.programacion2.service.dto.PaisDTO;
import ar.edu.um.ingenieria.programacion2.service.mapper.PaisMapper;
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
 * Service for executing complex queries for {@link Pais} entities in the database.
 * The main input is a {@link PaisCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PaisDTO} or a {@link Page} of {@link PaisDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PaisQueryService extends QueryService<Pais> {

    private final Logger log = LoggerFactory.getLogger(PaisQueryService.class);

    private final PaisRepository paisRepository;

    private final PaisMapper paisMapper;

    public PaisQueryService(PaisRepository paisRepository, PaisMapper paisMapper) {
        this.paisRepository = paisRepository;
        this.paisMapper = paisMapper;
    }

    /**
     * Return a {@link List} of {@link PaisDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PaisDTO> findByCriteria(PaisCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Pais> specification = createSpecification(criteria);
        return paisMapper.toDto(paisRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PaisDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PaisDTO> findByCriteria(PaisCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Pais> specification = createSpecification(criteria);
        return paisRepository.findAll(specification, page).map(paisMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PaisCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Pais> specification = createSpecification(criteria);
        return paisRepository.count(specification);
    }

    /**
     * Function to convert {@link PaisCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Pais> createSpecification(PaisCriteria criteria) {
        Specification<Pais> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Pais_.id));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), Pais_.nombre));
            }
            if (criteria.getContinente() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContinente(), Pais_.continente));
            }
        }
        return specification;
    }
}
