//Carolina Sun R. N. Castilho – 10386494
//Millie Talala Zogheib - 10443653 
package br.milliecarol.projeto.servico;

import br.milliecarol.projeto.entidade.Iniciativa;
import br.milliecarol.projeto.repositorio.IniciativaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * responsável pela lógica de negócios relacionadas a iniciativas
 * gerencia CRUD + cálculos de porcentagem de conclusão
 */
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
/**
 * cadastra uma nova iniciativa e atualiza o kr relacionado
 * @param novaIniciativa Iniciativa que será cadstrada
 * @return Iniciativa cadastrada
 * @throws IllegalArgumentException se o kr não foi informado ou não existir
 */
public Iniciativa cadastrar(Iniciativa novaIniciativa) {
    if (novaIniciativa == null || novaIniciativa.getKr() == null || novaIniciativa.getKr().getId() == null) {
        throw new IllegalArgumentException("Resultado-chave (kr.id) é obrigatório.");
    }

    var kr = resultadoChaveService.buscarPorId(novaIniciativa.getKr().getId());
    if (kr == null) {
        throw new IllegalArgumentException("Resultado-chave com id " + novaIniciativa.getKr().getId() + " não encontrado.");
    }

    novaIniciativa.setKr(kr);
    Iniciativa saved = iniciativaRepository.save(novaIniciativa);

    resultadoChaveService.atualizarPorcentagemKr(kr.getId());

    return saved;
}

// READ
public List<Iniciativa> listar() {
    return iniciativaRepository.findAll();
}

// DELETE
public void apagar(Long id) {
    Iniciativa iniciativa = buscarPorId(id);
    if (iniciativa != null && iniciativa.getKr()!=null){
        Long krId = iniciativa.getKr().getId();
        iniciativaRepository.deleteById(krId);
    }else{
        iniciativaRepository.deleteById(id);
    }
}

// UPDATE
public Iniciativa salvar(Iniciativa novaiIniciativa) {
    if(buscarPorId(novaiIniciativa.getId()) == null) return null;
    try {

        Iniciativa saved = iniciativaRepository.save(novaiIniciativa);
        if(saved.getKr()!=null){
            resultadoChaveService.atualizarPorcentagemKr(saved.getKr().getId());
        }
        return saved;
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

/**
 * atualiza a porcentagem de conclusão de uma iniciativa e a cascata de atualizações para o kr relacionado
 * @param id Id da iniciativa
 * @param porcentagem Nova porcentagem de 0 a 100
 * @return Iniciativa atualizada ou null
 */
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
