package br.milliecarol.projeto.entidade;

import java.util.*;
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

@Entity
@Table(name="objetivos")
public class Objetivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")  
    private Long id;
    @OneToMany
    private List<ResultadoChave> kr;
    private String titulo;
    private String desc;
    private double porcentagemConcGeral;
/* Logica de porcentagem??*/
    @OneToMany(mappedBy = "obj", cascade = CascadeType.ALL)
    private List<ResultadoChave> krs = new ArrayList<>();

    public void calcularPorcentagemConcGeral() {
        if (krs == null || krs.isEmpty()) {
            this.porcentagemConcGeral = 0.0;
            return;
        }

       double soma = 0.0;
        int count = 0;
        
        for (ResultadoChave kr : krs) {
            soma += kr.getPorcentagemConc();
            count++;
        }
        // % de conclusão geral é a média das % de resultado chave
        this.porcentagemConcGeral = count > 0 ? Math.round(soma / count) : 0.0;
    }

}