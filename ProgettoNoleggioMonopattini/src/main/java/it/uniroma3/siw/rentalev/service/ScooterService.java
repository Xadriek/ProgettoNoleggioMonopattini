package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


import it.uniroma3.siw.rentalev.model.Rent;
import it.uniroma3.siw.rentalev.model.Scooter;

import it.uniroma3.siw.rentalev.repository.ScooterRepository;

public class ScooterService {
	
	@Autowired
	protected ScooterRepository scooterRepository;

	@Transactional
	public Scooter getScooter(Long id) {
		Optional<Scooter> result = this.scooterRepository.findById(id);
		return result.orElse(null);
	}
	@Transactional
	public Scooter getScooterByRent(Rent rent) {
		Scooter result =   this.scooterRepository.findByRent(rent);
		return result;
	}
	@Transactional
	public Scooter saveScooter(Scooter scooter) {
		return this.scooterRepository.save(scooter);
	}
	@Transactional
	public List<Scooter> getAllScooters() {
		List<Scooter> result = new ArrayList<>();
		Iterable<Scooter> iterable = this.scooterRepository.findAll();
		for(Scooter scooter : iterable)
			result.add(scooter);
		return result;
	}

	@Transactional
	public boolean deletedScooter(Long id) {
		try {
			this.scooterRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
