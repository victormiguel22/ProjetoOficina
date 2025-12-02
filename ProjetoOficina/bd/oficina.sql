-- Tabela Clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(255),
    endereco VARCHAR(255)
);

-- Tabela Veiculos
CREATE TABLE veiculos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10) UNIQUE NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    ano INT NOT NULL,
    cor VARCHAR(20),
    cliente_id INT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);

-- Tabela Ordens de Serviço
CREATE TABLE ordens_servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_entrada DATE NOT NULL,
    data_saida DATE,
    descricao TEXT,
    servicos TEXT,
    valor_pecas DOUBLE DEFAULT 0,
    valor_mao_obra DOUBLE DEFAULT 0,
    valor_total DOUBLE DEFAULT 0,
    status VARCHAR(20) DEFAULT 'Pendente',
    veiculo_id INT NOT NULL,
    FOREIGN KEY (veiculo_id) REFERENCES veiculos(id) ON DELETE CASCADE
);

-- Dados de teste
INSERT INTO clientes (nome, cpf, telefone, email, endereco) VALUES
('João Silva', '12345678909', '(11) 98765-4321', 'joao@email.com', 'Rua A, 123'),
('Maria Oliveira', '98765432100', '(11) 91234-5678', 'maria@email.com', 'Av. B, 456');

INSERT INTO veiculos (placa, marca, modelo, ano, cor, cliente_id) VALUES
('ABC1234', 'Volkswagen', 'Gol', 2018, 'Prata', 1),
('XYZ9A99', 'Fiat', 'Palio', 2020, 'Preto', 2);