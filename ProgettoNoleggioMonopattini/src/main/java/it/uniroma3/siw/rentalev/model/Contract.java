package it.uniroma3.siw.rentalev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity

@Table(name = "contracts")
public class Contract {
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EContract plan;

	public Contract(EContract plan) {
		super();
		this.plan = plan;
	}

	
	public Contract() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public EContract getPlan() {
		return plan;
	}

	public void setPlan(EContract plan) {
		this.plan = plan;
	}

	

	
	
}
