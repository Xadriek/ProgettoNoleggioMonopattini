package it.uniroma3.siw.rentalev.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import it.uniroma3.siw.rentalev.model.Address;
import it.uniroma3.siw.rentalev.model.CustomerInformation;
import it.uniroma3.siw.rentalev.payload.request.RentRequest;
import it.uniroma3.siw.rentalev.repository.CustomerInformationRepository;
import it.uniroma3.siw.rentalev.repository.RentRepository;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class CustomerInformationController {

  @Autowired
  CustomerInformationRepository customerInformationRepository;
  @Autowired
  RentRepository rentRepository;


  @GetMapping("/customerInformations")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<CustomerInformation>> getAllCustomerInformations() {
    try {
      List<CustomerInformation> customerInformations = new ArrayList<CustomerInformation>();

      
     
    customerInformationRepository.findAll().forEach(customerInformations::add);
      

      if (customerInformations.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(customerInformations, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/customerInformations/{id}")
  public ResponseEntity<CustomerInformation> getCustomerInformationById(@PathVariable("id") long id) {
    Optional<CustomerInformation> customerInformationData = customerInformationRepository.findById(id);

    if (customerInformationData.isPresent()) {
      return new ResponseEntity<>(customerInformationData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/customerInformations")
  @PreAuthorize("hasRole('CUSTOMER')")
  public ResponseEntity<CustomerInformation> createCustomerInformation(@RequestBody RentRequest rentRequest) {
    try {
    Address _address= new Address(rentRequest.getStreet(), rentRequest.getCap(), rentRequest.getNumberStreet(), rentRequest.getMunicipality(), rentRequest.getCity(), rentRequest.getCountry());
      CustomerInformation _customerInformation =new CustomerInformation( rentRequest.getName(),rentRequest.getSurname(),rentRequest.getTelephon(),_address,rentRequest.getUserEmail(),rentRequest.getUsername());
      _customerInformation.getRent().getContract().setPlan(rentRequest.getPlan());
      CustomerInformation customerInformation =customerInformationRepository.save(_customerInformation);
      return new ResponseEntity<>(customerInformation, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/customerInformations/{id}")
  public ResponseEntity<CustomerInformation> updateCustomerInformation(@PathVariable("id") long id, @RequestBody Boolean isActive) {
    Optional<CustomerInformation> customerInformationData = customerInformationRepository.findById(id);

    if (customerInformationData.isPresent()) {
    	CustomerInformation _customerInformation = customerInformationData.get();
    	_customerInformation.getRent().setOngoing(isActive);
    	rentRepository.save(_customerInformation.getRent());

      return new ResponseEntity<>(customerInformationRepository.save(_customerInformation), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/customerInformations/{id}")
  public ResponseEntity<HttpStatus> deleteCustomerInformation(@PathVariable("id") long id) {
    try {
    	customerInformationRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/customerInformations")
  public ResponseEntity<HttpStatus> deleteAllCustomerInformations() {
    try {
    	customerInformationRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }


  @GetMapping("/customerInformation/{email}")
  @PreAuthorize("hasRole('CUSTOMER')")
  public ResponseEntity<CustomerInformation> getCustomerInformationByEmail(@PathVariable("email") String emailRequest) {
	  Optional<CustomerInformation> customerInformationData = customerInformationRepository.findByEmail(emailRequest);
    
    
    if (customerInformationData.isPresent()) {
        return new ResponseEntity<>(customerInformationData.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }

}