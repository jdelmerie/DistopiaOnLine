package fr.fms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.fms.dao.CityRepository;
import fr.fms.dao.TheaterRepository;
import fr.fms.entities.City;
import fr.fms.entities.Theater;

@Service
public class IBusinessImpl implements IBusiness {

	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	TheaterRepository theaterRepository;
	
	@Override
	public List<City> getAllCities() throws Exception {
		return cityRepository.findAll();
	}

	@Override
	public Page<Theater> getTheatersPages(String search, int page) throws Exception {
		return theaterRepository.findByNameContains(search, PageRequest.of(page -1, 5));
	}

	@Override
	public Page<Theater> getTheatersPagesByCity(long cityId, int page) throws Exception {
		return theaterRepository.findByCityId(cityId, PageRequest.of(page -1, 5));
	}
	
	@Override
	public City getCity(long id)  {
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





}
