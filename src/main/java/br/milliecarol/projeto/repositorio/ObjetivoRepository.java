package br.milliecarol.projeto.repositorio;
import br.milliecarol.projeto.entidade.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ObjetivoRepository extends JpaRepository <Objetivo, Long> {
    List<Objetivo> findByTituloContainingIgnoreCase(String titulo);
    List<Objetivo> findByDescContainingIgnoreCase(String desc);
    List<Objetivo> findByPorcentagemConcGeral(double porcentagemConcGeral);
    List<Objetivo> findById(long id);
    boolean existsByTituloAndDesc(String titulo, String desc);
}
