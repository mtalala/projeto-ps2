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


@RestController
@RequestMapping("/resultados-chave")
public class ResultadoChaveController {

    private final ResultadoChaveService resultadoChaveService;

    public ResultadoChaveController(ResultadoChaveService resultadoChaveService) {
        this.resultadoChaveService = resultadoChaveService;
    }

    // CREATE
    @PostMapping
    public ResultadoChave cadastrar(@RequestBody ResultadoChave novoKr) {
        ResultadoChave criado = resultadoChaveService.cadastrar(novoKr);
        if (criado == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível cadastrar o Resultado-Chave");
        }
        return criado;
    }

     // READ
    @GetMapping
    public List<ResultadoChave> listar() {
        List<ResultadoChave> resultados = resultadoChaveService.listar();
        if (!resultados.isEmpty()) {
            return resultados;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum resultado-chave encontrado");
    }

    @GetMapping("/{id}")
    public ResultadoChave buscarPorId(@PathVariable Long id) {
        ResultadoChave kr = resultadoChaveService.buscarPorId(id);
        if (kr == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultado-Chave não encontrado");
        }
        return kr;
    }

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
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable Long id) {
        ResultadoChave kr = resultadoChaveService.buscarPorId(id);
        if (kr == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultado-Chave não encontrado para deletar");
        }
        resultadoChaveService.apagar(id);
    }

    // UPDATE
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
    @PutMapping("/{id}/recalcular")
    public ResponseEntity<Void> recalcularPorcentagem(@PathVariable Long id) {
        resultadoChaveService.atualizarPorcentagemKr(id);
        return ResponseEntity.ok().build();
    }
}
