package fr.fms.business;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import fr.fms.entities.City;
import fr.fms.entities.Theater;

@Service
public interface IBusiness {
	public List<City> getAllCities() throws Exception;
	
	public Page<Theater> getTheatersPages(String search, int page) throws Exception;
	
	public Page<Theater> getTheatersPagesByCity(long cityId, int page) throws Exception;
	
	public City getCity(long id) ;
}
