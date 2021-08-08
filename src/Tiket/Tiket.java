package Tiket;

import Server.Koneksi;
import java.awt.Dialog;
import javax.swing.JOptionPane;
import java.sql. PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Tiket extends Koneksi{
    public Tiket(){
        try{
            super.createStatement ();
        }catch (Exception e) {
            JOptionPane.showMessageDialog (null, e);
        }
    }
    
    public void Simpan_Tiket (String no_kursi, String nama_penumpang, String jumlah_beli, String uang_bayar, String total_bayar, String uang_kembali){
        try{
            String sql="INSERT INTO tiket_ka (no_kursi,nama_penumpang,jumlah_beli,uang_bayar,total_bayar,uang_kembali) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement insert=connection.prepareStatement (sql);
            insert.setString(1, no_kursi);
            insert.setString(2, nama_penumpang);
            insert.setString(3, jumlah_beli);
            insert.setString(4, uang_bayar);
            insert.setString(5, total_bayar);
            insert.setString(6, uang_kembali);
            insert.executeUpdate ();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog (null, e);
        }
    }
    
    public void Edit_Tiket (String no_kursi, String nama_penumpang, String jumlah_beli, String uang_bayar, String total_bayar, String uang_kembali){
        try{
            String sql="INSERT INTO tiket_ka (no_kursi,nama_penumpang,jumlah_beli,uang_bayar,total_bayar,uang_kembali) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement insert=connection.prepareStatement (sql);
            insert.setString(1, no_kursi);
            insert.setString(2, nama_penumpang);
            insert.setString(3, jumlah_beli);
            insert.setString(4, uang_bayar);
            insert.setString(5, total_bayar);
            insert.setString(6, uang_kembali);
            insert.executeUpdate ();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog (null, e); 
        }
    }
    
    public void Hapus_Tiket (String no_kursi) {
        try{
            String sql="DELETE FROM tiket_ka WHERE no_kursi=?";
            PreparedStatement insert=connection.prepareStatement(sql);
            insert.setString (1, no_kursi);
            insert.executeUpdate ();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog (null, e);
        }
    }
    
    DefaultTableModel modeltiket_ka = new DefaultTableModel();
    void kosongkanTabel(){
        int row=modeltiket_ka.getRowCount ();
        for (int i=0;i<row;i++) {
            modeltiket_ka.removeRow (0);
        }
    }
    
    public void tampilDatatiket_ka () {
        kosongkanTabel();
        try{
            String sql="SELECT * From tiket_ka";
            String[] kolom={"No Kursi","Nama Penumpang","Jumlah Beli","Uang Bayar","Total Bayar","Uang Kembali"};
            modeltiket_ka.setColumnIdentifiers(kolom);
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                String no_kursi=set.getString("no_kursi");
                String nama_penumpang=set.getString("nama_penumpang");
                String jumlah_beli=set.getString ("jumlah_beli");
                String uang_bayar=set.getString ("uang_bayar");
                String total_bayar=set.getString("total_bayar");
                String uang_kembali=set.getString ("uang_kembali");
                String[] data ={no_kursi,nama_penumpang,jumlah_beli,uang_bayar,total_bayar,uang_kembali}; 
                modeltiket_ka.addRow (data);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog (null, e);
        }
    }
    
    public void laporanTiket (){
        try{
            HashMap parameter = new HashMap ();
            String report = "./src/Laporan/report_tiket.jasper";
            
            JasperPrint JPrint= JasperFillManager.fillReport(report,parameter,statement.getConnection());
            
            JasperViewer jasperViewer=new JasperViewer(JPrint, false);
            JDialog laporan = new JDialog();
            laporan.setContentPane(jasperViewer.getContentPane());
            laporan.setSize (jasperViewer.getSize());
            laporan.setTitle ("Laporan");
            laporan.setAlwaysOnTop (true);
            laporan.setModalityType (Dialog.ModalityType.MODELESS);
            laporan.setModal (true);
            laporan.setVisible (true);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog (null, e);
        }
    }
}
