package it.uniroma3.siw.rentalev.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Rent {
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private LocalDate startRent;
	@Column(nullable=false)
	private LocalDate finishRent;
	@Column(unique=true)
	private Long numberPolicy;
	@ManyToOne
	private Customer customer;
	@OneToOne(cascade = CascadeType.ALL)
	private Scooter scooter;
	@Column
	private Boolean ongoing;
	@OneToOne(cascade = CascadeType.ALL)
	private Contract contract;
	
	public Rent(Long id, LocalDate startRent, LocalDate finishRent, Long numberPolicy, Customer customer,
			Scooter scooter, Boolean ongoing, Contract contract) {
		super();
		this.id = id;
		this.startRent = startRent;
		this.finishRent = finishRent;
		this.numberPolicy = numberPolicy;
		this.customer = customer;
		this.scooter = scooter;
		this.ongoing = ongoing;
		this.contract = contract;
	}


	
	
}
