package it.uniroma3.siw.rentalev.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity

public class Scooter {
@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Battery battery;
	@Column(nullable=false)
	private LocalDate dateOfBirth;
	@Column(nullable=true)
	private LocalDate dateOfDismiss;
	@JsonIgnore
	@OneToMany(mappedBy="scooter")
	private List<Swap> swapList;
	
	
	public Scooter() {
		super();


		this.battery = new Battery(this,null);
		this.battery.setState(EBattery.IN_USO);
		this.dateOfBirth = LocalDate.now();
		this.swapList = new ArrayList<Swap>();
	}


	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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
