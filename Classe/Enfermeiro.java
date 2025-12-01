package pacote.USB;

import java.util.*;

public class Enfermeiro extends Pessoa {

    private String especialidade;
    private static List<Relatorio> relatorios = new ArrayList<>();
    private static List<Familia> familias = new ArrayList<>();

    public Enfermeiro(String nome, String cpf, String especialidade) {
        super(nome, cpf);
        this.especialidade = especialidade;
    }
    
    public static boolean autenticar(Scanner sc) {
        System.out.print("Digite o CPF do Enfermeiro: ");
        String cpfDigitado = sc.nextLine();

        String cpfCorreto = "12345678901"; // CPF de teste

        if (cpfDigitado.equals(cpfCorreto)) {
            System.out.println("Acesso permitido!");
            return true;
        } else {
            System.out.println("CPF inválido! Acesso negado.");
            return false;
        }
    }

    public static void executar(Scanner sc) {
        int opcao;
        do {
            System.out.println("\n--- OPÇÕES ---");
            System.out.println("1 - Visualizar Relatórios de Visitas");
            System.out.println("2 - Visualizar Moradores");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    visualizarRelatorios();
                    break;
                case 2:
                    visualizarMoradores();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void visualizarRelatorios() {
        if (relatorios.isEmpty()) {
            System.out.println("Nenhum relatório cadastrado.");
            return;
        }

        System.out.println("\n--- RELATÓRIOS ---");
        for (Relatorio r : relatorios) {
            System.out.println(r);
        }
    }

    private static void visualizarMoradores() {
        if (familias.isEmpty()) {
            System.out.println("Nenhuma família cadastrada.");
            return;
        }

        System.out.println("\n--- MORADORES ---");
        for (Familia f : familias) {
            System.out.println(f);
        }
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public static void adicionarRelatorio(Relatorio r) {
        relatorios.add(r);
    }

    public static void adicionarFamilia(Familia f) {
        familias.add(f);
    }

    @Override
    public String toString() {
        return "Enfermeiro: " + getNome() + " - Especialidade: " + especialidade;
    }
}
