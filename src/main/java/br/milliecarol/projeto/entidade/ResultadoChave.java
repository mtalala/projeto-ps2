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
    @OneToMany
    private List<Iniciativa> inic;
    private String desc;
    private String meta;
    private double porcentagemConc;
}