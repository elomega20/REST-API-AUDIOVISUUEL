package com.elomega.audiovisuel.model.acteur;

import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.model.tenu_de_combat.TenuDeCombat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "acteurs")
public class Acteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acteur_id")
    private Long acteurId;
    private String nom;
    private String prenom;
    @Column(name = "date_de_naissance")
    private LocalDate dateDeNaissance;
    @ManyToMany(mappedBy = "acteurs")
    private List<Film> films;
    @OneToOne(mappedBy = "acteur")
    private TenuDeCombat tenuDeCombat;

    public void addFilm(Film film){
        this.films.add(film);
    }

}
