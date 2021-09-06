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
import it.uniroma3.siw.rentalev.repository.BatteryRepository;
import it.uniroma3.siw.rentalev.repository.CoinTransationRepository;
import it.uniroma3.siw.rentalev.repository.CustomerInformationRepository;
import it.uniroma3.siw.rentalev.repository.HubRepository;
import it.uniroma3.siw.rentalev.repository.PartnerInformationRepository;
import it.uniroma3.siw.rentalev.repository.ScooterRepository;
import it.uniroma3.siw.rentalev.repository.SwapRepository;



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
	SwapRepository swapRepository;


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
		List<CoinTransation> coinTransactionData = coinTransationRepository.findByToPartner(_partnerInformation.get());

		if (!coinTransactionData.isEmpty()) {
			return new ResponseEntity<>(coinTransactionData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/coinTransations/customer/{customerId}")
	public ResponseEntity<List<CoinTransation>> getCoinTransationByCustomer(@PathVariable("customerId") long customerId) {
		Optional<CustomerInformation> _customerInformation= customerInformationRepository.findById(customerId);
		List<CoinTransation> coinTransactionData = coinTransationRepository.findByFromCustomer(_customerInformation.get());

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
		partnerInformation.getPartnerWallet().setCoin(partnerInformation.getPartnerWallet().getCoin()+2);
		customerInformation.getCustomerWallet().setCoin(customerInformation.getCustomerWallet().getCoin()-2);
		try {
			CoinTransation _coinTransation = coinTransationRepository.save(new CoinTransation( customerInformation,partnerInformation,coinTransationRequest.getCoin(),new Swap(partnerInformation.getHub(),battery,scooter)));
			battery.setHub(partnerInformation.getHub());
			battery.setScooter(null);
			battery.setState(EBattery.IN_CARICA);
			batteryRepository.save(battery);
			scooter.setBattery(null);
			scooterRepository.save(scooter);
			customerInformationRepository.save(customerInformation);
			partnerInformationRepository.save(partnerInformation);

			return new ResponseEntity<>(_coinTransation, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}  
	@PutMapping("/coinTransations/{id}")
	public ResponseEntity<CoinTransation> updateCoinTransation(@PathVariable("id") long id) {
		Swap _exitSwap=null;
		Optional<CoinTransation> coinTransactionData = coinTransationRepository.findById(id);
		Hub hub=coinTransactionData.get().getToPartner().getHub();
		List<Battery> batteries=hub.getStokedBattery();
		Optional<Battery> battery=batteries.stream()
				.filter(bat->bat.getState()==EBattery.CARICA)
				.findFirst();
		Scooter scooter=coinTransactionData.get().getFromCustomer().getRent().getScooter();
		
		if(battery.isPresent() && scooter!=null && hub!=null) {
			_exitSwap= new Swap(hub,battery.get(),scooter);
			battery.get().setHub(null);
			battery.get().setScooter(scooter);
			battery.get().setState(EBattery.IN_USO);
			batteryRepository.save(battery.get());
			scooter.setBattery(battery.get());
			scooterRepository.save(scooter);
			swapRepository.save(_exitSwap);
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




}