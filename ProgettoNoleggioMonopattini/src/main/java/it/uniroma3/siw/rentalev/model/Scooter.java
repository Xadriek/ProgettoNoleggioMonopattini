package it.uniroma3.siw.rentalev.model;


import java.util.ArrayList;
import java.util.Date;
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
	
	@OneToOne(cascade = CascadeType.ALL)
	private Battery battery;
	@Column(nullable=false)
	private Date dateOfBirth;
	@Column(nullable=true)
	private Date dateOfDismiss;
	@OneToMany(mappedBy="scooter", cascade = CascadeType.ALL)
	private List<Swap> swapList;
	
	
	public Scooter(Date dateOfBirth
			) {
		super();


		this.battery = new Battery(dateOfBirth,this,null);
		this.dateOfBirth = dateOfBirth;
		this.swapList = new ArrayList<Swap>();
	}


	public Scooter() {
		super();
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
	
	
	

	
	
	
}
