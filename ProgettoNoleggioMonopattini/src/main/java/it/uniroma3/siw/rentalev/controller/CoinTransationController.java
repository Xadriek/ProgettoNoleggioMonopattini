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

import it.uniroma3.siw.rentalev.model.Battery;
import it.uniroma3.siw.rentalev.model.CoinTransation;
import it.uniroma3.siw.rentalev.model.CustomerInformation;
import it.uniroma3.siw.rentalev.model.EBattery;
import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.model.PartnerInformation;
import it.uniroma3.siw.rentalev.model.Scooter;
import it.uniroma3.siw.rentalev.model.Swap;
import it.uniroma3.siw.rentalev.payload.request.CoinTransationRequest;
import it.uniroma3.siw.rentalev.payload.request.SwapRequest;
import it.uniroma3.siw.rentalev.repository.BatteryRepository;
import it.uniroma3.siw.rentalev.repository.CoinTransationRepository;
import it.uniroma3.siw.rentalev.repository.CustomerInformationRepository;
import it.uniroma3.siw.rentalev.repository.HubRepository;
import it.uniroma3.siw.rentalev.repository.PartnerInformationRepository;
import it.uniroma3.siw.rentalev.repository.ScooterRepository;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class CoinTransationController {

	@Autowired
	CoinTransationRepository coinTransationRepository;

	@Autowired
	BatteryRepository batteryRepository;

	@Autowired
	ScooterRepository scooterRepository;

	@Autowired
	HubRepository hubRepository;


	@Autowired
	PartnerInformationRepository partnerInformationRepository;

	@Autowired
	CustomerInformationRepository customerInformationRepository;

	@GetMapping("/coinTransations")
	public ResponseEntity<List<CoinTransation>> getAllCoinTransations() {
		try {
			List<CoinTransation> coinTransactions = new ArrayList<CoinTransation>();


			coinTransationRepository.findAll().forEach(coinTransactions::add);


			if (coinTransactions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(coinTransactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/coinTransations/{id}")
	public ResponseEntity<CoinTransation> getCoinTransationById(@PathVariable("id") long id) {
		Optional<CoinTransation> coinTransactionData = coinTransationRepository.findById(id);

		if (coinTransactionData.isPresent()) {
			return new ResponseEntity<>(coinTransactionData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/coinTransations/partner/{partnerId}")
	public ResponseEntity<List<CoinTransation>> getCoinTransationByPartner(@PathVariable("partnerId") long partnerId) {
		Optional<PartnerInformation> _partnerInformation= partnerInformationRepository.findById(partnerId);
		List<CoinTransation> coinTransactionData = coinTransationRepository.findByToPartner(_partnerInformation);

		if (!coinTransactionData.isEmpty()) {
			return new ResponseEntity<>(coinTransactionData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/coinTransations")
	public ResponseEntity<CoinTransation> createCoinTransation(@RequestBody CoinTransationRequest coinTransationRequest) {
		Optional<PartnerInformation> _partnerInformation= partnerInformationRepository.findById(Long.parseLong(coinTransationRequest.getIdPartner()));
		Optional<CustomerInformation> _customerInformation= customerInformationRepository.findById(Long.parseLong(coinTransationRequest.getIdCustomer()));

		PartnerInformation  partnerInformation=_partnerInformation.get();
		CustomerInformation customerInformation=_customerInformation.get();
		Scooter scooter=customerInformation.getRent().getScooter();
		Battery battery=scooter.getBattery();
		try {
			CoinTransation _coinTransation = coinTransationRepository.save(new CoinTransation( customerInformation,partnerInformation,coinTransationRequest.getCoin(),new Swap(partnerInformation.getHub(),battery,scooter)));
			battery.setHub(partnerInformation.getHub());
			battery.setScooter(null);
			battery.setState(EBattery.IN_CARICA);
			batteryRepository.save(battery);
			scooter.setBattery(null);
			scooterRepository.save(scooter);

			return new ResponseEntity<>(_coinTransation, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}  
	@PutMapping("/coinTransations/{id}")
	public ResponseEntity<CoinTransation> updateCoinTransation(@PathVariable("id") long id, @RequestBody SwapRequest swapRequest) {
		Swap _exitSwap=null;
		Optional<CoinTransation> coinTransactionData = coinTransationRepository.findById(id);
		Optional<Battery> batteryData=batteryRepository.findById(swapRequest.getId_battery());
		Optional<Scooter> scooterData=scooterRepository.findById(swapRequest.getId_scooter());
		Optional<Hub> hubData=hubRepository.findById(swapRequest.getId_hub());
		if(batteryData.isPresent() && scooterData.isPresent() && hubData.isPresent()) {
			Hub _hub=hubData.get();
			Scooter _scooter=scooterData.get();
			Battery _battery=batteryData.get();
			_exitSwap= new Swap(_hub,_battery,_scooter);
			_battery.setHub(null);
			_battery.setScooter(_scooter);
			_battery.setState(EBattery.IN_USO);
			batteryRepository.save(_battery);
			_scooter.setBattery(_battery);
			scooterRepository.save(_scooter);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (coinTransactionData.isPresent()) {
			CoinTransation _coinTransaction = coinTransactionData.get();
			_coinTransaction.setExitSwap(_exitSwap);
			_coinTransaction.setIsComplete(true);

			return new ResponseEntity<>(coinTransationRepository.save(_coinTransaction), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/coinTransations/{id}")
	public ResponseEntity<HttpStatus> deleteCoinTransation(@PathVariable("id") long id) {
		try {
			coinTransationRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/coinTransations")
	public ResponseEntity<HttpStatus> deleteAllCoinTransations() {
		try {
			coinTransationRepository.deleteAll();
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