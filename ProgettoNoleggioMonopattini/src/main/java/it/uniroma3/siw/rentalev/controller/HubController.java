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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.model.PartnerInformation;
import it.uniroma3.siw.rentalev.repository.HubRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class HubController {

  @Autowired
  HubRepository hubRepository;

  @GetMapping("/hubs")
  public ResponseEntity<List<Hub>> getAllHubs() {
    try {
      List<Hub> hubs = new ArrayList<Hub>();

      
     hubRepository.findAll().forEach(hubs::add);
   

      if (hubs.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(hubs, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/hubs/{id}")
  public ResponseEntity<Hub> getHubById(@PathVariable("id") long id) {
    Optional<Hub> hubData = hubRepository.findById(id);

    if (hubData.isPresent()) {
      return new ResponseEntity<>(hubData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/hubs")
  public ResponseEntity<Hub> createHub(@RequestBody Hub hub) {
    try {
      Hub _hub = hubRepository.save(new Hub(hub.getCustodial()));
      return new ResponseEntity<>(_hub, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/hubs/{id}")
  public ResponseEntity<Hub> updateHub(@PathVariable("id") long id, @RequestBody Hub hub) {
    Optional<Hub> hubData = hubRepository.findById(id);

    if (hubData.isPresent()) {
    	Hub _hub = hubData.get();
    	_hub.setCustodial(hub.getCustodial());
    	_hub.setDateOfDismiss(new Date());
      return new ResponseEntity<>(hubRepository.save(_hub), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/hubs/{id}")
  public ResponseEntity<HttpStatus> deleteHub(@PathVariable("id") long id) {
    try {
    	hubRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/hubs")
  public ResponseEntity<HttpStatus> deleteAllHubs() {
    try {
    	hubRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/hubs/custodial")
  public ResponseEntity<Hub> findByCustodial(@RequestBody PartnerInformation custodial) {
    try {
      Hub hub = hubRepository.findByCustodial(custodial);

      if (hub==null) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(hub, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  

}