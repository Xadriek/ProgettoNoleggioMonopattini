package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.rentalev.model.Battery;
import it.uniroma3.siw.rentalev.model.CoinTransation;
import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.model.Scooter;
import it.uniroma3.siw.rentalev.model.Swap;

import it.uniroma3.siw.rentalev.repository.SwapRepository;

public class SwapService {
	@Autowired
	protected SwapRepository swapRepository;
	
	@Transactional
    public Swap getSwap(Long id) {
        Optional<Swap> result = this.swapRepository.findById(id);
        return result.orElse(null);
    }
	 @Transactional
	    public Swap saveSwap(Swap swap) {
	        return this.swapRepository.save(swap);
	    }
	 @Transactional
	    public List<Swap> getAllSwaps() {
	        List<Swap> result = new ArrayList<>();
	        Iterable<Swap> iterable = this.swapRepository.findAll();
	        for(Swap swap : iterable)
	            result.add(swap);
	        return result;
	    }
	 
	 @Transactional
		public boolean deletedSwap(Long id) {
			try {
				this.swapRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}
	 @Transactional
	    public List<Swap> getSwapByScooter(Scooter scooter) {
	        List<Swap> result = this.swapRepository.findByScooter(scooter);
	        return result;
	    }
	 @Transactional
	    public List<Swap> getSwapByBattery(Battery battery) {
	        List<Swap> result = this.swapRepository.findByBattery(battery);
	        return result;
	    }
	 @Transactional
	    public List<Swap> getSwapByHub(Hub hub) {
	        List<Swap> result = this.swapRepository.findByHub(hub);
	        return result;
	    }
	 @Transactional
	    public Swap getSwapByCoinTransation(CoinTransation coinTransation) {
	        Swap result = this.swapRepository.findByCoinTransation(coinTransation);
	        return result;
	    }
}
