package it.uniroma3.siw.rentalev.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Scooter {
@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne 
	private Rent rent;
	@OneToOne
	private Battery battery;
	@Column(nullable=false)
	private LocalDate dateOfBirth;
	@Column(nullable=true)
	private LocalDate dateOfDismiss;
	@OneToMany(mappedBy="scooter", cascade = CascadeType.ALL)
	private List<Swap> swapList;
	
	
	

	
	
	
}
