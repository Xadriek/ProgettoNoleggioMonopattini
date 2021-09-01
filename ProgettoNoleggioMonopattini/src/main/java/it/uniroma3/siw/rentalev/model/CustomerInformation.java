package it.uniroma3.siw.rentalev.model;


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

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity

public class CustomerInformation implements UserInformation{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String surname;
	
	
	private Long telephon;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@Column
	private boolean isActive;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet customerWallet;
	
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Rent rent;
	
	@JsonIgnore
	@OneToMany(mappedBy="fromCustomer")
	private List<CoinTransation> coinTransactions;

	@Column
	private String email;
	
	@Column
	private String username;

	

	public CustomerInformation() {
		super();
		this.isActive = false;
		this.customerWallet = new Wallet(0);
		this.rent = new Rent();
	}

	public CustomerInformation(String name, String surname, Long telephon, Address address,String email,String username ) {
		super();

		this.name = name;
		this.surname = surname;
		this.telephon = telephon;
		this.address = address;
		this.isActive = false;
		this.customerWallet = new Wallet(20);
		this.rent = new Rent();
		this.email=email;
		this.username=username;
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

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	

	
	

	
	
	
	
	
	
}
