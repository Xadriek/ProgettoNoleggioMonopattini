package it.uniroma3.siw.rentalev.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import it.uniroma3.siw.rentalev.model.Battery;


@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long>{


	
		

}
