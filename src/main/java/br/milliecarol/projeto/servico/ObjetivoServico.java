package br.milliecarol.projeto.servico;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import br.milliecarol.projeto.entidade.Objetivo;
import br.milliecarol.projeto.repositorio.ObjetivoRepository;

public class ObjetivoServico {
    @Autowired
    private ObjetivoRepository objetivoRepository;
    private final Scanner scanner = new Scanner(System.in);

    public void cadastrar(Objetivo objetivo) {
        if (objetivo != null) {
            objetivoRepository.save(objetivo);
            System.out.println("Objetivo cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar Objetivo. Dados inválidos.");
        }
    }

    public void listar() {
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

    public void deletar(Long id) {
        objetivoRepository.deleteById(id);
    }

    public Objetivo salvar(Objetivo objetivo) {
        return objetivoRepository.save(objetivo);
    }

    public void buscar() {
        System.out.println("\n[Busca de Objetivos]");
        System.out.println("1. Buscar por Título");
        System.out.println("2. Buscar por Descrição");
        System.out.println("3. Buscar por Porcentagem de Conclusão Geral");
        System.out.println("4. Buscar por ID");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); 
    
        switch (opcao) {
            case 1:
                buscarPorTitulo();
                break;
            case 2:
                buscarPorDescricao();
                break;
            case 3:
                buscarPorPorcentagem();
                break;
            case 4:
                buscarPorId();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    public void buscarPorTitulo() {
        System.out.print("\nDigite o título desejado: ");
        String titulo = scanner.nextLine();
        List<Objetivo> objetivos = objetivoRepository.findByTituloContainingIgnoreCase(titulo);
    
        if (objetivos.isEmpty()) {
            System.out.println("Nenhum objetivo encontrado com este título.");
            return;
        }
    
        System.out.println("\n[Objetivos Encontrados por Título]");
        for (Objetivo obj : objetivos) {
            System.out.printf("- %s: %s (%.2f%%)%n", obj.getTitulo(), obj.getDesc(), obj.getPorcentagemConcGeral());
        }
    }
    
    public void buscarPorDescricao() {
        System.out.print("\nDigite a descrição desejada: ");
        String desc = scanner.nextLine();
        List<Objetivo> objetivos = objetivoRepository.findByDescContainingIgnoreCase(desc);
    
        if (objetivos.isEmpty()) {
            System.out.println("Nenhum objetivo encontrado com esta descrição.");
            return;
        }
    
        System.out.println("\n[Objetivos Encontrados por Descrição]");
        for (Objetivo obj : objetivos) {
            System.out.printf("- %s: %s (%.2f%%)%n", obj.getTitulo(), obj.getDesc(), obj.getPorcentagemConcGeral());
        }
    }
    
    public void buscarPorPorcentagem() {
        System.out.print("\nDigite a porcentagem desejada: ");
        double porcentagem = scanner.nextDouble();
        scanner.nextLine(); 
    
        List<Objetivo> objetivos = objetivoRepository.findByPorcentagemConcGeral(porcentagem);
    
        if (objetivos.isEmpty()) {
            System.out.println("Nenhum objetivo encontrado com esta porcentagem.");
            return;
        }
    
        System.out.println("\n[Objetivos Encontrados por Porcentagem de Conclusão]");
        for (Objetivo obj : objetivos) {
            System.out.printf("- %s: %s (%.2f%%)%n", obj.getTitulo(), obj.getDesc(), obj.getPorcentagemConcGeral());
        }
    }
    
    public void buscarPorId() {
        System.out.print("\nDigite o ID desejado: ");
        long id = scanner.nextLong();
        scanner.nextLine(); 
    
        List<Objetivo> objetivos = objetivoRepository.findById(id);
    
        if (objetivos.isEmpty()) {
            System.out.println("Nenhum objetivo encontrado com este ID.");
            return;
        }
    
        System.out.println("\n[Objetivo Encontrado por ID]");
        for (Objetivo obj : objetivos) {
            System.out.printf("- %s: %s (%.2f%%)%n", obj.getTitulo(), obj.getDesc(), obj.getPorcentagemConcGeral());
        }
    }
    
    
}
