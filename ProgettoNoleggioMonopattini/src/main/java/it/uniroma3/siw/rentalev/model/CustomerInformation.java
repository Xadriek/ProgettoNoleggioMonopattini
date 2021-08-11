package it.uniroma3.siw.rentalev.model;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity

public class CustomerInformation implements UserInformation{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String surname;
	
	@Column
	private Long telephon;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@Column
	private boolean isActive;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet customerWallet;
	
	@OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
	private List<Rent> rents;
	
	@OneToMany(mappedBy="fromCustomer",cascade = CascadeType.ALL)
	private List<CoinTransation> coinTransactions;

	
	private String email;

	

	public CustomerInformation() {
		super();
	}

	public CustomerInformation(String name, String surname, Long telephon, Address address,String email ) {
		super();

		this.name = name;
		this.surname = surname;
		this.telephon = telephon;
		this.address = address;
		this.isActive = false;
		this.customerWallet = new Wallet(0);
		this.rents = new ArrayList<Rent>();
		this.coinTransactions = new ArrayList<CoinTransation>();
		this.email=email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Wallet getCustomerWallet() {
		return customerWallet;
	}

	public void setCustomerWallet(Wallet customerWallet) {
		this.customerWallet = customerWallet;
	}

	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	public List<CoinTransation> getCoinTransactions() {
		return coinTransactions;
	}

	public void setCoinTransactions(List<CoinTransation> coinTransactions) {
		this.coinTransactions = coinTransactions;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String userEmail) {
		this.email = userEmail;
	}

	
	

	
	
	
	
	
	
}
