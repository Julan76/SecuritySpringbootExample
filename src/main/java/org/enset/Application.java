package org.enset;


import org.enset.dao.EtudiantRepository;
import org.enset.entities.Etudiant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws ParseException {

		ApplicationContext ctx= SpringApplication.run(Application.class, args);
		EtudiantRepository etudiantRepository=ctx.getBean(EtudiantRepository.class);

		DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
		etudiantRepository.save(new Etudiant("Hardy","Jeff",df.parse("1990-11-12")));
		etudiantRepository.save(new Etudiant("Cena","John",df.parse("1990-11-12")));
		etudiantRepository.save(new Etudiant("Ronaldo","Chris",df.parse("1990-11-12")));
		etudiantRepository.save(new Etudiant("Allen","Barry",df.parse("1990-11-12")));
		etudiantRepository.save(new Etudiant("Queen","Oliver",df.parse("1990-11-12")));

		List<Etudiant> etds=etudiantRepository.findAll();
		etds.forEach(e -> System.out.println(e.getNom()));
	}
}
