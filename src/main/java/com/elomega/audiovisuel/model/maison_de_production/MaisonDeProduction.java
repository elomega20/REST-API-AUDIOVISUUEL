package com.elomega.audiovisuel.model.maison_de_production;

import com.elomega.audiovisuel.model.film.Film;
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
@Table(name = "maison_de_productions")
public class MaisonDeProduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maison_de_production_id")
    private Long maisonDeProductionId;
    private String nom;
    private String directeur;
    @Column(name = "annee_de_creation")
    private LocalDate anneeDeCreation;
    @OneToMany(mappedBy = "maisonDeProduction")
    private List<Film> films;
    public void addFilm(Film film){
        films.add(film);
    }
}
