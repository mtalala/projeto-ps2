package br.milliecarol.projeto.servico;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import br.milliecarol.projeto.entidade.Objetivo;
import br.milliecarol.projeto.repositorio.ObjetivoRepository;

public class ObjetivoServico {
    @Autowired
    private ObjetivoRepository objetivoRepository;
    private final Scanner scanner = new Scanner(System.in);

    // private void cadastrar() {
    //     System.out.println("===== Cadastro de Objetivos =====");
    //     System.out.print("Digite o título: ");
    //     String titulo = scanner.nextLine();

    //     System.out.print("Digite a descrição: ");
    //     String desc = scanner.nextLine();

    //     System.out.print("Digite a porcentagem de conclusão geral: ");
    //     double porcentagemConcGeral = scanner.nextDouble();
    //     scanner.nextLine();

    //     System.out.print("Digite o ID: ");
    //     long id = scanner.nextLong();

    //     if (objetivoRepositoryRepository.existsByTituloAndDesc(titulo, desc)) {
    //         System.out.println("Erro: Já existe um objetivo com este título e descrição!");
    //         return;
    //     }

    //     Objetivo obj = new Objetivo(long id, String titulo, String desc, double porcentagemConcGeral);
    //     objetivoRepository.save(obj);

    //     System.out.println("Objetivo cadastrado com sucesso!");
    // }

    private void listar() {
        List<Objetivo> objetivos = objetivoRepository.findAll();
        if (objetivos.isEmpty()) {
            System.out.println("\nNenhum objetivo cadastrado!");
            return;
        }

        System.out.println("\n[Listagem Completa dos Objetivos]");
        System.out.println("ID | Título                      | Descrição           | Porcentagem de Conclusão Geral |");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Objetivo objetivo : objetivos) {
            System.out.printf("%-3d | %-25s | %-15s | %-4d | %-20s%n",
                    objetivo.getId(), objetivo.getTitulo(), objetivo.getDesc(), objetivo.getPorcentagemConcGeral());
        }
    }

    private void deletar(Long id) {
        objetivoRepository.deleteById(id);
    }

    public Objetivo salvar(Objetivo objetivo) {
        return objetivoRepository.save(objetivo);
    }
    
}
