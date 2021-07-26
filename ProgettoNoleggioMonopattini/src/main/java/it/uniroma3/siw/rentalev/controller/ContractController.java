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

import it.uniroma3.siw.rentalev.model.Contract;
import it.uniroma3.siw.rentalev.model.EContract;

import it.uniroma3.siw.rentalev.repository.ContractRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class ContractController {

  @Autowired
  ContractRepository contractRepository;

  @GetMapping("/contracts")
  public ResponseEntity<List<Contract>> getAllContracts() {
    try {
      List<Contract> contracts = new ArrayList<Contract>();

      
     contractRepository.findAll().forEach(contracts::add);
   

      if (contracts.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(contracts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/contracts/{id}")
  public ResponseEntity<Contract> getContractById(@PathVariable("id") long id) {
    Optional<Contract> contractData = contractRepository.findById(id);

    if (contractData.isPresent()) {
      return new ResponseEntity<>(contractData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/contracts")
  public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
    try {
      Contract _contract = contractRepository.save(new Contract(contract.getId(), contract.getRent(),contract.getPlan()));
      return new ResponseEntity<>(_contract, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/contracts/{id}")
  public ResponseEntity<Contract> updateContract(@PathVariable("id") long id, @RequestBody Contract contract) {
    Optional<Contract> contractData = contractRepository.findById(id);

    if (contractData.isPresent()) {
    	Contract _contract = contractData.get();
    	_contract.setRent(contract.getRent());
    	_contract.setPlan(contract.getPlan());
      return new ResponseEntity<>(contractRepository.save(_contract), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/contracts/{id}")
  public ResponseEntity<HttpStatus> deleteContract(@PathVariable("id") long id) {
    try {
    	contractRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/contracts")
  public ResponseEntity<HttpStatus> deleteAllContracts() {
    try {
    	contractRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/contracts/plan")
  public ResponseEntity<List<Contract>> findByPlan(@RequestBody EContract plan) {
    try {
      List<Contract> contract = contractRepository.findByPlan(plan);

      if (contract.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(contract, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  

}