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
import java.util.ArrayList;
import java.util.List;

public class Principal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public interface GameFetcher {
        void createPage(String userName, int role);
    }
    

    public Principal(String nome, int role) {
        setTitle("OlympicBET");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1065, 659);
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.DARK_GRAY);
        setContentPane(contentPane);

        // Header Panel
        JPanel headerPanel = createHeaderPanel(nome);
        contentPane.add(headerPanel, BorderLayout.NORTH);

        // Sidebar Panel
        JPanel sidebarPanel = createSidebarPanel();
        contentPane.add(sidebarPanel, BorderLayout.WEST);

        // Main Content Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.LIGHT_GRAY);

        mainPanel.add(createFutebolPanel("Futebol", nome, role));
        mainPanel.add(createNatacaoPanel("Natação", nome,role));
        mainPanel.add(createCorridaPanel("Corrida", nome,role));

        contentPane.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel(String nome) {
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

        return headerPanel;
    }

    private JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(70, 70, 70));
        sidebarPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        return sidebarPanel;
    }

    private JPanel createGamePanel(String gameName, String userName, int role, List<?> gamesList, GameFetcher fetcher, GameRenderer renderer) {
        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        gamePanel.setBackground(new Color(200, 200, 200));

        JLabel lblGame = new JLabel(gameName, SwingConstants.LEFT);
        lblGame.setFont(new Font("Tahoma", Font.PLAIN, 18));
        gamePanel.add(lblGame, BorderLayout.WEST);

        if (gamesList == null || gamesList.isEmpty()) {
            JLabel lblError = new JLabel("Erro ao carregar " + gameName.toLowerCase(), SwingConstants.CENTER);
            lblError.setFont(new Font("Tahoma", Font.BOLD, 16));
            lblError.setForeground(Color.RED);
            lblError.setOpaque(true);
            lblError.setBackground(Color.WHITE);
            lblError.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            gamePanel.add(lblError, BorderLayout.CENTER);
        } else {
            JPanel gamesContainer = new JPanel();
            gamesContainer.setLayout(new BoxLayout(gamesContainer, BoxLayout.Y_AXIS));

            for (Object game : gamesList) {
                JPanel gameDetailPanel = renderer.renderGame(game);
                gamesContainer.add(gameDetailPanel);
            }

            JScrollPane scrollPane = new JScrollPane(gamesContainer);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension(600, 400));
            gamePanel.add(scrollPane, BorderLayout.CENTER);
        }

        if (role != 0) {
            JButton btnBet = new JButton("Criar resultado");
            btnBet.setFont(new Font("Tahoma", Font.BOLD, 13));
            btnBet.setBackground(new Color(100, 100, 100));
            btnBet.setForeground(Color.WHITE);
            btnBet.addActionListener(e -> fetcher.createPage(userName, role));
            gamePanel.add(btnBet, BorderLayout.EAST);
        }

        return gamePanel;
    }

    private JPanel createFutebolPanel(String gameName, String userName, int role) {
        List<Futebol> futebols = new ArrayList<>();
        try (Connection conexao = new Conexao().getConnection()) {
            FutebolDAO futebolDAO = new FutebolDAO(conexao);
            futebols = futebolDAO.consultaFutebols();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return createGamePanel(gameName, userName, role, futebols, this::CreateFutebolPage, new FutebolRenderer());
    }

    private JPanel createNatacaoPanel(String gameName, String userName, int role) {
        List<Natacao> natacaos = new ArrayList<>();
        try (Connection conexao = new Conexao().getConnection()) {
            NatacaoDAO natacaoDAO = new NatacaoDAO(conexao);
            natacaos = natacaoDAO.consultaNatacaos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return createGamePanel(gameName, userName, role, natacaos, this::CreateNatacaoPage, new NatacaoRenderer());
    }

    private JPanel createCorridaPanel(String gameName, String userName, int role) {
        List<Corrida> corridas = new ArrayList<>();
        try (Connection conexao = new Conexao().getConnection()) {
            CorridaDAO corridaDAO = new CorridaDAO(conexao);
            corridas = corridaDAO.consultaCorridas();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return createGamePanel(gameName, userName, role, corridas, this::CreateCorridaPage, new CorridaRenderer());
    }

    // Renderer classes for each game type
    class FutebolRenderer implements GameRenderer {
        public JPanel renderGame(Object game) {
            Futebol futebol = (Futebol) game;
            JPanel futebolPanel = new JPanel(new BorderLayout());
            futebolPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            futebolPanel.setBackground(new Color(245, 245, 245));

            JLabel lblFutebol = new JLabel(futebol.getNome(), SwingConstants.LEFT);
            lblFutebol.setFont(new Font("Tahoma", Font.PLAIN, 18));
            futebolPanel.add(lblFutebol, BorderLayout.WEST);

            JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            scorePanel.setBackground(new Color(245, 245, 245));
            scorePanel.add(new JLabel(futebol.getTimeA() + " (" + futebol.getPontoA() + " pontos)"));
            scorePanel.add(new JLabel("vs"));
            scorePanel.add(new JLabel(futebol.getTimeB() + " (" + futebol.getPontoB() + " pontos)"));
            futebolPanel.add(scorePanel, BorderLayout.SOUTH);

            return futebolPanel;
        }
    }

    class NatacaoRenderer implements GameRenderer {
        public JPanel renderGame(Object game) {
            Natacao natacao = (Natacao) game;
            JPanel natacaoPanel = new JPanel(new BorderLayout());
            natacaoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            natacaoPanel.setBackground(new Color(245, 245, 245));

            JLabel lblNatacao = new JLabel(natacao.getNome(), SwingConstants.LEFT);
            lblNatacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
            natacaoPanel.add(lblNatacao, BorderLayout.WEST);

            JPanel positionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            positionPanel.setBackground(new Color(245, 245, 245));
            positionPanel.add(new JLabel("1º: " + natacao.getPrimeiro()));
            positionPanel.add(new JLabel("2º: " + natacao.getSegundo()));
            positionPanel.add(new JLabel("3º: " + natacao.getTerceiro()));
            natacaoPanel.add(positionPanel, BorderLayout.SOUTH);

            return natacaoPanel;
        }
    }

    class CorridaRenderer implements GameRenderer {
        public JPanel renderGame(Object game) {
            Corrida corrida = (Corrida) game;
            JPanel corridaPanel = new JPanel(new BorderLayout());
            corridaPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            corridaPanel.setBackground(new Color(245, 245, 245));

            JLabel lblCorrida = new JLabel(corrida.getNome(), SwingConstants.LEFT);
            lblCorrida.setFont(new Font("Tahoma", Font.PLAIN, 18));
            corridaPanel.add(lblCorrida, BorderLayout.WEST);

            JPanel placarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            placarPanel.setBackground(new Color(245, 245, 245));
            placarPanel.add(new JLabel("1º: " + corrida.getPrimeiro()));
            placarPanel.add(new JLabel("2º: " + corrida.getSegundo()));
            placarPanel.add(new JLabel("3º: " + corrida.getTerceiro()));
            corridaPanel.add(placarPanel, BorderLayout.SOUTH);

            return corridaPanel;
        }
    }

    interface GameRenderer {
        JPanel renderGame(Object game);
    }

    private void CreateFutebolPage(String name, int role) {
        dispose();
        telaFutebol telaFutebol = new telaFutebol("", name, role);
        telaFutebol.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaFutebol.setLocationRelativeTo(null);
        telaFutebol.setVisible(true);
    }

    private void CreateNatacaoPage(String name, int role) {
        dispose();
        telaNatacao telaNatacao = new telaNatacao("", name, role);
        telaNatacao.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaNatacao.setLocationRelativeTo(null);
        telaNatacao.setVisible(true);
    }

    private void CreateCorridaPage(String name, int role) {
        dispose();
        telaCorrida telaCorrida = new telaCorrida("", name, role);
        telaCorrida.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaCorrida.setLocationRelativeTo(null);
        telaCorrida.setVisible(true);
    }
}
