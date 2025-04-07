package br.milliecarol.projeto.entidade;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import jakarta.persistance.Entity;
import jakarta.persistance.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name="kr")
public class ResultadoChave {
    @ManyToOne
    @JoinColumn(name="objetivo_titulo")
    private Objetivo obj;
    @OneToMany
    private List<Iniciativa> inic;
    private String desc;
    private String meta;
    private double porcentagemConc;
    @Id
    private long id;
}