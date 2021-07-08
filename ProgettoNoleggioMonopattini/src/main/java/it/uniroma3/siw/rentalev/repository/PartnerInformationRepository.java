package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.Address;
import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.model.PartnerInformation;



public interface PartnerInformationRepository extends CrudRepository<PartnerInformation,Long> {
	
	public List<PartnerInformation> findByName(String name);
	
	public List<PartnerInformation> findByAddress(Address address);
	
	
	public List<PartnerInformation> findByHub(Hub hub);
	

}
