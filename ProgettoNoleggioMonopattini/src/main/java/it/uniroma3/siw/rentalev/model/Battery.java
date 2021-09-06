package it.uniroma3.siw.rentalev.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity

public class Battery {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	
	private Long id;
	@Column(nullable=false)
	private float voltage;
	@Column(nullable=false)
	private float capacity;
	@Column(nullable=false)
	private Date dateOfBirth;
	@Column(nullable=true)
	private Date dateOfDismiss;
	@JsonIgnore
	@OneToMany(mappedBy="battery")
	private List<Swap> swapList;
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	private Hub hub;
	@JsonIgnore
	@OneToOne(cascade=CascadeType.ALL)
	private Scooter scooter;
	
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EBattery state;
	
	

	public Battery(Scooter scooter,Hub hub) {
		super();
		this.voltage = 48;
		this.capacity = 13;
		this.dateOfBirth = new Date();
		this.swapList = new ArrayList<>();
		this.scooter=scooter;
		this.hub=hub;
		this.state = EBattery.IN_CARICA;
		
	}
	

	


	public Battery() {
		super();
		this.voltage = 48;
		this.capacity = 13;
		this.dateOfBirth = new Date();
		this.swapList = new ArrayList<>();
		
		this.state = EBattery.IN_USO;
		
	}





	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getVoltage() {
		return voltage;
	}

	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}

	public float getCapacity() {
		return capacity;
	}

	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfDismiss() {
		return dateOfDismiss;
	}

	public void setDateOfDismiss(Date dateOfDismiss) {
		this.dateOfDismiss = dateOfDismiss;
	}

	public List<Swap> getSwapList() {
		return swapList;
	}

	public void setSwapList(List<Swap> swapList) {
		this.swapList = swapList;
	}

	

	public EBattery getState() {
		return state;
	}

	public void setState(EBattery state) {
		this.state = state;
	}





	public Hub getHub() {
		return hub;
	}





	public void setHub(Hub hub) {
		this.hub = hub;
	}





	public Scooter getScooter() {
		return scooter;
	}





	public void setScooter(Scooter scooter) {
		this.scooter = scooter;
	}





	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
