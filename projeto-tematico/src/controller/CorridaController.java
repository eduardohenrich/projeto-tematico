package controller;

import java.sql.Connection;

import dao.Conexao;
import dao.CorridaDAO;
import model.Corrida;
import view.telaCorrida;

public class CorridaController {

    private telaCorrida tela;

    public CorridaController(telaCorrida tela) {
        this.tela = tela;
    }

    public void salvaCorrida() {
        String nome = tela.getNomeField().getText();
        String primeiro = tela.getPrimeiroField().getText();
        String segundo = tela.getSegundoField().getText();
        String terceiro = tela.getTerceiroField().getText();

        Corrida newCorrida = new Corrida(nome, primeiro, segundo, terceiro);
        // Add validation here if needed

        Connection conexao = null;
        try {
            conexao = new Conexao().getConnection();
            CorridaDAO corridaDAO = new CorridaDAO(conexao);
            corridaDAO.insert(newCorrida);
            tela.getMessageLabel().setText("Corrida cadastrada com sucesso!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            tela.getMessageLabel().setText("Ouve um erro ao cadastrar a corrida!");
            return;
        }
    }

    public void updateCorrida(int id) {

        String nome = tela.getNomeField().getText();
        String primeiro = tela.getPrimeiroField().getText();
        String segundo = tela.getSegundoField().getText();
        String terceiro = tela.getTerceiroField().getText();

        Corrida newCorrida = new Corrida(id, nome, primeiro, segundo, terceiro);

        Connection conexao = null;
        try {
            conexao = new Conexao().getConnection();
            CorridaDAO corridaDAO = new CorridaDAO(conexao);
            corridaDAO.update(newCorrida);
            tela.getMessageLabel().setText("Corrida atualizada com sucesso!");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            tela.getMessageLabel().setText("Ouve um erro ao atualizar a corrida!");
            return;
        }
    }

}
