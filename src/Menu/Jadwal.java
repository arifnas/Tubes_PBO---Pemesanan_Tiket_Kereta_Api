package Menu;

import Server.Koneksi;
import java.awt.Dialog;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

public class Jadwal extends Koneksi{
    public Jadwal(){
        try{
            super.createStatement ();
        }catch (Exception e) {
            JOptionPane.showMessageDialog (null, e);
        }
    }
    
    DefaultTableModel modelcek_jadwal = new DefaultTableModel();
    void kosongkanTabel(){
        int row=modelcek_jadwal.getRowCount ();
        for (int i=0;i<row;i++) {
            modelcek_jadwal.removeRow (0);
        }
    }
    
    public void tampildataCek_Jadwal () {
        kosongkanTabel();
        try{
            String sql="SELECT * From jadwal_ka";
            String[] kolom={"waktu","jurusan","kereta","jenis","harga"};
            modelcek_jadwal.setColumnIdentifiers(kolom);
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                String waktu=set.getString ("waktu");
                String jurusan=set.getString ("jurusan");
                String kereta=set.getString("kereta");
                String jenis=set.getString ("jenis");
                String harga=set.getString ("harga");
                
                String[] data ={waktu,jurusan,kereta,jenis,harga}; 
                modelcek_jadwal.addRow (data);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog (null, e);
        }
    }
    
}