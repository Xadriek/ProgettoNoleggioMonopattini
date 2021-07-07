package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.Battery;
import it.uniroma3.siw.rentalev.model.Scooter;

public interface BatteryRepository extends CrudRepository<Battery, Long>{
	
	public List<Battery> findByScooter(Scooter scooter);	

}
