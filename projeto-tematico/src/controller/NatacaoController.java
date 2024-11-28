package controller;

import java.sql.Connection;

import dao.Conexao;
import dao.NatacaoDAO;
import model.Natacao;
import view.telaNatacao;

public class NatacaoController {

    private telaNatacao tela;

    public NatacaoController(telaNatacao tela) {
        this.tela = tela;
    }

    public void salvaNatacao() {
        String nome = tela.getNomeField().getText();
        String primeiro = tela.getPrimeiroField().getText();
        String segundo = tela.getSegundoField().getText();
        String terceiro = tela.getTerceiroField().getText();

        Connection conexao = null;
        try {
            conexao = new Conexao().getConnection();
            NatacaoDAO natacaoDAO = new NatacaoDAO(conexao);
            natacaoDAO.insert(new Natacao(nome, primeiro, segundo, terceiro));
            tela.getMessageLabel().setText("Evento de Natação cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            tela.getMessageLabel().setText("Ouve um erro ao cadastrar o evento de Natação!");
            return;
        }
    }

    public void updateNatacao(int id) {

        String nome = tela.getNomeField().getText();
        String primeiro = tela.getPrimeiroField().getText();
        String segundo = tela.getSegundoField().getText();
        String terceiro = tela.getTerceiroField().getText();

        Connection conexao = null;
        try {
            conexao = new Conexao().getConnection();
            NatacaoDAO natacaoDAO = new NatacaoDAO(conexao);
            natacaoDAO.update(new Natacao(id, nome, primeiro, segundo, terceiro));
            tela.getMessageLabel().setText("Evento de Natação atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            tela.getMessageLabel().setText("Ouve um erro ao atualizar o evento de Natação!");
            return;
        }
    }
}
