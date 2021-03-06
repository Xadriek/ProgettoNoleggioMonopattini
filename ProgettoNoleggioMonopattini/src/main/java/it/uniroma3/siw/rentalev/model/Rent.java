package it.uniroma3.siw.rentalev.model;


import java.time.LocalDate;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;



@Entity

public class Rent {
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private LocalDate startRent;
	@Column(nullable=true)
	private LocalDate finishRent;
	@Column(unique=true)
	
	private Double numberPolicy;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Scooter scooter;
	
	private Boolean ongoing;
	@OneToOne(cascade = CascadeType.ALL)
	private Contract contract;
	

	
	

	public Rent() {
		super();
		this.startRent = LocalDate.now();
		this.scooter = new Scooter();
		this.ongoing = true;
		this.contract =new Contract(null);
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

	public LocalDate getStartRent() {
		return startRent;
	}

	public void setStartRent(LocalDate startRent) {
		this.startRent = startRent;
	}

	public LocalDate getFinishRent() {
		return finishRent;
	}

	public void setFinishRent(LocalDate finishRent) {
		this.finishRent = finishRent;
	}

	public Double getNumberPolicy() {
		return numberPolicy;
	}

	public void setNumberPolicy(Double numberPolicy) {
		this.numberPolicy = numberPolicy;
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
