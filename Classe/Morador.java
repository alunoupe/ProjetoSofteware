package pacote.USB;

public class Morador extends Pessoa {
    private String sus;
    private String dataNascimento;
    private int idade;
    private String endereco;
    private String sexo;
    private String condicoesSaude;
    private boolean riscoSaude;

    public Morador(String nome, String cpf) {
        super(nome, cpf);
    }

    public Morador(String nome, String cpf, String sus, String dataNascimento,String endereco,
    		String sexo, int idade , String condicoesSaude, boolean riscoSaude) {
        super(cpf, nome);
        this.sus = sus;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.idade = idade;
        this.sexo = sexo;
        this.condicoesSaude = condicoesSaude;
        this.riscoSaude = riscoSaude;
    }

    public String getSus() { return sus; }
    public void setSus(String sus) { this.sus = sus; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getCondicoesSaude() { return condicoesSaude; }
    public void setCondicoesSaude(String condicoesSaude) { this.condicoesSaude = condicoesSaude; }

    public boolean isRiscoSaude() { return riscoSaude; }
    public void setRiscoSaude(boolean riscoSaude) { this.riscoSaude = riscoSaude; }

 public String getDadosParaArquivo() {
     return getNome() + ";" + 
            getCpf() + ";" + 
            getSus() + ";" + 
            getDataNascimento() + ";" + 
            getEndereco() + ";" +
            getSexo() + ";" + 
            getIdade() + ";" + 
            getCondicoesSaude() + ";" + 
            isRiscoSaude(); // O boolean será salvo como true ou false
 }
    
    @Override
    public String toString() {
        return getNome() + " (CPF: " + getCpf() + ")"
                + " | SUS: " + sus
                + " | Data Nasc: " + dataNascimento
                + " | Endereço:: " + endereco
                + " | Sexo: " + sexo
                + " | Idade: " + idade
                + " | Condições: " + condicoesSaude
                + (riscoSaude ? " | [RISCO]" : "");
    }
}