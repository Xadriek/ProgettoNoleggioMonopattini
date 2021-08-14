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

public class Hub {
	
//INSERIRE GEOCODE
	

	



	public Hub() {
		super();
		this.dateOfAssembly = new Date();
		this.swapCompleted = new ArrayList<Swap>();
		this.stokedBattery = new ArrayList<Battery>();
		this.dateOfDismiss=null;
		this.coordinate=new Geocode(null,null);
		
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Date dateOfAssembly;
	@Column(nullable=true)
	private Date dateOfDismiss;
	@OneToMany(mappedBy="hub")
	private List<Swap> swapCompleted;
	
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
	
	public Date getDateOfAssembly() {
		return dateOfAssembly;
	}
	public void setDateOfAssembly(Date dateOfAssembly) {
		this.dateOfAssembly = dateOfAssembly;
	}
	public Date getDateOfDismiss() {
		return dateOfDismiss;
	}
	public void setDateOfDismiss(Date dateOfDismiss) {
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
	
	public void initStock(){
		
		new Battery(this,null);
		new Battery(this,null);
		new Battery(this,null);
		new Battery(this,null);
		new Battery(this,null);
		new Battery(this,null);
		new Battery(this,null);
	}
	public Geocode getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Geocode coordinate) {
		this.coordinate = coordinate;
	}
	
	
	

	
	
	

	
	
	
}
