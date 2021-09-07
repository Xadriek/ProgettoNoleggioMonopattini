package it.uniroma3.siw.rentalev.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.rentalev.model.Address;
import it.uniroma3.siw.rentalev.model.Geocode;
import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.model.PartnerInformation;
import it.uniroma3.siw.rentalev.payload.request.HubRequest;
import it.uniroma3.siw.rentalev.repository.GeocodeRepository;
import it.uniroma3.siw.rentalev.repository.HubRepository;
import it.uniroma3.siw.rentalev.repository.PartnerInformationRepository;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class PartnerInformationController {

  @Autowired
  PartnerInformationRepository partnerInformationRepository;
  @Autowired
  HubRepository hubRepository;
  @Autowired
  GeocodeRepository geocodeRepository;

  private static final Logger logger = LogManager.getLogger();

  @GetMapping("/partnerInformations")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<PartnerInformation>> getAllPartnerInformations(@RequestParam(required = false) Boolean isActive) {
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
  
  @GetMapping("/partnersPosition")
  public ResponseEntity<PartnerInformation> getPartnerInformationByLatAndLng(@RequestParam String lat,
		  																	 @RequestParam String lng) {
	  Geocode _geocode=geocodeRepository.findByLatitudeAndLongitude(lat,lng);
	  logger.info(_geocode);
	  Hub _hub=hubRepository.findByCoordinate(_geocode);
	  logger.info(_hub);
	  Optional<PartnerInformation> partnerInformationData = partnerInformationRepository.findByHub(_hub);
	  logger.info(partnerInformationData.get());
    if (partnerInformationData.isPresent()) {
      return new ResponseEntity<>(partnerInformationData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/partnerInformations")
  
  public ResponseEntity<PartnerInformation> createPartnerInformation(@RequestBody HubRequest hubRequest) {
    try {
      
    Address _address= new Address(hubRequest.getStreet(), hubRequest.getCap(), hubRequest.getNumberStreet(), hubRequest.getMunicipality(), hubRequest.getCity(), hubRequest.getCountry());
    Geocode _geocode=new Geocode(hubRequest.getLatitude(),hubRequest.getLongitude());
    Hub _hub= new Hub(_geocode);
    
    PartnerInformation _partnerInformation = new PartnerInformation( hubRequest.getName(), hubRequest.getpIva(),hubRequest.getTelephon(),_address,_hub,hubRequest.getUserEmail(),hubRequest.getUsername());
      PartnerInformation partnerInformation =partnerInformationRepository.save(_partnerInformation);
      return new ResponseEntity<>(partnerInformation, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/partnerInformations/{id}")
  public ResponseEntity<PartnerInformation> updatePartnerInformation(@PathVariable("id") long id,@RequestBody String date) {
    Optional<PartnerInformation> partnerInformationData = partnerInformationRepository.findById(id);

    if (partnerInformationData.isPresent()) {
    	PartnerInformation _partnerInformation = partnerInformationData.get();
    
    	if(date!=null) {
    	_partnerInformation.setClosurePartnership(LocalDate.now());
    	_partnerInformation.getHub().setDateOfDismiss(LocalDate.now());
    	hubRepository.save(_partnerInformation.getHub());
    	}
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


 @GetMapping("/partnerInformation/{email}")
 public ResponseEntity<PartnerInformation> getPartnerInformationByEmail(@PathVariable("email") String emailRequest) {
   Optional<PartnerInformation> partnerInformationData = partnerInformationRepository.findByEmail(emailRequest);
   
   if (partnerInformationData.isPresent()) {
       return new ResponseEntity<>(partnerInformationData.get(), HttpStatus.OK);
     } else {
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
 }

}