package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.cadastroController;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;

public class telaCadastro extends JFrame {

	private final cadastroController controller;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public telaCadastro(String message) {
		controller = new cadastroController(this);
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1129, 709);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(427, 141, 607, 419);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("E-mail");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(247, 177, 43, 13);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(247, 190, 143, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(247, 132, 43, 13);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(247, 145, 143, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(247, 244, 143, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(247, 231, 43, 13);
		panel.add(lblNewLabel_2);
		
		btnNewButton = new JButton("Criar Conta");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        controller.salvaUsuario();
		        
		    }
		});
		btnNewButton.setBounds(261, 309, 117, 21);
		panel.add(btnNewButton);
		
		lblNewLabel_3 = new JLabel("Cadastro");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(276, 56, 136, 45);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(message);
		lblNewLabel_4.setBounds(221, 273, 191, 13);
		panel.add(lblNewLabel_4);
	}
	
	public JTextField getTextFieldEmail() {
		return textField;
	}
	public void setTextFieldEmail(JTextField textField) {
		this.textField = textField;
	}
	public JTextField getTextFieldNome() {
		return textField_1;
	}
	public void setTextFieldNome(JTextField textField_1) {
		this.textField_1 = textField_1;
	}
	public JTextField getTextFieldSenha() {
		return textField_2;
	}
	public void setTextFieldSenha(JTextField textField_2) {
		this.textField_2 = textField_2;
	}
}
