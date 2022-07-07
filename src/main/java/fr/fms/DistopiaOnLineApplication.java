package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.CityRepository;
import fr.fms.dao.MovieRepository;
import fr.fms.dao.RoleRepository;
import fr.fms.dao.TheaterRepository;
import fr.fms.dao.UsersRepository;

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

	@Autowired
	MovieRepository movieRepository;

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
//		System.out.println( theater.getMovies(). size());
//		for (Movie movie : theater.getMovies()) {
//			System.out.println(movie);
//		}

//		List<Movie> movies = movieRepository.findAll();
//		
//		List<Theater> theaters = new ArrayList<Theater>();
//		
//		movies.forEach(movie -> {
//			System.out.println(movie.getTheaters().size());
//			
//			movie.getTheaters().forEach(th -> {
//				System.out.println(th.getId());
//			});
//		});


//		Movie movie = movieRepository.getById((long) 15);
//		System.out.println(movie.getId() + " " + movie.getTitle());
//	
//		
//		Theater theater = theaterRepository.getById((long) 1);
//		System.out.println(theater.getId() + " " + theater.getName());
//		
//		theater.getMovies().forEach(m -> {
//			System.out.println(m.getId() + " " + m.getTitle());
//		});
//		
//		theater.getMovies().add(movie);
//		theaterRepository.save(theater);
//		theater.getMovies().forEach(m -> {
//			System.out.println(m.getId() + " " + m.getTitle());
//		});
		
//		Movie movie = movieRepository.getById((long) 15);
//		
//		movie.getTheaters().forEach(th -> System.out.println(th.getName()));
		
//		movieRepository.save(new Movie(null, "Test", "dsfsdfsdfsq", "1:80"));

	}

}
