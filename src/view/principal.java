package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class principal extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public principal(String nome) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1065, 659);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 10, 1500, 96);
		panel.setBackground(new Color(128, 128, 128));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("OlympicBET");
		lblNewLabel.setBounds(24, 41, 120, 25);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 29, 168, 40);
		panel.add(panel_5);
		panel_5.setBackground(new Color(128, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Olá, " + nome);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(1300, 45, 140, 25);
		panel.add(lblNewLabel_2);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setBounds(0, 146, 120, 541);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnApostas = new JButton("Suas Apostas");
		btnApostas.setForeground(new Color(0, 0, 0));
		btnApostas.setVerticalAlignment(SwingConstants.TOP);
		btnApostas.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnApostas.setBackground(Color.WHITE);
		btnApostas.setBounds(0, 54, 115, 25);
		panel_1.add(btnApostas);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 128, 128));
		panel_2.setBounds(159, 146, 1538, 542);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3_4 = new JPanel();
		panel_3_4.setBounds(0, 53, 1500, 74);
		panel_2.add(panel_3_4);
		panel_3_4.setBackground(new Color(192, 192, 192));
		panel_3_4.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("      Futebol");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(324, 10, 155, 54);
		panel_3_4.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Apostar");
		btnNewButton.setBounds(1000, 26, 110, 28);
		panel_3_4.add(btnNewButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 154, 1500, 74);
		panel_2.add(panel_3);
		panel_3.setBackground(new Color(192, 192, 192));
		panel_3.setLayout(null);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(0, 250, 1500, 74);
		panel_2.add(panel_3_1);
		panel_3_1.setBackground(new Color(192, 192, 192));
		panel_3_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("      Natação");
		lblNewLabel_4.setBounds(324, 10, 155, 54);
		panel_3_1.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnNewButton_1 = new JButton("Apostar");
		btnNewButton_1.setBounds(1000, 26, 110, 28);
		panel_3_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBounds(0, 348, 1500, 74);
		panel_2.add(panel_3_2);
		panel_3_2.setBackground(new Color(192, 192, 192));
		
		JPanel panel_3_3 = new JPanel();
		panel_3_3.setBounds(0, 447, 1500, 74);
		panel_2.add(panel_3_3);
		panel_3_3.setBackground(new Color(192, 192, 192));
		panel_3_3.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("      Corrida 100m");
		lblNewLabel_5.setBounds(324, 10, 156, 54);
		panel_3_3.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnNewButton_2 = new JButton("Apostar");
		btnNewButton_2.setBounds(1000, 10, 110, 28);
		panel_3_3.add(btnNewButton_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(128, 0, 0));
		panel_4.setBounds(0, 10, 1408, 23);
		panel_2.add(panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("Jogos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(lblNewLabel_1);
		
	}
}
