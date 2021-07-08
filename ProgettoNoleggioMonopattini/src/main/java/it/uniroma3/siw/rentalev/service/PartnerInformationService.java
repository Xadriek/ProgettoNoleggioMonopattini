package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.rentalev.model.Address;

import it.uniroma3.siw.rentalev.model.Hub;

import it.uniroma3.siw.rentalev.model.PartnerInformation;


import it.uniroma3.siw.rentalev.repository.PartnerInformationRepository;

public class PartnerInformationService {
	@Autowired
    protected PartnerInformationRepository partnerInformationRepository;

    
    @Transactional
    public PartnerInformation getPartnerInformation(Long id) {
        Optional<PartnerInformation> result = this.partnerInformationRepository.findById(id);
        return result.orElse(null);
    }

    
    @Transactional
    public PartnerInformation savePartnerInformation(PartnerInformation partnerInformation) {
        return this.partnerInformationRepository.save(partnerInformation);
    }

    
    @Transactional
    public List<PartnerInformation> getAllUsers() {
        List<PartnerInformation> result = new ArrayList<>();
        Iterable<PartnerInformation> iterable = this.partnerInformationRepository.findAll();
        for(PartnerInformation partner : iterable)
            result.add(partner);
        return result;
    }
    @Transactional
	public Object partnerInformationToNameAndSurname(String name) {
		return this.partnerInformationRepository.findByName(name);
		
	}
    @Transactional
	public Object partnerInformationInformationToSurname(Hub hub) {
		return this.partnerInformationRepository.findByHub(hub);
		
	}
    
    @Transactional
		public Object partnerInformationToAddress(Address address) {
			return this.partnerInformationRepository.findByAddress(address);
			
		}
	@Transactional
	public boolean deletedPartnerInformation(Long id) {
		try {
			this.partnerInformationRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
