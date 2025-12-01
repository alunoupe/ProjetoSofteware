package pacote.USB;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        do {
            try {
                System.out.println("\n=== Sistema de Atendimentos em USBs ===");
                System.out.println("1 - Agente de Saúde");
                System.out.println("2 - Enfermeiro");
                System.out.println("3 - Relatórios");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        if (AgenteDeSaude.autenticar(sc)) {
                            AgenteDeSaude.executar(sc);
                        }
                        break;
                    case 2:
                    	if (Enfermeiro.autenticar(sc)) {
                            Enfermeiro.executar(sc);
                        }
                    	break;
                    case 3:
                        Relatorio.executar(sc);
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Erro. Por favor, digite apenas números.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
                sc.nextLine();
            }

        } while (opcao != 0);

        sc.close();
    }
}
