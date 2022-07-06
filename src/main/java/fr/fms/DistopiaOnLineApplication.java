package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.CityRepository;
import fr.fms.dao.RoleRepository;
import fr.fms.dao.TheaterRepository;
import fr.fms.dao.UsersRepository;
import fr.fms.entities.Movie;
import fr.fms.entities.Theater;
import fr.fms.entities.Users;

@SpringBootApplication
public class DistopiaOnLineApplication implements CommandLineRunner {

	@Autowired
	CityRepository cityRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	TheaterRepository theaterRepository;

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

//		Users user = usersRepository.findById((long) 1).get();
//		user.getRoles().forEach(r -> System.out.println(r));
//
//		Theater theater = theaterRepository.findById((long) 10).get();
//		System.out.println( theater.getMovies().size());
//		for (Movie movie : theater.getMovies()) {
//			System.out.println(movie);
//		}

	}

}
