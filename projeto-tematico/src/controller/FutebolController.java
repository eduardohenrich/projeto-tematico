package controller;

import java.sql.Connection;
import java.sql.SQLException;

import dao.*;
import model.*;
import view.telaFutebol;

public class FutebolController {

    private telaFutebol tela;

    public FutebolController(telaFutebol tela) {
        this.tela = tela;
    }

    public void salvaFutebol() {
        String nome = tela.getNomeField().getText();
        String timeA = tela.getTimeAField().getText();
        int pontoA = Integer.parseInt(tela.getPontoAField().getText());
        String timeB = tela.getTimeBField().getText();
        int pontoB = Integer.parseInt(tela.getPontoBField().getText());

        Connection conexao = null;
        try {
            conexao = new Conexao().getConnection();
            FutebolDAO futebolDAO = new FutebolDAO(conexao);
            futebolDAO.insert(new Futebol(nome, timeA, timeB, pontoA, pontoB));
            tela.getMessageLabel().setText("Jogo de futebol cadastrado com sucesso!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            tela.getMessageLabel().setText("Ouve um erro ao cadastrar o jogo de futebol!");
            return;
        }
    }

public void updateFutebol(int id) {
    String nome = tela.getNomeField().getText();
    String timeA = tela.getTimeAField().getText();
    int pontoA = Integer.parseInt(tela.getPontoAField().getText());
    String timeB = tela.getTimeBField().getText();
    int pontoB = Integer.parseInt(tela.getPontoBField().getText());

    Connection conexao = null;
    try {
        conexao = new Conexao().getConnection();
        FutebolDAO futebolDAO = new FutebolDAO(conexao);
        futebolDAO.update(new Futebol(id, nome, timeA, timeB, pontoA, pontoB));
        
        // If we get here, the update was successful
        tela.getMessageLabel().setText("Jogo de futebol atualizado com sucesso!");
    } catch (SQLException e) {
        // Handle SQLException (if the update fails)
        e.printStackTrace();
        tela.getMessageLabel().setText("Houve um erro ao atualizar o jogo de futebol: " + e.getMessage());
    } catch (Exception e) {
        // Handle other exceptions
        e.printStackTrace();
        tela.getMessageLabel().setText("Ocorreu um erro inesperado!");
    } finally {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();  // Close the connection after use
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


}