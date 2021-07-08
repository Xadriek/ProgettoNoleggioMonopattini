package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


import it.uniroma3.siw.rentalev.model.Customer;
import it.uniroma3.siw.rentalev.model.CustomerWallet;

import it.uniroma3.siw.rentalev.model.Wallet;

import it.uniroma3.siw.rentalev.repository.CustomerWalletRepository;

public class CustomerWalletService {
	
	@Autowired
	protected CustomerWalletRepository customerWalletRepository;
	
	@Transactional
    public Wallet getCustomerWallet(Long id) {
        Optional<Wallet> result = this.customerWalletRepository.findById(id);
        return result.orElse(null);
    }
	@Transactional
    public Wallet getCustomerWalletByOwner(Customer owner) {
        Wallet result =  this.customerWalletRepository.findByOwner(owner);
        return result;
	}
	 @Transactional
	    public CustomerWallet saveCustomerWallet(CustomerWallet contract) {
	        return this.customerWalletRepository.save(contract);
	    }
	 @Transactional
	    public List<CustomerWallet> getAllCustomerWallet() {
	        List<CustomerWallet> result = new ArrayList<>();
	        Iterable<Wallet> iterable = this.customerWalletRepository.findAll();
	        for(Wallet wallet : iterable)
	            result.add((CustomerWallet) wallet);
	        return result;
	    }
	 
	 @Transactional
		public boolean deletedCustomerWallet(Long id) {
			try {
				this.customerWalletRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}
}
