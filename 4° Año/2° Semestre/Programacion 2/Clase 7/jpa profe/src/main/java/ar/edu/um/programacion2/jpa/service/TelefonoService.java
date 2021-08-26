/**
 * 
 */
package ar.edu.um.programacion2.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.programacion2.jpa.exception.TelefonoNotFoundException;
import ar.edu.um.programacion2.jpa.model.Telefono;
import ar.edu.um.programacion2.jpa.repository.ITelefonoRepository;

/**
 * @author daniel
 *
 */
@Service
public class TelefonoService {

	@Autowired
	private ITelefonoRepository repository;

	public List<Telefono> saveAll(List<Telefono> telefonos) {
		return repository.saveAll(telefonos);
	}

	public Telefono findByTelefonoId(Long telefonoId) {
		return repository.findByTelefonoId(telefonoId).orElseThrow(() -> new TelefonoNotFoundException(telefonoId));
	}

}
