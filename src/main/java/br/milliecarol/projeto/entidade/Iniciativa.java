package br.milliecarol.projeto.entidade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name="iniciativas")
public class Iniciativa {
    @ManyToOne
    @JoinColumn(name="kr_id")
    private ResultadoChave kr;
    private String titulo;
    private String desc;
    private double porcentagemConcIndividual;
}
