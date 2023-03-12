package com.elomega.audiovisuel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Acteur extends User{
    @Column(name = "date_de_naissance")
    private LocalDate dateDeNaissance;
    @ManyToMany(mappedBy = "acteurs")
    @JsonIgnore
    private List<Film> films = new ArrayList<>();
    @OneToOne(mappedBy = "acteur")
    private TenuDeCombat tenuDeCombat;

    public void addFilm(Film film){
        films.add(film);
    }

}
