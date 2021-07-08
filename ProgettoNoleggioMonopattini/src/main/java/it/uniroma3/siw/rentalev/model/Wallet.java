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
	

	public Wallet(int coin) {
		super();
		this.coin = coin;
		
	}

	public Wallet() {
		
	}
	
}
