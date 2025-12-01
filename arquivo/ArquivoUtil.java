package pacote.USB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ArquivoUtil {

    public static final String DIRETORIO = "data/";
    private static final String NOME_ARQUIVO = "familias.txt";

    static {
        try {
            Path dir = Paths.get(DIRETORIO);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar diretório data/: " + e.getMessage());
        }
    }

    public static void salvar(String nomeArquivo, String conteudo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DIRETORIO + nomeArquivo, true))) {
            writer.write(conteudo);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo [" + nomeArquivo + "]: " + e.getMessage());
        }
    }
        
        public static ArrayList<String> ler(String nomeArquivo) {
            ArrayList<String> linhas = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(DIRETORIO + nomeArquivo))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    linhas.add(linha);
                }
            } catch (IOException e) {
            }
            return linhas;
        }
        
        public static ArrayList<Familia> carregarFamilias() {
            ArrayList<Familia> familias = new ArrayList<>();
            ArrayList<String> linhas = ler(NOME_ARQUIVO); 
            Familia familiaAtual = null;

            for (String linha : linhas) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;
                
                String[] dados = linha.split(";", -1); 
                
                if (linha.startsWith("RESPONSAVEL") || (linha.startsWith("MORADOR") && familiaAtual != null && dados.length >= 10)) { 
                    try {
                        Morador morador = new Morador( 
                        		 dados[1], 
                                 dados[2], 
                                 dados[3], 
                                 dados[4], 
                                 dados[5], 
                                 dados[6],
                                 Integer.parseInt(dados[7]), 
                                 dados[8],
                                 Boolean.parseBoolean(dados[9])
                             );

                        
                        if (linha.startsWith("RESPONSAVEL")) {
                            familiaAtual = new Familia(morador); 
                        } else { 
                            familiaAtual.adicionarMorador(morador);
                        }
                    } catch (Exception e) {
                        System.err.println("Erro ao carregar Morador/Responsável: " + linha + ". Pulando.");
                    }
                } else if (linha.startsWith("RELATORIO") && familiaAtual != null && dados.length >= 4) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 

                        String tipo = dados[1];
                        Date dataGeracao = sdf.parse(dados[2]); 
                        String conteudo = dados[3];

                        Relatorio relatorio = new Relatorio(dataGeracao, tipo, conteudo);
                        familiaAtual.adicionarRelatorio(relatorio); 
                        
                    } catch (Exception e) {
                        System.err.println("Erro ao carregar Relatório na linha: " + linha + ". Pulando.");
                    }
                } else if (linha.equals("FIMFAMILIA") && familiaAtual != null) {
                    familias.add(familiaAtual);
                    familiaAtual = null;
                }
            } 
            
            return familias;
        }
	        
	public static void salvarFamilias(ArrayList<Familia> familias) {
	 StringBuilder sb = new StringBuilder();
	 
	 for (Familia f : familias) {
	     Morador resp = f.getResponsavel();
	     sb.append("RESPONSAVEL;").append(resp.getDadosParaArquivo()).append("\n"); 
	
	     for (Morador m : f.getMoradores()) {
	         sb.append("MORADOR;").append(m.getDadosParaArquivo()).append("\n"); 
	     }
	
	     for (Relatorio r : f.getRelatorios()) {
	         sb.append("RELATORIO;").append(r.getDadosParaArquivo()).append("\n");
	     }
	     
	     sb.append("FIMFAMILIA\n");
	 }
	
	 try (BufferedWriter writer = new BufferedWriter(new FileWriter(DIRETORIO + NOME_ARQUIVO, false))) { 
	     writer.write(sb.toString());
	 } catch (IOException e) {
	     System.err.println("Erro ao salvar o arquivo de famílias: " + e.getMessage());
	 }
	}
}