package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


import it.uniroma3.siw.rentalev.model.Battery;

import it.uniroma3.siw.rentalev.repository.BatteryRepository;

public class BatteryService {
	@Autowired
	protected BatteryRepository batteryRepository;
	
	@Transactional
    public Battery getBattery(Long id) {
        Optional<Battery> result = this.batteryRepository.findById(id);
        return result.orElse(null);
    }
	 @Transactional
	    public Battery saveBattery(Battery battery) {
	        return this.batteryRepository.save(battery);
	    }
	 @Transactional
	    public List<Battery> getAllBatteries() {
	        List<Battery> result = new ArrayList<>();
	        Iterable<Battery> iterable = this.batteryRepository.findAll();
	        for(Battery battery : iterable)
	            result.add(battery);
	        return result;
	    }
	 
	 @Transactional
		public boolean deletedBattery(Long id) {
			try {
				this.batteryRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}
}
