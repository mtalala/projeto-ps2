//Carolina Sun R. N. Castilho – 10386494
//Millie Talala Zogheib - 10443653 
package br.milliecarol.projeto.servico;

import br.milliecarol.projeto.entidade.ResultadoChave;
import br.milliecarol.projeto.repositorio.ResultadoChaveRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        try {
            // objetivo é obrigatório
            if (novoKr.getObj() == null || novoKr.getObj().getId() == null) {
                throw new IllegalArgumentException("Objetivo (id) é obrigatório.");
            }

            // garante que o objetivo existe
            var objetivo = objetivoService.buscarPorId(novoKr.getObj().getId());
            if (objetivo == null) {
                throw new IllegalArgumentException("Objetivo com id " + novoKr.getObj().getId() + " não encontrado.");
            }
            novoKr.setObj(objetivo);

            // salva o KR com o objetivo associado
            return resultadoChaveRepository.save(novoKr);

        } catch (Exception ex) {
            ex.printStackTrace(); 
            return null;
        }
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
    public void apagar(Long id) {
        resultadoChaveRepository.deleteById(id);
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
