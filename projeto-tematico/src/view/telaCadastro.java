package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.cadastroController;
import java.awt.*;

public class telaCadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    private final cadastroController controller;
    private JPanel contentPane;
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JLabel messageLabel;

    public telaCadastro(String message) {
        controller = new cadastroController(this);
        initializeFrame(message);
    }

    private void initializeFrame(String message) {
        setTitle("Cadastro - OlympicResults");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1121, 694);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        JPanel cadastroPanel = createCadastroPanel(message);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(cadastroPanel, gbc);
    }

    private JPanel createCadastroPanel(String message) {
        JPanel cadastroPanel = new JPanel();
        cadastroPanel.setBackground(new Color(192, 192, 192));
        cadastroPanel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Cadastro");
        titleLabel.setFont(new Font("Sylfaen", Font.BOLD, 18));
        GridBagConstraints gbc_titleLabel = createGbc(0, 0, 2);
        cadastroPanel.add(titleLabel, gbc_titleLabel);

        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_nomeLabel = createGbc(0, 1);
        cadastroPanel.add(nomeLabel, gbc_nomeLabel);

        nomeField = new JTextField(20);
        nomeField.setBackground(Color.WHITE);
        GridBagConstraints gbc_nomeField = createGbc(1, 1);
        cadastroPanel.add(nomeField, gbc_nomeField);

        JLabel emailLabel = new JLabel("E-mail");
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_emailLabel = createGbc(0, 2);
        cadastroPanel.add(emailLabel, gbc_emailLabel);

        emailField = new JTextField(20);
        emailField.setBackground(Color.WHITE);
        GridBagConstraints gbc_emailField = createGbc(1, 2);
        cadastroPanel.add(emailField, gbc_emailField);

        JLabel senhaLabel = new JLabel("Senha");
        senhaLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_senhaLabel = createGbc(0, 3);
        cadastroPanel.add(senhaLabel, gbc_senhaLabel);

        senhaField = new JPasswordField(20);
        senhaField.setBackground(Color.WHITE);
        GridBagConstraints gbc_senhaField = createGbc(1, 3);
        cadastroPanel.add(senhaField, gbc_senhaField);

        JButton criarContaButton = new JButton("Criar Conta");
        criarContaButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        criarContaButton.setBackground(Color.WHITE);
        criarContaButton.addActionListener(e -> controller.salvaUsuario());
        GridBagConstraints gbc_criarContaButton = createGbc(1, 4);
        cadastroPanel.add(criarContaButton, gbc_criarContaButton);

        messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_messageLabel = createGbc(1, 5);
        cadastroPanel.add(messageLabel, gbc_messageLabel);

        JButton voltarButton = new JButton(" <- Voltar");
        voltarButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        voltarButton.setBackground(Color.WHITE);
        voltarButton.addActionListener(e -> voltarParaLogin());
        GridBagConstraints gbc_voltarButton = createGbc(0, 6);
        cadastroPanel.add(voltarButton, gbc_voltarButton);

        return cadastroPanel;
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

    private void voltarParaLogin() {
        dispose();
        Login login = new Login("");
        login.setExtendedState(JFrame.MAXIMIZED_BOTH);
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }

    public JTextField getNomeField() {
        return nomeField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getSenhaField() {
        return senhaField;
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }
}
