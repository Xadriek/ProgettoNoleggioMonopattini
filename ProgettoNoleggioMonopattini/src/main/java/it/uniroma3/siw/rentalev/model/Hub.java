package it.uniroma3.siw.rentalev.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Hub {
	

	public Hub(Long id2, PartnerInformation custodial2, LocalDate dateOfAssembly2, LocalDate dateOfDismiss2,
			List<Swap> swapCompleted2, List<Battery> stokedBattery2) {
		// TODO Auto-generated constructor stub
	}
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
	@OneToMany(mappedBy="hub",cascade=CascadeType.ALL)
	private List<Battery> stokedBattery;
	

	
	
	
}