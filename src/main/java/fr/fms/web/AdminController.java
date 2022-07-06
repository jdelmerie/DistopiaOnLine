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
public class AdminController {

	@Autowired
	IBusinessImpl iBusinessImpl;

	@GetMapping("/admin")
	public String admin(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "search", defaultValue = "") String search) {
		Page<Theater> theaters = null;
		try {
			theaters = iBusinessImpl.getTheatersPages(search, page);
			model.addAttribute("title", "All Dispotia's theaters");

			List<City> cities = iBusinessImpl.getAllCities();
			model.addAttribute("cities", cities);
			model.addAttribute("theaters", theaters);
			model.addAttribute("pages", new int[theaters.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("search", search);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "admin";
	}

//	@GetMapping("/admin/cities")
//	public String cities(Model model) {
//		List<City> cities;
//		try {
//			cities = iBusinessImpl.getAllCities();
//			model.addAttribute("cities", cities);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "cities";
//	}

}
