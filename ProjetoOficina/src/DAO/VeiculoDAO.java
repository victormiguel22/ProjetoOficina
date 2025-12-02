package DAO;

import conexao.ConexaoBD;
import DTO.VeiculoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    public void cadastrar(VeiculoDTO veiculo) throws SQLException {
        String sql = "INSERT INTO veiculos (placa, marca, modelo, ano, cor, cliente_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, veiculo.getPlaca());
            ps.setString(2, veiculo.getMarca());
            ps.setString(3, veiculo.getModelo());
            ps.setInt(4, veiculo.getAno());
            ps.setString(5, veiculo.getCor());
            ps.setInt(6, veiculo.getClienteId());
            ps.executeUpdate();
        }
    }

    public List<VeiculoDTO> listar() throws SQLException {
        List<VeiculoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM veiculos";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                VeiculoDTO v = new VeiculoDTO();
                v.setId(rs.getInt("id"));
                v.setPlaca(rs.getString("placa"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setAno(rs.getInt("ano"));
                v.setCor(rs.getString("cor"));
                v.setClienteId(rs.getInt("cliente_id"));
                lista.add(v);
            }
        }
        return lista;
    }

    public List<VeiculoDTO> listarPorCliente(int clienteId) throws SQLException {
        List<VeiculoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM veiculos WHERE cliente_id = ?";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VeiculoDTO v = new VeiculoDTO();
                    v.setId(rs.getInt("id"));
                    v.setPlaca(rs.getString("placa"));
                    v.setMarca(rs.getString("marca"));
                    v.setModelo(rs.getString("modelo"));
                    v.setAno(rs.getInt("ano"));
                    v.setCor(rs.getString("cor"));
                    v.setClienteId(rs.getInt("cliente_id"));
                    lista.add(v);
                }
            }
        }
        return lista;
    }

    public VeiculoDTO buscarPorId(int id) throws SQLException {
        VeiculoDTO v = null;
        String sql = "SELECT * FROM veiculos WHERE id = ?";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    v = new VeiculoDTO();
                    v.setId(rs.getInt("id"));
                    v.setPlaca(rs.getString("placa"));
                    v.setMarca(rs.getString("marca"));
                    v.setModelo(rs.getString("modelo"));
                    v.setAno(rs.getInt("ano"));
                    v.setCor(rs.getString("cor"));
                    v.setClienteId(rs.getInt("cliente_id"));
                }
            }
        }
        return v;
    }

    public VeiculoDTO buscarPorPlaca(String placa) throws SQLException {
        VeiculoDTO v = null;
        String sql = "SELECT * FROM veiculos WHERE placa = ?";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, placa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    v = new VeiculoDTO();
                    v.setId(rs.getInt("id"));
                    v.setPlaca(rs.getString("placa"));
                    v.setMarca(rs.getString("marca"));
                    v.setModelo(rs.getString("modelo"));
                    v.setAno(rs.getInt("ano"));
                    v.setCor(rs.getString("cor"));
                    v.setClienteId(rs.getInt("cliente_id"));
                }
            }
        }
        return v;
    }

    public void atualizar(VeiculoDTO veiculo) throws SQLException {
        String sql = "UPDATE veiculos SET placa = ?, marca = ?, modelo = ?, ano = ?, cor = ?, cliente_id = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, veiculo.getPlaca());
            ps.setString(2, veiculo.getMarca());
            ps.setString(3, veiculo.getModelo());
            ps.setInt(4, veiculo.getAno());
            ps.setString(5, veiculo.getCor());
            ps.setInt(6, veiculo.getClienteId());
            ps.setInt(7, veiculo.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM veiculos WHERE id = ?";
        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public int countByCliente(int clienteId) throws SQLException {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM veiculos WHERE cliente_id = ?";
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