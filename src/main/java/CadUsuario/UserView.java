package CadUsuario;

import Functions.DataBaseConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class UserView extends JFrame {

    public DefaultTableModel modelo;
    public JTable tabela;
    public JLabel lblLogo;
    public JTextField txtNome, txtEmail;
    public JPasswordField pswSenha, pswSenhaconfirm;

    public UserView() {
        //Definindo configurações da Janela
        super("Cadastro de usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        Components();
        carregarUsuarios();

    }
    private void Components() {
        //Criando um Jpanel para ser o cabecalho
        JPanel painelCabecalho = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelCabecalho.setBackground(new Color(59, 89, 182));
        painelCabecalho.setPreferredSize(new Dimension(800, 70));
        add(painelCabecalho, BorderLayout.NORTH); // Adiciona o painel ao topo do JFrame

        //logo criação
        lblLogo = new JLabel(redimensionarImagem("src/main/resources/logotipo.png", 65, 65));
        JLabel lblTitulo = new JLabel("VistaTech ERP");

        //titulo
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        //adicionando coisas ao cabecalho
        painelCabecalho.add(lblLogo);
        painelCabecalho.add(lblTitulo);

        //painel cinza que fica a esquerda
        JPanel painelItens = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelItens.setBackground(new Color(230, 230, 230));
        painelItens.setPreferredSize(new Dimension(330, 5));
        add(painelItens, BorderLayout.WEST);

        //div aonde preenche os dados
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelItens.add(painelFormulario, BorderLayout.NORTH);
        painelFormulario.setBackground(new Color(230, 230, 230));
        painelFormulario.setPreferredSize(new Dimension(320, 280));
        painelFormulario.setBorder(
                new CompoundBorder(
                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(Color.gray, 0, false),
                                "Dados do usuário",
                                TitledBorder.DEFAULT_JUSTIFICATION,
                                TitledBorder.ABOVE_TOP
                        ),
                        BorderFactory.createBevelBorder(BevelBorder.LOWERED)
                )
        );
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Ajusta as posições do formulário
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelFormulario.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        txtNome = new JTextField(15);
        painelFormulario.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        painelFormulario.add(new JLabel("Senha:"), gbc);
        gbc.gridx = 1;
        pswSenha = new JPasswordField(15);
        painelFormulario.add(pswSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        painelFormulario.add(new JLabel("Confirme a senha:"), gbc);
        gbc.gridx = 1;
        pswSenhaconfirm = new JPasswordField(15);
        painelFormulario.add(pswSenhaconfirm, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        painelFormulario.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(15);
        painelFormulario.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        painelFormulario.add(new JLabel("Admin:"), gbc);
        gbc.gridx = 1;
        JCheckBox cbValidadmin = new JCheckBox();
        cbValidadmin.setBackground(new Color(230, 230, 230));
        painelFormulario.add(cbValidadmin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        painelFormulario.add(new JLabel("Vendedor:"), gbc);
        gbc.gridx = 1;
        JCheckBox cbValidvendedor = new JCheckBox();
        cbValidvendedor.setBackground(new Color(230, 230, 230));
        painelFormulario.add(cbValidvendedor, gbc);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBotoes.setBackground(new Color(230, 230, 230));
        painelBotoes.setPreferredSize(new Dimension(320, 100));
        painelItens.add(painelBotoes, BorderLayout.SOUTH);

        //botão de adicionar
        gbc.gridx=0;
        gbc.gridy=0;
        JButton btnAdicionar = new JButton("Adicionar");
        styleButton(btnAdicionar);
        painelBotoes.add(btnAdicionar);

        //butão de alterar
        gbc.gridx=1;
        gbc.gridy=0;
        JButton btnAlterar = new JButton("Alterar");
        styleButton(btnAlterar);
        painelBotoes.add(btnAlterar);

        //botão de remover
        gbc.gridx=0;
        gbc.gridy=1;
        JButton btnRemover = new JButton("Remover");
        styleButton(btnRemover);
        painelBotoes.add(btnRemover);

        //botão de Limpar
        gbc.gridx=1;
        gbc.gridy=1;
        JButton btnLimpar = new JButton("Limpar");
        styleButton(btnLimpar);
        painelBotoes.add(btnLimpar);

        //painel de visualização dos usuários
        JPanel painelTabela = new JPanel(new BorderLayout());
        //add(painelTabela, BorderLayout.EAST);


        //tabela
        modelo = new ModeloTabela();
        tabela = new JTable(modelo);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.LEFT); // Centraliza o texto

        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centralizado);
        }

        //ajustando tamanbho das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(5); //id
        tabela.getColumnModel().getColumn(1).setPreferredWidth(20);//nome
        //tabela.getColumnModel().getColumn(2).setPreferredWidth(30);//email
        tabela.getColumnModel().getColumn(3).setPreferredWidth(5);//vendedor
        tabela.getColumnModel().getColumn(4).setPreferredWidth(5);//admin

        tabela.setRowSorter(new TableRowSorter<>(modelo));  // Permite ordenação correta
        modelo.fireTableDataChanged();

        tabela.setFont(new Font("Arial", Font.PLAIN, 12));
        tabela.setRowHeight(20);

        JScrollPane listagemProdutos = new JScrollPane(tabela);
        listagemProdutos.setBorder(BorderFactory.createTitledBorder("Lista de Produtos"));

        listagemProdutos.setBackground(new Color(230, 230, 230));
        listagemProdutos.setPreferredSize(new Dimension(450, 280));
        listagemProdutos.setBorder(
                new CompoundBorder(
                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(Color.gray, 0, false),
                                "Dados do usuário",
                                TitledBorder.DEFAULT_JUSTIFICATION,
                                TitledBorder.ABOVE_TOP
                        ),
                        BorderFactory.createBevelBorder(BevelBorder.LOWERED)
                )
        );

        add(listagemProdutos, BorderLayout.EAST);
    }


    private ImageIcon redimensionarImagem(String caminho, int largura, int altura) {
        ImageIcon icon = new ImageIcon(caminho);
        Image imagem = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(imagem);
    }

    //função responsavel por estilizar os botões
    private void styleButton(JButton button) {
        button.setBackground(new Color(59, 89, 182));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setPreferredSize(new Dimension(150, 40));

        // Efeitos de hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.WHITE);
                button.setForeground(new Color(59, 89, 182));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(59, 89, 182));
                button.setForeground(Color.WHITE);
            }
        });
    }
    class ModeloTabela extends DefaultTableModel {
        public ModeloTabela() {
            super(new String[]{"Id", "Nome", "Email", "Admin", "Vendedor"}, 0);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0: return Integer.class;  // ID
                case 1: return String.class;   // Nome
                case 2: return String.class;   // Email
                case 3: return String.class;   // Admin
                case 4: return String.class;  // Vendedor
                default: return Object.class;
            }
        }
    }

    private void carregarUsuarios() {
        try (Connection conn = DataBaseConnection.getConnection()) {
            String sql = "SELECT * FROM users";
            try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("username");
                    String email = rs.getString("email");
                    int admin = rs.getInt("admin");
                    int vendedor = rs.getInt("vendedor");

                    String vlrAdmin,vlrVendedor;

                    if (admin == 1) {
                        vlrAdmin = "Sim";
                    }else{
                        vlrAdmin = "Não";
                    }
                    if(vendedor == 1){
                        vlrVendedor = "Sim";
                    }else{
                        vlrVendedor = "Não";
                    }


                    modelo.addRow(new Object[]{id, nome, email, vlrAdmin, vlrVendedor});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuários", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        UserView userScreen = new UserView();
        userScreen.setVisible(true);
    }
}