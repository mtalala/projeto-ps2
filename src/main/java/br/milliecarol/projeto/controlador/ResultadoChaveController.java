//Carolina Sun R. N. Castilho – 10386494
//Millie Talala Zogheib - 10443653 
package br.milliecarol.projeto.controlador;

import br.milliecarol.projeto.entidade.ResultadoChave;
import br.milliecarol.projeto.servico.ResultadoChaveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * controlador para gerenciamento de resultados chave
 * endpoints para operações CRUD e porcentagem cálculo
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/resultados-chave")
public class ResultadoChaveController {

    private final ResultadoChaveService resultadoChaveService;

    public ResultadoChaveController(ResultadoChaveService resultadoChaveService) {
        this.resultadoChaveService = resultadoChaveService;
    }

    // CREATE
    /**
     * cadastra um novo Resultado Chave no sistema.
     * @param novoKr dados do kr a ser criado
     * @return kr criado
     * @throws ResponseStatusException 400 se houver problema com os dados
     */
    @PostMapping
    public ResultadoChave cadastrar(@RequestBody ResultadoChave novoKr) {
        ResultadoChave criado = resultadoChaveService.cadastrar(novoKr);
        if (criado == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível cadastrar o Resultado-Chave");
        }
        return criado;
    }


     // READ
     /**
     * retorna todos os KRs cadastrados.
     * @return Lista de KRs
     * @throws ResponseStatusException 404 se nenhum KR for encontrado
     */
    @GetMapping
    public List<ResultadoChave> listar() {
        List<ResultadoChave> resultados = resultadoChaveService.listar();
        if (!resultados.isEmpty()) {
            return resultados;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum resultado-chave encontrado");
    }

    
    /**
     * busca um KR por ID.
     * @param id ID do KR
     * @return KR encontrado
     * @throws ResponseStatusException 404 se não encontrado
     */
    @GetMapping("/{id}")
    public ResultadoChave buscarPorId(@PathVariable Long id) {
        ResultadoChave kr = resultadoChaveService.buscarPorId(id);
        if (kr == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultado-Chave não encontrado");
        }
        return kr;
    }


    /**
     * busca krs por descrição (contendo o texto, case insensitive).
     * @param desc texto para busca
     * @return KRs encontrados
     * @throws ResponseStatusException 404 se nenhum KR for encontrado
     */
    @GetMapping("/descricao/{desc}")
    public List<ResultadoChave> buscarPorDescricao(@PathVariable("desc") String desc) {
        List<ResultadoChave> krs = resultadoChaveService.buscarPorDescricao(desc);
        if (!krs.isEmpty()) return krs;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Resultado-Chave encontrado com essa descrição");
    }

    @GetMapping("/meta/{meta}")
    public List<ResultadoChave> buscarPorMeta(@PathVariable("meta") String meta) {
        List<ResultadoChave> krs = resultadoChaveService.buscarPorMeta(meta);
        if (!krs.isEmpty()) return krs;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Resultado-Chave encontrado com essa meta");
    }

    @GetMapping("/porcentagem/{porcentagem}")
    public List<ResultadoChave> buscarPorPorcentagem(@PathVariable("porcentagem") double porcentagem) {
        List<ResultadoChave> krs = resultadoChaveService.buscarPorPorcentagem(porcentagem);
        if (!krs.isEmpty()) return krs;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Resultado-Chave encontrado com essa porcentagem");
    }

    // DELETE
    /**
     * remove um KR do sistema.
     * @param id ID do KR a ser removido
     */
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable Long id) {
        ResultadoChave kr = resultadoChaveService.buscarPorId(id);
        if (kr == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultado-Chave não encontrado para deletar");
        }
        resultadoChaveService.apagar(id);
    }

    // UPDATE
    /**
     * atualiza um kr existente.
     * @param id ID do kr a ser atualizado
     * @param novoKr dados atualizados do kr
     * @return kr atualizado
     * @throws ResponseStatusException 404 se o kr não for encontrado
     */
    @PutMapping("/{id}")
    public ResultadoChave salvar(@PathVariable Long id, @RequestBody ResultadoChave novoKr) {
        novoKr.setId(id);
        ResultadoChave salvo = resultadoChaveService.salvar(novoKr);
        if (salvo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultado-Chave não encontrado para atualização");
        }
        return salvo;
    }


    //Lógica calculo
    /**
     * recalcula a porcentagem de conclusão de um kr específico.
     * @param id ID do kr
     * @return ResponseEntity vazio com status
     */
    @PutMapping("/{id}/recalcular")
    public ResponseEntity<Void> recalcularPorcentagem(@PathVariable Long id) {
        resultadoChaveService.atualizarPorcentagemKr(id);
        return ResponseEntity.ok().build();
    }
}
