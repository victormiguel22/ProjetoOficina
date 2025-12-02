package BO;

import DAO.VeiculoDAO;
import DAO.ClienteDAO;
import DAO.OrdemServicoDAO;
import DTO.VeiculoDTO;
import util.Validador;
import java.sql.SQLException;
import java.util.List;

public class VeiculoBO {
    private VeiculoDAO veiculoDAO = new VeiculoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();

    public void cadastrar(VeiculoDTO veiculo) throws Exception {
        if (!Validador.validarPlaca(veiculo.getPlaca())) {
            throw new Exception("Placa inválida!");
        }
        if (veiculoDAO.buscarPorPlaca(veiculo.getPlaca()) != null) {
            throw new Exception("Placa já cadastrada!");
        }
        if (clienteDAO.buscarPorId(veiculo.getClienteId()) == null) {
            throw new Exception("Cliente não existe!");
        }
        veiculoDAO.cadastrar(veiculo);
    }

    public List<VeiculoDTO> listar() throws SQLException {
        return veiculoDAO.listar();
    }

    public List<VeiculoDTO> listarPorCliente(int clienteId) throws SQLException {
        return veiculoDAO.listarPorCliente(clienteId);
    }

    public VeiculoDTO buscarPorId(int id) throws SQLException {
        return veiculoDAO.buscarPorId(id);
    }

    public VeiculoDTO buscarPorPlaca(String placa) throws SQLException {
        return veiculoDAO.buscarPorPlaca(placa);
    }

    public void atualizar(VeiculoDTO veiculo) throws Exception {
        if (!Validador.validarPlaca(veiculo.getPlaca())) {
            throw new Exception("Placa inválida!");
        }
        VeiculoDTO existing = veiculoDAO.buscarPorPlaca(veiculo.getPlaca());
        if (existing != null && existing.getId() != veiculo.getId()) {
            throw new Exception("Placa já cadastrada em outro veículo!");
        }
        if (clienteDAO.buscarPorId(veiculo.getClienteId()) == null) {
            throw new Exception("Cliente não existe!");
        }
        veiculoDAO.atualizar(veiculo);
    }

    public void excluir(int id) throws Exception {
        if (ordemServicoDAO.hasOrdensAtivas(id)) {
            throw new Exception("Veículo possui ordens de serviço ativas!");
        }
        veiculoDAO.excluir(id);
    }
}