package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.cadastroController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class telaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private final cadastroController controller;
	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField emailField;
	private JTextField senhaField;
	private JLabel messageLabel;

	public telaCadastro(String message) {
		controller = new cadastroController(this);
		initializeFrame();
		initializeComponents(message);
	}

	private void initializeFrame() {
		setTitle("Cadastro - OlympicBET");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1129, 709);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
	}

	private void initializeComponents(String message) {
		JPanel panel = createPanel();
		contentPane.add(panel);

		JLabel titleLabel = createLabel("Cadastro", 276, 56, 14);
		panel.add(titleLabel);

		JLabel nomeLabel = createLabel("Nome", 247, 132, 11);
		panel.add(nomeLabel);

		nomeField = createTextField(247, 145, 143, 19);
		panel.add(nomeField);

		JLabel emailLabel = createLabel("E-mail", 247, 177, 11);
		panel.add(emailLabel);

		emailField = createTextField(247, 190, 143, 19);
		panel.add(emailField);

		JLabel senhaLabel = createLabel("Senha", 247, 231, 11);
		panel.add(senhaLabel);

		senhaField = createTextField(247, 244, 143, 19);
		panel.add(senhaField);

		JButton criarContaButton = createButton("Criar Conta", 261, 309, 117, 21, e -> controller.salvaUsuario());
		panel.add(criarContaButton);

		messageLabel = createLabel(message, 221, 273, 11);
		panel.add(messageLabel);

		JButton voltarButton = createButton(" <- Voltar", 10, 10, 140, 25, e -> voltarParaLogin());
		panel.add(voltarButton);
	}

	private JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(427, 141, 607, 419);
		panel.setLayout(null);
		return panel;
	}

	private JLabel createLabel(String text, int x, int y, int fontSize) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Tahoma", Font.BOLD, fontSize));
		label.setBounds(x, y, 200, 20);
		return label;
	}

	private JTextField createTextField(int x, int y, int width, int height) {
		JTextField textField = new JTextField();
		textField.setBounds(x, y, width, height);
		textField.setBackground(new Color(192, 192, 192));
		return textField;
	}

	private JButton createButton(String text, int x, int y, int width, int height, ActionListener action) {
		JButton button = new JButton(text);
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(x, y, width, height);
		button.setBackground(Color.WHITE);
		button.addActionListener(action);
		return button;
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

	public JTextField getSenhaField() {
		return senhaField;
	}

	public void setNomeField(JTextField nomeField) {
		this.nomeField = nomeField;
	}

	public void setEmailField(JTextField emailField) {
		this.emailField = emailField;
	}

	public void setSenhaField(JTextField senhaField) {
		this.senhaField = senhaField;
	}

	public JLabel getMessageLabel() {
		return messageLabel;
	}
}
