package ar.edu.um.programacion2.service.impl;

import ar.edu.um.programacion2.domain.Provincia;
import ar.edu.um.programacion2.repository.ProvinciaRepository;
import ar.edu.um.programacion2.service.ProvinciaService;
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
public class ProvinciaServiceImpl implements ProvinciaService {

    private final Logger log = LoggerFactory.getLogger(ProvinciaServiceImpl.class);

    private final ProvinciaRepository provinciaRepository;

    public ProvinciaServiceImpl(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    @Override
    public Provincia save(Provincia provincia) {
        log.debug("Request to save Provincia : {}", provincia);
        return provinciaRepository.save(provincia);
    }

    @Override
    public Optional<Provincia> partialUpdate(Provincia provincia) {
        log.debug("Request to partially update Provincia : {}", provincia);

        return provinciaRepository
            .findById(provincia.getId())
            .map(
                existingProvincia -> {
                    if (provincia.getNombre() != null) {
                        existingProvincia.setNombre(provincia.getNombre());
                    }
                    if (provincia.getCodigoPostal() != null) {
                        existingProvincia.setCodigoPostal(provincia.getCodigoPostal());
                    }

                    return existingProvincia;
                }
            )
            .map(provinciaRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Provincia> findAll(Pageable pageable) {
        log.debug("Request to get all Provincias");
        return provinciaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Provincia> findOne(Long id) {
        log.debug("Request to get Provincia : {}", id);
        return provinciaRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Provincia : {}", id);
        provinciaRepository.deleteById(id);
    }
}
