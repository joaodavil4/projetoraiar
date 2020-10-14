package db;

import controller.OptionsController;

import javax.swing.*;
import java.io.File;
import java.sql.*;

public class Database {
    private static Database instance;

    public Connection con;
    public Statement stm;
    public String url = "jdbc:ucanaccess://home/jpdavila/StudioProjects/ProjetoRAIAR/src/main/java/db/Raiar.accdb";

    public static Database getInstance(){
        if (instance == null){
            instance = new Database();
        }
        return instance;
    }

    public void connect(){
        try

        {
            /* Tenta se conectar ao Driver */
//            Class.forName("ojdbc10.jar");

            /* nomedobanco é o nome que você deu anteriormente ao seu alias */
            con = DriverManager.getConnection(url);
            stm = con.createStatement();
//            JOptionPane.showMessageDialog(null, "ok");
            query();
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Problema ao conectar!");
            System.exit(0);
        }
    }

    public void disconect(){
        try {
            con.close();
        }
        catch (SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, "Problema ao desconectar!");
            System.exit(0);
        }
    }



    public int performInsert(String columns, String values, String table) throws SQLException {
        String sqlInsert = "INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ")";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pst = conn.prepareStatement(sqlInsert);
        int toReturn = 0;
        try {
            toReturn = pst.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            pst.close();
            conn.close();
            return toReturn;
        }
        pst.close();
        conn.close();
        return toReturn;
    }


    public int performInsertAll(String values, String table) throws SQLException {
        String sqlInsert = "INSERT INTO " + table + " VALUES (" + values + ")";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pst = conn.prepareStatement(sqlInsert);
        int toReturn = 0;
        try {
            toReturn = pst.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            pst.close();
            conn.close();
            return toReturn;
        }
        pst.close();
        conn.close();
        return toReturn;
    }



    public void query(){
        try
        {
            ResultSet rs = stm.executeQuery("SELECT * FROM CONSULTOR");
            while (rs.next())
            {
                int id = rs.getInt("ID");
                String login = rs.getString("LOGIN");
                String senha = rs.getString("SENHA");
                String nome = rs.getString("NOME");

                JOptionPane.showMessageDialog(null, "Nome: " + rs.getString("nome") + " - Idade: " + rs.getInt("idade"));
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"" + e.getMessage(),"Erro",0);
        }
        finally
        {

        }
    }
}
