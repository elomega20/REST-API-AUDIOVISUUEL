package com.elomega.audiovisuel.model.tenu_de_combat;

import com.elomega.audiovisuel.enumeration.Couleur;
import com.elomega.audiovisuel.enumeration.Pouvoir;
import com.elomega.audiovisuel.model.acteur.Acteur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tenu_de_combats")
public class TenuDeCombat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tenu_de_combat_id")
    private Long tenuDeCombatId;
    private String nom;
    private Pouvoir pouvoir;
    private Couleur couleur;
    @OneToOne
    @JoinColumn(name = "acteur_id",referencedColumnName = "acteur_id")
    private Acteur acteur;
}
