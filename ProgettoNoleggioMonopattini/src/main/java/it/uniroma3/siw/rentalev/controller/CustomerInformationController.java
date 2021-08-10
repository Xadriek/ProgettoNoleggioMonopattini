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

import it.uniroma3.siw.rentalev.model.CustomerInformation;

import it.uniroma3.siw.rentalev.repository.CustomerInformationRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class CustomerInformationController {

  @Autowired
  CustomerInformationRepository customerInformationRepository;

  @GetMapping("/customerInformations")
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
  public ResponseEntity<CustomerInformation> createCustomerInformation(@RequestBody CustomerInformation customerInformation) {
    try {
      CustomerInformation _customerInformation = customerInformationRepository.save(new CustomerInformation( customerInformation.getName(),customerInformation.getSurname(),customerInformation.getTelephon(),customerInformation.getAddress(),customerInformation.getEmail()));
      return new ResponseEntity<>(_customerInformation, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/customerInformations/{id}")
  public ResponseEntity<CustomerInformation> updateCustomerInformation(@PathVariable("id") long id, @RequestBody CustomerInformation customerInformation) {
    Optional<CustomerInformation> customerInformationData = customerInformationRepository.findById(id);

    if (customerInformationData.isPresent()) {
    	CustomerInformation _customerInformation = customerInformationData.get();
    	_customerInformation.setAddress(customerInformation.getAddress());
    	_customerInformation.setActive(customerInformation.isActive());
    	_customerInformation.setTelephon(customerInformation.getTelephon());

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

  @GetMapping("/customerInformations/isActive")
  public ResponseEntity<List<CustomerInformation>> findByIsActive() {
    try {
      List<CustomerInformation> customerInformation = customerInformationRepository.findByIsActive(true);

      if (customerInformation.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(customerInformation, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
  @GetMapping("/customerInformationsByEmail}")
  public CustomerInformation getCustomerInformationByEmail(@RequestBody String email) {
    CustomerInformation customerInformationData = customerInformationRepository.findByEmail(email);
    System.out.println(email);
    if (customerInformationData!=null) {
      return customerInformationData;
    } else {
      return null;
    }
  }

}