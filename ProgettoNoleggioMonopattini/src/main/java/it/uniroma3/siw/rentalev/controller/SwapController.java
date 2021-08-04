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

import it.uniroma3.siw.rentalev.model.Swap;

import it.uniroma3.siw.rentalev.repository.SwapRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class SwapController {

  @Autowired
  SwapRepository swapRepository;

  @GetMapping("/swaps")
  public ResponseEntity<List<Swap>> getAllSwaps() {
    try {
      List<Swap> swaps = new ArrayList<Swap>();

      
     swapRepository.findAll().forEach(swaps::add);
   

      if (swaps.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(swaps, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/swaps/{id}")
  public ResponseEntity<Swap> getSwapById(@PathVariable("id") long id) {
    Optional<Swap> swapData = swapRepository.findById(id);

    if (swapData.isPresent()) {
      return new ResponseEntity<>(swapData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/swaps")
  public ResponseEntity<Swap> createSwap(@RequestBody Swap swap) {
    try {
      Swap _swap = swapRepository.save(new Swap(new Date(),swap.getHub(),swap.getBattery(),swap.getScooter()));
      return new ResponseEntity<>(_swap, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
 @PutMapping("/swaps/{id}")
  public ResponseEntity<Swap> updateSwap(@PathVariable("id") long id, @RequestBody Swap swap) {
    Optional<Swap> swapData = swapRepository.findById(id);

    if (swapData.isPresent()) {
    	Swap _swap = swapData.get();
    	_swap.setCoinTransation(swap.getCoinTransation());

      return new ResponseEntity<>(swapRepository.save(_swap), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/swaps/{id}")
  public ResponseEntity<HttpStatus> deleteSwap(@PathVariable("id") long id) {
    try {
    	swapRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/swaps")
  public ResponseEntity<HttpStatus> deleteAllSwaps() {
    try {
    	swapRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

 @GetMapping("/swaps/custodial")
  public ResponseEntity<List<Swap>> findByIsActive() {
    try {
      List<Swap> swap = swapRepository.findByIsActive(true);

      if (swap.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(swap, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  

}