package it.uniroma3.siw.rentalev.model;




import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Address {
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String street;
	@Column(nullable=false)
	private int cap;
	@Column(nullable=true)
	private int numberStreet;
	@Column(nullable=false)
	private String municipality;
	@Column(nullable=false)
	private String city;
	@Column(nullable=false)
	private String country;
	
	
	public Address(Long id, String street, int cap, int numberStreet, String municipality, String city,
			String country) {
		super();
		this.id = id;
		this.street = street;
		this.cap = cap;
		this.numberStreet = numberStreet;
		this.municipality = municipality;
		this.city = city;
		this.country = country;
	}
	
	

	
	


}
