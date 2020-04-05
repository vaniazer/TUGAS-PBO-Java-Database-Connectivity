package crud;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class LihatDataMahasiswa extends JFrame {
    String[][] data = new String[500][3];
    String[] kolom = {"Nim", "Nama", "Alamat"};
    JTable table;
    JScrollPane scrollPane;

    String DBurl = "jdbc:mysql://localhost/praktikum";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;
    Statement statement;
    ResultSet resultSet;
    static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";

    public LihatDataMahasiswa(){
        setTitle("Data Mahasiswa");
        setSize(400, 150);

        try{
            Class.forName(JDBC_Driver);
            koneksi = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
            statement = koneksi.createStatement();
            String sql = "SELECT * FROM `mahasiswa`";
            resultSet = statement.executeQuery(sql);
            for(int p=0; resultSet.next(); p++){
                data[p][0] = resultSet.getString("NIM");
                data[p][1] = resultSet.getString("Nama");
                data[p][2] = resultSet.getString("Alamat");
            }
            statement.close();
            koneksi.close();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Data Gagal Ditampilkan!", "Hasil",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Driver Tidak Ditemukan!", "Hasil",
                    JOptionPane.ERROR_MESSAGE);
        }
        //ERROR VIEW
        table = new JTable(data, kolom);
        scrollPane = new JScrollPane(table);
        setLayout(new FlowLayout());
        add(scrollPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

    }
    public void showUser(){

    }

    public static void main(String[] args) {
        new LihatDataMahasiswa();
    }
}
