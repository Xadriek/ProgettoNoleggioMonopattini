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


@Entity

public class CustomerInformation implements UserInformation{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String surname;
	
	@Column(nullable=false)
	private Long telephon;
	
	@ManyToOne
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet customerWallet;
	
	@OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
	private List<Rent> rental;
	
	@OneToMany(mappedBy="fromCustomer",cascade = CascadeType.ALL)
	private List<CoinTransation> coinTransactions;

	public CustomerInformation(Long id, String name, String surname, Long telephon, Address address,
			Wallet customerWallet, List<Rent> rental, List<CoinTransation> coinTransactions) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.telephon = telephon;
		this.address = address;
		this.customerWallet = customerWallet;
		this.rental = rental;
		this.coinTransactions = coinTransactions;
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

	public List<Rent> getRental() {
		return rental;
	}

	public void setRental(List<Rent> rental) {
		this.rental = rental;
	}

	public List<CoinTransation> getCoinTransactions() {
		return coinTransactions;
	}

	public void setCoinTransactions(List<CoinTransation> coinTransactions) {
		this.coinTransactions = coinTransactions;
	}

	
	
	
	
	
	
}
