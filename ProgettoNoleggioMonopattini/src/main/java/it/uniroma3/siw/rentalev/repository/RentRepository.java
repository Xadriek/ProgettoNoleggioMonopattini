package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.Contract;

import it.uniroma3.siw.rentalev.model.Rent;
import it.uniroma3.siw.rentalev.model.Scooter;

public interface RentRepository extends CrudRepository<Rent, Long> {
	
	public Rent findByNumberPolicy(Long numberPolicy);
	
	public Rent findByScooter(Scooter scooter);
	
	public List<Rent> findByOngoing(Boolean ongoing);
	
	
	public List<Rent> findByContract(Contract contract);
}
