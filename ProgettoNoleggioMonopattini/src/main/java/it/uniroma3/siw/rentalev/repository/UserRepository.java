package it.uniroma3.siw.rentalev.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public Object findByUsername(String username);

	public Object findByEmail(String email);

}