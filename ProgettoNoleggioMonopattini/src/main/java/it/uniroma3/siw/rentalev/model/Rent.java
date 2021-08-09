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

public class Rent {
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private Date startRent;
	@Column(nullable=true)
	private Date finishRent;
	@Column(unique=true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Double numberPolicy;
	@ManyToOne(cascade = CascadeType.ALL)
	private CustomerInformation customer;
	@OneToOne(cascade = CascadeType.ALL)
	private Scooter scooter;
	@Column
	private Boolean ongoing;
	@OneToOne(cascade = CascadeType.ALL)
	private Contract contract;
	
	public Rent(Date startRent, CustomerInformation customer,
			Scooter scooter, Contract contract) {
		super();

		this.startRent = startRent;

		this.customer = customer;
		this.scooter = scooter;
		this.ongoing = false;
		this.contract = contract;
		this.numberPolicy=initNP();
	}

	private Double initNP() {
		
		return (double)Math.random()*100000;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartRent() {
		return startRent;
	}

	public void setStartRent(Date startRent) {
		this.startRent = startRent;
	}

	public Date getFinishRent() {
		return finishRent;
	}

	public void setFinishRent(Date finishRent) {
		this.finishRent = finishRent;
	}

	public Double getNumberPolicy() {
		return numberPolicy;
	}

	public void setNumberPolicy(Double numberPolicy) {
		this.numberPolicy = numberPolicy;
	}

	public CustomerInformation getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerInformation customer) {
		this.customer = customer;
	}

	public Scooter getScooter() {
		return scooter;
	}

	public void setScooter(Scooter scooter) {
		this.scooter = scooter;
	}

	public Boolean getOngoing() {
		return ongoing;
	}

	public void setOngoing(Boolean ongoing) {
		this.ongoing = ongoing;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	


	
	
}
