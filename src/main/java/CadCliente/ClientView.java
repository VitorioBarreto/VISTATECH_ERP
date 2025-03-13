package CadCliente;

import Functions.DataBaseConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import Functions.MachineSettings;

public class ClientView extends JFrame {

    public ClientView(){
        super("Cadastro de clientes");
        MachineSettings machine = new MachineSettings();
        int altura = machine.getResolutiony(0.69);
        int largura = machine.getResolutionx(0.71);
        setSize(largura, altura);
        //setSize(1366,768);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("logotipo.png")).getImage());
        componentsFormulario();

        JPanel painelCabecalho = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelCabecalho.setBackground(new Color(59, 89, 182));
        painelCabecalho.setPreferredSize(new Dimension(800, 70));
        add(painelCabecalho, BorderLayout.NORTH); // Adiciona o painel ao topo do JFrame

        JLabel lblLogo = new JLabel(redimensionarImagem("src/main/resources/whitelogo.png", 60, 60));
        JLabel lblTitulo = new JLabel("VistaTech ERP");

        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        painelCabecalho.add(lblLogo);
        painelCabecalho.add(lblTitulo);
    }

    public static void main(String[] args) {
        ClientView clientScreen = new ClientView();
        clientScreen.setVisible(true);
    }

    public ImageIcon redimensionarImagem(String caminho, int largura, int altura) {
        ImageIcon icon = new ImageIcon(caminho);
        Image imagem = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(imagem);
    }

    private void componentsFormulario(){
        JPanel painelEsquerda = new JPanel();
        painelEsquerda.setLayout(new FlowLayout(FlowLayout.LEFT));
        painelEsquerda.setBackground(new Color(230, 230, 230));
        painelEsquerda.setPreferredSize(new Dimension(600, 500));
        add(painelEsquerda,BorderLayout.WEST);

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(new Color(230, 230, 230));
        formulario.setPreferredSize(new Dimension(590, 500));
        formulario.setBorder(new CompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.gray, 0, false),
                        "Dados do cliente",
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.ABOVE_TOP),
                BorderFactory.createBevelBorder(BevelBorder.LOWERED)
        ));
        painelEsquerda.add(formulario);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // LINHA UM DO FORMULARIO
        gbc.gridx=0;gbc.gridy=0;
        JPanel lineOne = new JPanel(new GridBagLayout());
        lineOne.setPreferredSize(new Dimension(550, 30));
        lineOne.setBackground(new Color(103, 103, 103));
        formulario.add(lineOne,gbc);
        //ID

            gbc.gridx=0;gbc.gridy=0;
            lineOne.add(new JLabel("ID:"),gbc);
            JTextField txtID = new JTextField(2);
            txtID.setEditable(false);
            gbc.gridx=1;
            lineOne.add(txtID,gbc);

            //NOME
            gbc.gridx=2;
            lineOne.add(new JLabel("NOME:"),gbc);
            JTextField txtNome = new JTextField(30);
            gbc.gridx=3;
            lineOne.add(txtNome,gbc);


    }
}
