package fr.fms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.fms.dao.CityRepository;
import fr.fms.dao.MovieRepository;
import fr.fms.dao.TheaterRepository;
import fr.fms.entities.City;
import fr.fms.entities.Movie;
import fr.fms.entities.Theater;

@Service
public class IBusinessImpl implements IBusiness {

	@Autowired
	CityRepository cityRepository;

	@Autowired
	TheaterRepository theaterRepository;

	@Autowired
	MovieRepository movieRepository;

	@Override
	public List<City> getAllCities() throws Exception {
		return cityRepository.findAll();
	}

	@Override
	public Page<Theater> getTheatersPages(String search, int page) throws Exception {
		return theaterRepository.findByNameContains(search, PageRequest.of(page - 1, 5));
	}

	@Override
	public Page<Theater> getTheatersPagesByCity(long cityId, int page) throws Exception {
		return theaterRepository.findByCityId(cityId, PageRequest.of(page - 1, 5));
	}

	@Override
	public City getCity(long id) {
		return cityRepository.getById(id);
	}

	@Override
	public void saveTheater(Theater theater) throws Exception {
		theaterRepository.save(theater);
	}

	@Override
	public Theater getOneTheater(long id) throws Exception {
		return theaterRepository.getById(id);
	}

	@Override
	public void saveCity(City city) throws Exception {
		cityRepository.save(city);
	}

	@Override
	public Page<Movie> getAllMoviesPage(int page) throws Exception {
		return movieRepository.findAll(PageRequest.of(page - 1, 8));
	}

	@Override
	public List<Movie> getAllMovies() throws Exception {
		return movieRepository.findAll();
	}

	@Override
	public Movie getOneMovie(long id) throws Exception {
		return movieRepository.getById(id);
	}

	@Override
	public List<Theater> getAllTheaters() throws Exception {
		return theaterRepository.findAll();
	}

	@Override
	public void saveMovie(Movie movie) throws Exception {
		movieRepository.save(movie);
	}

	@Override
	public void deleteMovie(long id) throws Exception {
		movieRepository.deleteById(id);
	}

//	public boolean addMovieToTheater(long movieId, long theaterId) {
//		
////		Theater theater = iBusinessImpl.getOneTheater(id);
//	
////		theater.getMovies().add(movie);
////		iBusinessImpl.saveTheater(theater);
//		
//		try {
//			Movie movie = getOneMovie(movieId);
//			Theater theater = getOneTheater(theaterId);
//			theater.getMovies().forEach(testy -> {
//				if (testy.getId() != movieId) {
//					System.out.println("on peut ajouter");
//				} else {
//					System.out.println("film déjà mis");
//				}
//			});
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return false;
//	}

}
