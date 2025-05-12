//Carolina Sun R. N. Castilho – 10386494
//Millie Talala Zogheib - 10443653 
package br.milliecarol.projeto.controlador;
import br.milliecarol.projeto.repositorio.ObjetivoRepository;
import br.milliecarol.projeto.servico.*;
import br.milliecarol.projeto.entidade.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
/**
 * controlador REST para as operações relacionadas do objetivo
 * endpoints para o CRUD e cálculo de porcentagem
 */
@RestController
@RequestMapping("/objetivos")
public class ObjetivoController {
    private final ObjetivoService objetivoService;

    public ObjetivoController(ObjetivoService objetivoService, ObjetivoRepository objetivoRepository) {
        this.objetivoService = objetivoService;
        //this.objetivoRepository = objetivoRepository;
    }

 // CREATE
 /**
  * cadatras um novo objetivo no sistema
  * @param novoObjetivo são dados do objetivo que vai ser criado
  * @return objetivo criado
  */
 @PostMapping
 public Objetivo registrarLivro(@RequestBody Objetivo novoObjetivo) {
     Objetivo resp = objetivoService.cadastrar(novoObjetivo);
     if(resp != null) return resp;
     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema com os dados informados");
 }

  // READ
  /**
   * retorna todos os objetivos cadastrados
   * @return lista de objetivos
   */
  @GetMapping
  public List<Objetivo> listar() {
      List<Objetivo> objetivos = objetivoService.listar();
      if (!objetivos.isEmpty()) {
          return objetivos;
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum objetivo encontrado");
  }

// READ
/**
 * busca pelo título
 * @param titulo texto de busca
 * @return lista de obj encontrados
 */
    @GetMapping("{titulo}")
    public List<Objetivo> buscarPorTitulo(@PathVariable("titulo") String titulo) {
        List<Objetivo> objetivos = objetivoService.buscarPorTitulo(titulo);
        if (!objetivos.isEmpty()) return objetivos;

         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum objetivo encontrado com esse título");
    }

// READ
/**
 * @param descricao pra buscar por
 * @return objetivos encontrado
 */
    @GetMapping("/descricao/{descricao}")
    public List<Objetivo> buscarPorDescricao(@PathVariable("descricao") String descricao) {
        List<Objetivo> objetivos = objetivoService.buscarPorDescricao(descricao);
        if (!objetivos.isEmpty()) return objetivos;

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum objetivo encontrado com essa descrição");
    }

// READ
/**
 * buscar obj pela porcentagem
 * @param porcentagemConcGeral para buscar por
 * @return objetivos encontrados
 */
    @GetMapping("/porcentagem/{porcentagemConcGeral}")
    public List<Objetivo> buscarPorPorcentagem(@PathVariable("porcentagemConcGeral") Double porcentagemConcGeral) {
        List<Objetivo> objetivos = objetivoService.buscarPorPorcentagem(porcentagemConcGeral);
        if (!objetivos.isEmpty()) return objetivos;

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum objetivo encontrado com essa porcentagem");
    }


// UPDATE
/**
 *  atualiza um objetivo existente
 * @param id id do objetivo a ser atualizado
 * @param novoObjetivo  dados atualizados 
 * @return objeto atualizado
 */
@PutMapping("{id}")
public Objetivo salvar(@PathVariable("id") Long id, @RequestBody Objetivo novoObjetivo) {
    novoObjetivo.setId(id);
    Objetivo resp = objetivoService.salvar(novoObjetivo);
    if(resp != null) return resp;
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema com os dados informados");
}

// DELETE
/**
 * remove um obj do sistema i
 * @param id id do obj a ser removido 
 */
@DeleteMapping("{id}")
public void apagar(@PathVariable("id") Long id) {
    objetivoService.apagar(id);
}

//Lógica calculo %
/**
 * recalcula a porcentagem de conclusão geral 
 * @param id id do objetivo
 * @return objetivo com porcentagem atualizada
 */
@PutMapping("/{id}/recalcular-porcentagem")
    public ResponseEntity<Objetivo> recalcularPorcentagem(@PathVariable Long id) {
        Objetivo objetivo = objetivoService.buscarPorId(id);
        objetivo.calcularPorcentagemConcGeral(); // Chama o método da entidade
        return ResponseEntity.ok(objetivoService.salvar(objetivo));
    }

}
