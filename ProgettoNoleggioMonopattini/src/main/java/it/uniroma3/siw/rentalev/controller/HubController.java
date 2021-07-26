package it.uniroma3.siw.rentalev.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.service.HubService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class HubController {

  @Autowired
  HubService hubService;

  @GetMapping("/hubs")
  public ResponseEntity<List<Hub>> getAllHubs() {
    try {
      List<Hub> hubs = new ArrayList<Hub>();

      
     hubService.getAllHubs().forEach(hubs::add);
   

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
    Optional<Hub> tutorialData = Optional.ofNullable(hubService.getHub(id));

    if (tutorialData.isPresent()) {
      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/hubs")
  public ResponseEntity<Hub> createTutorial(@RequestBody Hub hub) {
    try {
      Hub _hub = hubService.saveHub(new Hub(hub.getId(), hub.getCustodial(),hub.getDateOfAssembly(),hub.getDateOfDismiss(),hub.getSwapCompleted(),hub.getStokedBattery()));
      return new ResponseEntity<>(_hub, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  

}