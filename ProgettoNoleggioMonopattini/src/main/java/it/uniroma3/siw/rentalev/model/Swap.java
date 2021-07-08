package it.uniroma3.siw.rentalev.model;


import java.time.LocalDateTime;

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

public class Swap {
	
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private LocalDateTime eventLog;
	@ManyToOne
	private Hub hub;
	@ManyToOne
	private Battery battery;
	@ManyToOne
	private Scooter scooter;
	@OneToOne(cascade = CascadeType.ALL)
	private CoinTransation coinTransation;


	
	
	
}
