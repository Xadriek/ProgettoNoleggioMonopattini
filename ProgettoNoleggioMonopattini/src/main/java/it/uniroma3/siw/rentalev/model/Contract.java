package it.uniroma3.siw.rentalev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity

@Table(name = "contracs")
public class Contract {
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private Rent rent;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EContract plan;

	public Contract(Long id, Rent rent, EContract plan) {
		super();
		this.id = id;
		this.rent = rent;
		this.plan = plan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

	public EContract getPlan() {
		return plan;
	}

	public void setPlan(EContract plan) {
		this.plan = plan;
	}

	

	
	
}
