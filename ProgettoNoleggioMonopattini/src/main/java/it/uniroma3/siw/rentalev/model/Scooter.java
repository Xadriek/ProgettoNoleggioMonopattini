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



@Entity

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
	
	
	public Scooter(Long id, Rent rent, Battery battery, LocalDate dateOfBirth, LocalDate dateOfDismiss,
			List<Swap> swapList) {
		super();
		this.id = id;
		this.rent = rent;
		this.battery = battery;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDismiss = dateOfDismiss;
		this.swapList = swapList;
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


	public Battery getBattery() {
		return battery;
	}


	public void setBattery(Battery battery) {
		this.battery = battery;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public LocalDate getDateOfDismiss() {
		return dateOfDismiss;
	}


	public void setDateOfDismiss(LocalDate dateOfDismiss) {
		this.dateOfDismiss = dateOfDismiss;
	}


	public List<Swap> getSwapList() {
		return swapList;
	}


	public void setSwapList(List<Swap> swapList) {
		this.swapList = swapList;
	}
	
	
	

	
	
	
}
