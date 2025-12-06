Sistema de Cadastro e Agendamento para Unidade Básica de Saúde (UBS)

Projeto acadêmico desenvolvido em Java 17, com foco em persistência de dados, boas práticas de POO e organização estrutural.



 Visão Geral do Projeto

O sistema tem como objetivo gerenciar Pacientes, Funcionários (Enfermeiros) e o Agendamento de Consultas em uma Unidade Básica de Saúde (UBS).
O desenvolvimento priorizou:

Organização do código

Encapsulamento de dados

Validação rigorosa de informações

Aplicação correta de conceitos de Programação Orientada a Objetos




 Funcionalidades Principais

 Gerenciamento de Pacientes
Cadastro completo: nome, data de nascimento, endereço, sexo, telefone

Validação de CPF

CRUD completo: criar, listar, atualizar e excluir

Consulta da ficha individual de cada paciente


 Gerenciamento de Funcionários (Enfermeiros)

Cadastro simples: nome, identificação, carga e local de trabalho

Geração de relatório organizado com informações dos funcionários


 Agendamento de Consultas

Registro dos dados da consulta

Associação ao paciente cadastrado

Garantia de persistência no banco de dados




 Tecnologias Utilizadas

Java 17

Programação Orientada a Objetos (POO)

Eclipse IDE

PlantUML

PostgreSQL + pgAdmin 4



 Nota Sobre o Padrão MVC e a Separação de Responsabilidades

Apesar do uso consistente de POO, o projeto apresenta um ponto de atenção:

A classe que atua como Controller (por exemplo, AgenteDeSaude, caso exista) está realizando entrada/saída de dados (I/O) junto com a lógica de negócio.

Problema Identificado

Essa abordagem viola:

O princípio Single Responsibility (SRP)

A estrutura recomendada do padrão Model–View–Controller (MVC)


No MVC, o Controller não deve lidar diretamente com I/O, e sim apenas processar dados vindos da View.


🗄️ Banco de Dados

O sistema utiliza um banco de dados relacional para manter a integridade e persistência entre execuções.

Tecnologia

PostgreSQL (pgAdmin 4)


Dados Armazenados

Informações de Pacientes

Registros de Consultas

Dados manipulados e enviados pela camada de visualização


 Como Executar o Projeto

1. Instale o SGBD

Certifique-se de que o PostgreSQL está instalado e rodando na porta padrão 5432.

2. Crie o Banco de Dados

Crie um banco com o nome SistemaUBS.

3. Configure as Tabelas

Execute o script SQL presente no projeto (na raiz ou na pasta db/).
Isso criará todas as tabelas necessárias.

4. Abra o Código

Abra o projeto em seu ambiente de desenvolvimento:

Eclipse

IntelliJ IDEA

VS Code


Confirme que todos os arquivos .java estão organizados na pasta src/.

5. Execute

Compile e execute o arquivo Main.java.




🔮 Próximas Etapas e Melhorias Futuras

Interface Gráfica (GUI) usando JavaFX

Sistema de Login e Perfis de Acesso

Relatórios profissionais (PDF e outros formatos)

Separação completa em MVC

Ampliação dos módulos de gerenciamento




🧑‍💻 Autores

Projeto acadêmico desenvolvido por:
Claudenira Nascimento
Felipe da Silva
