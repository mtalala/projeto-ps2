package br.milliecarol.projeto.aplicacao;

import br.milliecarol.projeto.entidade.*;
import br.milliecarol.projeto.servico.*;

import java.util.*;

import org.springframework.stereotype.Component;

@Component
public class ConsoleApp {

    private static final ObjetivoServico objetivoServico = new ObjetivoServico();
    private static final ResultadoChaveServico resultadoChaveServico = new ResultadoChaveServico();
    private static final IniciativaServico iniciativaServico = new IniciativaServico();

    public static void iniciar() {
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("1. Aparecer Objetivo");
            System.out.println("2. Aparecer Resultado");
            System.out.println("3. Aparecer Iniciativa");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();
            sc.nextLine(); 

            switch(op) {
                case 1:
                    menuObjetivo(sc);
                    break;
                case 2:
                    menuResultadoChave(sc);
                    break;
                case 3:
                    menuIniciativa(sc);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida!!!");
            }
        } while(op != 0);

        sc.close();
    }

    private static void menuObjetivo(Scanner sc) {
        int op;
        do {
            System.out.println("\n1. Listar Objetivos");
            System.out.println("2. Buscar Objetivo");
            System.out.println("3. Salvar Objetivo");
            System.out.println("4. Deletar Objetivo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();
            sc.nextLine(); 

            switch(op) {
                case 1:
                    objetivoServico.listar();
                    break;
                case 2:
                    objetivoServico.buscarPorTitulo();
                    break;
                case 3:
                    System.out.print("Digite o título: ");
                    String tituloSave = sc.nextLine();
                    System.out.print("Digite a descrição: ");
                    String descSave = sc.nextLine();
                    Objetivo objetivo = new Objetivo();
                    objetivo.setTitulo(tituloSave);
                    objetivo.setDesc(descSave);
                    objetivoServico.salvar(objetivo);
                    break;
                case 4:
                    System.out.print("Digite o ID do Objetivo para deletar: ");
                    Long idDelete = sc.nextLong();
                    objetivoServico.deletar(idDelete);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Inválida!!!");
            }
        } while(op != 0);
    }

    private static void menuResultadoChave(Scanner sc) {
        int op;
        do {
            System.out.println("\n1. Listar Resultados");
            System.out.println("2. Buscar Resultado");
            System.out.println("3. Salvar Resultado");
            System.out.println("4. Deletar Resultado");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();
            sc.nextLine(); 

            switch(op) {
                case 1:
                    resultadoChaveServico.listar();
                    break;
                case 2:
                    resultadoChaveServico.buscarPorDescricao();
                    break;
                case 3:
                    System.out.print("Digite a descrição: ");
                    String descricaoSave = sc.nextLine();
                    System.out.print("Digite a meta: ");
                    String metaSave = sc.nextLine();
                    ResultadoChave resultadoChave = new ResultadoChave();
                    resultadoChave.setDesc(descricaoSave);
                    resultadoChave.setMeta(metaSave);
                    resultadoChaveServico.salvar(resultadoChave);
                    break;
                case 4:
                    System.out.print("Digite o ID do Resultado para deletar: ");
                    Long idDelete = sc.nextLong();
                    resultadoChaveServico.deletar(idDelete);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Inválida!!!");
            }
        } while(op != 0);
    }

    private static void menuIniciativa(Scanner sc) {
        int op;
        do {
            System.out.println("\n1. Listar Iniciativas");
            System.out.println("2. Buscar Iniciativa");
            System.out.println("3. Salvar Iniciativa");
            System.out.println("4. Deletar Iniciativa");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();
            sc.nextLine(); 

            switch(op) {
                case 1:
                    iniciativaServico.listar();
                    break;
                case 2:
                    iniciativaServico.buscarPorTitulo();
                    break;
                case 3:
                    System.out.print("Digite o título: ");
                    String tituloSave = sc.nextLine();
                    System.out.print("Digite a descrição: ");
                    String descSave = sc.nextLine();
                    Iniciativa iniciativa = new Iniciativa();
                    iniciativa.setTitulo(tituloSave);
                    iniciativa.setDesc(descSave);
                    iniciativaServico.salvar(iniciativa);
                    break;
                case 4:
                    System.out.print("Digite o ID da Iniciativa para deletar: ");
                    Long idDelete = sc.nextLong();
                    iniciativaServico.deletar(idDelete);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Inválida!!!");
            }
        } while(op != 0);
    }
}
