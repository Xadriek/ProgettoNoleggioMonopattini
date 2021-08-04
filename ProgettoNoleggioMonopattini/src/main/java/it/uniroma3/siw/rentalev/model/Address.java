package it.uniroma3.siw.rentalev.model;




import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity

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
	
	
	public Address(String street, int cap, int numberStreet, String municipality, String city,
			String country) {
		super();
		this.street = street;
		this.cap = cap;
		this.numberStreet = numberStreet;
		this.municipality = municipality;
		this.city = city;
		this.country = country;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public int getCap() {
		return cap;
	}


	public void setCap(int cap) {
		this.cap = cap;
	}


	public int getNumberStreet() {
		return numberStreet;
	}


	public void setNumberStreet(int numberStreet) {
		this.numberStreet = numberStreet;
	}


	public String getMunicipality() {
		return municipality;
	}


	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}
	
	

	
	


}
