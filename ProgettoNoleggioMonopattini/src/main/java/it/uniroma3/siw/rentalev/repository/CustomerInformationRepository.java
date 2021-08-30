package it.uniroma3.siw.rentalev.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import it.uniroma3.siw.rentalev.model.Address;

import it.uniroma3.siw.rentalev.model.CustomerInformation;

@Repository
public interface CustomerInformationRepository extends JpaRepository<CustomerInformation, Long>{
	
		List<CustomerInformation> findByNameAndSurname(String name,String surname);
		
		List<CustomerInformation> findByAddress(Address address);
		
		List<CustomerInformation> findBySurname(String surname);

		List<CustomerInformation> findByIsActive(boolean b);

		CustomerInformation findByEmail(String email);

}
