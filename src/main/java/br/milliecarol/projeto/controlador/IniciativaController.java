//Carolina Sun R. N. Castilho – 10386494
//Millie Talala Zogheib - 10443653 

package br.milliecarol.projeto.controlador;
import br.milliecarol.projeto.entidade.Iniciativa;
import br.milliecarol.projeto.servico.IniciativaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * controlador REST para gerenciamento de Iniciativas.
 * endpoints para operações CRUD e atualização de progresso.
 */
@RestController
@RequestMapping("/iniciativas")
public class IniciativaController {

    private final IniciativaService iniciativaService;

    public IniciativaController(IniciativaService iniciativaService) {
        this.iniciativaService = iniciativaService;
    }

    // CREATE
    /**
     * cadastra uma nova iniciativa no sistema.
     * @param novaIniciativa dados da iniciativa a ser criada
     * @return Iniciativa criada
     * @throws ResponseStatusException 400 se o KR não for informado ou não existir
     */
    @PostMapping
    public Iniciativa cadastrar(@RequestBody Iniciativa novaIniciativa) {
        //Iniciativa criada = iniciativaService.cadastrar(novaIniciativa);
       
        try {
            return iniciativaService.cadastrar(novaIniciativa);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
      
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

    //CALCULO 
    /**
     * atualiza o progresso (porcentagem de conclusão) de uma iniciativa.
     * @param id ID da iniciativa
     * @param porcentagem nova porcentagem de 0 a100
     * @return iniciativa atualizada
     * @throws ResponseStatusException 400 se a porcentagem for inválida
     * @throws ResponseStatusException 404 se a iniciativa não for encontrada
     */
    @PutMapping("/{id}/progresso")
    public ResponseEntity<Iniciativa> atualizarProgresso(
            @PathVariable Long id,
            @RequestParam double porcentagem) {
        
        if (porcentagem < 0 || porcentagem > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Porcentagem deve estar entre 0 e 100");
        }

        Iniciativa iniciativa = iniciativaService.atualizarPorcentagem(id, porcentagem);
        if (iniciativa != null) {
            return ResponseEntity.ok(iniciativa);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Iniciativa não encontrada");
    }

}
