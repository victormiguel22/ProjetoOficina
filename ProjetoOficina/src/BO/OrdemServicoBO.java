package BO;

import DAO.OrdemServicoDAO;
import DAO.VeiculoDAO;
import DTO.OrdemServicoDTO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrdemServicoBO {
    private OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    public void cadastrar(OrdemServicoDTO ordem) throws Exception {
        if (veiculoDAO.buscarPorId(ordem.getVeiculoId()) == null) {
            throw new Exception("Veículo não existe!");
        }
        if (ordemServicoDAO.temOrdemAberta(ordem.getVeiculoId())) {
            throw new Exception("Veículo já tem ordem aberta!");
        }
        ordem.setStatus("Pendente");
        calcularValorTotal(ordem);
        ordemServicoDAO.cadastrar(ordem);
    }

    public List<OrdemServicoDTO> listar() throws SQLException {
        return ordemServicoDAO.listar();
    }

    public List<OrdemServicoDTO> listarPorStatus(String status) throws SQLException {
        return ordemServicoDAO.listarPorStatus(status);
    }

    public List<OrdemServicoDTO> listarPorData(Date start, Date end) throws SQLException {
        return ordemServicoDAO.listarPorData(start, end);
    }

    public List<OrdemServicoDTO> listarPorCliente(int clienteId) throws SQLException {
        return ordemServicoDAO.listarPorCliente(clienteId);
    }

    public OrdemServicoDTO buscarPorId(int id) throws SQLException {
        return ordemServicoDAO.buscarPorId(id);
    }

    public void atualizar(OrdemServicoDTO ordem) throws Exception {
        OrdemServicoDTO existing = ordemServicoDAO.buscarPorId(ordem.getId());
        if (existing == null) {
            throw new Exception("Ordem não existe!");
        }
        if (!validarTransicao(existing.getStatus(), ordem.getStatus())) {
            throw new Exception("Transição de status inválida!");
        }
        if (ordem.getStatus().equals("Concluida")) {
            ordem.setDataSaida(new Date());
        }
        calcularValorTotal(ordem);
        ordemServicoDAO.atualizar(ordem);
    }

    public void excluir(int id) throws Exception {
        OrdemServicoDTO ordem = ordemServicoDAO.buscarPorId(id);
        if (ordem == null) {
            throw new Exception("Ordem não existe!");
        }
        if (!ordem.getStatus().equals("Pendente")) {
            throw new Exception("Só pode excluir ordens em status Pendente!");
        }
        ordemServicoDAO.excluir(id);
    }

    private void calcularValorTotal(OrdemServicoDTO ordem) throws SQLException {
        double base = ordem.getValorPecas() + ordem.getValorMaoObra();
        double taxa = base * 0.10;
        double total = base + taxa;
        int clienteId = ordemServicoDAO.getClienteIdByVeiculo(ordem.getVeiculoId());
        if (ordemServicoDAO.contarOrdensConcluidas(clienteId) > 5) {
            total *= 0.95;
        }
        ordem.setValorTotal(total);
    }

    private boolean validarTransicao(String oldStatus, String newStatus) {
        if (oldStatus.equals("Pendente") && newStatus.equals("Em Andamento")) return true;
        if (oldStatus.equals("Em Andamento") && newStatus.equals("Concluida")) return true;
        if (oldStatus.equals("Concluida") && newStatus.equals("Entregue")) return true;
        return false;
    }
}