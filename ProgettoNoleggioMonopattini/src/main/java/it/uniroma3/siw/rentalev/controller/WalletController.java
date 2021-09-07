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

import it.uniroma3.siw.rentalev.model.Wallet;

import it.uniroma3.siw.rentalev.repository.WalletRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class WalletController {

  @Autowired
  WalletRepository walletRepository;

  @GetMapping("/wallets")
  public ResponseEntity<List<Wallet>> getAllWallets() {
    try {
      List<Wallet> wallets = new ArrayList<Wallet>();

      
     walletRepository.findAll().forEach(wallets::add);
   

      if (wallets.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(wallets, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/wallets/{id}")
  public ResponseEntity<Wallet> getWalletById(@PathVariable("id") long id) {
    Optional<Wallet> walletData = walletRepository.findById(id);

    if (walletData.isPresent()) {
      return new ResponseEntity<>(walletData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/wallets")
  public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet) {
    try {
      Wallet _wallet = walletRepository.save(new Wallet(0));
      return new ResponseEntity<>(_wallet, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/wallets/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Wallet> updateWallet(@PathVariable("id") long id, @RequestBody Wallet wallet) {
    Optional<Wallet> walletData = walletRepository.findById(id);

    if (walletData.isPresent()) {
    	Wallet _wallet = walletData.get();
    	_wallet.setCoin(wallet.getCoin());
      return new ResponseEntity<>(walletRepository.save(_wallet), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/wallets/{id}")
  public ResponseEntity<HttpStatus> deleteWallet(@PathVariable("id") long id) {
    try {
    	walletRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/wallets")
  public ResponseEntity<HttpStatus> deleteAllWallets() {
    try {
    	walletRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

/*  @GetMapping("/wallets/custodial")
  public ResponseEntity<Wallet> findByCustodial(@RequestBody PartnerInformation custodial) {
    try {
      Wallet wallet = walletRepository.findByCustodial(custodial);

      if (wallet==null) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(wallet, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  */

}