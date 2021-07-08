package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.rentalev.model.Wallet;

import it.uniroma3.siw.rentalev.repository.WalletRepository;

public class WalletService {

	@Autowired
	protected WalletRepository walletRepository;
	
	@Transactional
    public Wallet getWallet(Long id) {
        Optional<Wallet> result = this.walletRepository.findById(id);
        return result.orElse(null);
    }
	
	 @Transactional
	    public Wallet saveWallet(Wallet wallet) {
	        return this.walletRepository.save(wallet);
	    }
	 @Transactional
	    public List<Wallet> getAllWallet() {
	        List<Wallet> result = new ArrayList<>();
	        Iterable<Wallet> iterable = this.walletRepository.findAll();
	        for(Wallet wallet : iterable)
	            result.add(wallet);
	        return result;
	    }
	 
	 @Transactional
		public boolean deletedWallet(Long id) {
			try {
				this.walletRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}
}
