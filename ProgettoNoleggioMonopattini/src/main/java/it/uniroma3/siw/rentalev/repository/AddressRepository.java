package it.uniroma3.siw.rentalev.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
	
	public List<Address> findByStreet(String street);

	public List<Address> findByCity(String city);
	
	public List<Address> findByCap(int cap);
	
	public List<Address> findByStreetAndNumberStreet(String street, int numberStreet);
	
	public List<Address> findByMunicipality(String municipality);
}
