package fr.fms.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Movie;
import fr.fms.entities.Theater;

@Controller
public class MovieController {
	@Autowired
	IBusinessImpl iBusinessImpl;

	@GetMapping("/movies")
	public String admin(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
		Page<Movie> movies = null;
		try {
			movies = iBusinessImpl.getAllMoviesPage(page);
			model.addAttribute("title", "All Dispotia's movies");
			model.addAttribute("movies", movies);
			model.addAttribute("pages", new int[movies.getTotalPages()]);
			model.addAttribute("currentPage", page);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "movies";
	}

	@GetMapping("/admin/movies")
	public String adminMovies(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
		Page<Movie> movies = null;

		try {
			movies = iBusinessImpl.getAllMoviesPage(page);
			model.addAttribute("title", "All Dispotia's movies");
			model.addAttribute("pages", new int[movies.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("movies", movies.getContent());
		} catch (Exception e) {
			System.out.println(e);
		}
		return "moviesAdmin";
	}

	@GetMapping("/admin/movie/addTheater/{id}")
	public String addTheater(Model model, @PathVariable(name = "id") long id) {
		List<Theater> theaters = null;
		try {
			Movie movie = iBusinessImpl.getOneMovie(id);
			theaters = iBusinessImpl.getAllTheaters();
			model.addAttribute("title", "Add to theater");
			model.addAttribute("theaters", theaters);
			model.addAttribute("movie", movie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addToTheater";
	}

	@PostMapping("/addMovieToTheaters")
	public String addMovieToTheaters(@RequestParam(name = "movieId", defaultValue = "0") long movieId,
			@RequestParam(name = "theaterId", defaultValue = "0") long theaterId, Model model) {
		try {
			Theater theater = iBusinessImpl.getOneTheater(theaterId);
			Movie movie = iBusinessImpl.getOneMovie(movieId);
			movie.getTheaters().add(theater);
			theater.getMovies().add(movie);
			iBusinessImpl.saveMovie(movie);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/movies";
	}

	@GetMapping("/admin/movie/add")
	public String addMovie(Model model) {
		model.addAttribute("title", "Add a new movie");
		model.addAttribute("movie", new Movie());
		return "formMovie";
	}

	@GetMapping("/admin/movie/edit/{id}")
	public String editMovie(Model model, @PathVariable(name = "id") long id) {

		try {
			model.addAttribute("title", "Edit this movie");
			model.addAttribute("movie", iBusinessImpl.getOneMovie(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "formMovie";
	}

	@PostMapping("/saveMovie")
	public String saveMovie(Model model, @Valid Movie movie, BindingResult bindingResult,
			RedirectAttributes attributes) {

		try {
			if (bindingResult.hasErrors()) {
				return "formMovie";
			}
			iBusinessImpl.saveMovie(movie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/movies";
	}

	@GetMapping("/admin/movie/delete")
	public String delete(Long id, int page, Model model, RedirectAttributes redirectAttrs) {
		try {
			iBusinessImpl.deleteMovie(id);
		} catch (Exception e) {
			redirectAttrs.addAttribute("error",e.getMessage());
		}	
		return "redirect:/admin/movies?page=" + page;
	}
}
