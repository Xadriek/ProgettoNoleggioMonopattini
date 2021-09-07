package it.uniroma3.siw.rentalev.model;



import java.time.LocalTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;




@Entity


public class Swap {
	
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private LocalTime eventLog;
	@ManyToOne
	private Hub hub;
	@ManyToOne
	private Battery battery;
	@ManyToOne
	private Scooter scooter;
	
	

	
	
	



	public Swap(Hub hub, Battery battery, Scooter scooter
			 ) {
		super();
		this.eventLog =LocalTime.now();
		this.hub = hub;
		this.battery = battery;
		this.scooter = scooter;
		
	}


	public Swap() {
		super();
		this.eventLog = LocalTime.now();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalTime getEventLog() {
		return eventLog;
	}


	public void setEventLog(LocalTime eventLog) {
		this.eventLog = eventLog;
	}


	public Hub getHub() {
		return hub;
	}


	public void setHub(Hub hub) {
		this.hub = hub;
	}


	public Battery getBattery() {
		return battery;
	}


	public void setBattery(Battery battery) {
		this.battery = battery;
	}


	public Scooter getScooter() {
		return scooter;
	}


	public void setScooter(Scooter scooter) {
		this.scooter = scooter;
	}


	

	


	
	
	
}
