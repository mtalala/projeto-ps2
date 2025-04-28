package br.milliecarol.projeto.servico;

import br.milliecarol.projeto.entidade.ResultadoChave;
import br.milliecarol.projeto.repositorio.ResultadoChaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultadoChaveService {

    private final ResultadoChaveRepository resultadoChaveRepository;

    public ResultadoChaveService(ResultadoChaveRepository resultadoChaveRepository) {
        this.resultadoChaveRepository = resultadoChaveRepository;
    }

    // CREATE
    public ResultadoChave cadastrar(ResultadoChave novoKr) {
        if (buscarPorId(novoKr.getId()) != null) return null;
        try {
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
}
