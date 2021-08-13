package it.uniroma3.siw.rentalev.model;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
	
	public Hub(PartnerInformation partnerInformation2) {
		super();

		this.partnerInformation=partnerInformation2;
		this.dateOfAssembly = new Date();
		this.swapCompleted = new ArrayList<Swap>();
		this.stokedBattery = initStock();
	}
	



	public Hub() {
		super();
	}




	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private PartnerInformation partnerInformation;
	@Column
	private Date dateOfAssembly;
	@Column(nullable=true)
	private Date dateOfDismiss;
	@OneToMany(mappedBy="hub")
	private List<Swap> swapCompleted;
	
	@OneToMany(mappedBy="hub")
	private List<Battery> stokedBattery;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PartnerInformation getPartnerInformation() {
		return partnerInformation;
	}
	public void setPartnerInformation(PartnerInformation partnerInformation) {
		this.partnerInformation = partnerInformation;
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
	
	public List<Battery> initStock(){
		List<Battery> aux=new ArrayList<>();
		aux.add(new Battery(this,null));
		aux.add(new Battery(this,null));
		aux.add(new Battery(this,null));
		aux.add(new Battery(this,null));
		aux.add(new Battery(this,null));
		aux.add(new Battery(this,null));
	
		
		return aux;
	}
	
	
	

	
	
	

	
	
	
}
