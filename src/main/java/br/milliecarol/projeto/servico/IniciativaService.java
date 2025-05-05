//Carolina Sun R. N. Castilho – 10386494
//Millie Talala Zogheib - 10443653 
package br.milliecarol.projeto.servico;

import br.milliecarol.projeto.entidade.Iniciativa;
import br.milliecarol.projeto.repositorio.IniciativaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IniciativaService {

    @Autowired
    private final ResultadoChaveService resultadoChaveService;

    private final IniciativaRepository iniciativaRepository;

    public IniciativaService(IniciativaRepository iniciativaRepository, ResultadoChaveService resultadoChaveService) {
        this.iniciativaRepository = iniciativaRepository;
        this.resultadoChaveService = resultadoChaveService;
    }

// CREATE
public Iniciativa cadastrar(Iniciativa novaIniciativa) {
    
    if(buscarPorId(novaIniciativa.getId()) != null) return null;
    try {
        return iniciativaRepository.save(novaIniciativa);
    } catch(Exception ex) {
        ex.printStackTrace();
        return null;
    }
}

// READ
public List<Iniciativa> listar() {
    return iniciativaRepository.findAll();
}

// DELETE
public void apagar(Long id) {
    iniciativaRepository.deleteById(id);
}

// UPDATE
public Iniciativa salvar(Iniciativa novaiIniciativa) {
    if(buscarPorId(novaiIniciativa.getId()) == null) return null;
    try {
        return iniciativaRepository.save(novaiIniciativa);
    } catch(Exception ex) {
        ex.printStackTrace();
        return null;
    }
}
    

// READ
    public List<Iniciativa> buscarPorTitulo(String titulo) {
        return iniciativaRepository.findByTituloContainingIgnoreCase(titulo);
    }
// READ
    public List<Iniciativa> buscarPorDescricao(String desc) {
        return iniciativaRepository.findByDescContainingIgnoreCase(desc);
    }
//READ
    public List<Iniciativa> buscarPorPorcentagem(double porcentagemConcIndividual) {
        return iniciativaRepository.findByPorcentagemConcIndividual(porcentagemConcIndividual);
    }

    public List<Iniciativa> buscarPorResultadoChave(Long krId) {
        return iniciativaRepository.findByKrId(krId);
    }

//READ
    public Iniciativa buscarPorId(Long id) {
            Optional<Iniciativa> resp = iniciativaRepository.findById(id);
            if(resp.isPresent()) return resp.get();
            return null;
    }

    // CALCULO PORCENTAGEM
    public Iniciativa atualizarPorcentagem(Long id, double porcentagem) {
        Iniciativa iniciativa = buscarPorId(id);
        if (iniciativa != null) {
            iniciativa.setPorcentagemConcIndividual(porcentagem);
            Iniciativa atualizada = iniciativaRepository.save(iniciativa);
            
            // Atualiza o KR relacionado
            if (atualizada.getKr() != null) {
                resultadoChaveService.atualizarPorcentagemKr(atualizada.getKr().getId());
            }
            return atualizada;
        }
        return null;
    }
}
