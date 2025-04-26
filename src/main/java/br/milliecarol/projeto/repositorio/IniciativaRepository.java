package br.milliecarol.projeto.repositorio;
import java.util.*;
import br.milliecarol.projeto.entidade.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IniciativaRepository extends JpaRepository<Iniciativa, Long> {
    List<Iniciativa> findbyResultadoChave(ResultadoChave kr);
    List<Iniciativa> findbyTituloContainingIgnoreCase(String titulo);
    List<Iniciativa> findbyDescContainingIgnoreCase(String desc);
    List<Iniciativa> findbyPorcentagemConcIndividual(double porcentagemConcIndividual);
    boolean existsByResultadoChaveAndDesc(ResultadoChave kr, String desc);
}
