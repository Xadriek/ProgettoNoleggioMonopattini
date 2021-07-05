package it.uniroma3.siw.rentalev.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.rentalev.model.ERole;
import it.uniroma3.siw.rentalev.model.Role;
import it.uniroma3.siw.rentalev.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	public Role findByName(ERole roleCustomer) {
		
		return this.roleRepository.findByName(roleCustomer);
	}

}
