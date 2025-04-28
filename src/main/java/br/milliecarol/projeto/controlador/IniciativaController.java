package br.milliecarol.projeto.controlador;

import br.milliecarol.projeto.entidade.Iniciativa;
import br.milliecarol.projeto.servico.IniciativaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/iniciativas")
public class IniciativaController {

    private final IniciativaService iniciativaService;

    public IniciativaController(IniciativaService iniciativaService) {
        this.iniciativaService = iniciativaService;
    }

    // CREATE
    @PostMapping
    public Iniciativa cadastrar(@RequestBody Iniciativa novaIniciativa) {
        Iniciativa criada = iniciativaService.cadastrar(novaIniciativa);
        if (criada == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível cadastrar a iniciativa");
        }
        return criada;
    }

    // READ
    @GetMapping
    public List<Iniciativa> listar() {
        List<Iniciativa> iniciativas = iniciativaService.listar();
        if (!iniciativas.isEmpty()) {
            return iniciativas;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma iniciativa encontrada");
    }


    // READ
    @GetMapping("/titulo/{titulo}")
    public List<Iniciativa> buscarPorTitulo(@PathVariable("titulo") String titulo) {
        List<Iniciativa> iniciativas = iniciativaService.buscarPorTitulo(titulo);
        if (!iniciativas.isEmpty()) return iniciativas;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma iniciativa encontrada com esse título");
    }

    // READ
    @GetMapping("/descricao/{descricao}")
    public List<Iniciativa> buscarPorDescricao(@PathVariable("descricao") String descricao) {
        List<Iniciativa> iniciativas = iniciativaService.buscarPorDescricao(descricao);
        if (!iniciativas.isEmpty()) return iniciativas;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma iniciativa encontrada com essa descrição");
    }

    // READ
    @GetMapping("/porcentagem/{porcentagem}")
    public List<Iniciativa> buscarPorPorcentagem(@PathVariable("porcentagem") double porcentagem) {
        List<Iniciativa> iniciativas = iniciativaService.buscarPorPorcentagem(porcentagem);
        if (!iniciativas.isEmpty()) return iniciativas;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma iniciativa encontrada com essa porcentagem");
    }

    // READ
    @GetMapping("/resultado-chave/{krId}")
    public List<Iniciativa> buscarPorResultadoChave(@PathVariable("krId") Long krId) {
        List<Iniciativa> iniciativas = iniciativaService.buscarPorResultadoChave(krId);
        if (!iniciativas.isEmpty()) return iniciativas;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma iniciativa encontrada com esse Resultado Chave");
    }

    // READ (Buscar por ID)
    @GetMapping("/{id}")
    public Iniciativa buscarPorId(@PathVariable Long id) {
        Iniciativa iniciativa = iniciativaService.buscarPorId(id);
        if (iniciativa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Iniciativa não encontrada");
        }
        return iniciativa;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void apagar(@PathVariable Long id) {
        Iniciativa iniciativa = iniciativaService.buscarPorId(id);
        if (iniciativa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Iniciativa não encontrada para deletar");
        }
        iniciativaService.apagar(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Iniciativa salvar(@PathVariable("id") Long id, @RequestBody Iniciativa novaIniciativa) {
        novaIniciativa.setId(id);
        Iniciativa resp = iniciativaService.salvar(novaIniciativa);
        if(resp != null) return resp;
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema com os dados informados");
    }
}
