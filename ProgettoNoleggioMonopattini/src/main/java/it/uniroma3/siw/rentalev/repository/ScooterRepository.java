package it.uniroma3.siw.rentalev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.rentalev.model.Battery;
import it.uniroma3.siw.rentalev.model.Rent;
import it.uniroma3.siw.rentalev.model.Scooter;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long>{
	
	Scooter findByRent(Rent rent);
	
	Scooter findByBattery(Battery battery);
	
	Boolean existsByBattery(Battery battery);
	
	Boolean existsByRent(Rent rent);

}
