package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.CityRepository;
import fr.fms.dao.RoleRepository;
import fr.fms.entities.City;
import fr.fms.entities.Role;

@SpringBootApplication
public class DistopiaOnLineApplication implements CommandLineRunner {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(DistopiaOnLineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		cityRepository.save(new City("Paris", "75000"));
//		cityRepository.save(new City("Toulouse", "31000"));
//		cityRepository.save(new City("Lyon", "69000"));
//		cityRepository.save(new City("Bordeaux", "33000"));
//		
//		roleRepository.save(new Role("ADMIN"));
//		roleRepository.save(new Role("USER"));
		
	}

}
