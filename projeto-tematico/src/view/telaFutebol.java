package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.FutebolController;
import java.awt.*;

public class telaFutebol extends JFrame {

    private static final long serialVersionUID = 1L;
    private final FutebolController controller;
    private JPanel contentPane;
    private JTextField nomeField;
    private JTextField timeAField;
    private JTextField pontoAField;
    private JTextField timeBField;
    private JTextField pontoBField;
    private JLabel messageLabel;

    public telaFutebol(String message, String name, int role) {
        controller = new FutebolController(this);
        initializeFrame(message, name, role);
    }

    private void initializeFrame(String message, String name, int role) {
        setTitle("Cadastro de Futebol - OlympicBET");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1121, 694);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        JPanel futebolPanel = createFutebolPanel(message, name, role);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(futebolPanel, gbc);
    }

    private JPanel createFutebolPanel(String message, String name, int role) {
        JPanel futebolPanel = new JPanel();
        futebolPanel.setBackground(new Color(192, 192, 192));
        futebolPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Cadastro de Futebol");
        titleLabel.setFont(new Font("Sylfaen", Font.BOLD, 18));
        GridBagConstraints gbc_titleLabel = createGbc(0, 0, 2);
        futebolPanel.add(titleLabel, gbc_titleLabel);

        JLabel nomeLabel = new JLabel("Nome do Jogo");
        nomeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_nomeLabel = createGbc(0, 1);
        futebolPanel.add(nomeLabel, gbc_nomeLabel);

        nomeField = new JTextField(20);
        nomeField.setBackground(Color.WHITE);
        GridBagConstraints gbc_nomeField = createGbc(1, 1);
        futebolPanel.add(nomeField, gbc_nomeField);

        JLabel timeALabel = new JLabel("Time A");
        timeALabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_timeALabel = createGbc(0, 2);
        futebolPanel.add(timeALabel, gbc_timeALabel);

        timeAField = new JTextField(20);
        timeAField.setBackground(Color.WHITE);
        GridBagConstraints gbc_timeAField = createGbc(1, 2);
        futebolPanel.add(timeAField, gbc_timeAField);

        JLabel pontoALabel = new JLabel("Pontos Time A");
        pontoALabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_pontoALabel = createGbc(0, 3);
        futebolPanel.add(pontoALabel, gbc_pontoALabel);

        pontoAField = new JTextField(20);
        pontoAField.setBackground(Color.WHITE);
        GridBagConstraints gbc_pontoAField = createGbc(1, 3);
        futebolPanel.add(pontoAField, gbc_pontoAField);

        JLabel timeBLabel = new JLabel("Time B");
        timeBLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_timeBLabel = createGbc(0, 4);
        futebolPanel.add(timeBLabel, gbc_timeBLabel);

        timeBField = new JTextField(20);
        timeBField.setBackground(Color.WHITE);
        GridBagConstraints gbc_timeBField = createGbc(1, 4);
        futebolPanel.add(timeBField, gbc_timeBField);

        JLabel pontoBLabel = new JLabel("Pontos Time B");
        pontoBLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_pontoBLabel = createGbc(0, 5);
        futebolPanel.add(pontoBLabel, gbc_pontoBLabel);

        pontoBField = new JTextField(20);
        pontoBField.setBackground(Color.WHITE);
        GridBagConstraints gbc_pontoBField = createGbc(1, 5);
        futebolPanel.add(pontoBField, gbc_pontoBField);

        JButton cadastrarButton = new JButton("Cadastrar Jogo");
        cadastrarButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        cadastrarButton.setBackground(Color.WHITE);
        cadastrarButton.addActionListener(e -> controller.salvaFutebol());
        GridBagConstraints gbc_cadastrarButton = createGbc(1, 6);
        futebolPanel.add(cadastrarButton, gbc_cadastrarButton);

        messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_messageLabel = createGbc(1, 7);
        futebolPanel.add(messageLabel, gbc_messageLabel);

        JButton voltarButton = new JButton(" <- Voltar");
        voltarButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        voltarButton.setBackground(Color.WHITE);
        voltarButton.addActionListener(e -> voltarParaTelaInicial(name, role));
        GridBagConstraints gbc_voltarButton = createGbc(0, 8);
        futebolPanel.add(voltarButton, gbc_voltarButton);

        return futebolPanel;
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
        principal telaInicial = new principal(nome, role);
        telaInicial.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaInicial.setLocationRelativeTo(null);
        telaInicial.setVisible(true);
    }

    public JTextField getNomeField() {
        return nomeField;
    }

    public JTextField getTimeAField() {
        return timeAField;
    }

    public JTextField getPontoAField() {
        return pontoAField;
    }

    public JTextField getTimeBField() {
        return timeBField;
    }

    public JTextField getPontoBField() {
        return pontoBField;
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }
}
