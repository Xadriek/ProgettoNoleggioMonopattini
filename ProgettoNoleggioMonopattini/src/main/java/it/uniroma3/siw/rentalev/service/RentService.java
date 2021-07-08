package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.rentalev.model.Contract;


import it.uniroma3.siw.rentalev.model.Rent;
import it.uniroma3.siw.rentalev.model.Scooter;

import it.uniroma3.siw.rentalev.repository.RentRepository;

public class RentService {
	
	@Autowired
	protected RentRepository rentRepository;

	@Transactional
	public Rent getRent(Long id) {
		Optional<Rent> result = this.rentRepository.findById(id);
		return result.orElse(null);
	}
	@Transactional
	public Rent saveRent(Rent rent) {
		return this.rentRepository.save(rent);
	}
	@Transactional
	public List<Rent> getAllRental() {
		List<Rent> result = new ArrayList<>();
		Iterable<Rent> iterable = this.rentRepository.findAll();
		for(Rent rent : iterable)
			result.add(rent);
		return result;
	}

	@Transactional
	public boolean deletedRent(Long id) {
		try {
			this.rentRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	@Transactional
	public Rent getRentByNumerPolicy(Long numberPolicy) {
		Rent result =   this.rentRepository.findByNumberPolicy(numberPolicy);
		return result;
	}
	@Transactional
	public Rent getRentByScooter(Scooter scooter) {
		Rent result =   this.rentRepository.findByScooter(scooter);
		return result;
	}

	@Transactional
	public List<Rent> getRentByOngoing(Boolean ongoing) {
		List<Rent> result =   this.rentRepository.findByOngoing(ongoing);
		return result;
	}
	@Transactional
	public List<Rent> getRentByContract(Contract contract) {
		List<Rent> result =   this.rentRepository.findByContract(contract);
		return result;
	}
	
}
