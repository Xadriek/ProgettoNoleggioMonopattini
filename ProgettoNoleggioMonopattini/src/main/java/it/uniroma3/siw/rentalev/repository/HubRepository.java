package it.uniroma3.siw.rentalev.repository;



import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.Hub;

import it.uniroma3.siw.rentalev.model.PartnerInformation;

public interface HubRepository extends CrudRepository<Hub, Long>{
	
	public Hub findByCustodial(PartnerInformation custodial);

}
