package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.rentalev.model.Address;

import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.model.Partner;

import it.uniroma3.siw.rentalev.model.User;

import it.uniroma3.siw.rentalev.repository.PartnerRepository;

public class PartnerService {
	@Autowired
    protected PartnerRepository partnerRepository;

    
    @Transactional
    public User getPartner(Long id) {
        Optional<Partner> result = this.partnerRepository.findById(id);
        return result.orElse(null);
    }

    
    @Transactional
    public User saveCustomer(Partner partner) {
        return this.partnerRepository.save(partner);
    }

    
    @Transactional
    public List<Partner> getAllUsers() {
        List<Partner> result = new ArrayList<>();
        Iterable<Partner> iterable = this.partnerRepository.findAll();
        for(Partner partner : iterable)
            result.add(partner);
        return result;
    }
    @Transactional
	public Object customerToNameAndSurname(String name) {
		return this.partnerRepository.findByName(name);
		
	}
    @Transactional
	public Object customerToSurname(Hub hub) {
		return this.partnerRepository.findByHub(hub);
		
	}
    
    @Transactional
		public Object customerToPIva(String pIva) {
			return this.partnerRepository.findByPIva(pIva);
			
		}
    @Transactional
		public Object customerToAddress(Address address) {
			return this.partnerRepository.findByAddress(address);
			
		}
	@Transactional
	public boolean deletedCustomer(Long id) {
		try {
			this.partnerRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
