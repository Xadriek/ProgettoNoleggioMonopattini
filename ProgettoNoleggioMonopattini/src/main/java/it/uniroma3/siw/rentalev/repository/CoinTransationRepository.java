package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.CoinTransation;

import it.uniroma3.siw.rentalev.model.CustomerInformation;

import it.uniroma3.siw.rentalev.model.PartnerInformation;

public interface CoinTransationRepository extends CrudRepository<CoinTransation, Long> {
	
	public List<CoinTransation> findByFromCustomer(CustomerInformation customer);
	
	public List<CoinTransation> findByToPartner(PartnerInformation partner);

}
