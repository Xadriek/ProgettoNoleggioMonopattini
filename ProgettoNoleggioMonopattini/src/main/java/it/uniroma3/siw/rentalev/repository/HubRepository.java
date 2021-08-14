package it.uniroma3.siw.rentalev.repository;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.rentalev.model.Hub;

@Repository
public interface HubRepository extends JpaRepository<Hub, Long>{
	

}
