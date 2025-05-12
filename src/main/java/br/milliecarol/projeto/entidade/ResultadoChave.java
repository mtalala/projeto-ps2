package br.milliecarol.projeto.entidade;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
/**
 * resultados chaves vinculado a um objetivo e contem iniciativas
 */
@Entity
@Table(name="kr")
public class ResultadoChave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")  
    private Long id;

    private String desc;
    private String meta;
    private double porcentagemConc;

    //objetivo que está vinculado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="objetivo_id")
    @JsonBackReference
    private Objetivo obj;

    //lista de iniciativas associadas ao kr
    @OneToMany(mappedBy = "kr", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Iniciativa> iniciativas = new ArrayList<>();
    
    /**
     *   logica de calculo de porcentagem 
     * calculada como média das iniciativas
     */
    public void calcularPorcentagemConc() {
    if (iniciativas == null || iniciativas.isEmpty()) {
        this.porcentagemConc = 0.0;
        return;
    }

    double soma = 0.0;
    int count = 0;
    
    for (Iniciativa iniciativa : iniciativas) {
        soma += iniciativa.getPorcentagemConcIndividual();
        count++;
    }
    this.porcentagemConc =  Math.round(soma / count);
}

}