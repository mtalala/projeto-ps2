package br.milliecarol.projeto.repositorio;
import java.util.*;
import br.milliecarol.projeto.entidade.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IniciativaRepository extends JpaRepository<Iniciativa, Long> {
    List<Iniciativa> findByKr(ResultadoChave kr);
    List<Iniciativa> findByTituloContainingIgnoreCase(String titulo);
    List<Iniciativa> findByDescContainingIgnoreCase(String desc);
    List<Iniciativa> findByPorcentagemConcIndividual(double porcentagemConcIndividual);
    boolean existsByKrAndDesc(ResultadoChave kr, String desc);
}
