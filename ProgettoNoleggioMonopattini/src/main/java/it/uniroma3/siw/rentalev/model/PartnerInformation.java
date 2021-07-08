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

import lombok.Data;

@Entity
@Data
public class PartnerInformation implements UserInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	
	private Long id;
	@Column(nullable=false)
	private String name;
	
	@ManyToOne
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet partnerWallet;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Hub hub;
	
	
	@Column(nullable=false)
	private LocalDate startPartnership;
	
	@Column(nullable=true)
	private LocalDate closurePartership;
	

	@OneToMany(mappedBy="toPartner",cascade = CascadeType.ALL)
	private List<CoinTransation> coinTransactions;


	
	
	

	
	

	
	
}
