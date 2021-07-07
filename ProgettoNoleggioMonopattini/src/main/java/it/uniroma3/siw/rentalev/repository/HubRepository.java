package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.model.Partner;

public interface HubRepository extends CrudRepository<Hub, Long>{
	
	public List<Hub> findByCustodial(Partner custodial);

}
