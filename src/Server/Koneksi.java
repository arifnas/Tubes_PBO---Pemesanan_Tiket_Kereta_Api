package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
    public java.sql.Statement statement;
    public Connection connection;
    public Koneksi () {
        String driver="com.mysql.jdbc.Driver";
        String dbhost="localhost";
        String dbport="3306";
        String dbdatabase="db_kereta";
        String user="root";
        String password="";
        String url="";
        try{
            Class.forName (driver);
            url="jdbc:mysql://"+dbhost+":"+dbport+"/"+dbdatabase;
            connection=(Connection) DriverManager.getConnection (url, user, password);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog (null, ex);
        }
    }
    public void createStatement () throws SQLException {
        statement = connection.createStatement ();
    }
}
