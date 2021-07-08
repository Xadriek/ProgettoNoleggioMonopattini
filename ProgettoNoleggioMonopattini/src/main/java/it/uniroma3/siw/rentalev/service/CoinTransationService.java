package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


import it.uniroma3.siw.rentalev.model.CoinTransation;
import it.uniroma3.siw.rentalev.model.Customer;
import it.uniroma3.siw.rentalev.model.Partner;
import it.uniroma3.siw.rentalev.repository.CoinTransationRepository;

public class CoinTransationService {
	@Autowired
	protected CoinTransationRepository coinTransationRepository;
	
	@Transactional
    public CoinTransation getCoinTransation(Long id) {
        Optional<CoinTransation> result = this.coinTransationRepository.findById(id);
        return result.orElse(null);
    }
	 @Transactional
	    public CoinTransation saveCoinTransation(CoinTransation coinTransation) {
	        return this.coinTransationRepository.save(coinTransation);
	    }
	 @Transactional
	    public List<CoinTransation> getAllCoinTransation() {
	        List<CoinTransation> result = new ArrayList<>();
	        Iterable<CoinTransation> iterable = this.coinTransationRepository.findAll();
	        for(CoinTransation coinTransation : iterable)
	            result.add(coinTransation);
	        return result;
	    }
	 
	 @Transactional
		public boolean deletedCoinTransation(Long id) {
			try {
				this.coinTransationRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}
	 @Transactional
		public List<CoinTransation> coinTransactionFromCustomer(Customer customer) {
			return coinTransationRepository.findByFromCustomer(customer);
		}
	 @Transactional
		public List<CoinTransation> coinTransactionToPartner(Partner partner) {
			return coinTransationRepository.findByToPartner(partner);
		}
}
