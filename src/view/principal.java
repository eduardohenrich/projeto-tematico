package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;



public class principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal frame = new principal();
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
	public principal() {
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
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setBounds(0, 146, 120, 541);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnApostas = new JButton("Apostas");
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 154, 1500, 74);
		panel_2.add(panel_3);
		panel_3.setBackground(new Color(192, 192, 192));
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(0, 250, 1500, 74);
		panel_2.add(panel_3_1);
		panel_3_1.setBackground(new Color(192, 192, 192));
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBounds(0, 348, 1500, 74);
		panel_2.add(panel_3_2);
		panel_3_2.setBackground(new Color(192, 192, 192));
		
		JPanel panel_3_3 = new JPanel();
		panel_3_3.setBounds(0, 447, 1500, 74);
		panel_2.add(panel_3_3);
		panel_3_3.setBackground(new Color(192, 192, 192));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(128, 0, 0));
		panel_4.setBounds(0, 10, 1408, 23);
		panel_2.add(panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("Jogos");
		panel_4.add(lblNewLabel_1);
	}
}
