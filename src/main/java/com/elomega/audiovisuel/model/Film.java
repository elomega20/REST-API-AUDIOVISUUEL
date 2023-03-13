package com.elomega.audiovisuel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;
    private String titre;
    private String description;
    @Column(name = "url_video")
    private String urlVideo;
    @Column(name = "annee_de_production")
    private LocalDate anneeDeProduction;
    @ManyToMany
    @JoinTable(
            name = "acteurs_films",
            joinColumns = @JoinColumn(name = "film_id",referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "acteur_id",referencedColumnName = "id"))
    @JsonIgnore
    private List<Acteur> acteurs;

    @ManyToOne
    @JoinColumn(name = "maison_de_production_id",referencedColumnName = "id")
    @JsonIgnore
    private MaisonDeProduction maisonDeProduction;

    public void addActeur(Acteur acteur){
        acteurs.add(acteur);
    }

}
