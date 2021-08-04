package it.uniroma3.siw.rentalev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.rentalev.model.Battery;
import it.uniroma3.siw.rentalev.model.CoinTransation;
import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.model.Scooter;
import it.uniroma3.siw.rentalev.model.Swap;

@Repository
public interface SwapRepository extends JpaRepository<Swap, Long>{
	
	List<Swap> findByScooter(Scooter scooter);
	
	List<Swap> findByBattery(Battery battery);
	
	List<Swap> findByHub(Hub hub);
	
	Optional<Swap> findByCoinTransation(CoinTransation coinTransation);
	
	Boolean existsByScooter(Scooter scooter);
	
	Boolean existsByBattery(Battery battery);
	
	Boolean existsByHub(Hub hub);


	
}
