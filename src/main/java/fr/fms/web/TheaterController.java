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
import fr.fms.entities.City;
import fr.fms.entities.Movie;
import fr.fms.entities.Theater;

@Controller
public class TheaterController {

	@Autowired
	IBusinessImpl iBusinessImpl;

	@GetMapping("/theaters")
	public String cities(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "search", defaultValue = "") String search,
			@RequestParam(name = "cityId", defaultValue = "0") long cityId) {

		Page<Theater> theaters = null;

		try {
			if (cityId > 0) {
				theaters = iBusinessImpl.getTheatersPagesByCity(cityId, page);
				model.addAttribute("title", "Theaters in " + iBusinessImpl.getCity(cityId).getName());
			} else {
				theaters = iBusinessImpl.getTheatersPages(search, page);
				model.addAttribute("title", "All Dispotia's theaters");
			}

			List<City> cities = iBusinessImpl.getAllCities();
			model.addAttribute("cities", cities);
			model.addAttribute("theaters", theaters);
			model.addAttribute("pages", new int[theaters.getTotalPages()]);
			model.addAttribute("cityId", cityId);
			model.addAttribute("currentPage", page);
			model.addAttribute("search", search);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "theaters";
	}

	@PostMapping("/saveTheater")
	public String saveTheater(Model model, @Valid Theater theater, BindingResult bindingResult,
			RedirectAttributes attributes) {

		List<City> cities;
		try {

			if (bindingResult.hasErrors()) {
				cities = iBusinessImpl.getAllCities();
				model.addAttribute("cities", cities);
				return "formTheater";
			}
			iBusinessImpl.saveTheater(theater);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin";
	}

	@GetMapping("/admin/theater")
	public String theater(Model model) {
		List<City> cities;
		try {
			cities = iBusinessImpl.getAllCities();
			model.addAttribute("title", "Add a new theater");
			model.addAttribute("cities", cities);
			model.addAttribute("theater", new Theater());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "formTheater";
	}

	@GetMapping("/admin/theater/edit/{id}")
	public String edit(Model model, @PathVariable(name = "id") long id) {

		try {
			List<City> cities = iBusinessImpl.getAllCities();
			Theater theater = iBusinessImpl.getOneTheater(id);
			model.addAttribute("title", "Edit this theater");
			model.addAttribute("theater", theater);
			model.addAttribute("cities", cities);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "formTheater";
	}

	@GetMapping("/theater")
	public String theater(@RequestParam(name = "theaterId", defaultValue = "0") long theaterId,
			@RequestParam(name = "page", defaultValue = "1") int page, Model model) {

		Theater theater = null;
		List<Movie> movies = null;

		try {
			theater = iBusinessImpl.getOneTheater(theaterId);
			movies = (List<Movie>) theater.getMovies();
			model.addAttribute("movies", movies);
			model.addAttribute("title", "Movies available in " + theater.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "moviesTheater";
	}
}
