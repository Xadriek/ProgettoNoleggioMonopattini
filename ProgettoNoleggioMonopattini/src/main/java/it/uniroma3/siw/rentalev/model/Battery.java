package it.uniroma3.siw.rentalev.model;

import java.time.LocalDate;
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
	private LocalDate dateOfBirth;
	@Column(nullable=true)
	private LocalDate dateOfDismiss;
	@OneToMany(mappedBy="battery",cascade = CascadeType.ALL)
	private List<Swap> swapList;
	@ManyToOne
	private Hub hub;
	@OneToOne
	private Scooter scooter;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EBattery state;
	
	

	public Battery(Long id, float voltage, float capacity, LocalDate dateOfBirth, LocalDate dateOfDismiss,
			List<Swap> swapList, Hub hub, Scooter scooter, EBattery state) {
		super();
		this.id = id;
		this.voltage = voltage;
		this.capacity = capacity;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDismiss = dateOfDismiss;
		this.swapList = swapList;
		this.hub = hub;
		this.scooter = scooter;
		this.state = state;
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

	public EBattery getState() {
		return state;
	}

	public void setState(EBattery state) {
		this.state = state;
	}





	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
