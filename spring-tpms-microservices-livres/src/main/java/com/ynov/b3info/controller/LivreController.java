package com.ynov.b3info.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.b3info.models.Livre;
import com.ynov.b3info.models.LivreRepository;

@RestController
@RequestMapping("/api/livre")
public class LivreController {
	
	@Autowired
	private LivreRepository LivreRepository;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Livre>> getLivres() {
		return ResponseEntity.ok(LivreRepository.findAll());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Livre>> getLivre(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(LivreRepository.findById(id));
	}

	@RequestMapping(value = "/search/{auteur}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Livre>> searchLivresByAuteur(@PathVariable("name") String auteur) {
		return ResponseEntity.ok(LivreRepository.findByAuteur(auteur));
	}
	
	@RequestMapping(value = "/{titre}/{auteur}/{dateParution}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Livre> createLivre(@PathVariable("titre") String titre, @PathVariable("auteur") String auteur, @PathVariable("dateParution") String dateParution) {
		Livre livre = new Livre();
		livre.setTitre(titre);
		livre.setAuteur(auteur);
		
		//tranformation chaine en type date
		try {
			SimpleDateFormat dateFormatter=new SimpleDateFormat("dd/MM/yyyy");
			livre.setDateParution(dateFormatter.parse(dateParution));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(LivreRepository.save(livre));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Livre> updateLivre(@PathVariable("id") Integer id, @RequestBody Livre newLivre) {
		Optional<Livre> optLivre = LivreRepository.findById(id);
		if (optLivre.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Livre livre = optLivre.get();
		livre.setTitre(newLivre.getTitre());
		livre.setAuteur(newLivre.getAuteur());
		livre.setDateParution(newLivre.getDateParution());
		
		return ResponseEntity.ok(LivreRepository.save(livre));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Livre> deleteLivre(@PathVariable("id") Integer id) {
		Optional<Livre> optLivre = LivreRepository.findById(id);
		if (optLivre.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Livre Livre = optLivre.get();
		LivreRepository.delete(Livre);
		return ResponseEntity.ok(Livre);
	}
}
