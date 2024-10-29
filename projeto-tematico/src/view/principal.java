package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public principal(String nome) {
		setTitle("OlympicBET");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1065, 659);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.DARK_GRAY);
		setContentPane(contentPane);

		// Header Panel
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		headerPanel.setBackground(new Color(60, 60, 60));
		JLabel lblTitle = new JLabel("OlympicBET", SwingConstants.LEFT);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setForeground(Color.WHITE);
		headerPanel.add(lblTitle, BorderLayout.WEST);

		JLabel lblWelcome = new JLabel("Olá, " + nome, SwingConstants.RIGHT);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblWelcome.setForeground(Color.WHITE);
		headerPanel.add(lblWelcome, BorderLayout.EAST);

		contentPane.add(headerPanel, BorderLayout.NORTH);

		// Sidebar Panel
		JPanel sidebarPanel = new JPanel();
		sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
		sidebarPanel.setBackground(new Color(70, 70, 70));
		sidebarPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JButton btnApostas = new JButton("Suas Apostas");
		btnApostas.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnApostas.setForeground(Color.WHITE);
		btnApostas.setBackground(new Color(100, 100, 100));
		btnApostas.setAlignmentX(Component.CENTER_ALIGNMENT);
		sidebarPanel.add(btnApostas);
		sidebarPanel.add(Box.createVerticalStrut(15));

		contentPane.add(sidebarPanel, BorderLayout.WEST);

		// Main Content Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.LIGHT_GRAY);

		mainPanel.add(createGamePanel("Futebol"));
		mainPanel.add(createGamePanel("Natação"));
		mainPanel.add(createGamePanel("Corrida 100m"));

		contentPane.add(mainPanel, BorderLayout.CENTER);
	}

	private JPanel createGamePanel(String gameName) {
		JPanel gamePanel = new JPanel(new BorderLayout());
		gamePanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		gamePanel.setBackground(new Color(192, 192, 192));

		JLabel lblGame = new JLabel(gameName, SwingConstants.LEFT);
		lblGame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		gamePanel.add(lblGame, BorderLayout.WEST);

		JButton btnBet = new JButton("Apostar");
		btnBet.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBet.setBackground(new Color(100, 100, 100));
		btnBet.setForeground(Color.WHITE);
		gamePanel.add(btnBet, BorderLayout.EAST);

		return gamePanel;
	}
}
