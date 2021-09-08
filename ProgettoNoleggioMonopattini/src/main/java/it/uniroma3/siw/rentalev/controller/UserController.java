package it.uniroma3.siw.rentalev.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.uniroma3.siw.rentalev.model.ERole;
import it.uniroma3.siw.rentalev.model.Role;
import it.uniroma3.siw.rentalev.model.User;
import it.uniroma3.siw.rentalev.payload.request.SignupRequest;
import it.uniroma3.siw.rentalev.payload.request.UpdateUserRequest;
import it.uniroma3.siw.rentalev.payload.response.MessageResponse;
import it.uniroma3.siw.rentalev.repository.RoleRepository;
import it.uniroma3.siw.rentalev.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rest")
public class UserController {

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  RoleRepository roleRepository;
  
  @Autowired
  PasswordEncoder encoder;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    try {
      List<User> users = new ArrayList<User>();

      
     userRepository.findAll().forEach(users::add);
   

      if (users.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
    Optional<User> userData = userRepository.findById(id);

    if (userData.isPresent()) {
      return new ResponseEntity<>(userData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

 
 @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") long id) {
    Optional<User> userData = userRepository.findById(id);
    Set<Role> roles = new HashSet<>();
    Optional<Role> role=roleRepository.findByName(ERole.ROLE_PARTNER);
    roles.add(role.get());
    if (userData.isPresent()) {
    	User _user = userData.get();
    	_user.setRoles(roles);
    	


      return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
 @PostMapping("/users/update")
	public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest updateUserRequest ){
	 	Optional<User> userData = userRepository.findById(updateUserRequest.getId());
		User _user=userData.get();
		if(encoder.encode(updateUserRequest.getOldPassword()).equals(_user.getPassword())){
		_user.setPassword(encoder.encode(updateUserRequest.getNewPassword()));
		userRepository.save(_user);

		return ResponseEntity.ok(new MessageResponse("Password update successfully!"));
		}else {
			return ResponseEntity
			.badRequest()
			.body(new MessageResponse("Error: Qualcosa è andato storto!"));
		}
	}
 
 
 
 
  @DeleteMapping("/users/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
    try {
    	userRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  

  


}

