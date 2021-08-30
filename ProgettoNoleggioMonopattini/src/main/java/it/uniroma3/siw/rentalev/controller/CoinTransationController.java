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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.rentalev.model.CoinTransation;
import it.uniroma3.siw.rentalev.model.PartnerInformation;
import it.uniroma3.siw.rentalev.repository.CoinTransationRepository;
import it.uniroma3.siw.rentalev.repository.PartnerInformationRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class CoinTransationController {

  @Autowired
  CoinTransationRepository coinTransactionRepository;
  
  @Autowired
  PartnerInformationRepository partnerInformationRepository;

  @GetMapping("/coinTransations")
  public ResponseEntity<List<CoinTransation>> getAllCoinTransations() {
    try {
      List<CoinTransation> coinTransactions = new ArrayList<CoinTransation>();

      
     coinTransactionRepository.findAll().forEach(coinTransactions::add);
   

      if (coinTransactions.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(coinTransactions, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/coinTransactions/{id}")
  public ResponseEntity<CoinTransation> getCoinTransationById(@PathVariable("id") long id) {
    Optional<CoinTransation> coinTransactionData = coinTransactionRepository.findById(id);

    if (coinTransactionData.isPresent()) {
      return new ResponseEntity<>(coinTransactionData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/coinTransations/toPartner")
  public ResponseEntity<List<CoinTransation>> getCoinTransationByPartner(@RequestParam long id) {
	  Optional<PartnerInformation> _partnerInformation= partnerInformationRepository.findById(id);
    List<CoinTransation> coinTransactionData = coinTransactionRepository.findByToPartner(_partnerInformation);

    if (!coinTransactionData.isEmpty()) {
      return new ResponseEntity<>(coinTransactionData, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @PostMapping("/coinTransactions")
  public ResponseEntity<CoinTransation> createCoinTransation(@RequestBody CoinTransation coinTransaction) {
    try {
      CoinTransation _coinTransaction = coinTransactionRepository.save(new CoinTransation( coinTransaction.getFromCustomer(),coinTransaction.getToPartner(),coinTransaction.getCoin(),coinTransaction.getEntrySwap()));
      return new ResponseEntity<>(_coinTransaction, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/coinTransactions/{id}")
  public ResponseEntity<CoinTransation> updateCoinTransation(@PathVariable("id") long id, @RequestBody CoinTransation coinTransaction) {
    Optional<CoinTransation> coinTransactionData = coinTransactionRepository.findById(id);

    if (coinTransactionData.isPresent()) {
    	CoinTransation _coinTransaction = coinTransactionData.get();
    	_coinTransaction.setExitSwap(coinTransaction.getEntrySwap());
    	_coinTransaction.setIsComplete(coinTransaction.getIsComplete());

      return new ResponseEntity<>(coinTransactionRepository.save(_coinTransaction), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/coinTransactions/{id}")
  public ResponseEntity<HttpStatus> deleteCoinTransation(@PathVariable("id") long id) {
    try {
    	coinTransactionRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/coinTransactions")
  public ResponseEntity<HttpStatus> deleteAllCoinTransations() {
    try {
    	coinTransactionRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

 /* @GetMapping("/coinTransactions/custodial")
  public ResponseEntity<CoinTransation> findByCustodial(@RequestBody PartnerInformation custodial) {
    try {
      CoinTransation coinTransaction = coinTransactionRepository.findByCustodial(custodial);

      if (coinTransaction==null) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(coinTransaction, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }*/
  

}