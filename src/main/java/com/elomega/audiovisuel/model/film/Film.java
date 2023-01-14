package com.elomega.audiovisuel.model.film;

import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.maison_de_production.MaisonDeProduction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;
    private String titre;
    private String description;
    @Column(name = "annee_de_production")
    private LocalDate anneeDeProduction;
    @ManyToMany
    @JoinTable(
            name = "acteurs_films",
            joinColumns = @JoinColumn(name = "film_id",referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "acteur_id",referencedColumnName = "acteur_id"))
    private List<Acteur> acteurs;

    @ManyToOne
    @JoinColumn(name = "maison_de_production_id",referencedColumnName = "maison_de_production_id")
    private MaisonDeProduction maisonDeProduction;

    public void addActeur(Acteur acteur){
        acteurs.add(acteur);
    }

}
