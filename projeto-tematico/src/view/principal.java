package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.Conexao;
import dao.CorridaDAO;
import dao.FutebolDAO;
import dao.NatacaoDAO;
import model.Corrida;
import model.Futebol;
import model.Natacao;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public principal(String nome, int role) {
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

		mainPanel.add(createFutebolPanel("Futebol", role));
		mainPanel.add(createNatacaoPanel("Natação", role));
		mainPanel.add(createCorridaPanel("Corrida 100m", role));

		contentPane.add(mainPanel, BorderLayout.CENTER);
	}

	private JPanel createCorridaPanel(String gameName, int role) {
		JPanel gamePanel = new JPanel(new BorderLayout());
		gamePanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		gamePanel.setBackground(new Color(192, 192, 192));

		JLabel lblGame = new JLabel(gameName, SwingConstants.LEFT);
		lblGame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		gamePanel.add(lblGame, BorderLayout.WEST);

		Connection conexao;
		Corrida[] corridas = null;
		try {
			conexao = new Conexao().getConnection();
			CorridaDAO corridaDAO = new CorridaDAO(conexao);
			corridas = corridaDAO.consultaCorridas();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (corridas == null) {
			JLabel lblError = new JLabel("Erro ao carregar corridas", SwingConstants.RIGHT);
			lblError.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblError.setForeground(Color.RED);
			gamePanel.add(lblError, BorderLayout.EAST);
		} else {
			for (Corrida corrida : corridas) {
				JPanel corridaPanel = new JPanel(new BorderLayout());
				corridaPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
				corridaPanel.setBackground(new Color(192, 192, 192));

				JLabel lblCorrida = new JLabel(corrida.getNome(), SwingConstants.LEFT);
				lblCorrida.setFont(new Font("Tahoma", Font.PLAIN, 18));
				corridaPanel.add(lblCorrida, BorderLayout.WEST);

				if (role != 0) {
					JButton btnBet = new JButton("Criar jogo");
					btnBet.setFont(new Font("Tahoma", Font.BOLD, 13));
					btnBet.setBackground(new Color(100, 100, 100));
					btnBet.setForeground(Color.WHITE);
					corridaPanel.add(btnBet, BorderLayout.EAST);
				}

				gamePanel.add(corridaPanel);
			}
		}

		if (role != 0) {
			JButton btnBet = new JButton("Criar jogo");
			btnBet.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnBet.setBackground(new Color(100, 100, 100));
			btnBet.setForeground(Color.WHITE);
			gamePanel.add(btnBet, BorderLayout.EAST);
		}

		return gamePanel;
	}

	private JPanel createFutebolPanel(String gameName, int role) {
		JPanel gamePanel = new JPanel(new BorderLayout());
		gamePanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		gamePanel.setBackground(new Color(192, 192, 192));

		JLabel lblGame = new JLabel(gameName, SwingConstants.LEFT);
		lblGame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		gamePanel.add(lblGame, BorderLayout.WEST);

		Connection conexao;
		Futebol[] futebols = null;
		try {
			conexao = new Conexao().getConnection();
			FutebolDAO futebolDAO = new FutebolDAO(conexao);
			futebols = futebolDAO.consultaFutebols();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (futebols == null) {
			JLabel lblError = new JLabel("Erro ao carregar jogos de futebol", SwingConstants.RIGHT);
			lblError.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblError.setForeground(Color.RED);
			gamePanel.add(lblError, BorderLayout.EAST);
		} else {
			for (Futebol futebol : futebols) {
				JPanel futebolPanel = new JPanel(new BorderLayout());
				futebolPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
				futebolPanel.setBackground(new Color(192, 192, 192));

				JLabel lblFutebol = new JLabel(futebol.getNome(), SwingConstants.LEFT);
				lblFutebol.setFont(new Font("Tahoma", Font.PLAIN, 18));
				futebolPanel.add(lblFutebol, BorderLayout.WEST);

				if (role != 0) {
					JButton btnBet = new JButton("Criar jogo");
					btnBet.setFont(new Font("Tahoma", Font.BOLD, 13));
					btnBet.setBackground(new Color(100, 100, 100));
					btnBet.setForeground(Color.WHITE);
					futebolPanel.add(btnBet, BorderLayout.EAST);
				}

				gamePanel.add(futebolPanel);
			}
		}

		if (role != 0) {
			JButton btnBet = new JButton("Criar resultado");
			btnBet.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnBet.setBackground(new Color(100, 100, 100));
			btnBet.setForeground(Color.WHITE);
			gamePanel.add(btnBet, BorderLayout.EAST);
		}

		return gamePanel;
	}

	private JPanel createNatacaoPanel(String gameName, int role) {
		JPanel gamePanel = new JPanel(new BorderLayout());
		gamePanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		gamePanel.setBackground(new Color(192, 192, 192));

		JLabel lblGame = new JLabel(gameName, SwingConstants.LEFT);
		lblGame.setFont(new Font("Tahoma", Font.PLAIN, 18));
		gamePanel.add(lblGame, BorderLayout.WEST);

		Natacao[] natacaos = null;
		try {
			Connection conexao = new Conexao().getConnection();
			NatacaoDAO natacaoDAO = new NatacaoDAO(conexao);
			natacaos = natacaoDAO.consultaNatacaos();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (natacaos == null) {
			JLabel lblError = new JLabel("Erro ao carregar jogos de natação", SwingConstants.RIGHT);
			lblError.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblError.setForeground(Color.RED);
			gamePanel.add(lblError, BorderLayout.EAST);
		} else {
			for (Natacao natacao : natacaos) {
				JPanel natacaoPanel = new JPanel(new BorderLayout());
				natacaoPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
				natacaoPanel.setBackground(new Color(192, 192, 192));

				JLabel lblNatacao = new JLabel(natacao.getNome(), SwingConstants.LEFT);
				lblNatacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
				natacaoPanel.add(lblNatacao, BorderLayout.WEST);

				if (role != 0) {
					JButton btnBet = new JButton("Criar resultado");
					btnBet.setFont(new Font("Tahoma", Font.BOLD, 13));
					btnBet.setBackground(new Color(100, 100, 100));
					btnBet.setForeground(Color.WHITE);
					natacaoPanel.add(btnBet, BorderLayout.EAST);
				}

				gamePanel.add(natacaoPanel);
			}
		}

		if (role != 0) {
			JButton btnBet = new JButton("Criar resultado");
			btnBet.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnBet.setBackground(new Color(100, 100, 100));
			btnBet.setForeground(Color.WHITE);
			gamePanel.add(btnBet, BorderLayout.EAST);
		}

		return gamePanel;
	}
}
