package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


import it.uniroma3.siw.rentalev.model.Contract;
import it.uniroma3.siw.rentalev.model.EContract;
import it.uniroma3.siw.rentalev.repository.ContractRepository;

public class ContractService {
	
	@Autowired
	protected ContractRepository contractRepository;
	
	@Transactional
    public Contract getContract(Long id) {
        Optional<Contract> result = this.contractRepository.findById(id);
        return result.orElse(null);
    }
	@Transactional
    public List<Contract> getContractByPlan(EContract plan) {
        List<Contract> result = this.contractRepository.findByPlan(plan);
        return result;
	}
	 @Transactional
	    public Contract saveContract(Contract contract) {
	        return this.contractRepository.save(contract);
	    }
	 @Transactional
	    public List<Contract> getAllContract() {
	        List<Contract> result = new ArrayList<>();
	        Iterable<Contract> iterable = this.contractRepository.findAll();
	        for(Contract contract : iterable)
	            result.add(contract);
	        return result;
	    }
	 
	 @Transactional
		public boolean deletedContract(Long id) {
			try {
				this.contractRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}
}
