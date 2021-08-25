package it.uniroma3.siw.rentalev.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.rentalev.model.Geocode;
import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.model.PartnerInformation;
import it.uniroma3.siw.rentalev.repository.GeocodeRepository;
import it.uniroma3.siw.rentalev.repository.HubRepository;
import it.uniroma3.siw.rentalev.repository.PartnerInformationRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	 @Autowired
	  PartnerInformationRepository partnerInformationRepository;
	  @Autowired
	  HubRepository hubRepository;
	  @Autowired
	  GeocodeRepository geocodeRepository;
	  
	  private static final Logger logger = LogManager.getLogger();
	  
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/customer")
	@PreAuthorize("hasRole('CUSTOMER')")
	public String customerAccess() {
		return "Customer Content.";
	}

	@GetMapping("/partner")
	@PreAuthorize("hasRole('PARTNER')")
	public String partnerAccess() {
		return "Partner Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
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
}