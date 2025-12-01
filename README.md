 Sistema de Cadastro e Agendamento para Unidade Básica de Saúde (UBS)
Projeto acadêmico desenvolvido em Java com foco em persistência de dados e aplicação de Programação Orientada a Objetos (POO).

 Visão Geral do Projeto
Este projeto consiste em um Sistema de Gerenciamento básico e robusto, desenvolvido em Java 17, para otimizar os processos de cadastro de Pacientes e Funcionários (Enfermeiros) e o Agendamento de Consultas em uma Unidade Básica de Saúde (UBS).

O desenvolvimento priorizou a organização do código, o encapsulamento de dados sensíveis e a validação rigorosa, garantindo a integridade da informação e a aplicação de conceitos sólidos de POO.

 Funcionalidades Principais
O sistema oferece um conjunto completo de operações para a gestão de dados na UBS:

1. Gerenciamento de Pacientes
 Cadastro Completo: Registro de nome, data de nascimento, endereço, sexo e telefone.

 Validação de Documento: Implementação de lógica para a validação de CPF.

 CRUD (Create, Read, Update, Delete): Funcionalidades para Cadastrar, Listar, Editar dados e Remover pacientes.

 Consulta de Ficha: Exibição da ficha completa do paciente.

2. Gerenciamento de Funcionários (Enfermeiros)
 Cadastro Simples: Registro de nome, identificação, cargo e horário de trabalho.

 Relatórios: Geração de um relatório simples com informações organizadas sobre o corpo de funcionários.

 Tecnologias Utilizadas -Java17

Programação Orientada a Objetos (POO)

Eclipse

PlantUML

PostgreSQL (pgAdmin 4)

Banco de Dados
 
 ⚠️ Nota sobre o Padrão MVC e a Separação de Preocupações
 
Embora o projeto aplique fortes princípios de POO, observou-se uma limitação no escopo atual:

A classe Controller (ou o que desempenha seu papel, como a classe AgenteDeSaude – se esta existir, ou a classe que gerencia a lógica) está atualmente realizando a coleta de dados (Input/Output), juntamente com a lógica de negócio.

Problema: 

Essa abordagem viola o princípio de Separação de Preocupações (Single Responsibility Principle) e a arquitetura ideal do padrão Model-View-Controller .(MVC), onde o Controller deve apenas

💾 Banco de Dados

O sistema utiliza um banco de dados relacional para garantir a persistência e a integridade dos dados entre diferentes execuções.

Tecnologia: PostgreSQL (Gerenciado via pgAdmin 4).

Dados Armazenados:

Informações de Pacientes cadastrados.

Registros de Consultas agendadas.

Informações importantes para o funcionamento contínuo do sistema.manipular dados já recebidos da View (I/O).

🚀 Como Executar o Projeto
Siga os passos abaixo para configurar e rodar o sistema localmente:

Instalação do SGBD:

Certifique-se de ter o PostgreSQL instalado e em execução, utilizando a porta padrão (5432).

Criação do Banco de Dados:

Crie um novo banco de dados com o nome exato: SistemaUBS.

Configuração das Tabelas:

Execute o script SQL (localizado na raiz do projeto ou em uma pasta db/) no seu cliente PostgreSQL (ex: pgAdmin 4) para criar todas as tabelas necessárias.

Abertura e Execução do Código:


Próximas Etapas e Melhorias Futuras

Este projeto serve como base para um sistema mais abrangente. As principais melhorias planejadas incluem:

Interface Gráfica (GUI): Implementação de uma interface amigável utilizando JavaFX para substituir a I/O baseada em console.

Segurança e Controle de Acesso: Desenvolvimento de um sistema completo de Login e Permissões (perfis de acesso).

Relatórios Profissionais: Geração de relatórios de gestão em formatos padronizados (ex: PDF).
Abra o projeto em sua IDE (Eclipse, IntelliJ ou VS Code).

Confirme que todos os arquivos .java estão corretamente localizados na pasta src/.

Compile e Execute o arquivo Main.java.

🧑‍💻 Autores
Este projeto acadêmico foi desenvolvido por:

Claudenira Nascimento
Felipe da Silva
