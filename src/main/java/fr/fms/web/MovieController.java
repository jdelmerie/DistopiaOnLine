package fr.fms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.Movie;

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
}
