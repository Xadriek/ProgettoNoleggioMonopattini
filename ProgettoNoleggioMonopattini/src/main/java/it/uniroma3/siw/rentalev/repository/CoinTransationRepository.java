package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.CoinTransation;
import it.uniroma3.siw.rentalev.model.Customer;
import it.uniroma3.siw.rentalev.model.Partner;

public interface CoinTransationRepository extends CrudRepository<CoinTransation, Long> {
	
	public List<CoinTransation> findByFromCustomer(Customer customer);
	
	public List<CoinTransation> findByToPartner(Partner partner);

}
