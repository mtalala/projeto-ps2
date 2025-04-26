package br.milliecarol.projeto.repositorio;
import br.milliecarol.projeto.entidade.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ObjetivoRepository extends JpaRepository <Objetivo, Long> {
    List<Objetivo> findbyTituloContainingIgnoreCase(String titulo);
    List<Objetivo> findbyDescContainingIgnoreCase(String desc);
    List<Objetivo> findbyPorcentagemConcGeralContainingIgnoreCase(double porcentagemConcGeral);
    List<Objetivo> findbyId(long id);
    boolean existsByTituloAndDesc(String titulo, String desc);
}
