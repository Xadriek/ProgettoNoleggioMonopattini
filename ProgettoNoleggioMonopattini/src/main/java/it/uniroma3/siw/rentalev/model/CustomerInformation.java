package it.uniroma3.siw.rentalev.model;

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
public class CustomerInformation implements UserInformation{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String surname;
	
	@Column(nullable=false)
	private Long telephon;
	
	@ManyToOne
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet customerWallet;
	
	@OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
	private List<Rent> rental;
	
	@OneToMany(mappedBy="fromCustomer",cascade = CascadeType.ALL)
	private List<CoinTransation> coinTransactions;

	
	
	
	
	
	
}
