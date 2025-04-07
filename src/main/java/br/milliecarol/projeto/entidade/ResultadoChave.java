package br.milliecarol.projeto.entidade;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import jakarta.persistance.Entity;
import jakarta.persistance.Table;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name="kr")
public class ResultadoChave {
    private Objetivo obj;
    private String desc;
    private String meta;
    private double porcentagemConc;
}