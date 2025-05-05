//Carolina Sun R. N. Castilho – 10386494
//Millie Talala Zogheib - 10443653 
package br.milliecarol.projeto.repositorio;

import br.milliecarol.projeto.entidade.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ResultadoChaveRepository extends JpaRepository<ResultadoChave, Long> {
    List<ResultadoChave> findByObj(Objetivo obj);
    List<ResultadoChave> findByDescContainingIgnoreCase(String desc);
    List<ResultadoChave> findByMetaContainingIgnoreCase(String meta);
    List<ResultadoChave> findByPorcentagemConc(double porcentagemConc);
    Optional<ResultadoChave> findById(long id);
    boolean existsByObjAndDesc(Objetivo obj, String desc);
}
