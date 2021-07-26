package it.uniroma3.siw.rentalev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.rentalev.model.Contract;
import it.uniroma3.siw.rentalev.model.EContract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>{
	
	List<Contract> findByPlan(EContract plan);

}
