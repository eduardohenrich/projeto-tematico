package view;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao.Conexao;
import dao.UsuarioDAO;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.Color;

public class telaCadastro extends JFrame {

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
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                telaCadastro frame = new telaCadastro();
	                frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	                frame.setVisible(true);
	                frame.setLocationRelativeTo(null);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}
	/**
	 * Create the frame.
	 */
	public telaCadastro() {
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
		        String nome = textField_1.getText();
		        String email = textField.getText();
		        String senha = textField_2.getText();
		        
		        Usuario usuario = new Usuario(nome, email, senha);
		        
				try {
					Connection conexao = new Conexao().getConnection();
					UsuarioDAO user = new UsuarioDAO(conexao);
					user.insert(usuario);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		        
		    }
		});
		btnNewButton.setBounds(261, 309, 117, 21);
		panel.add(btnNewButton);
		
		lblNewLabel_3 = new JLabel("Cadastro");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(276, 56, 136, 45);
		panel.add(lblNewLabel_3);
	}
}
