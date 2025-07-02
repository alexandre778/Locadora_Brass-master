# Banco de Dados - Locadora Brass

Este projeto define a estrutura do banco de dados para a aplicação **Locadora Brass**, uma locadora de veículos. O banco de dados é implementado em **MySQL** e contém as tabelas principais necessárias para gerenciar clientes, veículos e aluguéis.

---

## 📦 Estrutura do Banco de Dados

O script SQL cria e popula automaticamente o banco `locadora_brass` com as seguintes tabelas:

### 🔹 `clientes`
Armazena os dados dos clientes cadastrados.

| Coluna          | Tipo         | Descrição                            |
|------------------|--------------|----------------------------------------|
| `id`            | INT (PK)     | Identificador único (auto incremento) |
| `nome`          | VARCHAR(100) | Nome completo do cliente              |
| `cpfCnpj`       | VARCHAR(20)  | CPF ou CNPJ do cliente (único)        |
| `dataNascimento`| DATE         | Data de nascimento                    |
| `endereco`      | VARCHAR(255) | Endereço completo                     |

---

### 🔹 `veiculos`
Contém os dados dos veículos disponíveis para locação.

| Coluna    | Tipo           | Descrição                                 |
|-----------|----------------|---------------------------------------------|
| `id`      | INT (PK)       | Identificador único (auto incremento)      |
| `modelo`  | VARCHAR(100)   | Modelo do veículo                         |
| `placa`   | VARCHAR(10)    | Placa do veículo (único)                  |
| `tipo`    | VARCHAR(50)    | Tipo do veículo (Ex: Hatch, Sedan)        |
| `ano`     | INT            | Ano de fabricação                         |
| `diaria`  | DECIMAL(10,2)  | Valor da diária de locação                |
| `status`  | VARCHAR(20)    | Status atual (Disponível, Alugado, etc.) |

---

### 🔹 `alugueis`
Registra os contratos de aluguel feitos por clientes.

| Coluna         | Tipo           | Descrição                             |
|----------------|----------------|-----------------------------------------|
| `id`           | INT (PK)       | Identificador único do aluguel         |
| `cliente_id`   | INT (FK)       | Referência ao cliente que alugou       |
| `veiculo_id`   | INT (FK)       | Referência ao veículo alugado          |
| `dias`         | INT            | Quantidade de dias de aluguel          |
| `valor_total`  | DECIMAL(10,2)  | Valor total da locação                 |
| `data_aluguel` | DATE           | Data em que a locação foi realizada    |

---

## 🔄 Relacionamentos

- Um cliente pode realizar vários aluguéis.
- Um veículo pode ser alugado várias vezes, mas apenas um aluguel por vez.
- As tabelas `alugueis` fazem referência direta às tabelas `clientes` e `veiculos`.

---

## 🧪 Dados de Exemplo

O script também insere alguns registros iniciais:

- **Clientes**: João Silva, Maria Souza
- **Veículos**: Fiat Uno, Gol, Onix, Corolla, Ford Ka
- **Aluguel de Exemplo**: João Silva alugou o Onix por 3 dias.

---

## ▶️ Como Executar

1. Abra seu cliente MySQL (como MySQL Workbench, phpMyAdmin ou terminal).
2. Execute o script SQL completo fornecido.
3. Certifique-se de que o usuário possui permissões para criar banco e tabelas.
4. Verifique se o banco `locadora_brass` foi criado com sucesso.

---

## ⚙️ Requisitos

- **MySQL Server** (versão 5.7 ou superior recomendada)
- Permissões de criação de banco e tabelas

---

## 📁 Autor e Projeto

Este banco de dados faz parte do projeto **Locadora Brass**, um sistema de gestão de locação de veículos desenvolvido em Java (Swing) com MySQL.

