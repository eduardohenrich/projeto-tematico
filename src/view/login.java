package view;

import java.awt.EventQueue;

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
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;


public class login extends JFrame {

	private final loginController controler;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login frame = new login();
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public login(String message) {
    	controler = new loginController(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1121, 694);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(192, 192, 192));
        panel.setBounds(436, 113, 611, 449);
        contentPane.add(panel);
        panel.setLayout(null);

        textField = new JTextField();
        textField.setBackground(new Color(192, 192, 192));
        textField.setBounds(202, 106, 225, 25);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Email");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblNewLabel.setBounds(201, 92, 75, 19);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Senha");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblNewLabel_1.setBounds(202, 168, 43, 13);
        panel.add(lblNewLabel_1);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(192, 192, 192));
        passwordField.setBounds(202, 181, 225, 25);
        panel.add(passwordField);

        JButton btnNewButton = new JButton("Entrar");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					controler.autenticar();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
         
                
            }
        });
        btnNewButton.setBounds(259, 265, 115, 25);
        panel.add(btnNewButton);

        JLabel lblNewLabel_2 = new JLabel("OlympicBET");
        lblNewLabel_2.setFont(new Font("Sylfaen", Font.BOLD, 18));
        lblNewLabel_2.setBounds(268, 34, 128, 25);
        panel.add(lblNewLabel_2);

        JButton btnNewButton_1 = new JButton("Criar conta");
        btnNewButton_1.setForeground(new Color(0, 0, 0));
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1.setBounds(248, 331, 140, 25);
        panel.add(btnNewButton_1);
        
        JLabel lblNewLabel_3 = new JLabel(message);
        lblNewLabel_3.setBounds(202, 227, 225, 13);
        panel.add(lblNewLabel_3);
        
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                telaCadastro telaCadastro = new telaCadastro("");
                telaCadastro.setExtendedState(JFrame.MAXIMIZED_BOTH);
                telaCadastro.setLocationRelativeTo(telaCadastro);
                telaCadastro.setVisible(true);
            }
        });
    }
	
	
	public login() {
    	controler = new loginController(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1121, 694);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(192, 192, 192));
        panel.setBounds(436, 113, 611, 449);
        contentPane.add(panel);
        panel.setLayout(null);

        textField = new JTextField();
        textField.setBackground(new Color(192, 192, 192));
        textField.setBounds(202, 106, 225, 25);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Email");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblNewLabel.setBounds(201, 92, 75, 19);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Senha");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblNewLabel_1.setBounds(202, 168, 43, 13);
        panel.add(lblNewLabel_1);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(192, 192, 192));
        passwordField.setBounds(202, 181, 225, 25);
        panel.add(passwordField);

        JButton btnNewButton = new JButton("Entrar");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					controler.autenticar();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
         
                
            }
        });
        btnNewButton.setBounds(259, 265, 115, 25);
        panel.add(btnNewButton);

        JLabel lblNewLabel_2 = new JLabel("OlympicBET");
        lblNewLabel_2.setFont(new Font("Sylfaen", Font.BOLD, 18));
        lblNewLabel_2.setBounds(268, 34, 128, 25);
        panel.add(lblNewLabel_2);

        JButton btnNewButton_1 = new JButton("Criar conta");
        btnNewButton_1.setForeground(new Color(0, 0, 0));
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1.setBounds(248, 331, 140, 25);
        panel.add(btnNewButton_1);
        
        JLabel lblNewLabel_3 = new JLabel();
        lblNewLabel_3.setBounds(202, 227, 225, 13);
        panel.add(lblNewLabel_3);
        
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                telaCadastro telaCadastro = new telaCadastro("");
                telaCadastro.setExtendedState(JFrame.MAXIMIZED_BOTH);
                telaCadastro.setLocationRelativeTo(telaCadastro);
                telaCadastro.setVisible(true);
            }
        });
    }
	public JTextField getTextUsuario() {
		return textField;
	}

	public void setTextUsuario(JTextField textField) {
		this.textField = textField;
	}

	public JPasswordField getTextSenha() {
		return passwordField;
	}

	public void setTextSenha(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}
}