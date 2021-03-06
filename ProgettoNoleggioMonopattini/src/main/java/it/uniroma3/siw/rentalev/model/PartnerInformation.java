package it.uniroma3.siw.rentalev.model;


import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity

public class PartnerInformation implements UserInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	
	private Long id;
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String pIva;
	
	@Column
	private Long telephon;
	
	@ManyToOne(cascade=CascadeType.ALL)
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
	private String email;
	@Column
	private String username;
	@JsonIgnore
	@OneToMany(mappedBy="toPartner")
	private List<CoinTransation> coinTransations;


	


	public PartnerInformation( String name, String pIva,Long telephon, Address address,Hub hub, 
			 String email, String username
			) {
		super();

		this.name = name;
		this.pIva = pIva;
		this.telephon = telephon;
		this.address = address;
		this.partnerWallet = new Wallet(0);
		this.hub=hub;
		this.startPartnership = LocalDate.now();

		this.coinTransations = new ArrayList<CoinTransation>();
		this.email=email;
		this.username=username;
	}
	


	public PartnerInformation() {
		super();
		this.partnerWallet = new Wallet(0);
		this.startPartnership = LocalDate.now();
		this.coinTransations = new ArrayList<CoinTransation>();
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



	public List<CoinTransation> getCoinTransations() {
		return coinTransations;
	}



	public void setCoinTransations(List<CoinTransation> coinTransations) {
		this.coinTransations = coinTransations;
	}
	
	
	
	
	
	

	
	

	
	
}
