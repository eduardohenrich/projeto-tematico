package view;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.loginController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;

public class Login extends JFrame {
    private static final long serialVersionUID = 1L;
    private final loginController controller;
    private JPanel contentPane;
    private JTextField textFieldEmail;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login("");
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login(String message) {
        controller = new loginController(this);
        initializeFrame(message);
    }

    private void initializeFrame(String message) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1121, 694);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        JPanel loginPanel = createLoginPanel(message);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(loginPanel, gbc);
    }

    private JPanel createLoginPanel(String message) {
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(192, 192, 192));
        loginPanel.setLayout(new GridBagLayout());

        JLabel lblTitle = new JLabel("OlympicBET");
        lblTitle.setFont(new Font("Sylfaen", Font.BOLD, 18));
        GridBagConstraints gbc_lblTitle = createGbc(0, 0, 2);
        loginPanel.add(lblTitle, gbc_lblTitle);

        textFieldEmail = new JTextField(20);
        textFieldEmail.setBackground(new Color(255, 255, 255));
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_lblEmail = createGbc(0, 1);
        loginPanel.add(lblEmail, gbc_lblEmail);
        GridBagConstraints gbc_textEmail = createGbc(1, 1);
        loginPanel.add(textFieldEmail, gbc_textEmail);

        passwordField = new JPasswordField(20);
        passwordField.setBackground(new Color(255, 255, 255));
        JLabel lblPassword = new JLabel("Senha");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_lblPassword = createGbc(0, 2);
        loginPanel.add(lblPassword, gbc_lblPassword);
        GridBagConstraints gbc_textPassword = createGbc(1, 2);
        loginPanel.add(passwordField, gbc_textPassword);

        JButton btnLogin = new JButton("Entrar");
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLogin.setBackground(Color.WHITE);
        btnLogin.addActionListener(e -> login());
        GridBagConstraints gbc_btnLogin = createGbc(1, 3);
        loginPanel.add(btnLogin, gbc_btnLogin);

        JLabel lblMessage = new JLabel(message);
        lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GridBagConstraints gbc_lblMessage = createGbc(1, 4);
        loginPanel.add(lblMessage, gbc_lblMessage);

        JButton btnCreateAccount = new JButton("Criar Conta");
        btnCreateAccount.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCreateAccount.setBackground(Color.WHITE);
        btnCreateAccount.addActionListener(e -> openCadastroScreen());
        GridBagConstraints gbc_btnCreateAccount = createGbc(1, 5);
        loginPanel.add(btnCreateAccount, gbc_btnCreateAccount);

        return loginPanel;
    }

    private void login() {
        try {
            controller.autenticar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openCadastroScreen() {
        dispose();
        telaCadastro telaCadastro = new telaCadastro("");
        telaCadastro.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaCadastro.setLocationRelativeTo(telaCadastro);
        telaCadastro.setVisible(true);
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

    public JTextField getTextUsuario() {
        return textFieldEmail;
    }

    public void setTextUsuario(JTextField textField) {
        this.textFieldEmail = textField;
    }

    public JPasswordField getTextSenha() {
        return passwordField;
    }

    public void setTextSenha(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }
}
