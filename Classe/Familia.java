package pacote.USB;

import java.util.ArrayList;
import java.util.List;

public class Familia {
    private Morador responsavel;
    private String endereco;
    private List<Morador> moradores = new ArrayList<>();
    private List<VisitaDomiciliar> visitas = new ArrayList<>();
    private List<Relatorio> relatorios = new ArrayList<>(); 
    
    public Familia(Morador responsavel) {
        this.responsavel = responsavel;
    }

    public void adicionarMorador(Morador m) {
        moradores.add(m);
    }

    public boolean removerMorador(String cpf) {
        return moradores.removeIf(m -> m.getCpf().equals(cpf));
    }
    
    public Morador buscarMorador(String cpf) {
        for (Morador m : moradores) {
            if (m.getCpf().equals(cpf)) {
                return m;
            }
        }
        return null;
    }
    
    public void adicionarVisita(VisitaDomiciliar v) {
        visitas.add(v);
    }

    public List<Relatorio> getRelatorios() {
        return relatorios;
    }
    
    public void adicionarRelatorio(Relatorio r) {
        this.relatorios.add(r);
    }

    public Morador getResponsavel() {
        return responsavel;
    }

    public List<Morador> getMoradores() {
        return moradores;
    }

    public List<VisitaDomiciliar> getVisitas() {
        return visitas;
    }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Responsável: ").append(responsavel).append("\nMoradores:\n");
            for (Morador m : moradores) {
                sb.append(" - ").append(m).append("\n");
            }
            return sb.toString();
        }
    }