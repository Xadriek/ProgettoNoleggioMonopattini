package it.uniroma3.siw.rentalev.repository;

import it.uniroma3.siw.rentalev.model.ERole;
import it.uniroma3.siw.rentalev.model.Role;

public interface RoleRepository {

	Role findByName(ERole roleCustomer);

}
