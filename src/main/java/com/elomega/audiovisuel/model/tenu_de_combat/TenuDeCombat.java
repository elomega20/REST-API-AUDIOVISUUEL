package com.elomega.audiovisuel.model.tenu_de_combat;

import com.elomega.audiovisuel.enumeration.Couleur;
import com.elomega.audiovisuel.enumeration.Pouvoir;
import com.elomega.audiovisuel.model.acteur.Acteur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tenu_de_combats")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TenuDeCombat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tenu_de_combat_id")
    private Long tenuDeCombatId;
    @NotEmpty(message = "le nom ne doit pas etre vide")
    private String nom;
    private Pouvoir pouvoir;
    private Couleur couleur;
    @OneToOne
    @JoinColumn(name = "acteur_id",referencedColumnName = "acteur_id")
    @JsonIgnore
    private Acteur acteur;
}
