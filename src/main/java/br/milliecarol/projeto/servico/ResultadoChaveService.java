//Carolina Sun R. N. Castilho – 10386494
//Millie Talala Zogheib - 10443653 
package br.milliecarol.projeto.servico;

import br.milliecarol.projeto.entidade.Objetivo;
import br.milliecarol.projeto.entidade.ResultadoChave;
import br.milliecarol.projeto.repositorio.ResultadoChaveRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * responsável pela lógica de negócios do resultado chave(krs)
 * gerencia CRUD + cálculo de porcentagem de conclusão
 */
@Service
public class ResultadoChaveService {
    @Autowired
    private ObjetivoService objetivoService;
    
    @Autowired
    private final ResultadoChaveRepository resultadoChaveRepository;

    public ResultadoChaveService(ResultadoChaveRepository resultadoChaveRepository) {
        this.resultadoChaveRepository = resultadoChaveRepository;
    }

    // CREATE
    public ResultadoChave cadastrar(ResultadoChave novoKr) {
        if (novoKr == null || novoKr.getObj() == null || novoKr.getObj().getId() == null) {
            throw new IllegalArgumentException("O campo 'obj.id' é obrigatório.");
        }

        var objetivo = objetivoService.buscarPorId(novoKr.getObj().getId());
        if (objetivo == null) {
            throw new IllegalArgumentException("Objetivo com id " + novoKr.getObj().getId() + " não encontrado.");
        }

        novoKr.setObj(objetivo);
        return resultadoChaveRepository.save(novoKr);
    }

    // READ
    public List<ResultadoChave> listar() {
        return resultadoChaveRepository.findAll();
    }

    // READ
    public ResultadoChave buscarPorId(Long id) {
        Optional<ResultadoChave> resp = resultadoChaveRepository.findById(id);
        if (resp.isPresent()) return resp.get();
        return null;
    }

    // DELETE
    /**
     * remove um resultado chave e atualiza a porcentagem do objetivo relacionado
     * @param id ID do kr a ser removido
     */
    public void apagar(Long id) {
         ResultadoChave kr = buscarPorId(id);
        if (kr != null) {
            Objetivo objetivo = kr.getObj();
            resultadoChaveRepository.deleteById(id);
            
            // atualiza o obj relacionado
            if (objetivo != null) {
                objetivoService.atualizarPorcentagemObjetivo(objetivo.getId());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultado-Chave não encontrado");
        }
    }

    // UPDATE
    public ResultadoChave salvar(ResultadoChave novoKr) {
        if (buscarPorId(novoKr.getId()) == null) return null;
        try {
            return resultadoChaveRepository.save(novoKr);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // READ
    public List<ResultadoChave> buscarPorDescricao(String desc) {
        return resultadoChaveRepository.findByDescContainingIgnoreCase(desc);
    }

    public List<ResultadoChave> buscarPorMeta(String meta) {
        return resultadoChaveRepository.findByMetaContainingIgnoreCase(meta);
    }

    public List<ResultadoChave> buscarPorPorcentagem(double porcentagem) {
        return resultadoChaveRepository.findByPorcentagemConc(porcentagem);
    }

    //CALCULO %
    /**
     * atualiza a porcentagem de conclusão de um kr específico e cascata a atualiização para o obj relaciionado
     * 
     * @param krId id do kr a que vai ser atualizado
     */
    public void atualizarPorcentagemKr(Long krId) {
        ResultadoChave kr = buscarPorId(krId);
        if (kr != null) {
            kr.calcularPorcentagemConc();
            resultadoChaveRepository.save(kr);
            
            // Atualiza o Objetivo relacionado
            if (kr.getObj() != null) {
                objetivoService.atualizarPorcentagemObjetivo(kr.getObj().getId());
            }
        }
    }
}
