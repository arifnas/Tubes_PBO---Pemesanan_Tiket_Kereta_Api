package Menu;

import Server.Koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Login extends Koneksi{
    public Login() {
    try{
        super.createStatement ();
    } catch (SQLException e) {
        Logger.getLogger (Login.class.getName()).log (Level.SEVERE, null, e);
    }
  } 
    public boolean CekLogin (String username, String password) {
        boolean hasil=false;
       try{
           String sql="SELECT  * FROM user WHERE username=? AND password=?";
           PreparedStatement select=connection.prepareStatement(sql);
           select.setString (1, username);
           select.setString (2, password);
           ResultSet set = select.executeQuery();
           if (set.next()) {
               hasil=true;
       }
       }catch (Exception e) {
           JOptionPane.showMessageDialog (null, e);
       }
       return hasil;
    }  
}
