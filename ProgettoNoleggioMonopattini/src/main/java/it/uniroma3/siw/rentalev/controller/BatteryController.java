package it.uniroma3.siw.rentalev.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import it.uniroma3.siw.rentalev.model.Battery;

import it.uniroma3.siw.rentalev.repository.BatteryRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class BatteryController {

  @Autowired
  BatteryRepository batteryRepository;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @GetMapping("/batteries")
  public ResponseEntity<List<Battery>> getAllBatterys() {
    try {
      List<Battery> batterys = new ArrayList<Battery>();

      
     batteryRepository.findAll().forEach(batterys::add);
   

      if (batterys.isEmpty()) {
    	  this.logger.debug("non ci sono batterie");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      this.logger.debug("arrivano le batterie");
      return new ResponseEntity<>(batterys, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/batteries/{id}")
  public ResponseEntity<Battery> getBatteryById(@PathVariable("id") long id) {
    Optional<Battery> tutorialData = batteryRepository.findById(id);

    if (tutorialData.isPresent()) {
      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/batteries")
  public ResponseEntity<Battery> createBattery(@RequestBody Battery battery) {
    try {
      Battery _battery = batteryRepository.save(new Battery());
      return new ResponseEntity<>(_battery, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/batteries/{id}")
  public ResponseEntity<Battery> updateBattery(@PathVariable("id") long id, @RequestBody Battery battery) {
    Optional<Battery> batteryData = batteryRepository.findById(id);
    Battery _battery = batteryData.get();
    
    if ((batteryData.isPresent()) && (battery.getHub()!=null) && (_battery.getHub()==null)) {
    	_battery.setHub(battery.getHub());

    	_battery.setState(battery.getState());
      return new ResponseEntity<>(batteryRepository.save(_battery), HttpStatus.OK);
    } else 
    	if (batteryData.isPresent() && (_battery.getHub()==battery.getHub())) {
    	_battery.setState(battery.getState());
    	if(battery.getDateOfDismiss()!=null) {
    		_battery.setDateOfDismiss(new Date());
    	}
      return new ResponseEntity<>(batteryRepository.save(_battery), HttpStatus.OK);
    }else
    	if(batteryData.isPresent() &&(battery.getHub()==null) && (_battery.getHub()!=null)){
    		_battery.setHub(null);
    		_battery.setState(battery.getState());
    	      return new ResponseEntity<>(batteryRepository.save(_battery), HttpStatus.OK);
    	}else
    
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    
  }

  @DeleteMapping("/batteries/{id}")
  public ResponseEntity<HttpStatus> deleteBattery(@PathVariable("id") long id) {
    try {
    	batteryRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/batteries")
  public ResponseEntity<HttpStatus> deleteAllBatterys() {
    try {
    	batteryRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }


  

}