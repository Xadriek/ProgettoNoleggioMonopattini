package it.uniroma3.siw.rentalev.model;

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
public class Customer extends User{
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String surname;
	
	@Column(nullable=false)
	private Long telephon;
	
	@ManyToOne
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private CustomerWallet customerWallet;
	
	@OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
	private List<Rent> rental;
	
	@OneToMany(mappedBy="fromCustomer",cascade = CascadeType.ALL)
	private List<CoinTransation> coinTransactions;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(address, other.address) && Objects.equals(telephon, other.telephon)
				&& Objects.equals(customerWallet, other.customerWallet);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(address, telephon, customerWallet);
		return result;
	}
	
	public Customer(String name, String surname, Long telephon, Address address, CustomerWallet customerWallet,
			List<Rent> rental, List<CoinTransation> coinTransactions) {
		super();
		this.name = name;
		this.surname = surname;
		this.telephon = telephon;
		this.address = address;
		this.customerWallet = customerWallet;
		this.rental = rental;
		this.coinTransactions = coinTransactions;
	}
	
	
	
	
}
