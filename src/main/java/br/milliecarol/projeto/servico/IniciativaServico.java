package br.milliecarol.projeto.servico;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import br.milliecarol.projeto.entidade.Iniciativa;
import br.milliecarol.projeto.entidade.ResultadoChave;
import br.milliecarol.projeto.repositorio.IniciativaRepository;
import br.milliecarol.projeto.repositorio.ResultadoChaveRepository;

public class IniciativaServico {
    @Autowired
    private IniciativaRepository iniciativaRepository;
    @Autowired
    private ResultadoChaveRepository resultadoChaveRepository;
    private final Scanner scanner = new Scanner(System.in);


    public void cadastrar(Iniciativa iniciativa) {
        if (iniciativa != null) {
            iniciativaRepository.save(iniciativa);
            System.out.println("Iniciativa cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar Iniciativa. Dados inválidos.");
        }
    }

    public void listar() {
        List<Iniciativa> iniciativas = iniciativaRepository.findAll();
        if (iniciativas.isEmpty()) {
            System.out.println("\nNenhuma iniciativa cadastrada!");
            return;
        }

        System.out.println("\n[Listagem Completa das Iniciativas]");
        System.out.println("KR | Título | Descrição | Porcentagem de Conclusão Individual");
        System.out.println("--------------------------------------------------------");
        for (Iniciativa iniciativa : iniciativas) {
            System.out.printf("- %s: %s (%.2f%%)%n",
                    iniciativa.getKr(), iniciativa.getTitulo(), iniciativa.getDesc(), iniciativa.getPorcentagemConcIndividual());
        }
    }

    public void deletar(Long id) {
        iniciativaRepository.deleteById(id);
    }

    public Iniciativa salvar(Iniciativa iniciativa) {
        return iniciativaRepository.save(iniciativa);
    }

    public void buscar() {
        System.out.println("\n[Busca de Iniciativas]");
        System.out.println("1. Buscar por Título");
        System.out.println("2. Buscar por Descrição");
        System.out.println("3. Buscar por Porcentagem de Conclusão Individual");
        System.out.println("4. Buscar por Resultado Chave (KR)");
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
                buscarPorResultadoChave();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    public void buscarPorTitulo() {
        System.out.print("\nDigite o título desejado: ");
        String titulo = scanner.nextLine();
        List<Iniciativa> iniciativas = iniciativaRepository.findByTituloContainingIgnoreCase(titulo);

        if (iniciativas.isEmpty()) {
            System.out.println("Nenhuma iniciativa encontrada com este título.");
            return;
        }

        System.out.println("\n[Iniciativas Encontradas por Título]");
        for (Iniciativa ini : iniciativas) {
            System.out.printf("- %s: %s (%.2f%%)%n", ini.getTitulo(), ini.getDesc(), ini.getPorcentagemConcIndividual());
        }
    }

    public void buscarPorDescricao() {
        System.out.print("\nDigite a descrição desejada: ");
        String desc = scanner.nextLine();
        List<Iniciativa> iniciativas = iniciativaRepository.findByDescContainingIgnoreCase(desc);

        if (iniciativas.isEmpty()) {
            System.out.println("Nenhuma iniciativa encontrada com esta descrição.");
            return;
        }

        System.out.println("\n[Iniciativas Encontradas por Descrição]");
        for (Iniciativa ini : iniciativas) {
            System.out.printf("- %s: %s (%.2f%%)%n", ini.getTitulo(), ini.getDesc(), ini.getPorcentagemConcIndividual());
        }
    }

    public void buscarPorPorcentagem() {
        System.out.print("\nDigite a porcentagem desejada: ");
        double porcentagem = scanner.nextDouble();
        scanner.nextLine();

        List<Iniciativa> iniciativas = iniciativaRepository.findByPorcentagemConcIndividual(porcentagem);

        if (iniciativas.isEmpty()) {
            System.out.println("Nenhuma iniciativa encontrada com esta porcentagem.");
            return;
        }

        System.out.println("\n[Iniciativas Encontradas por Porcentagem de Conclusão]");
        for (Iniciativa ini : iniciativas) {
            System.out.printf("- %s: %s (%.2f%%)%n", ini.getTitulo(), ini.getDesc(), ini.getPorcentagemConcIndividual());
        }
    }

    public void buscarPorResultadoChave() {
        System.out.print("\nDigite o ID do Resultado Chave (KR) desejado: ");
        Long krId = scanner.nextLong();
        scanner.nextLine();

        Optional<ResultadoChave> resultadoChaveOptional = resultadoChaveRepository.findById(krId);

        if (resultadoChaveOptional.isEmpty()) {
            System.out.println("Resultado Chave não encontrado!");
            return;
        }

        List<Iniciativa> iniciativas = iniciativaRepository.findByKr(resultadoChaveOptional.get());

        if (iniciativas.isEmpty()) {
            System.out.println("Nenhuma iniciativa encontrada para este Resultado Chave.");
            return;
        }

        System.out.println("\n[Iniciativas Encontradas por Resultado Chave]");
        for (Iniciativa ini : iniciativas) {
            System.out.printf("- %s: %s (%.2f%%)%n", ini.getTitulo(), ini.getDesc(), ini.getPorcentagemConcIndividual());
        }
    }
}
