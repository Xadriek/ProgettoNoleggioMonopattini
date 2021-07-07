package it.uniroma3.siw.rentalev.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;

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
	@ManyToMany
	private List<Hub> hubList;
	@OneToMany(mappedBy="battery",cascade = CascadeType.ALL)
	private List<Scooter> scooters;
	
	
	public Battery(Long id, float voltage, float capacity, LocalDate dateOfBirth, LocalDate dateOfDismiss,
			List<Swap> swapList, List<Hub> hubList, List<Scooter> scooters) {
		super();
		this.id = id;
		this.voltage = voltage;
		this.capacity = capacity;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDismiss = dateOfDismiss;
		this.swapList = swapList;
		this.hubList = hubList;
		this.scooters = scooters;
	}
	
	
	
	
	
	
	
	
	
	
}
