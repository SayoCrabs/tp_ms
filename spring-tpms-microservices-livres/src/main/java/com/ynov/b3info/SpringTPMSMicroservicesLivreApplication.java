package com.ynov.b3info;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ynov.b3info.models.Livre;
import com.ynov.b3info.models.LivreRepository;

@SpringBootApplication
public class SpringTPMSMicroservicesLivreApplication {
	
	@Autowired
	LivreRepository livreRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringTPMSMicroservicesLivreApplication.class, args);
	}
	
	
	//initialisation des données  //
	@Bean
	CommandLineRunner runner() {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				livreRepository.save(new Livre("SpringBoot","Antonio", (new SimpleDateFormat("dd/MM/yyyy")).parse("03/08/2010")));
				
				livreRepository.save(new Livre("Angular","Querido", (new SimpleDateFormat("dd/MM/yyyy")).parse("03/08/2010")));
				
				livreRepository.save(new Livre("Git","Marvin", (new SimpleDateFormat("dd/MM/yyyy")).parse("03/10/2011")));
				
			}
		};
	}

}
