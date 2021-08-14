package it.uniroma3.siw.rentalev.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Geocode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double latitude;
	
	private Double langitude;

	public Geocode(Double latitude, Double langitude) {
		super();

		this.latitude = latitude;
		this.langitude = langitude;
	}

	public Geocode() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLangitude() {
		return langitude;
	}

	public void setLangitude(Double langitude) {
		this.langitude = langitude;
	}
	
	
	
}
