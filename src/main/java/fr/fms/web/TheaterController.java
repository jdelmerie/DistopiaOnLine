package fr.fms.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.City;
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
			model.addAttribute("menuName", "cities");
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

	@GetMapping("/city")
	public String city(long cityId, Model model) {
		City city = iBusinessImpl.getCity(cityId);
		model.addAttribute("cityId", city.getId());
		return "redirect:/theaters?cityId=" + cityId;
	}
}
