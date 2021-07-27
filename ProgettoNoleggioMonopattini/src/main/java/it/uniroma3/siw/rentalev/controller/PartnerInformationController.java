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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.rentalev.model.PartnerInformation;

import it.uniroma3.siw.rentalev.repository.PartnerInformationRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class PartnerInformationController {

  @Autowired
  PartnerInformationRepository partnerInformationRepository;

  @GetMapping("/partnerInformations")
  public ResponseEntity<List<PartnerInformation>> getAllPartnerInformations() {
    try {
      List<PartnerInformation> partnerInformations = new ArrayList<PartnerInformation>();

      
     partnerInformationRepository.findAll().forEach(partnerInformations::add);
   

      if (partnerInformations.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(partnerInformations, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/partnerInformations/{id}")
  public ResponseEntity<PartnerInformation> getPartnerInformationById(@PathVariable("id") long id) {
    Optional<PartnerInformation> partnerInformationData = partnerInformationRepository.findById(id);

    if (partnerInformationData.isPresent()) {
      return new ResponseEntity<>(partnerInformationData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/partnerInformations")
  public ResponseEntity<PartnerInformation> createPartnerInformation(@RequestBody PartnerInformation partnerInformation) {
    try {
      PartnerInformation _partnerInformation = partnerInformationRepository.save(new PartnerInformation(partnerInformation.getId(), partnerInformation.getName(), partnerInformation.getpIva(),partnerInformation.getAddress(),partnerInformation.getPartnerWallet(),partnerInformation.getHub(),partnerInformation.getStartPartnership(),partnerInformation.getClosurePartnership(),partnerInformation.getCoinTransactions(), partnerInformation.isActive()));
      return new ResponseEntity<>(_partnerInformation, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/partnerInformations/{id}")
  public ResponseEntity<PartnerInformation> updatePartnerInformation(@PathVariable("id") long id, @RequestBody PartnerInformation partnerInformation) {
    Optional<PartnerInformation> partnerInformationData = partnerInformationRepository.findById(id);

    if (partnerInformationData.isPresent()) {
    	PartnerInformation _partnerInformation = partnerInformationData.get();
    	_partnerInformation.setAddress(partnerInformation.getAddress());
    	_partnerInformation.setPartnerWallet(partnerInformation.getPartnerWallet());
    	_partnerInformation.setHub(partnerInformation.getHub());
    	_partnerInformation.setClosurePartnership(partnerInformation.getClosurePartnership());
    	_partnerInformation.setPartnerWallet(partnerInformation.getPartnerWallet());
      return new ResponseEntity<>(partnerInformationRepository.save(_partnerInformation), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/partnerInformations/{id}")
  public ResponseEntity<HttpStatus> deletePartnerInformation(@PathVariable("id") long id) {
    try {
    	partnerInformationRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/partnerInformations")
  public ResponseEntity<HttpStatus> deleteAllPartnerInformations() {
    try {
    	partnerInformationRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

 @GetMapping("/partnerInformations/isActive")
  public ResponseEntity<List<PartnerInformation>> findByCustodial() {
    try {
      List<PartnerInformation> partnerInformation = partnerInformationRepository.findByIsActive(true);

      if (partnerInformation.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(partnerInformation, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
 

}