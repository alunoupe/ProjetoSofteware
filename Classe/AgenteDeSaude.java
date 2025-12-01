package pacote.USB;

import java.util.ArrayList;
import java.util.Scanner;

public class AgenteDeSaude extends Pessoa {

    private static ArrayList<Familia> familias = null;
    
    private static ArrayList<Familia> getFamilias() {
        if (familias == null) {
            System.out.println("Carregando dados...");
            familias = ArquivoUtil.carregarFamilias();
            if (familias == null) {
                 familias = new ArrayList<>();
            }
        }
        return familias;
    }
    
    public static boolean autenticar(Scanner sc) {
        System.out.print("Digite o CPF do Agente de Saúde: ");
        String cpfDigitado = sc.nextLine();
        
        String cpfCorreto = "12345678900"; // CPF de teste

        if (cpfDigitado.equals(cpfCorreto)) {
            System.out.println("Acesso permitido!");
            return true;
        } else {
            System.out.println("CPF inválido! Acesso negado.");
            return false;
        }
    }

    public static void executar(Scanner sc) {
        int opcao = 0;
        do {
            System.out.println("\n--- OPÇÕES ---");
            System.out.println("1 - Cadastrar Famílias");
            System.out.println("2 - Listar Famílias");
            System.out.println("3 - Editar Famílias/Moradores");
            System.out.println("4 - Remover Famílias/Moradores");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine()); 
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 1: registrar(sc); 
                break;
                case 2: listar(sc); 
                break;
                case 3: editar(sc); 
                break;
                case 4: remover(sc); 
                break;
                case 0: System.out.println("Voltando ao menu principal..."); 
                break;
                default: System.out.println("Opção inválida!"); 
                break;
            }
        } while (opcao != 0);
    }
    
    private static Morador buscarMoradorEmTodasFamilias(String cpf) {
        for (Familia f : getFamilias()) { 
            // aqui vai verificar o Responsável
            if (f.getResponsavel().getCpf().equals(cpf)) {
                return f.getResponsavel();
            }
            // aqui vai verificar Moradores
            for (Morador m : f.getMoradores()) {
                if (m.getCpf().equals(cpf)) {
                    return m;
                }
            }
        }
        return null;
    }
    
    private static void registrar(Scanner sc) {
        System.out.println("\n--- CADASTRO DE FAMÍLIA ---");
        Morador responsavel = criarMoradorCompleto(sc, "Responsável"); 

        Familia familia = new Familia(responsavel);

        String opcao;
        do {
            System.out.print("Deseja adicionar mais um morador à família (s/n)? ");
            opcao = sc.nextLine();
            if (opcao.equalsIgnoreCase("s")) {
                Morador morador = criarMoradorCompleto(sc, "Morador");
                familia.adicionarMorador(morador);
            }
        } while (opcao.equalsIgnoreCase("s"));

        getFamilias().add(familia);
        ArquivoUtil.salvarFamilias(getFamilias());
        System.out.println("Família cadastrada com sucesso!");
    }
    
    private static void listar(Scanner sc) {
        ArrayList<Familia> lista = getFamilias();
        
        if (lista.isEmpty()) {
            System.out.println("Nenhuma família cadastrada.");
            return;
        }
        
        System.out.println("\n--- LISTA DE FAMÍLIAS ---");
        for (Familia f : lista) {
            System.out.println(f);
        }
    }
    
    private static Familia buscarFamilia(String cpfResponsavel) {
        for (Familia f : getFamilias()) {
            if (f.getResponsavel().getCpf().equals(cpfResponsavel)) {
            	
                return f;
            }
        }
        return null;
    }

    public static void editar(Scanner sc) {
        System.out.print("Digite o CPF do morador/responsável que deseja editar: ");
        String cpf = sc.nextLine();

        Morador moradorEncontrado = buscarMoradorEmTodasFamilias(cpf); 
        
        if (moradorEncontrado == null) {
            System.out.println("Morador com CPF " + cpf + " não encontrado.");
            return;
        }

        System.out.println("\n--- EDITANDO: " + moradorEncontrado.getNome() + " ---");
        int opcao = -1;
        do {
            System.out.println("1 - Editar Nome");
            System.out.println("2 - Editar Idade");
            System.out.println("3 - Editar Endereço");
            System.out.println("4 - Editar Condições de Saúde");
            System.out.println("5 - Editar Risco de Saúde (true/false)");
            System.out.println("0 - Salvar e Sair da Edição");
            System.out.print("Escolha o campo para editar: ");

            try {
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    opcao = -1;
                    continue;
                }
                opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Novo Nome: ");
                    moradorEncontrado.setNome(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Nova Idade: ");
                    try {
                        moradorEncontrado.setIdade(Integer.parseInt(sc.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("Idade inválida. Use apenas números.");
                    }
                    break;
                case 3:
                    System.out.print("Novo Endereço: ");
                    moradorEncontrado.setEndereco(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Novas Condições de Saúde: ");
                    moradorEncontrado.setCondicoesSaude(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Novo Risco de Saúde (true/false): ");
                    moradorEncontrado.setRiscoSaude(Boolean.parseBoolean(sc.nextLine()));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        ArquivoUtil.salvarFamilias(getFamilias()); 
        System.out.println("Dados de " + moradorEncontrado.getNome() + " atualizados e salvos.");
    }

    private static void remover(Scanner sc) {
        System.out.print("Digite o CPF do morador que deseja remover: ");
        String cpf = sc.nextLine();
        
        Familia familia = buscarFamiliaDoMorador(cpf);
        
        if (familia == null) {
            System.out.println("Morador com CPF " + cpf + " não encontrado.");
            return;
        }

        if (familia.getResponsavel().getCpf().equals(cpf)) { // Se o CPF for do RESPONSÁVEL, vai remover a família inteira
            getFamilias().remove(familia);
            ArquivoUtil.salvarFamilias(getFamilias());
            System.out.println("Responsável e toda a família foram removidos!");
            return;
        }
        
        if (familia.removerMorador(cpf)) { // Se não é o responsável, vai remover como Morador
            ArquivoUtil.salvarFamilias(getFamilias());
            System.out.println("Morador removido com sucesso!");
            return;
        }

        System.out.println("Morador com CPF " + cpf + " não encontrado nesta família.");
    }

    public static Familia buscarFamiliaDoMorador(String cpf) {
    	 for (Familia f : getFamilias()) { 
    	     if (f.getResponsavel().getCpf().equals(cpf)) {
    	         return f;
    	     }
    	     for (Morador m : f.getMoradores()) {
    	         if (m.getCpf().equals(cpf)) {
    	             return f;
    	         }
    	     }
    	 }
    	 return null;
    	}
    
    private static Morador criarMoradorCompleto(Scanner sc, String tipo) {
        System.out.println("\n-- Dados do " + tipo + " --");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("SUS: ");
        String sus = sc.nextLine();
        System.out.print("Data de Nascimento: ");
        String dataNascimento = sc.nextLine();
        System.out.println("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Sexo: ");
        String sexo = sc.nextLine();
        System.out.print("Condições de Saúde: ");
        String condicoesSaude = sc.nextLine();

        int idade = 0;
        boolean idadeValida = false;
        while (!idadeValida) {
            try {
                System.out.print("Idade (número): ");
                idade = Integer.parseInt(sc.nextLine());
                idadeValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Idade inválida. Por favor, digite um número.");
            }
        }

        System.out.print("Risco de Saúde (true/false): ");
        boolean riscoSaude = Boolean.parseBoolean(sc.nextLine());

        return new Morador(nome, cpf, sus, dataNascimento, endereco, sexo, idade, condicoesSaude, riscoSaude);
    }
    
    public static void salvarFamilias() {
    	 ArquivoUtil.salvarFamilias(getFamilias());
    	}
}