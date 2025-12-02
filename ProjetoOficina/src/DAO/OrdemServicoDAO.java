package DAO;

import conexao.ConexaoBD;
import DTO.OrdemServicoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdemServicoDAO {

    public void cadastrar(OrdemServicoDTO ordem) throws SQLException {
        String sql = "INSERT INTO ordens_servico (data_entrada, descricao, servicos, valor_pecas, valor_mao_obra, valor_total, status, veiculo_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(ordem.getDataEntrada().getTime()));
            ps.setString(2, ordem.getDescricao());
            ps.setString(3, ordem.getServicos());
            ps.setDouble(4, ordem.getValorPecas());
            ps.setDouble(5, ordem.getValorMaoObra());
            ps.setDouble(6, ordem.getValorTotal());
            ps.setString(7, ordem.getStatus());
            ps.setInt(8, ordem.getVeiculoId());
            ps.executeUpdate();
        }
    }

    public List<OrdemServicoDTO> listar() throws SQLException {
        List<OrdemServicoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM ordens_servico";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                OrdemServicoDTO o = new OrdemServicoDTO();
                o.setId(rs.getInt("id"));
                o.setDataEntrada(rs.getDate("data_entrada"));
                o.setDataSaida(rs.getDate("data_saida"));
                o.setDescricao(rs.getString("descricao"));
                o.setServicos(rs.getString("servicos"));
                o.setValorPecas(rs.getDouble("valor_pecas"));
                o.setValorMaoObra(rs.getDouble("valor_mao_obra"));
                o.setValorTotal(rs.getDouble("valor_total"));
                o.setStatus(rs.getString("status"));
                o.setVeiculoId(rs.getInt("veiculo_id"));
                lista.add(o);
            }
        }
        return lista;
    }

    public List<OrdemServicoDTO> listarPorStatus(String status) throws SQLException {
        List<OrdemServicoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM ordens_servico WHERE status = ?";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrdemServicoDTO o = new OrdemServicoDTO();
                    o.setId(rs.getInt("id"));
                    o.setDataEntrada(rs.getDate("data_entrada"));
                    o.setDataSaida(rs.getDate("data_saida"));
                    o.setDescricao(rs.getString("descricao"));
                    o.setServicos(rs.getString("servicos"));
                    o.setValorPecas(rs.getDouble("valor_pecas"));
                    o.setValorMaoObra(rs.getDouble("valor_mao_obra"));
                    o.setValorTotal(rs.getDouble("valor_total"));
                    o.setStatus(rs.getString("status"));
                    o.setVeiculoId(rs.getInt("veiculo_id"));
                    lista.add(o);
                }
            }
        }
        return lista;
    }

    public List<OrdemServicoDTO> listarPorData(Date start, Date end) throws SQLException {
        List<OrdemServicoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM ordens_servico WHERE data_entrada BETWEEN ? AND ?";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(start.getTime()));
            ps.setDate(2, new java.sql.Date(end.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrdemServicoDTO o = new OrdemServicoDTO();
                    o.setId(rs.getInt("id"));
                    o.setDataEntrada(rs.getDate("data_entrada"));
                    o.setDataSaida(rs.getDate("data_saida"));
                    o.setDescricao(rs.getString("descricao"));
                    o.setServicos(rs.getString("servicos"));
                    o.setValorPecas(rs.getDouble("valor_pecas"));
                    o.setValorMaoObra(rs.getDouble("valor_mao_obra"));
                    o.setValorTotal(rs.getDouble("valor_total"));
                    o.setStatus(rs.getString("status"));
                    o.setVeiculoId(rs.getInt("veiculo_id"));
                    lista.add(o);
                }
            }
        }
        return lista;
    }

    public List<OrdemServicoDTO> listarPorCliente(int clienteId) throws SQLException {
        List<OrdemServicoDTO> lista = new ArrayList<>();
        String sql = "SELECT o.* FROM ordens_servico o INNER JOIN veiculos v ON o.veiculo_id = v.id WHERE v.cliente_id = ?";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrdemServicoDTO o = new OrdemServicoDTO();
                    o.setId(rs.getInt("id"));
                    o.setDataEntrada(rs.getDate("data_entrada"));
                    o.setDataSaida(rs.getDate("data_saida"));
                    o.setDescricao(rs.getString("descricao"));
                    o.setServicos(rs.getString("servicos"));
                    o.setValorPecas(rs.getDouble("valor_pecas"));
                    o.setValorMaoObra(rs.getDouble("valor_mao_obra"));
                    o.setValorTotal(rs.getDouble("valor_total"));
                    o.setStatus(rs.getString("status"));
                    o.setVeiculoId(rs.getInt("veiculo_id"));
                    lista.add(o);
                }
            }
        }
        return lista;
    }

    public OrdemServicoDTO buscarPorId(int id) throws SQLException {
        OrdemServicoDTO o = null;
        String sql = "SELECT * FROM ordens_servico WHERE id = ?";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    o = new OrdemServicoDTO();
                    o.setId(rs.getInt("id"));
                    o.setDataEntrada(rs.getDate("data_entrada"));
                    o.setDataSaida(rs.getDate("data_saida"));
                    o.setDescricao(rs.getString("descricao"));
                    o.setServicos(rs.getString("servicos"));
                    o.setValorPecas(rs.getDouble("valor_pecas"));
                    o.setValorMaoObra(rs.getDouble("valor_mao_obra"));
                    o.setValorTotal(rs.getDouble("valor_total"));
                    o.setStatus(rs.getString("status"));
                    o.setVeiculoId(rs.getInt("veiculo_id"));
                }
            }
        }
        return o;
    }

    public void atualizar(OrdemServicoDTO ordem) throws SQLException {
        String sql = "UPDATE ordens_servico SET data_entrada = ?, data_saida = ?, descricao = ?, servicos = ?, valor_pecas = ?, valor_mao_obra = ?, valor_total = ?, status = ?, veiculo_id = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(ordem.getDataEntrada().getTime()));
            ps.setDate(2, ordem.getDataSaida() != null ? new java.sql.Date(ordem.getDataSaida().getTime()) : null);
            ps.setString(3, ordem.getDescricao());
            ps.setString(4, ordem.getServicos());
            ps.setDouble(5, ordem.getValorPecas());
            ps.setDouble(6, ordem.getValorMaoObra());
            ps.setDouble(7, ordem.getValorTotal());
            ps.setString(8, ordem.getStatus());
            ps.setInt(9, ordem.getVeiculoId());
            ps.setInt(10, ordem.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM ordens_servico WHERE id = ?";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public boolean temOrdemAberta(int veiculoId) throws SQLException {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM ordens_servico WHERE veiculo_id = ? AND status IN ('Pendente', 'Em Andamento')";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, veiculoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        }
        return count > 0;
    }

    public int getClienteIdByVeiculo(int veiculoId) throws SQLException {
        int clienteId = -1;
        String sql = "SELECT cliente_id FROM veiculos WHERE id = ?";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, veiculoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    clienteId = rs.getInt("cliente_id");
                }
            }
        }
        return clienteId;
    }

    public int contarOrdensConcluidas(int clienteId) throws SQLException {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM ordens_servico o INNER JOIN veiculos v ON o.veiculo_id = v.id WHERE v.cliente_id = ? AND o.status = 'Concluida'";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        }
        return count;
    }

    public boolean hasOrdensAtivas(int veiculoId) throws SQLException {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM ordens_servico WHERE veiculo_id = ? AND status NOT IN ('Concluida', 'Entregue')";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, veiculoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        }
        return count > 0;
    }
}