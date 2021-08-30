package it.uniroma3.siw.rentalev.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import it.uniroma3.siw.rentalev.model.Geocode;

@Repository
public interface GeocodeRepository extends JpaRepository<Geocode, Long>{
	
	Geocode findByLatitudeAndLongitude(String latitude, String longitude);

}
