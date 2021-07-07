package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.Contract;
import it.uniroma3.siw.rentalev.model.EContract;

public interface ContractRepository extends CrudRepository<Contract, Long>{
	
	public List<Contract> findByPlan(EContract plan);

}
