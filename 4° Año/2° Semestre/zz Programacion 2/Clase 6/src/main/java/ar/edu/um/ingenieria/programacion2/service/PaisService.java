package ar.edu.um.ingenieria.programacion2.service;

import ar.edu.um.ingenieria.programacion2.domain.Pais;
import ar.edu.um.ingenieria.programacion2.repository.PaisRepository;
import ar.edu.um.ingenieria.programacion2.service.dto.PaisDTO;
import ar.edu.um.ingenieria.programacion2.service.mapper.PaisMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Pais}.
 */
@Service
@Transactional
public class PaisService {

    private final Logger log = LoggerFactory.getLogger(PaisService.class);

    private final PaisRepository paisRepository;

    private final PaisMapper paisMapper;

    public PaisService(PaisRepository paisRepository, PaisMapper paisMapper) {
        this.paisRepository = paisRepository;
        this.paisMapper = paisMapper;
    }

    /**
     * Save a pais.
     *
     * @param paisDTO the entity to save.
     * @return the persisted entity.
     */
    public PaisDTO save(PaisDTO paisDTO) {
        log.debug("Request to save Pais : {}", paisDTO);
        Pais pais = paisMapper.toEntity(paisDTO);
        pais = paisRepository.save(pais);
        return paisMapper.toDto(pais);
    }

    /**
     * Partially update a pais.
     *
     * @param paisDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PaisDTO> partialUpdate(PaisDTO paisDTO) {
        log.debug("Request to partially update Pais : {}", paisDTO);

        return paisRepository
            .findById(paisDTO.getId())
            .map(
                existingPais -> {
                    paisMapper.partialUpdate(existingPais, paisDTO);
                    return existingPais;
                }
            )
            .map(paisRepository::save)
            .map(paisMapper::toDto);
    }

    /**
     * Get all the pais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PaisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pais");
        return paisRepository.findAll(pageable).map(paisMapper::toDto);
    }

    /**
     * Get one pais by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PaisDTO> findOne(Long id) {
        log.debug("Request to get Pais : {}", id);
        return paisRepository.findById(id).map(paisMapper::toDto);
    }

    /**
     * Delete the pais by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Pais : {}", id);
        paisRepository.deleteById(id);
    }
}
