package com.elomega.audiovisuel.model;

import com.elomega.audiovisuel.model.Film;
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
@Table(name = "maison_de_productions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MaisonDeProduction extends User{
    @Column(name = "annee_de_creation")
    private LocalDate anneeDeCreation;
    @OneToMany(mappedBy = "maisonDeProduction")
    @JsonIgnore
    private List<Film> films;
    public void addFilm(Film film){
        films.add(film);
    }
}
