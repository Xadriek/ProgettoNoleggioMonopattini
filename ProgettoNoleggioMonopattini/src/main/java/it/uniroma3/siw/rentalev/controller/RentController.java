package it.uniroma3.siw.rentalev.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.rentalev.model.Rent;
import it.uniroma3.siw.rentalev.repository.BatteryRepository;
import it.uniroma3.siw.rentalev.repository.RentRepository;
import it.uniroma3.siw.rentalev.repository.ScooterRepository;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class RentController {

	@Autowired
	RentRepository rentRepository;
	
	@Autowired
	BatteryRepository batteryRepository;

	@Autowired
	ScooterRepository scooterRepository;

	@GetMapping("/rents")
	public ResponseEntity<List<Rent>> getAllRents(@RequestParam(required = false) Boolean ongoing) {
		try {
			List<Rent> rents = new ArrayList<Rent>();

			if(ongoing== null) {
				rentRepository.findAll().forEach(rents::add);
			}else if(ongoing) {
				rentRepository.findByOngoing(ongoing).forEach(rents::add);
			}

			if (rents.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(rents, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/rents/{id}")
	public ResponseEntity<Rent> getRentById(@PathVariable("id") long id) {
		Optional<Rent> rentData = rentRepository.findById(id);

		if (rentData.isPresent()) {
			return new ResponseEntity<>(rentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/* @PostMapping("/rents")
  public ResponseEntity<Rent> createRent(@RequestBody RentRequest rentRequest) {
    try {
    	Address _address= new Address(rentRequest.getStreet(), rentRequest.getCap(), rentRequest.getNumberStreet(), rentRequest.getMunicipality(), rentRequest.getCity(), rentRequest.getCountry());
    	CustomerInformation _customer=new CustomerInformation(rentRequest.getName(), rentRequest.getSurname(), rentRequest.getTelephon(),_address, rentRequest.getUserEmail(),rentRequest.getUsername());

      Rent _rent = rentRepository.save(new Rent(_customer,rentRequest.getPlan()));
      return new ResponseEntity<>(_rent, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }*/
	@PutMapping("/rents/{id}")
	public ResponseEntity<Rent> updateRent(@PathVariable("id") long id, @RequestBody String dismiss) {
		Optional<Rent> rentData = rentRepository.findById(id);

		if (rentData.isPresent()) {
			Rent _rent = rentData.get();
			if(dismiss!=null) {
			_rent.setFinishRent(new Date());
			_rent.setOngoing(false);
			_rent.getScooter().setDateOfDismiss(new Date());
			_rent.getScooter().getBattery().setDateOfDismiss(new Date());
			scooterRepository.save(_rent.getScooter());
			batteryRepository.save(_rent.getScooter().getBattery());
			}

			
			return new ResponseEntity<>(rentRepository.save(_rent), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/rents/{id}")
	public ResponseEntity<HttpStatus> deleteRent(@PathVariable("id") long id) {
		try {
			rentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/rents")
	public ResponseEntity<HttpStatus> deleteAllRents() {
		try {
			rentRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/rents/ongoing")
	public ResponseEntity<List<Rent>> findByOngoing() {
		try {
			List<Rent> rent = rentRepository.findByOngoing(true);

			if (rent.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(rent, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}