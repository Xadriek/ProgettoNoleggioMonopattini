package it.uniroma3.siw.rentalev.model;


import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity


public class Swap {
	
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private LocalDateTime eventLog;
	@ManyToOne
	private Hub hub;
	@ManyToOne
	private Battery battery;
	@ManyToOne
	private Scooter scooter;
	@OneToOne(cascade = CascadeType.ALL)
	private CoinTransation coinTransation;
	
	@Column
	private boolean isActive;
	
	
	



	public Swap(Long id, LocalDateTime eventLog, Hub hub, Battery battery, Scooter scooter,
			CoinTransation coinTransation, boolean isActive) {
		super();
		this.id = id;
		this.eventLog = eventLog;
		this.hub = hub;
		this.battery = battery;
		this.scooter = scooter;
		this.coinTransation = coinTransation;
		this.isActive = isActive;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getEventLog() {
		return eventLog;
	}


	public void setEventLog(LocalDateTime eventLog) {
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


	public CoinTransation getCoinTransation() {
		return coinTransation;
	}


	public void setCoinTransation(CoinTransation coinTransation) {
		this.coinTransation = coinTransation;
	}

	
	public boolean isActive() {
		return isActive;
	}
	
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	
	
}
