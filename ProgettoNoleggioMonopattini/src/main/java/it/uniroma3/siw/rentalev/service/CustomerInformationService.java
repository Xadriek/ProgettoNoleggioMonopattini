package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.rentalev.model.Address;

import it.uniroma3.siw.rentalev.model.CustomerInformation;

import it.uniroma3.siw.rentalev.repository.CustomerInformationRepository;


public class CustomerInformationService {
	
	@Autowired
	    protected CustomerInformationRepository customerInformationRepository;

	    
	    @Transactional
	    public CustomerInformation getCustomerInformation(Long id) {
	        Optional<CustomerInformation> result = this.customerInformationRepository.findById(id);
	        return result.orElse(null);
	    }

	    
	    @Transactional
	    public CustomerInformation saveCustomerInformation(CustomerInformation customer) {
	        return this.customerInformationRepository.save(customer);
	    }

	    
	    @Transactional
	    public List<CustomerInformation> getAllCustomerInformations() {
	        List<CustomerInformation> result = new ArrayList<>();
	        Iterable<CustomerInformation> iterable = this.customerInformationRepository.findAll();
	        for(CustomerInformation customer : iterable)
	            result.add(customer);
	        return result;
	    }
	    @Transactional
		public Object customerInformationToNameAndSurname(String name,String surname) {
			return this.customerInformationRepository.findByNameAndSurname(name,surname);
			
		}
	    @Transactional
		public Object customerInformationToSurname(String surname) {
			return this.customerInformationRepository.findBySurname(surname);
			
		}

	    @Transactional
 		public Object customerInformationToAddress(Address address) {
 			return this.customerInformationRepository.findByAddress(address);
 			
 		}
		@Transactional
		public boolean deletedCustomerInformation(Long id) {
			try {
				this.customerInformationRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}

		


	
}
