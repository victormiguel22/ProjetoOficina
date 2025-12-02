package BO;

import DAO.ClienteDAO;
import DAO.VeiculoDAO;
import DTO.ClienteDTO;
import util.Validador;
import java.sql.SQLException;
import java.util.List;

public class ClienteBO {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    public void cadastrar(ClienteDTO cliente) throws Exception {
        if (!Validador.validarCpf(cliente.getCpf())) {
            throw new Exception("CPF inválido!");
        }
        if (clienteDAO.buscarPorCpf(cliente.getCpf()) != null) {
            throw new Exception("CPF já cadastrado!");
        }
        clienteDAO.cadastrar(cliente);
    }

    public List<ClienteDTO> listar() throws SQLException {
        return clienteDAO.listar();
    }

    public ClienteDTO buscarPorId(int id) throws SQLException {
        return clienteDAO.buscarPorId(id);
    }

    public List<ClienteDTO> buscarPorNome(String nome) throws SQLException {
        return clienteDAO.buscarPorNome(nome);
    }

    public ClienteDTO buscarPorCpf(String cpf) throws SQLException {
        return clienteDAO.buscarPorCpf(cpf);
    }

    public void atualizar(ClienteDTO cliente) throws Exception {
        if (!Validador.validarCpf(cliente.getCpf())) {
            throw new Exception("CPF inválido!");
        }
        ClienteDTO existing = clienteDAO.buscarPorCpf(cliente.getCpf());
        if (existing != null && existing.getId() != cliente.getId()) {
            throw new Exception("CPF já cadastrado em outro cliente!");
        }
        clienteDAO.atualizar(cliente);
    }

    public void excluir(int id) throws Exception {
        if (veiculoDAO.countByCliente(id) > 0) {
            throw new Exception("Cliente possui veículos associados!");
        }
        clienteDAO.excluir(id);
    }
}