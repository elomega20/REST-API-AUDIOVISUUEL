package com.elomega.audiovisuel.model;

import com.elomega.audiovisuel.model.Acteur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
    private String nom;
    private String pouvoir;
    private String couleur;
    @OneToOne
    @JoinColumn(name = "acteur_id",referencedColumnName = "acteur_id")
    @JsonIgnore
    private Acteur acteur;
}
