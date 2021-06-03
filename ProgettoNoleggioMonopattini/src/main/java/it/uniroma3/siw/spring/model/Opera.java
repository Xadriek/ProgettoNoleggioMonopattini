package it.uniroma3.siw.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
public @Data class Opera {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;
	
	@Column(nullable=false)
	private String titolo;
	
	@Column(nullable=false, length=4)
	private Long anno;
	
	@Column(length=299)
	private String descrizione;
	
	@ManyToOne
	private Collezione collezione;
	
	@ManyToOne
	private Artista artista;

	
}