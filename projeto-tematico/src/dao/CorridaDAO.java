package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Corrida;
import model.Futebol;

public class CorridaDAO {

    private final Connection connection;

    public CorridaDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Corrida corrida) throws SQLException {
        String sql = "INSERT INTO corrida(nome, primeiro, segundo, terceiro) VALUES (?, ?, ?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, corrida.getNome());
        stm.setString(2, corrida.getPrimeiro());
        stm.setString(3, corrida.getSegundo());
        stm.setString(4, corrida.getTerceiro());
        stm.execute();

        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                corrida.setId(generatedKeys.getInt(1));
            }
        }

        stm.close();
        connection.close();
    }

    public void update(Corrida corrida) throws SQLException {
        String sql = "UPDATE corrida SET nome = ?, primeiro = ?, segundo = ?, terceiro = ? WHERE id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, corrida.getNome());
        stm.setString(2, corrida.getPrimeiro());
        stm.setString(3, corrida.getSegundo());
        stm.setString(4, corrida.getTerceiro());
        stm.setInt(5, corrida.getId());
        stm.execute();

        stm.close();
        connection.close();
    }

    public Corrida consultaCorridaPorNome(String nome) throws SQLException {
        String sql = "SELECT id, nome, primeiro, segundo, terceiro FROM corrida WHERE nome = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String primeiro = rs.getString("primeiro");
                    String segundo = rs.getString("segundo");
                    String terceiro = rs.getString("terceiro");

                    return new Corrida(id, nome, primeiro, segundo, terceiro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Corrida consultaCorridaPorId(int id) throws SQLException {
        String sql = "SELECT nome, primeiro, segundo, terceiro FROM corrida WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String primeiro = rs.getString("primeiro");
                    String segundo = rs.getString("segundo");
                    String terceiro = rs.getString("terceiro");

                    return new Corrida(id, nome, primeiro, segundo, terceiro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Corrida> consultaCorridas() {
        String sql = "SELECT id, nome, primeiro, segundo, terceiro FROM corrida";
        List<Corrida> corridas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String primeiro = rs.getString("primeiro");
                    String segundo = rs.getString("segundo");
                    String terceiro = rs.getString("terceiro");

                    Corrida corrida = new Corrida(id, nome, primeiro, segundo, terceiro);
                    corridas.add(corrida);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return corridas; // Return the List
    }

    public boolean deleteCorrida(int id) throws SQLException {
        String sql = "DELETE FROM corrida WHERE id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        int rows = stm.executeUpdate();

        stm.close();
        connection.close();

        return rows > 0;
    }
}
