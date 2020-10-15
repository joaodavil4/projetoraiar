package db;

import controller.OptionsController;

import javax.swing.*;
import java.io.File;
import java.sql.*;

public class Database {
    private static Database instance;

    public Connection con;
    public Statement stm;
    public String url = "jdbc:ucanaccess://src/main/java/db/Raiar8.accdb";

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
            Class.forName("org.hsqldb.jdbcDriver");

            /* nomedobanco é o nome que você deu anteriormente ao seu alias */
            con = DriverManager.getConnection(url);
            stm = con.createStatement();
//            JOptionPane.showMessageDialog(null, "ok");
//            query();
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Problema ao conectar!");
            System.exit(0);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

    public int performSelect(String table) throws SQLException {
        String sql = "SELECT * FROM " + table;

        ResultSet toReturn;
        try {
            toReturn = stm.executeQuery(sql);
        }
        catch(Exception e){
            e.printStackTrace();
            stm.close();
            con.close();
        }
        stm.close();
        con.close();
        return 0;

    }

    public int performInsert(String columns, String values, String table) throws SQLException {
        String sqlInsert = "INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ")";

        int toReturn = 0;
        try {
            toReturn = stm.executeUpdate(sqlInsert);
        }
        catch(Exception e){
            e.printStackTrace();
            stm.close();
            con.close();
            return toReturn;
        }
        stm.close();
        con.close();
        return toReturn;
    }


    public int performInsertAll(String table, String values) throws SQLException {
        String sqlInsert = "INSERT INTO " + table + " VALUES (" + values + ")";
        int toReturn = 0;
        try {
            toReturn = stm.executeUpdate(sqlInsert);
        }
        catch(Exception e){
            e.printStackTrace();
            stm.close();
            con.close();
            return toReturn;
        }
        stm.close();
        con.close();
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
