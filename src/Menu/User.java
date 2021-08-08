package Menu;

import Server.Koneksi;
import java.sql. PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class User extends Koneksi {
    public User(){
        try{
            super.createStatement ();
        }catch (Exception e){
            JOptionPane.showMessageDialog (null, e);
        }
    }
    
    public void Simpan_Username (String username, String password) {
        try{
            String sql="INSERT INTO user (username, password) VALUES (?, ?)";
            PreparedStatement insert=connection.prepareStatement (sql);
            insert.setString (1, username);
            insert.setString (2, password);
            insert.executeUpdate();
        }catch (Exception e) {
            JOptionPane.showMessageDialog (null, e);
        }
    }
    
    public void Edit_Username (String username, String password) {
        try {
           String sql="UPDATE user SET password=?, WHERE username=?";
            PreparedStatement insert=connection.prepareStatement (sql); 
            insert.setString (1, username);
            insert.setString (2, password);
            insert.executeUpdate ();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog (null, e);
        }
    }
    
    public void Hapus_Username (String username) {
        try{
           String sql="DELETE FROM user WHERE username=?";
            PreparedStatement insert=connection.prepareStatement (sql);
            insert.setString (1, username);
            insert.executeUpdate();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog (null, e);
        }
    }
    
    DefaultTableModel model_username = new DefaultTableModel();
        void kosongkanTabel(){
            int row=model_username.getRowCount ();
            for (int i=0;i<row;i++) {
                model_username.removeRow (0);
            }
        }
        
    public void tampilDataUsername () {
        kosongkanTabel();
        try{
            String sql="SELECT * From user";
            String[] kolom={"username", "password"}; 
            model_username.setColumnIdentifiers(kolom);
            ResultSet set = statement.executeQuery (sql);
            while (set.next()) {
                String username=set.getString ("username"); 
                String password=set.getString("password"); 
                String[] data = {username, password}; 
                model_username.addRow (data);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog (null, e);
        }
    }
}
