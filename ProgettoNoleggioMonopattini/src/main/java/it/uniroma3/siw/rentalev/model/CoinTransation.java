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
public class CoinTransation {
	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Customer fromCustomer;
	@ManyToOne
	private Partner toPartner;
	@Column(nullable=false)
	private LocalDateTime logTransition;
	@Column(nullable=false)
	private int coin;
	@OneToOne(cascade = CascadeType.ALL)
	private Swap swap;
	
	public CoinTransation(Long id, Customer fromCustomer, Partner toPartner, LocalDateTime logTransition, int coin,
			Swap swap) {
		super();
		this.id = id;
		this.fromCustomer = fromCustomer;
		this.toPartner = toPartner;
		this.logTransition = logTransition;
		this.coin = coin;
		this.swap = swap;
	}
	
	
	
	
	

}
