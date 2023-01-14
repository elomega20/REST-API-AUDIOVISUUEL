package com.elomega.audiovisuel;

import com.elomega.audiovisuel.enumeration.Couleur;
import com.elomega.audiovisuel.enumeration.Pouvoir;
import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.model.maison_de_production.MaisonDeProduction;
import com.elomega.audiovisuel.model.tenu_de_combat.TenuDeCombat;
import com.elomega.audiovisuel.repository.ActeurRepository;
import com.elomega.audiovisuel.repository.FilmRepository;
import com.elomega.audiovisuel.repository.MaisonDeProductionRepository;
import com.elomega.audiovisuel.repository.TenuDeCombatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class AudiovisuelApplication {

	public static void main(String[] args) {
		SpringApplication.run(AudiovisuelApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ActeurRepository acteurRepository, FilmRepository filmRepository,
						  MaisonDeProductionRepository maisonDeProductionRepository,
						  TenuDeCombatRepository tenuDeCombatRepository){
		return args -> {
			Acteur acteurSave = acteurRepository.save(new Acteur(null,"Sino","Jone",LocalDate.now(),new ArrayList<>(),null));
			Film filmSave = filmRepository.save(new Film(null,"titre 1","descriptipn 1",LocalDate.now(),new ArrayList<>(),null));
			acteurSave.addFilm(filmSave);
			filmSave.addActeur(acteurSave);

			acteurSave = acteurRepository.save(acteurSave);
			filmSave = filmRepository.save(filmSave);

			MaisonDeProduction maisonDeProductionSave = maisonDeProductionRepository.save(new MaisonDeProduction(null,"marvel","Mr. Diop",LocalDate.now(),new ArrayList<>()));
			filmSave.setMaisonDeProduction(maisonDeProductionSave);
			filmSave = filmRepository.save(filmSave);
			maisonDeProductionSave.addFilm(filmSave);
			maisonDeProductionSave = maisonDeProductionRepository.save(maisonDeProductionSave);

			TenuDeCombat tenuDeCombatSave = tenuDeCombatRepository.save(new TenuDeCombat(null,"nom du tenu", Pouvoir.VOLER, Couleur.JAUNE,null));
			tenuDeCombatSave.setActeur(acteurSave);
			tenuDeCombatSave = tenuDeCombatRepository.save(tenuDeCombatSave);
			acteurSave.setTenuDeCombat(tenuDeCombatSave);
			acteurSave = acteurRepository.save(acteurSave);

		};
	}
}
