package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.rentalev.model.Address;
import it.uniroma3.siw.rentalev.model.Customer;
import it.uniroma3.siw.rentalev.model.Rent;
import it.uniroma3.siw.rentalev.model.User;
import it.uniroma3.siw.rentalev.repository.CustomerRepository;


public class CustomerService {
	
	@Autowired
	    protected CustomerRepository customerRepository;

	    
	    @Transactional
	    public User getCustomer(Long id) {
	        Optional<Customer> result = this.customerRepository.findById(id);
	        return result.orElse(null);
	    }

	    
	    @Transactional
	    public User saveCustomer(Customer customer) {
	        return this.customerRepository.save(customer);
	    }

	    
	    @Transactional
	    public List<Customer> getAllUsers() {
	        List<Customer> result = new ArrayList<>();
	        Iterable<Customer> iterable = this.customerRepository.findAll();
	        for(Customer customer : iterable)
	            result.add(customer);
	        return result;
	    }
	    @Transactional
		public Object customerToNameAndSurname(String name,String surname) {
			return this.customerRepository.findByNameAndSurname(name,surname);
			
		}
	    @Transactional
		public Object customerToSurname(String surname) {
			return this.customerRepository.findBySurname(surname);
			
		}

	    @Transactional
 		public Object customerToAddress(Address address) {
 			return this.customerRepository.findByAddress(address);
 			
 		}
		@Transactional
		public boolean deletedCustomer(Long id) {
			try {
				this.customerRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}

		


	
}
