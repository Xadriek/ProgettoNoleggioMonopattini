package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


import it.uniroma3.siw.rentalev.model.Partner;
import it.uniroma3.siw.rentalev.model.PartnerWallet;
import it.uniroma3.siw.rentalev.model.Wallet;

import it.uniroma3.siw.rentalev.repository.PartnerWalletRepository;

public class PartnerWalletService {
	@Autowired
	protected PartnerWalletRepository partnerWalletRepository;
	
	@Transactional
    public Wallet getPartnerWallet(Long id) {
        Optional<Wallet> result = this.partnerWalletRepository.findById(id);
        return result.orElse(null);
    }
	@Transactional
    public Wallet getPartnerWalletByOwner(Partner owner) {
        Wallet result =  this.partnerWalletRepository.findByOwner(owner);
        return result;
	}
	 @Transactional
	    public PartnerWallet savePartnerWallet(PartnerWallet partnerWallet) {
	        return this.partnerWalletRepository.save(partnerWallet);
	    }
	 @Transactional
	    public List<PartnerWallet> getAllPartnerWallet() {
	        List<PartnerWallet> result = new ArrayList<>();
	        Iterable<Wallet> iterable = this.partnerWalletRepository.findAll();
	        for(Wallet wallet : iterable)
	            result.add((PartnerWallet) wallet);
	        return result;
	    }
	 
	 @Transactional
		public boolean deletedPartnerWallet(Long id) {
			try {
				this.partnerWalletRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}
}
