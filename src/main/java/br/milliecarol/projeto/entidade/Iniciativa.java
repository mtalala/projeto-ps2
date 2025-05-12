package br.milliecarol.projeto.entidade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

/**
 * uma iniciativa vinculada a um resultado chave (kr)
 */

@Entity
@Table(name="iniciativas")
public class Iniciativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")  
    private Long id;
   
    private String titulo;
    private String desc;
    private double porcentagemConcIndividual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="kr_id")
    @JsonBackReference
    private ResultadoChave kr;
}
