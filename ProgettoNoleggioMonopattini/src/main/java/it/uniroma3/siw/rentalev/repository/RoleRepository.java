package it.uniroma3.siw.rentalev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.rentalev.model.ERole;
import it.uniroma3.siw.rentalev.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}