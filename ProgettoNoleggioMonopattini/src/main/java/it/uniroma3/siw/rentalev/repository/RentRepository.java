package it.uniroma3.siw.rentalev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.rentalev.model.Contract;
import it.uniroma3.siw.rentalev.model.CustomerInformation;
import it.uniroma3.siw.rentalev.model.Rent;
import it.uniroma3.siw.rentalev.model.Scooter;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
	
	Optional<Rent> findByNumberPolicy(Long numberPolicy);
	
	Optional<Rent> findByScooter(Scooter scooter);
	
	List<Rent> findByOngoing(Boolean ongoing);
	
	
	List<Rent> findByContract(Contract contract);
	
	Boolean existsByContract(Contract contract);
	
	Boolean existsByScooter(Scooter scooter);
	
	Boolean existsByCustomer(CustomerInformation customer);
	
	
}
