package fr.fms.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.fms.business.IBusinessImpl;
import fr.fms.entities.City;

@Controller
public class CityController {
	@Autowired
	IBusinessImpl iBusinessImpl;

	@GetMapping("/city")
	public String city(long cityId, Model model) {
		City city = iBusinessImpl.getCity(cityId);
		model.addAttribute("cityId", city.getId());
		return "redirect:/theaters?cityId=" + cityId;
	}
	
	@GetMapping("/admin/cities")
	public String cities(Model model) {
		List<City> cities;
		try {
			cities = iBusinessImpl.getAllCities();
			model.addAttribute("cities", cities);
			model.addAttribute("title", "All Dispotia's cities");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "cities";
	}

	@GetMapping("/admin/city")
	public String add(Model model) {
		model.addAttribute("title", "Add a new city");
		model.addAttribute("city", new City());
		return "formCity";
	}
	
	@GetMapping("/admin/city/edit/{id}")
	public String edit(Model model, @PathVariable(name = "id") long id) {
		
		try {
			City city = iBusinessImpl.getCity(id);
			model.addAttribute("title", "Edit this city");
			model.addAttribute("city", city);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "formCity";
	}
	
	@PostMapping("/saveCity")
	public String saveTheater(Model model, @Valid City city, BindingResult bindingResult,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "formCity";
		}
		
		try {
			iBusinessImpl.saveCity(city);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/cities";
	}
}
