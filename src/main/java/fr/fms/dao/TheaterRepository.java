package fr.fms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Long> {

	public Page<Theater> findByCityId(long cityId, Pageable pageable);

	public Page<Theater> findByNameContains(String search, Pageable pageable);

}
