package it.uniroma3.siw.rentalev.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.rentalev.model.ERole;
import it.uniroma3.siw.rentalev.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByName(ERole role);

}
