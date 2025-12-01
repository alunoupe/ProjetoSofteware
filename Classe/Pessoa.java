package pacote.USB;

public abstract class Pessoa {
protected String cpf;
protected String nome;


public Pessoa() {}


public Pessoa(String cpf, String nome) {
this.cpf = cpf;
this.nome = nome;
}


public String getCpf() {
return cpf;
}


public void setCpf(String cpf) {
this.cpf = cpf;
}


public String getNome() {
return nome;
}


public void setNome(String nome) {
this.nome = nome;
}


@Override
public String toString() {
return "Pessoa{CPF=" + cpf + ", nome='" + nome + "'}";
}
}