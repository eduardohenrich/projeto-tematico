package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.NatacaoController;
import model.Natacao;
import java.awt.*;

public class telaNatacao extends JFrame {

    private static final long serialVersionUID = 1L;
    private final NatacaoController controller;
    private JPanel contentPane;
    private JTextField nomeField;
    private JTextField primeiroField;
    private JTextField segundoField;
    private JTextField terceiroField;
    private JLabel messageLabel;

    public telaNatacao(String message, String name, int role, Natacao natacao) {
        controller = new NatacaoController(this);
        initializeFrame(message, name, role, natacao);
    }

    private void initializeFrame(String message, String name, int role, Natacao natacao) {
        boolean update = natacao != null;
        if (update) {
            setTitle("Atualizar Evento de Natação - OlympicBET");
        } else {
            setTitle("Cadastro de Natação - OlympicBET");
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1121, 694);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        JPanel natacaoPanel = createNatacaoPanel(message, name, role, natacao);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(natacaoPanel, gbc);
    }

    private JPanel createNatacaoPanel(String message, String name, int role, final Natacao natacao) {

        boolean update = natacao != null;

        JPanel natacaoPanel = new JPanel();
        natacaoPanel.setBackground(new Color(192, 192, 192));
        natacaoPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Cadastro de Natação");
        titleLabel.setFont(new Font("Sylfaen", Font.BOLD, 18));
        GridBagConstraints gbc_titleLabel = createGbc(0, 0, 2);
        natacaoPanel.add(titleLabel, gbc_titleLabel);

        JLabel nomeLabel = new JLabel("Nome do Evento");
        nomeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_nomeLabel = createGbc(0, 1);
        natacaoPanel.add(nomeLabel, gbc_nomeLabel);

        nomeField = new JTextField(20);
        nomeField.setBackground(Color.WHITE);
        if (update) {
            nomeField.setText(natacao.getNome());
        }
        GridBagConstraints gbc_nomeField = createGbc(1, 1);
        natacaoPanel.add(nomeField, gbc_nomeField);

        JLabel primeiroLabel = new JLabel("1º Colocado");
        primeiroLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_primeiroLabel = createGbc(0, 2);
        natacaoPanel.add(primeiroLabel, gbc_primeiroLabel);

        primeiroField = new JTextField(20);
        primeiroField.setBackground(Color.WHITE);
        if (update) {
            primeiroField.setText(natacao.getPrimeiro());
        }
        GridBagConstraints gbc_primeiroField = createGbc(1, 2);
        natacaoPanel.add(primeiroField, gbc_primeiroField);

        JLabel segundoLabel = new JLabel("2º Colocado");
        segundoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_segundoLabel = createGbc(0, 3);
        natacaoPanel.add(segundoLabel, gbc_segundoLabel);

        segundoField = new JTextField(20);
        segundoField.setBackground(Color.WHITE);
        if (update) {
            segundoField.setText(natacao.getSegundo());
        }
        GridBagConstraints gbc_segundoField = createGbc(1, 3);
        natacaoPanel.add(segundoField, gbc_segundoField);

        JLabel terceiroLabel = new JLabel("3º Colocado");
        terceiroLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_terceiroLabel = createGbc(0, 4);
        natacaoPanel.add(terceiroLabel, gbc_terceiroLabel);

        terceiroField = new JTextField(20);
        terceiroField.setBackground(Color.WHITE);
        if (update) {
            terceiroField.setText(natacao.getTerceiro());
        }
        GridBagConstraints gbc_terceiroField = createGbc(1, 4);
        natacaoPanel.add(terceiroField, gbc_terceiroField);

        JButton cadastrarButton = new JButton(natacao == null ? "Cadastrar Evento" : "Atualizar Evento");
        cadastrarButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        cadastrarButton.setBackground(Color.WHITE);
        cadastrarButton.addActionListener(e -> {

            if (!update) {
                controller.salvaNatacao(); // Cadastrar novo evento
            } else {
                controller.updateNatacao(natacao.getId()); // Atualizar evento existente
            }
        });
        GridBagConstraints gbc_cadastrarButton = createGbc(1, 5);
        natacaoPanel.add(cadastrarButton, gbc_cadastrarButton);

        messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_messageLabel = createGbc(1, 6);
        natacaoPanel.add(messageLabel, gbc_messageLabel);

        JButton voltarButton = new JButton(" <- Voltar");
        voltarButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        voltarButton.setBackground(Color.WHITE);
        voltarButton.addActionListener(e -> voltarParaTelaInicial(name, role));
        GridBagConstraints gbc_voltarButton = createGbc(0, 7);
        natacaoPanel.add(voltarButton, gbc_voltarButton);

        return natacaoPanel;
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    private GridBagConstraints createGbc(int x, int y, int width) {
        GridBagConstraints gbc = createGbc(x, y);
        gbc.gridwidth = width;
        return gbc;
    }

    private void voltarParaTelaInicial(String nome, int role) {
        dispose();
        // Assuming you have a TelaInicial class to go back to
        Principal telaInicial = new Principal(nome, role);
        telaInicial.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaInicial.setLocationRelativeTo(null);
        telaInicial.setVisible(true);
    }

    public JTextField getNomeField() {
        return nomeField;
    }

    public JTextField getPrimeiroField() {
        return primeiroField;
    }

    public JTextField getSegundoField() {
        return segundoField;
    }

    public JTextField getTerceiroField() {
        return terceiroField;
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }
}
