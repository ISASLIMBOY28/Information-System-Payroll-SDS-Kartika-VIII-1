/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.*;

/**
 *
 * @author ThanatosXYZ
 */
public class koneksi {
private Connection koneksi;
    public Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection Established");
        }
        catch(ClassNotFoundException ex){
            System.out.println("Connection Failed "+ex);
        }
        String url = "jdbc:mysql://localhost/sdskartikaviii1";
        try{
            koneksi = DriverManager.getConnection(url,"root","");
            System.out.println("Database Connection Established");
        }
        catch(SQLException ex){
            System.out.println("Database Connection failed "+ex);
        }
        return koneksi;
    }    
}
