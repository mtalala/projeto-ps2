package br.milliecarol.projeto.repositorio;
import br.milliecarol.projeto.entidade.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ResultadoChaveRepository extends JpaRepository<ResultadoChave, Long> {
    List<Iniciativa> findbyObjetivo(Objetivo obj);
    List<Iniciativa> findbyDescContainingIgnoreCase(String desc);
    List<Iniciativa> findbyMetaContainingIgnoreCase(String meta);
    List<Iniciativa> findbyPorcentagemConcContainingIgnoreCase(double porcentagemConc);
    List<Iniciativa> findbyId(long id);
    boolean existsByObjetivoAndDesc(Objetivo obj, String desc);
}
