package br.milliecarol.projeto.servico;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import br.milliecarol.projeto.entidade.*;
import br.milliecarol.projeto.repositorio.*;

public class ResultadoChaveServico {

    @Autowired
    private ResultadoChaveRepository resultadoChaveRepository;
    private final Scanner scanner = new Scanner(System.in);

    public void listar() {
        List<ResultadoChave> resultados = resultadoChaveRepository.findAll();
        if (resultados.isEmpty()) {
            System.out.println("\nNenhum Resultado Chave cadastrado!");
            return;
        }

        System.out.println("\n[Listagem Completa dos Resultados Chave]");
        System.out.println("ID | Descrição               | Meta                | Porcentagem de Conclusão | Objetivo |");
        System.out.println("-------------------------------------------------------------------------------");
        for (ResultadoChave resultado : resultados) {
            System.out.printf("%-3d | %-25s | %-15s | %-4d%% | %-15s%n",
                    resultado.getId(), resultado.getDesc(), resultado.getMeta(), resultado.getPorcentagemConc(), resultado.getObj().getTitulo());
        }
    }

    public void deletar(Long id) {
        resultadoChaveRepository.deleteById(id);
    }

    public ResultadoChave salvar(ResultadoChave resultadoChave) {
        return resultadoChaveRepository.save(resultadoChave);
    }

    public void buscar() {
        System.out.println("\n[Busca de Resultados Chave]");
        System.out.println("1. Buscar por Descrição");
        System.out.println("2. Buscar por Meta");
        System.out.println("3. Buscar por Porcentagem de Conclusão");
        System.out.println("4. Buscar por ID");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); 
    
        switch (opcao) {
            case 1:
                buscarPorDescricao();
                break;
            case 2:
                buscarPorMeta();
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

    public void buscarPorDescricao() {
        System.out.print("\nDigite a descrição desejada: ");
        String desc = scanner.nextLine();
        List<ResultadoChave> resultados = resultadoChaveRepository.findByDescContainingIgnoreCase(desc);
    
        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado chave encontrado com esta descrição.");
            return;
        }
    
        System.out.println("\n[Resultados Chave Encontrados por Descrição]");
        for (ResultadoChave resultado : resultados) {
            System.out.printf("- %s | Meta: %s | Porcentagem de Conclusão: %.2f%% | Objetivo: %s%n",
                    resultado.getDesc(), resultado.getMeta(), resultado.getPorcentagemConc(), resultado.getObj().getTitulo());
        }
    }

    public void buscarPorMeta() {
        System.out.print("\nDigite a meta desejada: ");
        String meta = scanner.nextLine();
        List<ResultadoChave> resultados = resultadoChaveRepository.findByMetaContainingIgnoreCase(meta);
    
        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado chave encontrado com esta meta.");
            return;
        }
    
        System.out.println("\n[Resultados Chave Encontrados por Meta]");
        for (ResultadoChave resultado : resultados) {
            System.out.printf("- %s | Descrição: %s | Porcentagem de Conclusão: %.2f%% | Objetivo: %s%n",
                    resultado.getMeta(), resultado.getDesc(), resultado.getPorcentagemConc(), resultado.getObj().getTitulo());
        }
    }

    public void buscarPorPorcentagem() {
        System.out.print("\nDigite a porcentagem desejada: ");
        double porcentagem = scanner.nextDouble();
        scanner.nextLine(); 
    
        List<ResultadoChave> resultados = resultadoChaveRepository.findByPorcentagemConc(porcentagem);
    
        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado chave encontrado com esta porcentagem.");
            return;
        }
    
        System.out.println("\n[Resultados Chave Encontrados por Porcentagem de Conclusão]");
        for (ResultadoChave resultado : resultados) {
            System.out.printf("- %s | Descrição: %s | Meta: %s | Objetivo: %s%n",
                    resultado.getDesc(), resultado.getMeta(), resultado.getPorcentagemConc(), resultado.getObj().getTitulo());
        }
    }

    public void buscarPorId() {
        System.out.print("\nDigite o ID desejado: ");
        long id = scanner.nextLong();
        scanner.nextLine(); 
    
        Optional<ResultadoChave> resultadoOpt = resultadoChaveRepository.findById(id);
    
        if (resultadoOpt.isEmpty()) {
            System.out.println("Nenhum resultado chave encontrado com este ID.");
            return;
        }
    
        ResultadoChave resultado = resultadoOpt.get();
        System.out.println("\n[Resultado Chave Encontrado por ID]");
        System.out.printf("- %s | Meta: %s | Porcentagem de Conclusão: %.2f%% | Objetivo: %s%n",
                resultado.getDesc(), resultado.getMeta(), resultado.getPorcentagemConc(), resultado.getObj().getTitulo());
    }
}
