//Carolina Sun R. N. Castilho – 10386494
//Millie Talala Zogheib - 10443653 
package br.milliecarol.projeto.servico;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.milliecarol.projeto.entidade.Objetivo;
import br.milliecarol.projeto.repositorio.ObjetivoRepository;

/**
 * responsável pela lógica de negócios relacionada a Objetivos
 * ele gerencia as operações CRUD e cálculo de porcentagem de conclusão
 */
@Service
public class ObjetivoService {
    @Autowired
    private ObjetivoRepository objetivoRepository;

// CREATE
 /**
     * Cadastra um novo objetivo
     * @param novoObjetivo Objetivo a ser cadastrado
     * @return Objetivo cadastrado null se ja existir
     */
    public Objetivo cadastrar(Objetivo novoObjetivo) {
        if(buscarPorId(novoObjetivo.getId()) != null) return null;
        try {
            return objetivoRepository.save(novoObjetivo);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


   // READ
    public List<Objetivo> listar() {
        return objetivoRepository.findAll();
    }

// DELETE
    public void apagar(Long id) {
        objetivoRepository.deleteById(id);
    }

// UPDATE
    public Objetivo salvar(Objetivo novoObjetivo) {
        if(buscarPorId(novoObjetivo.getId()) == null) return null;
        try {
            return objetivoRepository.save(novoObjetivo);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Objetivo> buscarPorTitulo(String titulo) {
        return objetivoRepository.findByTituloContainingIgnoreCase(titulo);
    }
    
    public List<Objetivo> buscarPorDescricao(String desc) {
        return objetivoRepository.findByDescContainingIgnoreCase(desc);
    }
    
    public List<Objetivo> buscarPorPorcentagem(Double porcentagemConcGeral) {
        return objetivoRepository.findByPorcentagemConcGeral(porcentagemConcGeral);
    }

    public Objetivo buscarPorId(Long id) {
        Optional<Objetivo> resp = objetivoRepository.findById(id);
        if(resp.isPresent()) return resp.get();
        return null;
    }
    
    //CALCULO %
    /**
     * atualiza a porcentagem de conclusão de um objetivo espeifico
     * @param objetivoId do objetivo a ser atualizado
     */
    public void atualizarPorcentagemObjetivo(Long objetivoId) {
        Objetivo objetivo = buscarPorId(objetivoId);
        if (objetivo != null) {
            objetivo.calcularPorcentagemConcGeral();
            objetivoRepository.save(objetivo);
        }
    }
}
