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

@RestController
@RequestMapping("/objetivos")
public class ObjetivoController {

    private final ObjetivoRepository objetivoRepository;

    private final ObjetivoService objetivoService;

    public ObjetivoController(ObjetivoService objetivoService, ObjetivoRepository objetivoRepository) {
        this.objetivoService = objetivoService;
        this.objetivoRepository = objetivoRepository;
    }

 // CREATE
 @PostMapping("/objetivos")
 public Objetivo registrarLivro(@RequestBody Objetivo novoObjetivo) {
     Objetivo resp = objetivoService.cadastrar(novoObjetivo);
     if(resp != null) return resp;
     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema com os dados informados");
 }

  // READ
  @GetMapping
  public List<Objetivo> listar() {
      List<Objetivo> objetivos = objetivoService.listar();
      if (!objetivos.isEmpty()) {
          return objetivos;
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum objetivo encontrado");
  }

// READ
    @GetMapping("/objetivos/{titulo}")
    public List<Objetivo> buscarPorTitulo(@PathVariable("titulo") String titulo) {
        List<Objetivo> objetivos = objetivoService.buscarPorTitulo(titulo);
        if (!objetivos.isEmpty()) return objetivos;

         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum objetivo encontrado com esse título");
    }

// READ
    @GetMapping("/descricao/{descricao}")
    public List<Objetivo> buscarPorDescricao(@PathVariable("descricao") String descricao) {
        List<Objetivo> objetivos = objetivoService.buscarPorDescricao(descricao);
        if (!objetivos.isEmpty()) return objetivos;

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum objetivo encontrado com essa descrição");
    }

// READ
    @GetMapping("/porcentagem/{porcentagemConcGeral}")
    public List<Objetivo> buscarPorPorcentagem(@PathVariable("porcentagemConcGeral") Double porcentagemConcGeral) {
        List<Objetivo> objetivos = objetivoService.buscarPorPorcentagem(porcentagemConcGeral);
        if (!objetivos.isEmpty()) return objetivos;

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum objetivo encontrado com essa porcentagem");
    }

// UPDATE
@PutMapping("/objetivos/{id}")
public Objetivo salvar(@PathVariable("id") Long id, @RequestBody Objetivo novoObjetivo) {
    novoObjetivo.setId(id);
    Objetivo resp = objetivoService.salvar(novoObjetivo);
    if(resp != null) return resp;
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema com os dados informados");
}

// DELETE
@DeleteMapping("/objetivos/{id}")
public void apagar(@PathVariable("id") Long id) {
    objetivoService.apagar(id);
}

//Lógica calculo %

@PutMapping("/{id}/recalcular-porcentagem")
    public ResponseEntity<Objetivo> recalcularPorcentagem(@PathVariable Long id) {
        Objetivo objetivo = objetivoService.buscarPorId(id);
        objetivo.calcularPorcentagemConcGeral(); // Chama o método da entidade
        return ResponseEntity.ok(objetivoService.salvar(objetivo));
    }

}
