package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.Battery;
import it.uniroma3.siw.rentalev.model.CoinTransation;
import it.uniroma3.siw.rentalev.model.Hub;
import it.uniroma3.siw.rentalev.model.Scooter;
import it.uniroma3.siw.rentalev.model.Swap;

public interface SwapRepository extends CrudRepository<Swap, Long>{
	
	public List<Swap> findByScooter(Scooter scooter);
	
	public List<Swap> findByBattery(Battery battery);
	
	public List<Swap> findByHub(Hub hub);
	
	public Swap findByCoinTransation(CoinTransation coinTransation);
}
