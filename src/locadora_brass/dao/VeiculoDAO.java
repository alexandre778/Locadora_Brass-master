package locadora_brass.dao;

import locadora_brass.dto.VeiculoDTO;


import java.sql.*;
import locadora_brass.DBConnection;

public class VeiculoDAO {

    public int inserir(VeiculoDTO veiculo) {
        String sql = "INSERT INTO veiculos (modelo, placa, tipo, ano, diaria, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, veiculo.getModelo());
            ps.setString(2, veiculo.getPlaca());
            ps.setString(3, veiculo.getTipo());
            ps.setInt(4, veiculo.getAno());
            ps.setDouble(5, veiculo.getDiaria());
            ps.setString(6, veiculo.getStatus());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("Falha ao inserir veículo, nenhuma linha afetada.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    veiculo.setId(idGerado); // seta o ID no DTO
                    return idGerado;
                } else {
                    throw new SQLException("Falha ao obter ID gerado do veículo.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // erro
        }
    }

    // Outros métodos como listar, atualizar, deletar etc...
}
