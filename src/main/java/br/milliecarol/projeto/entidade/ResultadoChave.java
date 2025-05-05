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
@Table(name="kr")
public class ResultadoChave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")  
    private Long id;
    @ManyToOne
    @JoinColumn(name="objetivo_id")
    private Objetivo obj;
   // @OneToMany
    //private List<Iniciativa> inic;
    private String desc;
    private String meta;
    private double porcentagemConc;

/*  logica de calculo de porcentagem ?? */
    @OneToMany(mappedBy = "kr")
    private List<Iniciativa> iniciativas = new ArrayList<>();
    
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
    // % de conclusão é a média das % individuais das iniciativas
    this.porcentagemConc = count > 0 ? Math.round(soma / count) : 0.0;
}

}