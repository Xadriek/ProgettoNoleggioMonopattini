package it.uniroma3.siw.rentalev.controller;

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

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.rentalev.model.Geocode;
import it.uniroma3.siw.rentalev.repository.GeocodeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class GeocodeController {

  @Autowired
  GeocodeRepository GeocodeRepository;

  @GetMapping("/geocodes")
  public ResponseEntity<List<Geocode>> getAllGeocodes() {
    try {
      List<Geocode> Geocodes = new ArrayList<Geocode>();

      
     GeocodeRepository.findAll().forEach(Geocodes::add);
   

      if (Geocodes.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(Geocodes, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/geocodes/{id}")
  public ResponseEntity<Geocode> getGeocodeById(@PathVariable("id") long id) {
    Optional<Geocode> geocodeData = GeocodeRepository.findById(id);

    if (geocodeData.isPresent()) {
      return new ResponseEntity<>(geocodeData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  
  @PutMapping("/geocodes/{id}")
  public ResponseEntity<Geocode> updateGeocode(@PathVariable("id") long id, @RequestBody Geocode geocode) {
    Optional<Geocode> geocodeData = GeocodeRepository.findById(id);

    if (geocodeData.isPresent()) {
    	Geocode _geocode = geocodeData.get();
    	_geocode.setLatitude(geocode.getLatitude());
    	_geocode.setLongitude(geocode.getLongitude());
    	
      return new ResponseEntity<>(GeocodeRepository.save(_geocode), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/geocodes/{id}")
  public ResponseEntity<HttpStatus> deleteGeocode(@PathVariable("id") long id) {
    try {
    	GeocodeRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/geocodes")
  public ResponseEntity<HttpStatus> deleteAllGeocodes() {
    try {
    	GeocodeRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

 
  

}