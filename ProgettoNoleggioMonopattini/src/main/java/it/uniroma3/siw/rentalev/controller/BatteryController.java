package it.uniroma3.siw.rentalev.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import it.uniroma3.siw.rentalev.model.EBattery;
import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.payload.request.BatteryRequest;
import it.uniroma3.siw.rentalev.repository.BatteryRepository;
import it.uniroma3.siw.rentalev.repository.HubRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class BatteryController {

  @Autowired
  BatteryRepository batteryRepository;
  
  @Autowired
  HubRepository hubRepository;
  
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @GetMapping("/batteries")
  @PreAuthorize("hasRole('ADMIN')")
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
  
  @GetMapping("/batteries/hub/{hubId}")
  
  public ResponseEntity<List<Battery>> getBatteryByHub(@PathVariable("hubId") long hubId) {
    Optional<Hub> _hub=hubRepository.findById(hubId);
	  List<Battery> tutorialData = batteryRepository.findByHub(_hub.get());

    if (!tutorialData.isEmpty()) {
      return new ResponseEntity<>(tutorialData, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/batteries")
  @PreAuthorize("hasRole('PARTNER')")
  public ResponseEntity<Battery> createBattery(@RequestBody BatteryRequest batteryRequest) {
    try {
    Optional<Hub> _hub=hubRepository.findById(batteryRequest.getHubId());
      Battery _battery = batteryRepository.save(new Battery(null,_hub.get()));
      return new ResponseEntity<>(_battery, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/batteries/{id}")
  @PreAuthorize("hasRole('PARTNER')")
  public ResponseEntity<Battery> updateBattery(@PathVariable("id") long id) {
    Optional<Battery> batteryData = batteryRepository.findById(id);
    if(batteryData.isPresent()) {
    Battery _battery = batteryData.get();
    _battery.setState(EBattery.CARICA);
    
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