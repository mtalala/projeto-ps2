package br.milliecarol.projeto.entidade;

import java.util.*;

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
 * um objetivo estratégico que tem resultados chave (krs) associados
 * a porcentagem de conclusão geral é calculada como média das porcentagens dos krs vinculados
 */

@Entity
@Table(name="objetivos")
public class Objetivo {
    @Id 
    @Column(name = "id")  
    private Long id;
    private String titulo;
    private String desc;
    private double porcentagemConcGeral;

    @OneToMany(mappedBy = "obj", cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    @JsonManagedReference // vai aparecer normalmenteno json (evita relaçoes bidirecionais)
    private List<ResultadoChave> krs = new ArrayList<>();

   /**
    * Lógica de cálculo
    * calcula a porcentagem de conclusão geral como média dos krs associados
    * arredonda para o inteiro mais p´roximo com o Math.round
    */
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
        this.porcentagemConcGeral = Math.round(soma / count);
    }

}