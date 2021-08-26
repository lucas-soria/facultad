package ar.edu.um.programacion2.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.um.programacion2.jpa.model.Persona;
import ar.edu.um.programacion2.jpa.model.Telefono;
import ar.edu.um.programacion2.jpa.service.PersonaService;
import ar.edu.um.programacion2.jpa.service.TelefonoService;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class JpaApplication implements CommandLineRunner {

	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private TelefonoService telefonoService;

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Persona> personas = new ArrayList<Persona>();
		personas.add(new Persona(null, "22222222", "Juan", "Guzman"));
		personas.add(new Persona(null, "33333333", "Sergio", "Massa"));
		personas = personaService.saveAll(personas);
		log.info("Personas -> {}", personas);
		List<Telefono> telefonos = new ArrayList<Telefono>();
		telefonos.add(new Telefono(null, "Movil", "2616666666", personas.get(1)));
		telefonos.add(new Telefono(null, "Personal", "02614234567", personas.get(1)));
		telefonos = telefonoService.saveAll(telefonos);
		log.info("Telefonos -> {}", telefonos);
	}

}
