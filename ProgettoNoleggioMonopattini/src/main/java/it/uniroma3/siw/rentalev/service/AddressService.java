package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.rentalev.model.Address;
import it.uniroma3.siw.rentalev.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	protected AddressRepository addressRepository;
	
	@Transactional
    public Address getAddress(Long id) {
        Optional<Address> result = this.addressRepository.findById(id);
        return result.orElse(null);
    }
	 @Transactional
	    public Address saveAddress(Address address) {
	        return this.addressRepository.save(address);
	    }
	 @Transactional
	    public List<Address> getAllAddresses() {
	        List<Address> result = new ArrayList<>();
	        Iterable<Address> iterable = this.addressRepository.findAll();
	        for(Address address : iterable)
	            result.add(address);
	        return result;
	    }
	 @Transactional
		public boolean alreadyExists(Address address) {
			List<Address> addresses = this.addressRepository.findByStreetAndNumberStreetAndCap(address.getStreet(),address.getNumberStreet(),address.getCap());
			if (addresses.size() > 0)
				return true;
			else 
				return false;
		}
	 @Transactional
		public boolean deletedAddress(Long id) {
			try {
				this.addressRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}
	 @Transactional
		public List<Address> addressesToStreet(String street) {
			return addressRepository.findByStreet(street);
		}
	 @Transactional
		public List<Address> addressesToMunicipality(String municipality) {
			return addressRepository.findByMunicipality(municipality);
		}
	 @Transactional
		public List<Address> addressesToCap(int cap) {
			return addressRepository.findByCap(cap);
		}
	 @Transactional
		public List<Address> addressesToCity(String city) {
			return addressRepository.findByCity(city);
		}
}
