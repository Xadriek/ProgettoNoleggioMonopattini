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

public class Hub {
	
//INSERIRE GEOCODE
	
	public Hub(Geocode geocode) {
		super();
		this.dateOfAssembly = LocalDate.now();
		this.swapCompleted = new ArrayList<Swap>();
		this.stokedBattery = new ArrayList<Battery>();
		this.dateOfDismiss=null;
		this.coordinate=geocode;
		
	}
	



	public Hub() {
		super();
		this.dateOfAssembly = LocalDate.now();
		this.swapCompleted = new ArrayList<Swap>();
		this.stokedBattery = new ArrayList<Battery>();
		this.dateOfDismiss=null;
		
		
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private LocalDate dateOfAssembly;
	@Column(nullable=true)
	private LocalDate dateOfDismiss;
	@JsonIgnore
	@OneToMany(mappedBy="hub")
	private List<Swap> swapCompleted;
	@JsonIgnore
	@OneToMany(mappedBy="hub")
	private List<Battery> stokedBattery;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Geocode coordinate;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDate getDateOfAssembly() {
		return dateOfAssembly;
	}
	public void setDateOfAssembly(LocalDate dateOfAssembly) {
		this.dateOfAssembly = dateOfAssembly;
	}
	public LocalDate getDateOfDismiss() {
		return dateOfDismiss;
	}
	public void setDateOfDismiss(LocalDate dateOfDismiss) {
		this.dateOfDismiss = dateOfDismiss;
	}
	public List<Swap> getSwapCompleted() {
		return swapCompleted;
	}
	public void setSwapCompleted(List<Swap> swapCompleted) {
		this.swapCompleted = swapCompleted;
	}
	public List<Battery> getStokedBattery() {
		return stokedBattery;
	}
	public void setStokedBattery(List<Battery> stokedBattery) {
		this.stokedBattery = stokedBattery;
	}
	
	
	public Geocode getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Geocode coordinate) {
		this.coordinate = coordinate;
	}
	
	
	

	
	
	

	
	
	
}
