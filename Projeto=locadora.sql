-- Criar o banco de dados
CREATE DATABASE IF NOT EXISTS locadora_brass;
USE locadora_brass;

-- ============================
-- TABELA: clientes
-- ============================
CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpfCnpj VARCHAR(20) NOT NULL UNIQUE,
    dataNascimento DATE,
    endereco VARCHAR(255)
);

-- ============================
-- TABELA: veiculos
-- ============================
CREATE TABLE IF NOT EXISTS veiculos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    placa VARCHAR(10) NOT NULL UNIQUE,
    tipo VARCHAR(50),
    ano INT,
    diaria DECIMAL(10,2),
    status VARCHAR(20) -- Ex: Disponível, Alugado, Manutenção
);

-- ============================
-- TABELA: alugueis
-- ============================
CREATE TABLE IF NOT EXISTS alugueis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    veiculo_id INT NOT NULL,
    dias INT NOT NULL,
    valor_total DECIMAL(10,2),
    data_aluguel DATE,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (veiculo_id) REFERENCES veiculos(id)
);

-- ============================
-- DADOS DE EXEMPLO
-- ============================

-- Clientes
INSERT INTO clientes (nome, cpfCnpj, dataNascimento, endereco) VALUES
('João Silva', '123.456.789-00', '1990-05-20', 'Rua A, nº 100'),
('Maria Souza', '987.654.321-00', '1985-10-10', 'Av. B, nº 200');

-- Veículos
INSERT INTO veiculos (modelo, placa, tipo, ano, diaria, status) VALUES
('Fiat Uno', 'ABC1234', 'Hatch', 2015, 89.90, 'Disponível'),
('Volkswagen Gol', 'DEF5678', 'Hatch', 2018, 95.00, 'Disponível'),
('Chevrolet Onix', 'GHI9012', 'Sedan', 2020, 120.00, 'Alugado'),
('Toyota Corolla', 'JKL3456', 'Sedan', 2019, 150.00, 'Disponível'),
('Ford Ka', 'MNO7890', 'Hatch', 2017, 85.00, 'Manutenção');

-- Aluguéis
INSERT INTO alugueis (cliente_id, veiculo_id, dias, valor_total, data_aluguel) VALUES
(1, 3, 3, 360.00, '2025-06-25');
