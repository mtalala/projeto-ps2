package br.milliecarol.projeto.servico;

import br.milliecarol.projeto.entidade.Iniciativa;
import br.milliecarol.projeto.repositorio.IniciativaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IniciativaService {

    private final IniciativaRepository iniciativaRepository;

    public IniciativaService(IniciativaRepository iniciativaRepository) {
        this.iniciativaRepository = iniciativaRepository;
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
}
