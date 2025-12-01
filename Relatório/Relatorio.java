package pacote.USB;

import java.util.*;
import java.util.Date;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat; 

public class Relatorio {
    private Date dataGeracao;
    private String tipo;
    private String conteudo;

    public Relatorio(Date dataGeracao, String tipo, String conteudo) {
        this.dataGeracao = dataGeracao;
        this.tipo = tipo;
        this.conteudo = conteudo;
    }
    
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getDadosParaArquivo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return tipo + ";" + sdf.format(dataGeracao) + ";" + conteudo;
    }

    public Date getDataGeracao() {
    	return dataGeracao; 
    	}
    public String getTipo() { 
    	return tipo; 
    	}
    public String getConteudo() { 
    	return conteudo; 
    }

    public static void executar(Scanner sc) {
        int opcao = 0;
        do {
            System.out.println("\n=== MENU RELATÓRIOS ===");
            System.out.println("1 - Criar novo relatório");
            System.out.println("2 - Visualizar relatórios");
            System.out.println("3 - Editar Relatório"); 
            System.out.println("4 - Excluir Relatório"); 
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                 String input = sc.nextLine();
                 if (input.isEmpty()) continue;
                 opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 1: criarRelatorio(sc); 
                break;
                case 2: visualizarRelatorios(sc); 
                break;
                case 3: editarRelatorio(sc); 
                break;
                case 4: excluirRelatorio(sc); 
                break;
                case 0: System.out.println("Voltando ao menu principal..."); 
                break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void criarRelatorio(Scanner sc) {
        System.out.println("\n--- NOVO RELATÓRIO ---");
        System.out.print("Digite o CPF do Morador/Responsável para vincular o relatório: ");
        
        String cpf = sc.nextLine();

        Familia familiaEncontrada = AgenteDeSaude.buscarFamiliaDoMorador(cpf); 
        
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido! O CPF deve conter exatamente 11 dígitos numéricos.");
    }
        else if (familiaEncontrada == null) {
            System.out.println("Erro: Morador com CPF " + cpf + " não encontrado. Relatório não criado.");
            return;
        }

        System.out.print("Tipo do relatório: ");
        String tipo = sc.nextLine();
        
        
        while (true) {
            System.out.print("Conteúdo: ");
            String conteudo = sc.nextLine();

            if (conteudo.isEmpty()) {
                break; // Sai do loop se a linha estiver vazia
            }

        Relatorio r = new Relatorio(new Date(), tipo, conteudo);
        familiaEncontrada.adicionarRelatorio(r); 

        AgenteDeSaude.salvarFamilias(); // salva a lista completa de famílias e relatórios
        
        System.out.println("Relatório criado e salvo com sucesso na família de " + familiaEncontrada.getResponsavel().getNome() + "!");
        }
    }

    private static void visualizarRelatorios(Scanner sc) {
        System.out.println("\n--- VISUALIZAR RELATÓRIOS POR FAMÍLIA ---");
        System.out.print("Digite o CPF do Morador/Responsável da família: ");
        String cpf = sc.nextLine();

        Familia familiaEncontrada = AgenteDeSaude.buscarFamiliaDoMorador(cpf); 
        
        if (familiaEncontrada == null) {
            System.out.println("Erro: Família com CPF " + cpf + " não encontrada.");
            return;
        }
        
        List<Relatorio> relatoriosDaFamilia = familiaEncontrada.getRelatorios();

        if (relatoriosDaFamilia.isEmpty()) {
            System.out.println("Nenhum relatório encontrado para esta família.");
            return;
        }

        System.out.println("\n=== RELATÓRIOS DA FAMÍLIA DE " + familiaEncontrada.getResponsavel().getNome() + " ===");
        for (Relatorio r : relatoriosDaFamilia) {
             System.out.println(r.toString());
        }
    }

    private static void editarRelatorio(Scanner sc) {
        System.out.println("\n--- EDITAR RELATÓRIO ---");
        System.out.print("Digite o CPF do Morador/Responsável da família: ");
        String cpf = sc.nextLine();

        Familia familiaEncontrada = AgenteDeSaude.buscarFamiliaDoMorador(cpf); 
        if (familiaEncontrada == null) {
            System.out.println("Erro: Família com CPF " + cpf + " não encontrada.");
            return;
        }
        
        System.out.print("Digite o TIPO do relatório para edição: ");
        String tipo = sc.nextLine().trim();
        
        System.out.print("Digite a DATA DE GERAÇÃO (exatamente como aparece na lista, ex: DD/MM/YYYY HH:MM:SS): ");
        String dataStr = sc.nextLine().trim();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Relatorio relatorioEncontrado = familiaEncontrada.getRelatorios().stream()
            .filter(r -> r.getTipo().equalsIgnoreCase(tipo) && sdf.format(r.getDataGeracao()).equals(dataStr))
            .findFirst()
            .orElse(null);

        if (relatorioEncontrado != null) {
            System.out.println("Relatório encontrado. Conteúdo atual: " + relatorioEncontrado.getConteudo());
            System.out.print("Digite o NOVO CONTEÚDO para o relatório: ");
            relatorioEncontrado.setConteudo(sc.nextLine()); 
            
            AgenteDeSaude.salvarFamilias();
            System.out.println("Relatório atualizado e salvo com sucesso!");
        } else {
            System.out.println("Relatório não encontrado na família.");
        }
    }

    private static void excluirRelatorio(Scanner sc) {
        System.out.println("\n--- EXCLUIR RELATÓRIO ---");
        System.out.print("Digite o CPF do Morador/Responsável da família: ");
        String cpf = sc.nextLine();

        Familia familiaEncontrada = AgenteDeSaude.buscarFamiliaDoMorador(cpf); 
        if (familiaEncontrada == null) {
            System.out.println("Erro: Família com CPF " + cpf + " não encontrada.");
            return;
        }
        
        System.out.print("Digite o TIPO do relatório a ser excluído: ");
        String tipo = sc.nextLine().trim();
        
        System.out.print("Digite a DATA DE GERAÇÃO (exatamente como aparece na lista): ");
        String dataStr = sc.nextLine().trim();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        boolean removido = familiaEncontrada.getRelatorios().removeIf(r -> {
            String dataFormatada = sdf.format(r.getDataGeracao());
            return r.getTipo().equalsIgnoreCase(tipo) && dataFormatada.equals(dataStr);
        });

        if (removido) {
            AgenteDeSaude.salvarFamilias();
            System.out.println("Relatório excluído com sucesso e arquivo atualizado!");
        } else {
            System.out.println("Erro: Relatório com o Tipo e Data especificados não foi encontrado nesta família.");
        }
    }
   }