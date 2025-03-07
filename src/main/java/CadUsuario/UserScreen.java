package CadUsuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserScreen extends JFrame {

    public UserScreen() {
        super("Cadastro de usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        Components();
    }
    private void Components() {

    }
    public static void main(String[] args) {
        UserScreen userScreen = new UserScreen();
        userScreen.setVisible(true);
    }
}