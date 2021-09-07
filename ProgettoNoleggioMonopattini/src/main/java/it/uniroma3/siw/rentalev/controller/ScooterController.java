package it.uniroma3.siw.rentalev.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import it.uniroma3.siw.rentalev.model.Scooter;
import it.uniroma3.siw.rentalev.repository.ScooterRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class ScooterController {

  @Autowired
  ScooterRepository scooterRepository;


  @GetMapping("/scooters")
  public ResponseEntity<List<Scooter>> getAllScooters() {
    try {
      List<Scooter> scooters = new ArrayList<Scooter>();

      
     scooterRepository.findAll().forEach(scooters::add);
   

      if (scooters.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(scooters, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/scooters/{id}")
  public ResponseEntity<Scooter> getScooterById(@PathVariable("id") long id) {
    Optional<Scooter> scooterData = scooterRepository.findById(id);

    if (scooterData.isPresent()) {
      return new ResponseEntity<>(scooterData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/scooters")
  public ResponseEntity<Scooter> createScooter(@RequestBody Scooter scooter) {
    try {
      Scooter _scooter = scooterRepository.save(new Scooter());
      return new ResponseEntity<>(_scooter, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/scooters/{id}")
  public ResponseEntity<Scooter> updateScooter(@PathVariable("id") long id, @RequestBody Scooter scooter) {
    Optional<Scooter> scooterData = scooterRepository.findById(id);

    if (scooterData.isPresent()) {
    	Scooter _scooter = scooterData.get();
    	_scooter.setBattery(scooter.getBattery());
    	if(scooter.getDateOfDismiss()!=null) {
    	_scooter.setDateOfDismiss(LocalDate.now());
    	_scooter.getBattery().setDateOfDismiss(LocalDate.now());
    	}
      return new ResponseEntity<>(scooterRepository.save(_scooter), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/scooters/{id}")
  public ResponseEntity<HttpStatus> deleteScooter(@PathVariable("id") long id) {
    try {
    	scooterRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/scooters")
  public ResponseEntity<HttpStatus> deleteAllScooters() {
    try {
    	scooterRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }



}