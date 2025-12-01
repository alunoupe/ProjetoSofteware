 Sistema de Cadastro de Pacientes – Java

Este projeto é um sistema simples de cadastro para uma clínica hospitalar, desenvolvido em Java, com foco em organização, encapsulamento, validação e manipulação de dados de pacientes e funcionários.

 Funcionalidades

Pacientes

- Cadastro de paciente  
- Listar pacientes cadastrados  
- Editar dados  
- Remover paciente  
- Consultar ficha completa  
- Validação de CPF  
- Registro de data de nascimento, endereço, sexo e telefone

 Consultas
 
- Registrar motivos da consulta  
- Exibir horários disponíveis  
- Bloquear horários já ocupados  
- Evitar conflitos de agendamento  
- Exibir ficha completa do paciente com o horário marcado

Enfermeiros / Funcionários

- Cadastro de enfermeiros  
- Relatório simples com informações organizadas  
- Atributos como nome, identificação, cargo e horário de trabalho  

 Estrutura do Projeto
src/
├── Main.java
├── Paciente.java
├── Enfermeiro.java
├── Familia.java
├── Relatorio.java
└── util/
└── ArquivoUtil.java

 Tecnologias Utilizadas
 
- Programação Orientada a Objetos (POO)**  
- Encapsulamento  
- Construtores  
- Sobrecarga de métodos  
- Tratamento de exceções (`throws`, `throw`)  
- `toString()` para exibir fichas  
- Leitura e escrita em arquivo de texto
- Eclipse
- PlantUML 
- PostgreSQL (pgAdmin 4)

Persistência de Dados

O arquivo `dados_usb.txt` é usado para armazenar:
- Pacientes cadastrados  
- Consultas  
- Informações importantes para manter o funcionamento entre execuções  

Todo o controle é feito pela classe ArquivoUtil, responsável por:
- Salvar dados  
- Ler dados  
- Gerar estrutura de armazenamento  
- Evitar corrompimento do arquivo  

 Como Executar

1.  Instalar: Certifique-se de ter o PostgreSQL instalado e rodando na porta 5432.
2.  Criar o BD: Crie um banco de dados chamado SistemaUBS.
3.  Rodar o Script: Execute o arquivo PostgreSQL no seu cliente (pgAdmin) para criar todas as tabelas.
4. Abra o projeto em uma IDE como Eclipse, IntelliJ ou VS Code.
5. Certifique-se de que todos os arquivos .java estejam dentro da pasta src/.
6. Compile e execute o arquivo

   Melhorias Futuras 
- Interface gráfica com JavaFX  
- Sistema completo de login e permissões  
- Relatórios em PDF  

---

Autores

- Claudernira Nascimento  
- Felipe da Silva  

Projeto desenvolvido para fins acadêmicos. 
