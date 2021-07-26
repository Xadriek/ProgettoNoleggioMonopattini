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

import it.uniroma3.siw.rentalev.model.Address;
import it.uniroma3.siw.rentalev.repository.AddressRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class AddressController {

  @Autowired
  AddressRepository AddressRepository;

  @GetMapping("/Addresses")
  public ResponseEntity<List<Address>> getAllAddresss() {
    try {
      List<Address> Addresss = new ArrayList<Address>();

      
     AddressRepository.findAll().forEach(Addresss::add);
   

      if (Addresss.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(Addresss, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/Addresses/{id}")
  public ResponseEntity<Address> getAddressById(@PathVariable("id") long id) {
    Optional<Address> addressData = AddressRepository.findById(id);

    if (addressData.isPresent()) {
      return new ResponseEntity<>(addressData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Addresses")
  public ResponseEntity<Address> createAddress(@RequestBody Address Address) {
    try {
      Address _Address = AddressRepository.save(new Address(Address.getId(), Address.getStreet(),Address.getCap(),Address.getNumberStreet(),Address.getMunicipality(),Address.getCity(),Address.getCountry()));
      return new ResponseEntity<>(_Address, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/Addresses/{id}")
  public ResponseEntity<Address> updateAddress(@PathVariable("id") long id, @RequestBody Address Address) {
    Optional<Address> addressData = AddressRepository.findById(id);

    if (addressData.isPresent()) {
    	Address _Address = addressData.get();
    	_Address.setStreet(Address.getStreet());
    	_Address.setCap(Address.getCap());
    	_Address.setNumberStreet(Address.getNumberStreet());
    	_Address.setMunicipality(Address.getMunicipality());
    	_Address.setCity(Address.getCity());
    	_Address.setCountry(Address.getCountry());
      return new ResponseEntity<>(AddressRepository.save(_Address), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Addresses/{id}")
  public ResponseEntity<HttpStatus> deleteAddress(@PathVariable("id") long id) {
    try {
    	AddressRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/Addresses")
  public ResponseEntity<HttpStatus> deleteAllAddresss() {
    try {
    	AddressRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

 
  

}