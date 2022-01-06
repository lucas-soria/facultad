package ar.edu.um.ingenieria.programacion2.service;

import ar.edu.um.ingenieria.programacion2.domain.Provincia;
import ar.edu.um.ingenieria.programacion2.repository.ProvinciaRepository;
import ar.edu.um.ingenieria.programacion2.service.dto.ProvinciaDTO;
import ar.edu.um.ingenieria.programacion2.service.mapper.ProvinciaMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Provincia}.
 */
@Service
@Transactional
public class ProvinciaService {

    private final Logger log = LoggerFactory.getLogger(ProvinciaService.class);

    private final ProvinciaRepository provinciaRepository;

    private final ProvinciaMapper provinciaMapper;

    public ProvinciaService(ProvinciaRepository provinciaRepository, ProvinciaMapper provinciaMapper) {
        this.provinciaRepository = provinciaRepository;
        this.provinciaMapper = provinciaMapper;
    }

    /**
     * Save a provincia.
     *
     * @param provinciaDTO the entity to save.
     * @return the persisted entity.
     */
    public ProvinciaDTO save(ProvinciaDTO provinciaDTO) {
        log.debug("Request to save Provincia : {}", provinciaDTO);
        Provincia provincia = provinciaMapper.toEntity(provinciaDTO);
        provincia = provinciaRepository.save(provincia);
        return provinciaMapper.toDto(provincia);
    }

    /**
     * Partially update a provincia.
     *
     * @param provinciaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProvinciaDTO> partialUpdate(ProvinciaDTO provinciaDTO) {
        log.debug("Request to partially update Provincia : {}", provinciaDTO);

        return provinciaRepository
            .findById(provinciaDTO.getId())
            .map(
                existingProvincia -> {
                    provinciaMapper.partialUpdate(existingProvincia, provinciaDTO);
                    return existingProvincia;
                }
            )
            .map(provinciaRepository::save)
            .map(provinciaMapper::toDto);
    }

    /**
     * Get all the provincias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProvinciaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Provincias");
        return provinciaRepository.findAll(pageable).map(provinciaMapper::toDto);
    }

    /**
     * Get one provincia by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProvinciaDTO> findOne(Long id) {
        log.debug("Request to get Provincia : {}", id);
        return provinciaRepository.findById(id).map(provinciaMapper::toDto);
    }

    /**
     * Delete the provincia by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Provincia : {}", id);
        provinciaRepository.deleteById(id);
    }
}
