package dao;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Futebol;

public class FutebolDAO {

    private final Connection connection;

    public FutebolDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Futebol futebol) throws SQLException {
        String sql = "INSERT INTO futebol(nome, timeA, pontoA, timeB, pontoB) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, futebol.getNome());
        stm.setString(2, futebol.getTimeA());
        stm.setInt(3, futebol.getPontoA());
        stm.setString(4, futebol.getTimeB());
        stm.setInt(5, futebol.getPontoB());
        stm.execute();

        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                futebol.setId(generatedKeys.getInt(1));
            }
        }

        stm.close();
        connection.close();
    }

    public Futebol consultaFutebolPorNome(String nome) throws SQLException {
        String sql = "SELECT id, nome, timeA, pontoA, timeB, pontoB FROM futebol WHERE nome = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String timeA = rs.getString("timeA");
                    int pontoA = rs.getInt("pontoA");
                    String timeB = rs.getString("timeB");
                    int pontoB = rs.getInt("pontoB");

                    return new Futebol(id, nome, timeA, timeB, pontoA, pontoB);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Futebol consultaFutebolPorId(int id) throws SQLException {
        String sql = "SELECT nome, timeA, pontoA, timeB, pontoB FROM futebol WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String timeA = rs.getString("timeA");
                    int pontoA = rs.getInt("pontoA");
                    String timeB = rs.getString("timeB");
                    int pontoB = rs.getInt("pontoB");

                    return new Futebol(id, nome, timeA, timeB, pontoA, pontoB);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

public List<Futebol> consultaFutebols() {
    String sql = "SELECT nome, timeA, pontoA, timeB, pontoB FROM futebol";
    
    List<Futebol> futebols = new ArrayList<>(); // Usando ArrayList

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                String timeA = rs.getString("timeA");
                int pontoA = rs.getInt("pontoA");
                String timeB = rs.getString("timeB");
                int pontoB = rs.getInt("pontoB");

                futebols.add(new Futebol(nome, timeA, timeB, pontoA, pontoB));
            }
            // Se necessário, imprimir os dados
            for (Futebol futebol : futebols) {
                System.out.println(futebol);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return futebols;
}

}
