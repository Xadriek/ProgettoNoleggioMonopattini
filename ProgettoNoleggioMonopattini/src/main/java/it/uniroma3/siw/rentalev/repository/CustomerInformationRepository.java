package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.Address;

import it.uniroma3.siw.rentalev.model.CustomerInformation;

public interface CustomerInformationRepository extends CrudRepository<CustomerInformation, Long>{
	
		public List<CustomerInformation> findByNameAndSurname(String name,String surname);
		
		public List<CustomerInformation> findByAddress(Address address);
		
		public List<CustomerInformation> findBySurname(String surname);

}
