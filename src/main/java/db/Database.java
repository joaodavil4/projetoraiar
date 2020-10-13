package db;

import javax.swing.*;
import java.sql.*;

public class Database {
    public Connection con;
    public Statement stm;
    public String url;

    public void connect(){
        try
        {
            /* Tenta se conectar ao Driver */
//            Class.forName("ojdbc10.jar");

            url = "jdbc:ucanaccess://C:\\Users\\jpdav\\IdeaProjects\\projetoraiar\\src\\main\\java\\db\\Raiar2.accdb";

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
