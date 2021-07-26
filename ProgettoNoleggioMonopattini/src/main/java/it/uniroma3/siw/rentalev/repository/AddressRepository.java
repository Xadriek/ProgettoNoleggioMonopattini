package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import it.uniroma3.siw.rentalev.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	
	List<Address> findByStreet(String street);

	List<Address> findByCity(String city);
	
	List<Address> findByCap(int cap);
	
	List<Address> findByStreetAndNumberStreet(String street, int numberStreet);
	
	List<Address> findByMunicipality(String municipality);
	
	List<Address> findByStreetAndNumberStreetAndCap(String street, int numberStreet, int cap);
}
