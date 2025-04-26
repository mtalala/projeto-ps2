package br.milliecarol.projeto.aplicacao;
import br.milliecarol.projeto.entidade.*;
import java.util.*;

import org.springframework.stereotype.Component;

@Component
public class ConsoleApp {
    
    public static void iniciar() {
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("1. Aparecer Objetivo");
            System.out.println("2. Aparecer Resultado");
            System.out.println("3. Aparecer Iniciativa");
            System.out.println("0. Sair");

            System.out.println("Escolha uma opção: ");

            op = sc.nextInt();
            switch(op) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Inválida!!!");

            }

        } while(op!=0);
        sc.close();
    }

}
