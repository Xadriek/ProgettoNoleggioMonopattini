package it.uniroma3.siw.rentalev.model;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity

public class CoinTransation {
	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private CustomerInformation fromCustomer;
	@ManyToOne
	private PartnerInformation toPartner;
	@Column(nullable=false)
	private Date logTransition;
	@Column(nullable=false)
	private int coin;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Swap entrySwap;
	@OneToOne
	private Swap exitSwap;
	
	private Boolean isComplete;
	
	
	public CoinTransation(CustomerInformation fromCustomer, PartnerInformation toPartner,
			 int coin) {
		super();

		this.fromCustomer = fromCustomer;
		this.toPartner = toPartner;
		this.logTransition = new Date();
		this.coin = coin;
		this.entrySwap = new Swap();
		this.exitSwap= null;
		this.isComplete=false;
	}
	
	
	public CoinTransation() {
		super();
		this.logTransition = new Date();
		this.isComplete=false;
		this.entrySwap = new Swap();
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CustomerInformation getFromCustomer() {
		return fromCustomer;
	}
	public void setFromCustomer(CustomerInformation fromCustomer) {
		this.fromCustomer = fromCustomer;
	}
	public PartnerInformation getToPartner() {
		return toPartner;
	}
	public void setToPartner(PartnerInformation toPartner) {
		this.toPartner = toPartner;
	}
	public Date getLogTransition() {
		return logTransition;
	}
	public void setLogTransition(Date logTransition) {
		this.logTransition = logTransition;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public Swap getEntrySwap() {
		return entrySwap;
	}
	public void setEntrySwap(Swap entrySwap) {
		this.entrySwap = entrySwap;
	}
	public Swap getExitSwap() {
		return exitSwap;
	}
	public void setExitSwap(Swap exitSwap) {
		this.exitSwap = exitSwap;
	}


	public Boolean getIsComplete() {
		return isComplete;
	}


	public void setIsComplete(Boolean isComplete) {
		this.isComplete = isComplete;
	}
	

	
	
	
	
	

}
