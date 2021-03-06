package it.uniroma3.siw.rentalev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import it.uniroma3.siw.rentalev.model.Address;

import it.uniroma3.siw.rentalev.model.CustomerInformation;

@Repository
public interface CustomerInformationRepository extends JpaRepository<CustomerInformation, Long>{
	
		List<CustomerInformation> findByNameAndSurname(String name,String surname);
		
		List<CustomerInformation> findByAddress(Address address);
		
		List<CustomerInformation> findBySurname(String surname);

		Optional<CustomerInformation> findByEmail(String email);

}
