package pacote.USB;

import java.util.Date;

public class VisitaDomiciliar {
    private Date data;
    private String observacoes;
    private AgenteDeSaude agente;
    private Familia familiaVisitada;

    public VisitaDomiciliar(Date data, String observacoes, AgenteDeSaude agente, Familia familiaVisitada) {
        this.data = data;
        this.observacoes = observacoes;
        this.agente = agente;
        this.familiaVisitada = familiaVisitada;
    }

    @Override
    public String toString() {
        return "Visita[data=" + data + ", agente=" + (agente != null ? agente.getNome() : "N/A")
                + ", familiaId=" + (familiaVisitada != null ? familiaVisitada.toString() : "N/A")
                + ", obs=" + observacoes + "]";
    }

    public void salvarEmArquivo() {
        ArquivoUtil.salvar("visitas.txt", toString());
    }
}