//Carolina Sun R. N. Castilho – 10386494
//Millie Talala Zogheib - 10443653 
package br.milliecarol.projeto.repositorio;

import br.milliecarol.projeto.entidade.Iniciativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IniciativaRepository extends JpaRepository<Iniciativa, Long> {

    List<Iniciativa> findByTituloContainingIgnoreCase(String titulo);
    List<Iniciativa> findByDescContainingIgnoreCase(String desc);
    List<Iniciativa> findByPorcentagemConcIndividual(double porcentagemConcIndividual);
    List<Iniciativa> findByKrId(Long krId);
}
