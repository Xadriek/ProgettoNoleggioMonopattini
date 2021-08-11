package it.uniroma3.siw.rentalev.model;


import java.util.ArrayList;
import java.util.Date;
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
	private Date startPartnership;
	
	@Column(nullable=true)
	private Date closurePartnership;
	
	@Column
	private boolean isActive;
	
	private String email;
	

	@OneToMany(mappedBy="toPartner",cascade = CascadeType.ALL)
	private List<CoinTransation> coinTransations;


	


	public PartnerInformation( String name, String pIva,Long telephon, Address address, 
			Date startPartnership, String email
			) {
		super();

		this.name = name;
		this.pIva = pIva;
		this.telephon = telephon;
		this.address = address;
		this.partnerWallet = new Wallet(0);
		this.startPartnership = startPartnership;
		this.isActive = false;
		this.coinTransations = new ArrayList<CoinTransation>();
		this.email=email;
	}
	


	public PartnerInformation() {
		super();
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


	public Date getStartPartnership() {
		return startPartnership;
	}


	public void setStartPartnership(Date startPartnership) {
		this.startPartnership = startPartnership;
	}


	public Date getClosurePartnership() {
		return closurePartnership;
	}


	public void setClosurePartnership(Date closurePartnership) {
		this.closurePartnership = closurePartnership;
	}


	public List<CoinTransation> getCoinTransactions() {
		return coinTransations;
	}


	public void setCoinTransactions(List<CoinTransation> coinTransations) {
		this.coinTransations = coinTransations;
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
