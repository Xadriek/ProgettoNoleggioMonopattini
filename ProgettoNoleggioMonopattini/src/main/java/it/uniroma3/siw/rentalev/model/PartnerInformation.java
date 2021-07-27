package it.uniroma3.siw.rentalev.model;

import java.time.LocalDate;
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

//METTERE GEOCODE

@Entity

public class PartnerInformation implements UserInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	
	private Long id;
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String pIva;
	
	@ManyToOne
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet partnerWallet;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Hub hub;
	
	
	@Column(nullable=false)
	private LocalDate startPartnership;
	
	@Column(nullable=true)
	private LocalDate closurePartnership;
	
	@Column
	private boolean isActive;
	

	@OneToMany(mappedBy="toPartner",cascade = CascadeType.ALL)
	private List<CoinTransation> coinTransactions;


	


	public PartnerInformation(Long id, String name, String pIva, Address address, Wallet partnerWallet, Hub hub,
			LocalDate startPartnership, LocalDate closurePartnership, List<CoinTransation> coinTransactions,
			boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.pIva = pIva;
		this.address = address;
		this.partnerWallet = partnerWallet;
		this.hub = hub;
		this.startPartnership = startPartnership;
		this.closurePartnership = closurePartnership;
		this.isActive = isActive;
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


	public String getpIva() {
		return pIva;
	}


	public void setpIva(String pIva) {
		this.pIva = pIva;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public Wallet getPartnerWallet() {
		return partnerWallet;
	}


	public void setPartnerWallet(Wallet partnerWallet) {
		this.partnerWallet = partnerWallet;
	}


	public Hub getHub() {
		return hub;
	}


	public void setHub(Hub hub) {
		this.hub = hub;
	}


	public LocalDate getStartPartnership() {
		return startPartnership;
	}


	public void setStartPartnership(LocalDate startPartnership) {
		this.startPartnership = startPartnership;
	}


	public LocalDate getClosurePartnership() {
		return closurePartnership;
	}


	public void setClosurePartnership(LocalDate closurePartnership) {
		this.closurePartnership = closurePartnership;
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


	

	
	
	

	
	

	
	
}
