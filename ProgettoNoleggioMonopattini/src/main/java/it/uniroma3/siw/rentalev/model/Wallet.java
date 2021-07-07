package it.uniroma3.siw.rentalev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Wallet {
	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private int coin;
	@Column(nullable=false)
	private User owner;

	public Wallet(int coin, Object owner) {
		super();
		this.coin = coin;
		this.owner = (User)owner;
	}
	
}
