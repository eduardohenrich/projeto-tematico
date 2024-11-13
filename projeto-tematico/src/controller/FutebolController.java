package controller;

import java.sql.Connection;

import dao.Conexao;
import dao.FutebolDAO;
import model.Futebol;
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

}