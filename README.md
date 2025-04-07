# Desenvolvimento de um aplicativo com a persistência de dados com o uso do SQLite no Androide

### Introdução

O sistema de catálogo de plantas desenvolvido é uma aplicação Android que permite o gerenciamento de plantas, como adicionar, editar, listar e excluir informações sobre elas. O aplicativo segue uma estrutura de três camadas (Camada de Apresentação, Lógica de Negócios e Persistência), que visa melhorar a modularidade, organização e manutenção do código. O sistema permite que o usuário registre informações sobre plantas, como nome, espécie, quantidade, altura e se são tóxicas.

### Desenvolvimento

Arquitetura de Três Camadas
O projeto foi estruturado em três camadas principais, garantindo a separação de responsabilidades e facilitando o gerenciamento do código:
Interface do usuário:
Responsável pela interface com o usuário. Nela, estão as Activities que interagem diretamente com a interface gráfica.
* MainActivity: Exibe a lista de plantas registradas e permite a navegação para a tela de detalhamento de uma planta.
*	PlantaDetailActivity: Formulário de inserção ou edição de informações de uma planta.
Camada de Lógica de Negócios:
Contém a lógica de controle, manipulando as interações entre a camada de apresentação e a camada de persistência (banco de dados).
*	PlantaController: Responsável por realizar operações de negócio, como adicionar, atualizar e deletar plantas, além de recuperar a lista de plantas para a UI.
Camada de Persistência (DAO):
Responsável por interagir com o banco de dados SQLite para realizar operações de CRUD (Create, Read, Update, Delete) nas plantas.
*	PlantaDAO: Contém os métodos para adicionar, atualizar, deletar e recuperar plantas do banco de dados.
*	PlantaSQLiteHelper: Gerencia a criação e a atualização do banco de dados, definindo a estrutura das tabelas e realizando migrações.


### Implementação

MainActivity.java: Exibe a lista de plantas, permitindo ao usuário adicionar novas plantas e visualizar as existentes. Ela também interage com o PlantaController para recuperar e exibir as plantas no ListView.
PlantaDetailActivity.java: Fornece uma interface para o usuário inserir ou editar os dados de uma planta. Ao clicar no botão "Salvar", as informações são passadas para o PlantaController, que se encarrega de salvar ou atualizar as informações no banco de dados.
PlantaController.java: Atua como intermediário entre a camada de UI e a camada de persistência. Ele recebe os dados da UI, processa as operações de negócio e delega para o DAO salvar ou recuperar dados do banco.
PlantaDAO.java: Interage diretamente com o banco de dados SQLite, utilizando comandos SQL para adicionar, buscar, atualizar e remover plantas.
PlantaSQLiteHelper.java: Define a estrutura do banco de dados SQLite, criando a tabela para armazenar as plantas e controlando as versões do banco de dados.


### Fluxo do Sistema

*	Listagem de Plantas: Ao abrir o aplicativo, a MainActivity solicita ao PlantaController a lista de plantas e exibe no ListView.
*	Adicionar Planta: Ao clicar no botão "Adicionar", a PlantaDetailActivity é aberta, permitindo ao usuário inserir dados sobre a planta. Quando o usuário clica em "Salvar", os dados são enviados ao PlantaController, que por sua vez chama o PlantaDAO para adicionar a planta ao banco de dados.
*	Verificar planta: Ao clicar em editar o usuário verifica todos os detalhes da planta que buscou(getPlantaById).
*	Editar Planta: O usuário pode editar informações de uma planta existente. Quando o botão "Salvar" é pressionado, o PlantaController realiza a atualização no banco de dados via PlantaDAO.
*	Deletar Planta: O sistema também permite a exclusão de plantas, removendo o registro do banco de dados.


### Conclusão

A arquitetura de três camadas adotada no desenvolvimento do sistema de catálogo de plantas proporciona uma estrutura organizada e escalável, com a separação clara de responsabilidades entre a interface com o usuário, a lógica de negócios e a persistência de dados. Essa estrutura permite a evolução do sistema de forma controlada e fácil de organizar, garantindo que futuras mudanças no banco de dados, na lógica de negócios ou na interface de usuário possam ser feitas sem impacto negativo no restante do código.

### App

<img src"Picture1.png">
