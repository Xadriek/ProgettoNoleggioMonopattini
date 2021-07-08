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
public class Battery {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	
	private Long id;
	@Column(nullable=false)
	private float voltage;
	@Column(nullable=false)
	private float capacity;
	@Column(nullable=false)
	private LocalDate dateOfBirth;
	@Column(nullable=true)
	private LocalDate dateOfDismiss;
	@OneToMany(mappedBy="battery",cascade = CascadeType.ALL)
	private List<Swap> swapList;
	@ManyToOne
	private Hub hub;
	@OneToOne
	private Scooter scooters;
	
	
	
	
	
	
	
	
	
	
	
	
	
}
