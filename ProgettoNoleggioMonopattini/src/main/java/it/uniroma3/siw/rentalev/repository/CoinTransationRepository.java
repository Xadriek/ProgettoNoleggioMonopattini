package it.uniroma3.siw.rentalev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import it.uniroma3.siw.rentalev.model.CoinTransation;

import it.uniroma3.siw.rentalev.model.CustomerInformation;

import it.uniroma3.siw.rentalev.model.PartnerInformation;

@Repository
public interface CoinTransationRepository extends JpaRepository<CoinTransation, Long> {
	
	List<CoinTransation> findByFromCustomer(CustomerInformation customer);
	
	List<CoinTransation> findByToPartner(Optional<PartnerInformation> _partnerInformation);

	List<CoinTransation> findByToPartner(PartnerInformation partnerInformation);

}
