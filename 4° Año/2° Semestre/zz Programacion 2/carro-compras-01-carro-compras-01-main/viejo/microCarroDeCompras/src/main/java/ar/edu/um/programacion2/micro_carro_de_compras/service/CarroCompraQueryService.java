package ar.edu.um.programacion2.micro_carro_de_compras.service;

import ar.edu.um.programacion2.micro_carro_de_compras.domain.*; // for static metamodels
import ar.edu.um.programacion2.micro_carro_de_compras.domain.CarroCompra;
import ar.edu.um.programacion2.micro_carro_de_compras.repository.CarroCompraRepository;
import ar.edu.um.programacion2.micro_carro_de_compras.service.criteria.CarroCompraCriteria;
import ar.edu.um.programacion2.micro_carro_de_compras.service.dto.CarroCompraDTO;
import ar.edu.um.programacion2.micro_carro_de_compras.service.mapper.CarroCompraMapper;
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
 * Service for executing complex queries for {@link CarroCompra} entities in the database.
 * The main input is a {@link CarroCompraCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CarroCompraDTO} or a {@link Page} of {@link CarroCompraDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CarroCompraQueryService extends QueryService<CarroCompra> {

    private final Logger log = LoggerFactory.getLogger(CarroCompraQueryService.class);

    private final CarroCompraRepository carroCompraRepository;

    private final CarroCompraMapper carroCompraMapper;

    public CarroCompraQueryService(CarroCompraRepository carroCompraRepository, CarroCompraMapper carroCompraMapper) {
        this.carroCompraRepository = carroCompraRepository;
        this.carroCompraMapper = carroCompraMapper;
    }

    /**
     * Return a {@link List} of {@link CarroCompraDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CarroCompraDTO> findByCriteria(CarroCompraCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CarroCompra> specification = createSpecification(criteria);
        return carroCompraMapper.toDto(carroCompraRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CarroCompraDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CarroCompraDTO> findByCriteria(CarroCompraCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CarroCompra> specification = createSpecification(criteria);
        return carroCompraRepository.findAll(specification, page).map(carroCompraMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CarroCompraCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CarroCompra> specification = createSpecification(criteria);
        return carroCompraRepository.count(specification);
    }

    /**
     * Function to convert {@link CarroCompraCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CarroCompra> createSpecification(CarroCompraCriteria criteria) {
        Specification<CarroCompra> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CarroCompra_.id));
            }
            if (criteria.getCliente() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCliente(), CarroCompra_.cliente));
            }
            if (criteria.getTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotal(), CarroCompra_.total));
            }
            if (criteria.getFinalizado() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalizado(), CarroCompra_.finalizado));
            }
            if (criteria.getVendido() != null) {
                specification = specification.and(buildSpecification(criteria.getVendido(), CarroCompra_.vendido));
            }
            if (criteria.getProductoSeleccionadoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getProductoSeleccionadoId(),
                            root -> root.join(CarroCompra_.productoSeleccionados, JoinType.LEFT).get(ProductoSeleccionado_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
