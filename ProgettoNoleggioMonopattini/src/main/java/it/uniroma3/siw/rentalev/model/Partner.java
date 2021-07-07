package it.uniroma3.siw.rentalev.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Partner extends User {
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String pIva;
	
	@Column(nullable=false)
	private Long telephon;
	
	@ManyToOne
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private PartnerWallet partnerWallet;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Hub hub;
	
	@Column(nullable=false)
	private String referencePartner;
	
	@Column(nullable=false)
	private LocalDate startPartnership;
	
	@Column(nullable=true)
	private LocalDate closurePartership;
	
	@OneToMany(mappedBy="partner",cascade = CascadeType.ALL)
	private List<Rent> rental;
	
	@OneToMany(mappedBy="toPartner",cascade = CascadeType.ALL)
	private CoinTransation coinTransaction;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partner other = (Partner) obj;
		return Objects.equals(name, other.name) && Objects.equals(pIva, other.pIva);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(name, pIva);
		return result;
	}

	public Partner(String name, String pIva, Long telephon, Address address, PartnerWallet partnerWallet, Hub hub,
			String referencePartner, LocalDate startPartnership, LocalDate closurePartership, List<Rent> rental,
			CoinTransation coinTransaction) {
		super();
		this.name = name;
		this.pIva = pIva;
		this.telephon = telephon;
		this.address = address;
		this.partnerWallet = partnerWallet;
		this.hub = hub;
		this.referencePartner = referencePartner;
		this.startPartnership = startPartnership;
		this.closurePartership = closurePartership;
		this.rental = rental;
		this.coinTransaction = coinTransaction;
	}

	
	
}
