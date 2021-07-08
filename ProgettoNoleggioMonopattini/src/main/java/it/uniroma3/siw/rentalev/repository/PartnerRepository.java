package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.Address;
import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.model.Partner;
import it.uniroma3.siw.rentalev.model.Rent;

public interface PartnerRepository extends CrudRepository<Partner,Long> {
	
	public List<Partner> findByName(String name);
	
	public List<Partner> findByAddress(Address address);
	
	
	public List<Partner> findByHub(Hub hub);
	
	public List<Partner> findByPIva(String pIva);
}
