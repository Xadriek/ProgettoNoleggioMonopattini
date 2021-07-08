package it.uniroma3.siw.rentalev.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Hub {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private PartnerInformation custodial;
	@Column(nullable=false)
	private LocalDate dateOfAssembly;
	@Column(nullable=true)
	private LocalDate dateOfDismiss;
	@OneToMany(mappedBy="hub",cascade = CascadeType.ALL)
	private List<Swap> swapCompleted;
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Battery> stokedBattery;
	
	public Hub(Long id, PartnerInformation custodial, LocalDate dateOfAssembly, LocalDate dateOfDismiss, List<Swap> swapCompleted,
			List<Battery> stokedBattery) {
		super();
		this.id = id;
		this.custodial = custodial;
		this.dateOfAssembly = dateOfAssembly;
		this.dateOfDismiss = dateOfDismiss;
		this.swapCompleted = swapCompleted;
		this.stokedBattery = stokedBattery;
	}
	
	
	
}
