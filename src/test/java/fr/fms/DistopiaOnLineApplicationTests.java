package fr.fms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Movie;
import fr.fms.entities.Theater;

@SpringBootTest
class DistopiaOnLineApplicationTests {
	
	@Autowired
	IBusinessImpl iBusinessImpl;
	
	@Test
	public void testCheckMovieTheaters() {
		try {
			Theater theater = iBusinessImpl.getOneTheater((long) 22);
			Movie movie = iBusinessImpl.getOneMovie((long) 18);
			
			iBusinessImpl.checkMovieTheaters(theater.getId(), movie.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void contextLoads() {
	}

}
