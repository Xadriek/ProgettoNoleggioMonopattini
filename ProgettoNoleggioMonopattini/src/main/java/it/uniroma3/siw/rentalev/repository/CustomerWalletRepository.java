package it.uniroma3.siw.rentalev.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.User;
import it.uniroma3.siw.rentalev.model.Wallet;

public interface CustomerWalletRepository extends CrudRepository<Wallet, Long>{
	
	public Wallet findByOwner(User owner);

}
