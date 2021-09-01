package it.uniroma3.siw.rentalev.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import it.uniroma3.siw.rentalev.model.Battery;
import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.model.Scooter;


@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long>{

	Battery findByScooter(Scooter scooter);
	
	List<Battery> findByHub(Hub hub);
	
		

}
