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
    @OneToMany
    private List<ResultadoChave> kr;
    @Id
    private long id;
    private String titulo;
    private String desc;
    private double porcentagemConcGeral;
}