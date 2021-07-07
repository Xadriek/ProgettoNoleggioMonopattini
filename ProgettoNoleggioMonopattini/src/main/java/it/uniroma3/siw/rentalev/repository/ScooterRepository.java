package it.uniroma3.siw.rentalev.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.Rent;
import it.uniroma3.siw.rentalev.model.Scooter;

public interface ScooterRepository extends CrudRepository<Scooter, Long>{
	
	public Scooter findByRent(Rent rent);

}
