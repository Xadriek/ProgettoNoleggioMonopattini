package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.Address;
import it.uniroma3.siw.rentalev.model.Customer;
import it.uniroma3.siw.rentalev.model.Rent;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
		public List<Customer> findByNameAndSurname(String name,String surname);
		
		public List<Customer> findByAddress(Address address);
		
		public List<Customer> findByRent(Rent rent);
}
