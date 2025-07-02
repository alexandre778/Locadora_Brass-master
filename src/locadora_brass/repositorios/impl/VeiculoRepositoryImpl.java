package locadora_brass.repositorios.impl;

import locadora_brass.DBConnection;
import locadora_brass.dto.VeiculoDTO;
import locadora_brass.repositorios.IVeiculoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoRepositoryImpl implements IVeiculoRepository {

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    @Override
    public boolean salvar(VeiculoDTO veiculo) {
        String sql = "INSERT INTO veiculos (modelo, placa, tipo, ano, diaria, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, veiculo.getModelo());
            ps.setString(2, veiculo.getPlaca());
            ps.setString(3, veiculo.getTipo());
            ps.setInt(4, veiculo.getAno());
            ps.setDouble(5, veiculo.getDiaria());
            ps.setString(6, veiculo.getStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        veiculo.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<VeiculoDTO> listarTodos() {
        List<VeiculoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM veiculos";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                VeiculoDTO v = new VeiculoDTO();
                v.setId(rs.getInt("id"));
                v.setModelo(rs.getString("modelo"));
                v.setPlaca(rs.getString("placa"));
                v.setTipo(rs.getString("tipo"));
                v.setAno(rs.getInt("ano"));
                v.setDiaria(rs.getDouble("diaria"));
                v.setStatus(rs.getString("status"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<VeiculoDTO> listarDisponiveis() {
        List<VeiculoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM veiculos WHERE status = 'Disponível'";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                VeiculoDTO v = new VeiculoDTO();
                v.setId(rs.getInt("id"));
                v.setModelo(rs.getString("modelo"));
                v.setPlaca(rs.getString("placa"));
                v.setTipo(rs.getString("tipo"));
                v.setAno(rs.getInt("ano"));
                v.setDiaria(rs.getDouble("diaria"));
                v.setStatus(rs.getString("status"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public VeiculoDTO buscarPorId(int id) {
        VeiculoDTO v = null;
        String sql = "SELECT * FROM veiculos WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    v = new VeiculoDTO();
                    v.setId(rs.getInt("id"));
                    v.setModelo(rs.getString("modelo"));
                    v.setPlaca(rs.getString("placa"));
                    v.setTipo(rs.getString("tipo"));
                    v.setAno(rs.getInt("ano"));
                    v.setDiaria(rs.getDouble("diaria"));
                    v.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }
}
