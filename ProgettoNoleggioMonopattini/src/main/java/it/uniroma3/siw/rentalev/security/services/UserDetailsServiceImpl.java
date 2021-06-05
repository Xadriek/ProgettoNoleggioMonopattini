package it.uniroma3.siw.rentalev.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.rentalev.model.User;
import it.uniroma3.siw.rentalev.service.UserService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserService userService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		try {
			 user = (User) userService.findByUsername(username);
		} catch (Exception e) {
			new UsernameNotFoundException("User Not Found with username: " + username);
		}		

		return UserDetailsImpl.build(user);
	}

}