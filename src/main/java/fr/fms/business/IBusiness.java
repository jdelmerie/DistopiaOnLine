package fr.fms.business;

import java.util.List;

import org.hibernate.query.criteria.LiteralHandlingMode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import fr.fms.entities.City;
import fr.fms.entities.Movie;
import fr.fms.entities.Theater;

@Service
public interface IBusiness {
	
	/**
	 * List of all theaters
	 * @return
	 * @throws Exception
	 */
	public List<Theater> getAllTheaters() throws Exception;
	
	/**
	 * List of all the cities
	 * @return
	 * @throws Exception
	 */
	public List<City> getAllCities() throws Exception;
	
	/**
	 * List of all theaters by page and search
	 * @param search
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page<Theater> getTheatersPages(String search, int page) throws Exception;

	/**
	 * Page of theaters by cities
	 * @param cityId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page<Theater> getTheatersPagesByCity(long cityId, int page) throws Exception;

	/**
	 * Page of all movies
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page<Movie> getAllMoviesPage(int page) throws Exception;

	/**
	 * List of all movies
	 * @return
	 * @throws Exception
	 */
	public List<Movie> getAllMovies() throws Exception;

	/**
	 * Return a city by id
	 * @param id
	 * @return
	 */
	public City getCity(long id);

	
	/**
	 * Save a theater
	 * @param theater
	 * @throws Exception
	 */
	public void saveTheater(Theater theater) throws Exception;

	/**
	 * Save a movie
	 * @param movie
	 * @throws Exception
	 */
	public void saveMovie(Movie movie) throws Exception;
	
	/**
	 * Save a city
	 * @param city
	 * @throws Exception
	 */
	public void saveCity(City city) throws Exception;

	/**
	 * Return a theater by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Theater getOneTheater(long id) throws Exception;

	/**
	 * Return a movie by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Movie getOneMovie(long id) throws Exception;
	
	/**
	 * Delete a movie
	 * @param id
	 * @throws Exception
	 */
	public void deleteMovie(long id) throws Exception;
}
