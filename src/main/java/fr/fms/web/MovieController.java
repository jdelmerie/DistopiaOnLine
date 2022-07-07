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
	public String adminMovies(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "search", defaultValue = "") String search) {
		List<Movie> movies = null;
		Page<Theater> theaters = null;

		try {
			theaters = iBusinessImpl.getTheatersPages(search, page);
			movies = iBusinessImpl.getAllMovies();
			model.addAttribute("title", "All Dispotia's movies");
			model.addAttribute("theaters", theaters);
			model.addAttribute("pages", new int[theaters.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("movies", movies);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "moviesAdmin";
	}

	@GetMapping("/admin/movie/edit/{id}")
	public String edit(Model model, @PathVariable(name = "id") long id) {
		List<Movie> movies = null;
		try {
			Theater theater = iBusinessImpl.getOneTheater(id);
			movies = iBusinessImpl.getAllMovies();
			model.addAttribute("title", "Movies");
			model.addAttribute("theater", theater);
			model.addAttribute("movies", movies);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "formMovie";
	}

	@PostMapping("/addMovieToTheaters")
	public String addMovieToTheaters(@RequestParam(name = "movie", defaultValue = "0") long movieId,
			@RequestParam(name = "id", defaultValue = "0") long id, Model model, @Valid Theater theater,
			BindingResult bindingResult, RedirectAttributes attributes) {

		try {
			if (bindingResult.hasErrors()) {
				return "redirect:/admin/movie/edit/" + id;
			}

			Movie movieToAdd = iBusinessImpl.getOneMovie(movieId);
			theater.getMovies().add(movieToAdd);
			movieToAdd.getTheaters().add(theater);
			iBusinessImpl.saveTheater(theater);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/admin/movie/edit/" + theater.getId();
	}
}
