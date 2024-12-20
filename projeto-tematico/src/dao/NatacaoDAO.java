package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Natacao;

public class NatacaoDAO {

    private final Connection connection;

    public NatacaoDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Natacao natacao) throws SQLException {
        String sql = "INSERT INTO natacao(nome, primeiro, segundo, terceiro) VALUES (?, ?, ?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, natacao.getNome());
        stm.setString(2, natacao.getPrimeiro());
        stm.setString(3, natacao.getSegundo());
        stm.setString(4, natacao.getTerceiro());
        stm.execute();

        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                natacao.setId(generatedKeys.getInt(1));
            }
        }

        stm.close();
        connection.close();
    }

    public void update(Natacao natacao) throws SQLException {
        String sql = "UPDATE natacao SET nome = ?, primeiro = ?, segundo = ?, terceiro = ? WHERE id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, natacao.getNome());
        stm.setString(2, natacao.getPrimeiro());
        stm.setString(3, natacao.getSegundo());
        stm.setString(4, natacao.getTerceiro());
        stm.setInt(5, natacao.getId());
        stm.execute();

        stm.close();
        connection.close();
    }

    public Natacao consultaNatacaoPorNome(String nome) throws SQLException {
        String sql = "SELECT id, nome, primeiro, segundo, terceiro FROM natacao WHERE nome = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String primeiro = rs.getString("primeiro");
                    String segundo = rs.getString("segundo");
                    String terceiro = rs.getString("terceiro");

                    return new Natacao(id, nome, primeiro, segundo, terceiro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Natacao consultaNatacaoPorId(int id) throws SQLException {
        String sql = "SELECT nome, primeiro, segundo, terceiro FROM natacao WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String primeiro = rs.getString("primeiro");
                    String segundo = rs.getString("segundo");
                    String terceiro = rs.getString("terceiro");

                    return new Natacao(id, nome, primeiro, segundo, terceiro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Natacao> consultaNatacaos() throws SQLException {
        String sql = "SELECT id, nome, primeiro, segundo, terceiro FROM natacao";
        List<Natacao> natacaos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String primeiro = rs.getString("primeiro");
                    String segundo = rs.getString("segundo");
                    String terceiro = rs.getString("terceiro");

                    Natacao natacao = new Natacao(id, nome, primeiro, segundo, terceiro);
                    natacaos.add(natacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return natacaos; // Return the List
    }

    public boolean deleteNatacao(int id) throws SQLException {
        String sql = "DELETE FROM natacao WHERE id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);
        int rows = stm.executeUpdate();

        stm.close();
        connection.close();

        return rows > 0;
    }
}
