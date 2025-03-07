package MainScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

public class MainScreen extends JFrame {

    // Tamanho fixo padrão do sistema
    private static final Dimension FIXED_DIMENSION = new Dimension(900, 600);

    public MainScreen(boolean isAdmin) {
        setTitle("Sistema ERP - Tela Inicial");
        setIconImage(new ImageIcon("src/main/resources/logotipo.png").getImage());

        // Inicializa a janela como maximizada
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(true);

        // Listener para monitorar mudanças no estado da janela
        addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if (!isMaximized() && !isMinimized()) {
                    setResizable(true);
                    setSize(FIXED_DIMENSION);
                } else if (isMaximized()) {
                    setResizable(true);
                }
            }
        });

        // Listener adicional para redimensionamento (usar tamanho fixo quando restaurada)
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Quando não está maximizada nem minimizada, mantém o tamanho fixo
                if (!isMaximized() && !isMinimized()) {
                    setSize(FIXED_DIMENSION);
                }
            }
        });

        setLayout(new BorderLayout());

        JPanel hotbar = new JPanel();
        hotbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        hotbar.setBackground(new Color(59, 89, 182));
        hotbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botões do menu principal
        String[] modules = {"Cadastro", "Pré Venda", "Estoque Produtos", "Financeiro", "Notas Eletrônicas", "Relatórios"};
        for (String module : modules) {
            JButton button = new JButton(module);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setBackground(Color.WHITE);
            button.setForeground(new Color(59, 89, 182));
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.DARK_GRAY, 1),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));

            // Efeito de hover para os botões
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    button.setBackground(new Color(230, 230, 230)); // Cor ao passar o mouse
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    button.setBackground(Color.WHITE); // Volta ao branco
                }
            });

            hotbar.add(button);
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Redimensiona a imagem da logomarca
        ImageIcon originalLogo = new ImageIcon("src/main/resources/logotipo.png");
        Image scaledImage = originalLogo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledLogo = new ImageIcon(scaledImage);

        JLabel logoLabel = new JLabel(scaledLogo);

        // Adiciona o texto ao lado da logomarca
        JLabel textLabel = new JLabel("<html><b>VISTATECH ERP</b><br><i>Sistema de Gestão Oftalmológica</i></html>");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        textLabel.setForeground(new Color(59, 89, 182));

        logoPanel.add(logoLabel);
        logoPanel.add(textLabel);
        mainPanel.add(logoPanel);
        add(hotbar, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Verifica se a janela está maximizada
    private boolean isMaximized() {
        return (getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH;
    }

    // Verifica se a janela está minimizada
    private boolean isMinimized() {
        return (getExtendedState() & JFrame.ICONIFIED) == JFrame.ICONIFIED;
    }
}