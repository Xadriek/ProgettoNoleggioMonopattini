package it.uniroma3.siw.rentalev.payload.request;

import it.uniroma3.siw.rentalev.model.EContract;


public class RentRequest {
	
	private String name;
	
	private String surname;
	
	private Long telephon;
	
	private String street;
	
	private int cap;
	
	private int numberStreet;
	
	private String municipality;
	
	private String city;
	
	private String country;
	
	private EContract plan;
	
	private String userEmail;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getTelephon() {
		return telephon;
	}

	public void setTelephon(Long telephon) {
		this.telephon = telephon;
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

	public EContract getPlan() {
		return plan;
	}

	public void setPlan(EContract plan) {
		this.plan = plan;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	
	
	
	
}
