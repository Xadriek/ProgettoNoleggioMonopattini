package it.uniroma3.siw.rentalev.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;
@Entity
@Data
public class CustomerWallet extends Wallet {
	
	@OneToOne
	private Customer owner;

	public CustomerWallet(int coin, Customer owner) {
		super(coin, owner);
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerWallet other = (CustomerWallet) obj;
		return Objects.equals(owner, other.owner);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(owner);
		return result;
	}
	

}
