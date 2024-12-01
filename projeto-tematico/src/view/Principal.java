package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.*;
import model.*;
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
        setTitle("OlympicResults");
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
        mainPanel.add(createNatacaoPanel("Natação", nome, role));
        mainPanel.add(createCorridaPanel("Corrida", nome, role));

        contentPane.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel(String nome) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        headerPanel.setBackground(new Color(60, 60, 60));

        JLabel lblTitle = new JLabel("OlympicResults", SwingConstants.LEFT);
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
        // Main game panel with fixed height
        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        gamePanel.setBackground(new Color(230, 230, 230));
        gamePanel.setPreferredSize(new Dimension(800, 500)); // Fixed width and height

        // Title panel with sport name and "+" button
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        titlePanel.setOpaque(false); // Transparent background to match the parent panel

        JLabel lblGame = new JLabel(gameName, SwingConstants.LEFT);
        lblGame.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblGame.setForeground(new Color(50, 50, 50));

        // Add the game name to the title panel
        titlePanel.add(lblGame);
        gamePanel.add(titlePanel, BorderLayout.NORTH);

        if (role != 0) {
            JButton btnAdd = new JButton("+");
            btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
            btnAdd.setBackground(new Color(139, 0, 0));
            btnAdd.setForeground(Color.WHITE);
            btnAdd.setFocusPainted(false);
            btnAdd.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnAdd.addActionListener(e -> fetcher.createPage(userName, role));

            titlePanel.add(btnAdd);
        }

        // Check if the game list is empty
        if (gamesList == null || gamesList.isEmpty()) {
            JLabel lblError = new JLabel("Erro ao carregar " + gameName.toLowerCase(), SwingConstants.CENTER);
            lblError.setFont(new Font("Tahoma", Font.BOLD, 16));
            lblError.setForeground(Color.RED);
            lblError.setOpaque(true);
            lblError.setBackground(new Color(255, 245, 245));
            lblError.setBorder(new EmptyBorder(10, 10, 10, 10));
            gamePanel.add(lblError, BorderLayout.CENTER);
        } else {
            // Container for game details using a grid layout
            JPanel gamesContainer = new JPanel();
            gamesContainer.setLayout(new GridLayout(0, 3, 10, 10)); // 3 cards per row with gaps
            gamesContainer.setBackground(new Color(245, 245, 245));
            gamesContainer.setBorder(new EmptyBorder(10, 10, 10, 10));

            for (Object game : gamesList) {
                JPanel gameCard = renderer.renderGame(game);

                // Style for each card
                gameCard.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
                gameCard.setBackground(new Color(255, 255, 255));
                gameCard.setPreferredSize(new Dimension(200, 150)); // Set fixed size for cards
                gameCard.setMaximumSize(new Dimension(200, 150));

                // Add delete button to each game card
                if (role != 0) {
                    // Delete button
                    JButton deleteButton = new JButton("Excluir");
                    deleteButton.setBackground(new Color(255, 0, 0));
                    deleteButton.setForeground(Color.WHITE);
                    deleteButton.addActionListener(e -> showDeleteConfirmationDialog(userName, role, game, getGameId(game)));
                    gameCard.add(deleteButton, BorderLayout.EAST);

                    // Update button
                    JButton updateButton = new JButton("Atualizar");
                    updateButton.setBackground(new Color(0, 128, 0)); // Green color
                    updateButton.setForeground(Color.WHITE);
                    updateButton.addActionListener(e -> openUpdatePage(game, userName, role, getGameId(game)));
                    gameCard.add(updateButton, BorderLayout.SOUTH);
                }

                gamesContainer.add(gameCard);
            }

            // Scroll pane to hold the game list
            JScrollPane scrollPane = new JScrollPane(gamesContainer);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
            scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            scrollPane.setPreferredSize(new Dimension(600, 400)); // Ensure it fits within the fixed panel height
            gamePanel.add(scrollPane, BorderLayout.CENTER);
        }

        return gamePanel;
    }

    private int getGameId(Object game) {
    if (game instanceof Futebol) {
        return ((Futebol) game).getId(); // Get the ID for Futebol game
    } else if (game instanceof Natacao) {
        return ((Natacao) game).getId(); // Get the ID for Natacao game
    } else if (game instanceof Corrida) {
        return ((Corrida) game).getId(); // Get the ID for Corrida game
    }
    return 0; // Return -1 if game is of unknown type
}

    private void openUpdatePage(Object game, String userName, int role, int id) {
        dispose(); // Close the current page

        if (game instanceof Futebol) {
            Futebol futebol = (Futebol) game;
            futebol.setId(id);
            telaFutebol telaFutebol = new telaFutebol("", userName, role, futebol);
            telaFutebol.setExtendedState(JFrame.MAXIMIZED_BOTH);
            telaFutebol.setLocationRelativeTo(null);
            telaFutebol.setVisible(true);
        } else if (game instanceof Natacao) {
            Natacao natacao = (Natacao) game;
            natacao.setId(id);
            telaNatacao telaNatacao = new telaNatacao("", userName, role, natacao);
            telaNatacao.setExtendedState(JFrame.MAXIMIZED_BOTH);
            telaNatacao.setLocationRelativeTo(null);
            telaNatacao.setVisible(true);
        } else if (game instanceof Corrida) {
            Corrida corrida = (Corrida) game;
            corrida.setId(id);
            telaCorrida telaCorrida = new telaCorrida("", userName, role, corrida);
            telaCorrida.setExtendedState(JFrame.MAXIMIZED_BOTH);
            telaCorrida.setLocationRelativeTo(null);
            telaCorrida.setVisible(true);
        }
    }

    private void showDeleteConfirmationDialog(String userName, int role, Object game, int id) {
        int option = JOptionPane.showConfirmDialog(this, "Você tem certeza que deseja excluir este item?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            deleteGame(userName, role, game, id); // Call delete function after confirmation
        }
    }

    private void deleteGame(String userName, int role, Object game, int id) {
        // Implement your deletion logic here based on the game type
        if (game instanceof Futebol) {
            Futebol futebol = (Futebol) game;
            futebol.setId(id);
            deleteFutebol(userName, role, futebol);
        } else if (game instanceof Natacao) {
            Natacao natacao = (Natacao) game;
            natacao.setId(id);
            deleteNatacao(userName, role, natacao);
        } else if (game instanceof Corrida) {
            Corrida corrida = (Corrida) game;
            corrida.setId(id);
            deleteCorrida(userName, role, corrida);
        }
    }

    private void deleteFutebol(String userName, int role, Futebol futebol) {
        try (Connection conexao = new Conexao().getConnection()) {
            FutebolDAO futebolDAO = new FutebolDAO(conexao);
            futebolDAO.deleteFutebol(futebol.getId()); // Delete logic for Futebol
            JOptionPane.showMessageDialog(this, "Futebol excluído com sucesso!");
            refreshGamePanels(userName, role); // Refresh the game panels after deletion
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao excluir Futebol!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteNatacao(String userName, int role, Natacao natacao) {
        try (Connection conexao = new Conexao().getConnection()) {
            NatacaoDAO natacaoDAO = new NatacaoDAO(conexao);
            natacaoDAO.deleteNatacao(natacao.getId()); // Delete logic for Natacao
            JOptionPane.showMessageDialog(this, "Natação excluída com sucesso!");
            refreshGamePanels(userName, role); // Refresh the game panels after deletion
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao excluir Natação!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCorrida(String userName, int role, Corrida corrida) {
        try (Connection conexao = new Conexao().getConnection()) {
            CorridaDAO corridaDAO = new CorridaDAO(conexao);
            corridaDAO.deleteCorrida(corrida.getId()); // Delete logic for Corrida
            JOptionPane.showMessageDialog(this, "Corrida excluída com sucesso!");
            refreshGamePanels(userName, role); // Refresh the game panels after deletion
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao excluir Corrida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshGamePanels(String userName, int role) {

        // Show the page again
        Principal principal = new Principal(userName, role);
        principal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        principal.setLocationRelativeTo(null);
        principal.setVisible(true);

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
        telaFutebol telaFutebol = new telaFutebol("", name, role, null);
        telaFutebol.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaFutebol.setLocationRelativeTo(null);
        telaFutebol.setVisible(true);
    }

    private void CreateNatacaoPage(String name, int role) {
        dispose();
        telaNatacao telaNatacao = new telaNatacao("", name, role, null);
        telaNatacao.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaNatacao.setLocationRelativeTo(null);
        telaNatacao.setVisible(true);
    }

    private void CreateCorridaPage(String name, int role) {
        dispose();
        telaCorrida telaCorrida = new telaCorrida("", name, role, null);
        telaCorrida.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaCorrida.setLocationRelativeTo(null);
        telaCorrida.setVisible(true);
    }
}
