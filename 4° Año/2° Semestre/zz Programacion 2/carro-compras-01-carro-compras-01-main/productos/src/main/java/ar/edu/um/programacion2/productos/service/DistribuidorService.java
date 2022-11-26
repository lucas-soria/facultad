package ar.edu.um.programacion2.productos.service;

import ar.edu.um.programacion2.productos.domain.Distribuidor;
import ar.edu.um.programacion2.productos.repository.DistribuidorRepository;
import ar.edu.um.programacion2.productos.service.dto.DistribuidorDTO;
import ar.edu.um.programacion2.productos.service.dto.ProductoDTO;
import ar.edu.um.programacion2.productos.service.mapper.DistribuidorMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Distribuidor}.
 */
@Service
@Transactional
public class DistribuidorService {

    private final Logger log = LoggerFactory.getLogger(DistribuidorService.class);

    private final DistribuidorRepository distribuidorRepository;

    private final DistribuidorMapper distribuidorMapper;

    public DistribuidorService(DistribuidorRepository distribuidorRepository, DistribuidorMapper distribuidorMapper) {
        this.distribuidorRepository = distribuidorRepository;
        this.distribuidorMapper = distribuidorMapper;
    }

    /**
     * Save a distribuidor.
     *
     * @param distribuidorDTO the entity to save.
     * @return the persisted entity.
     */
    public DistribuidorDTO save(DistribuidorDTO distribuidorDTO) {
        log.debug("Request to save Distribuidor : {}", distribuidorDTO);
        Distribuidor distribuidor = distribuidorMapper.toEntity(distribuidorDTO);
        distribuidor = distribuidorRepository.save(distribuidor);
        return distribuidorMapper.toDto(distribuidor);
    }

    /**
     * Partially update a distribuidor.
     *
     * @param distribuidorDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DistribuidorDTO> partialUpdate(DistribuidorDTO distribuidorDTO) {
        log.debug("Request to partially update Distribuidor : {}", distribuidorDTO);

        return distribuidorRepository
            .findById(distribuidorDTO.getId())
            .map(existingDistribuidor -> {
                distribuidorMapper.partialUpdate(existingDistribuidor, distribuidorDTO);

                return existingDistribuidor;
            })
            .map(distribuidorRepository::save)
            .map(distribuidorMapper::toDto);
    }

    /**
     * Get all the distribuidors.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DistribuidorDTO> findAll() {
        log.debug("Request to get all Distribuidors");
        return distribuidorRepository.findAll().stream().map(distribuidorMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one distribuidor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DistribuidorDTO> findOne(Long id) {
        log.debug("Request to get Distribuidor : {}", id);
        return distribuidorRepository.findById(id).map(distribuidorMapper::toDto);
    }

    /**
     * Delete the distribuidor by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Distribuidor : {}", id);
        DistribuidorDTO distribuidorDTO = distribuidorRepository.findById(id).map(distribuidorMapper::toDto).get();
        if (distribuidorDTO.getHabilitado()) {
            distribuidorDTO.setHabilitado(false);
            this.partialUpdate(distribuidorDTO);
        } else {
            distribuidorRepository.deleteById(id);
        }
    }


// ------------------------------------------------------------------------------------------------


    /**
     * set default values before saving a distribuidor.
     *
     * @param distribuidorDTO the entity to save.
     * @return the persisted entity.
     */
    public DistribuidorDTO preSave(DistribuidorDTO distribuidorDTO) {
        log.debug("Setting defaults of distribuidor : {}", distribuidorDTO);
        if (distribuidorDTO.getHabilitado() == null) {
            distribuidorDTO.setHabilitado(true);
        }
        return this.save(distribuidorDTO);
    }


}
