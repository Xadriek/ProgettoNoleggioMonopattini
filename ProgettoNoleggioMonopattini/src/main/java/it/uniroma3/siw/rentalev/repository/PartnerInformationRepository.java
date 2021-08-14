package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.rentalev.model.Address;
import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.model.PartnerInformation;


@Repository
public interface PartnerInformationRepository extends JpaRepository<PartnerInformation,Long> {
	
	List<PartnerInformation> findByName(String name);
	
	List<PartnerInformation> findByAddress(Address address);
	
	
	List<PartnerInformation> findByHub(Hub hub);

	List<PartnerInformation> findByIsActive(boolean b);

	PartnerInformation findByEmail(String emailRequest);

	PartnerInformation findByUsername(String username);
	

}
