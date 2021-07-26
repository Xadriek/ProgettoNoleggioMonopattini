package it.uniroma3.siw.rentalev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.rentalev.model.Hub;

import it.uniroma3.siw.rentalev.model.PartnerInformation;
import it.uniroma3.siw.rentalev.repository.HubRepository;


public class HubService {


		@Autowired
		protected HubRepository hubRepository;

		@Transactional
		public Hub getHub(Long id) {
			Optional<Hub> result = this.hubRepository.findById(id);
			return result.orElse(null);
		}
		@Transactional
		public Hub getHubByCustodial(PartnerInformation custodial) {
			Hub result =   this.hubRepository.findByCustodial(custodial);
			return result;
		}
		@Transactional
		public Hub saveHub(Hub hub) {
			return this.hubRepository.save(hub);
		}
		@Transactional
		public List<Hub> getAllHubs() {
			List<Hub> result = new ArrayList<>();
			Iterable<Hub> iterable = this.hubRepository.findAll();
			for(Hub hub : iterable)
				result.add(hub);
			return result;
		}

		@Transactional
		public boolean deletedHub(Long id) {
			try {
				this.hubRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				return false;
			}

		}
	}

