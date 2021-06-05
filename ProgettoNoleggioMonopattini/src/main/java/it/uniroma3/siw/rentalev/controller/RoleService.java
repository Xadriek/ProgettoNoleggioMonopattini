package it.uniroma3.siw.rentalev.controller;

import it.uniroma3.siw.rentalev.model.ERole;
import it.uniroma3.siw.rentalev.model.Role;
import it.uniroma3.siw.rentalev.repository.RoleRepository;

public class RoleService {

	private RoleRepository roleRepository;

	public Role findByName(ERole roleCustomer) {
		
		return this.roleRepository.findByName(roleCustomer);
	}

}
