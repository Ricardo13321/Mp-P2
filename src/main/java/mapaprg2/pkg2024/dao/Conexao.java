package mapaprg2.pkg2024.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ricg_
 */
public class Conexao {
    private static Connection connection;
    private static final String user = "root";
    private static final String password = "483TH#48hg43gh*HgwW??th";
    private static final String url = "jdbc:mysql://localhost:3306/ead";
    
    
    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Erro ao tentar se conectar", JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }
}
