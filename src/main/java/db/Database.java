package db;

import com.sun.org.apache.xpath.internal.objects.XString;
import controller.OptionsController;
import model.*;

import javax.print.DocFlavor;
import javax.swing.*;
import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Database {
    private static Database instance;

    public Connection con;
    public Statement stm;
    public String url = "jdbc:ucanaccess://src/main/java/db/Raiar (2).accdb";

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
//            Class.forName("org.hsqldb.jdbcDriver");

            con = DriverManager.getConnection(url);
            stm = con.createStatement();

//            JOptionPane.showMessageDialog(null, "ok");
           //query();

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
        return 0;

    }

    public int performInsert(String columns, String values, String table) throws SQLException {
        con = DriverManager.getConnection(url);
        stm = con.createStatement();
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
         return toReturn;
    }


    public int performInsertAll(String table, String values) throws SQLException {

        String sqlInsert = "INSERT INTO " + table + " (LOGIN, CARGO, SENHA, SETOR, NOME ) "+" VALUES (" + values + ")";
        System.out.println(sqlInsert);
        int toReturn = 0;
        con = DriverManager.getConnection(url);
        stm = con.createStatement();
        try
        {
            toReturn = stm.executeUpdate(sqlInsert);
        }
        catch(Exception e){
            e.printStackTrace();
            stm.close();
            con.close();
            return toReturn;
        }

        return toReturn;
    }

    public boolean queryForLogin(String paramLogin, String paramPassword) throws SQLException {
        con = DriverManager.getConnection(url);
        stm = con.createStatement();
        String sql = "SELECT * FROM CONSULTOR WHERE LOGIN ='" + paramLogin + "'" + " AND SENHA = '" + paramPassword + "'";
        try {
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) return true;
            stm.close();
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public Consultant getConsultant(String id) throws SQLException {
        con = DriverManager.getConnection(url);
        stm = con.createStatement();
        String sql = "SELECT * FROM CONSULTOR WHERE ID = " + id;
        Consultant consultant = null;
        try {
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                consultant = new Consultant(rs.getString("Login"), rs.getString("Senha"),rs.getString("Nome"),rs.getString("Cargo"), rs.getString("Setor"));
            }
        } catch (SQLException e){
            e.printStackTrace();
            return consultant;
        }
        return consultant;
    }

    public Entrepreneur getEntrepreneur(String id) throws SQLException {
        con = DriverManager.getConnection(url);
        stm = con.createStatement();
        String sql = "SELECT Login,Cargo,Senha,Nome,Telefone,Email,IdEmpresa,Setor FROM EMPREENDEDOR WHERE ID = " + id;
        Entrepreneur empreendedor = null;
        try {
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                empreendedor = new Entrepreneur(rs.getString("Login"),rs.getString("Cargo"), rs.getString("Senha"),rs.getString("Nome"),rs.getString("Telefone"), rs.getString("Email"),rs.getString("IdEmpresa"),rs.getString("Setor"));
                empreendedor.setRole(rs.getString("Cargo"));
                empreendedor.setName(rs.getString("Nome"));
                empreendedor.setPassword(rs.getString("Senha"));
            }
        } catch (SQLException e){
            e.printStackTrace();
            return empreendedor;
        }
        return empreendedor;
    }

    public int UpdateConsultant(Consultant consultant, String id) throws SQLException {
        con = DriverManager.getConnection(url);
        stm = con.createStatement();
        int Retorno = -1;
        String sql = "UPDATE CONSULTOR SET LOGIN = '" + consultant.getLogin() + " ', SENHA = '"+ consultant.getPassword()+"' , CARGO = '"+consultant.getRole()+"' , SETOR = '"+ consultant.getSector()+ "' , NOME = '"+ consultant.getName()+"' WHERE ID = " + id;
        try {
            Retorno = stm.executeUpdate(sql);
            stm.close();
            con.close();
            return Retorno;
        } catch (SQLException e){
            e.printStackTrace();
            return Retorno;
        }
    }

    public int UpdateEntrepreneur(Entrepreneur empreendedor, String id) throws SQLException {
        con = DriverManager.getConnection(url);
        stm = con.createStatement();
        int Retorno = -1;
        String sql = "UPDATE EMPREENDEDOR SET EMAIL = '"+empreendedor.getEmail()+"' , TELEFONE = '"+empreendedor.getPhone()+"' , CARGO = '"+empreendedor.getRole()+"' , SETOR = '"+ empreendedor.getSector()+ "' , NOME = '"+ empreendedor.getName()+"' WHERE ID = " + id;
        try {
            Retorno = stm.executeUpdate(sql);
            stm.close();
            con.close();
            return Retorno;
        } catch (SQLException e){
            e.printStackTrace();
            return Retorno;
        }
    }

    public String SelectWhereEnterprise(String NomeTabela , String[] CampoWhere, String[] ValorWhere ) throws SQLException {
        con = DriverManager.getConnection(url);
        stm = con.createStatement();
        try
        {
            String query = "SELECT * FROM " + NomeTabela + " WHERE ";
            for(int i = 0; i<CampoWhere.length;i++)
            {
                query = query + CampoWhere[i] + " = " + ValorWhere[i];
                if(CampoWhere.length > 1 && i < CampoWhere.length-1)
                {
                    query = query + " AND ";
                }
            }
            ResultSet rs = stm.executeQuery(query);
            if(rs.next()){
                do{
                    return "Sucesso," + rs.getInt("ID");
                }while(rs.next());
            }else{
                return "Falha, registro Não encontrada por favor digite novamente";
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Erro,Erro na pesquisa: " + e.getMessage();
        }

    }
    public ArrayList<String> SelectConsultant() throws SQLException {
        con = DriverManager.getConnection(url);
        stm = con.createStatement();
        ArrayList<String> consultant = new ArrayList<String>();
        try
        {
            ResultSet rs = stm.executeQuery("SELECT * FROM Consultor");
            while (rs.next())
            {
                int id = rs.getInt("ID");
                //String login = rs.getString("LOGIN");
                // String senha = rs.getString("SENHA");
                String nome = rs.getString("NOME");
                consultant.add(""+ id + "," + nome);
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"" + e.getMessage(),"Erro",0);
        }
        return consultant;
    }

    public ArrayList<String> SelectEntrepreneur() throws SQLException {
        con = DriverManager.getConnection(url);
        stm = con.createStatement();
        ArrayList<String> empreendedor = new ArrayList<String>();
        try
        {
            ResultSet rs = stm.executeQuery("SELECT * FROM EMPREENDEDOR");
            while (rs.next())
            {
                int id = rs.getInt("ID");
                //String login = rs.getString("LOGIN");
                // String senha = rs.getString("SENHA");
                String nome = rs.getString("NOME");
                empreendedor.add(""+ id + "," + nome);
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"" + e.getMessage(),"Erro",0);
        }
        return empreendedor;
    }

    public ArrayList<String> SelectEnterprise() throws SQLException {
        ArrayList<String> enterprise = new ArrayList<String>();
        try
        {
            ResultSet rs = stm.executeQuery("SELECT * FROM EMPRESA");
            while (rs.next())
            {
                int id = rs.getInt("ID");
                //String login = rs.getString("LOGIN");
                // String senha = rs.getString("SENHA");
                String nome = rs.getString("NOME");
                enterprise.add(""+ id + "," + nome);
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"" + e.getMessage(),"Erro",0);
        }
        return enterprise;
    }

    public Enterprise selectEnterprise(String empresa) throws SQLException {

        try
        {
            String query = "SELECT * FROM EMPRESA WHERE ID = '" + empresa + "'";
            ResultSet rs = stm.executeQuery(query);

            if (rs.next()) {
                String name = rs.getString("Nome");
                String phone = rs.getString("Telefone");
                String email = rs.getString("Email");
                String site = rs.getString("Site");
                String lifetime = rs.getString("AnoFundacao");
                String registrationDate = rs.getString("DataCadastro");
                String programa = rs.getString("IDPrograma");
                String advisor = rs.getString("IDAdvisor");



                return new Enterprise(name, phone, email, site, lifetime, registrationDate,programa,advisor);
            } else{
                return null;
            }

        }
        catch (SQLException  e)
        {
            JOptionPane.showMessageDialog(null,"" + e.getMessage(),"Erro",0);
            return null;
        }

    }

    public ArrayList<String> SelectPerguntas() throws SQLException {
        ArrayList<String> perguntas = new ArrayList<String>();
        try
        {
            ResultSet rs = stm.executeQuery("SELECT * FROM PERGUNTA");
            while (rs.next())
            {
                int id = rs.getInt("ID");
                String pergunta = rs.getString("PERGUNTA");
                perguntas.add(""+ id + ";" + pergunta);
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"" + e.getMessage(),"Erro",0);
        }
        return perguntas;
    }
    public ArrayList<Diagnosis> SelectDiagnosis(String empresa) throws SQLException {
        ArrayList<Diagnosis> diagnosticos = new ArrayList<Diagnosis>();
        try
        {
            String query = "SELECT EIXO.NOME ,PERGUNTA.PERGUNTA ,AVALIACAO.SCORE FROM DIAGNOSTICO JOIN AVALIACAO ON DIAGNOSTICO.ID= AVALIACAO.IDDIAGNOSTICO JOIN PERGUNTA ON AVALIACAO.IDPERGUNTA= PERGUNTA.ID JOIN EIXO ON PERGUNTA.IDEIXO= EIXO.ID WHERE DIAGNOSTICO.IDEMPRESA = " + empresa + " AND DIAGNOSTICO.DATACRIACAO = (SELECT MAX(DATACRIACAO) FROM DIAGNOSTICO WHERE DIAGNOSTICO.IDEMPRESA = " + empresa + ") ORDER BY EIXO.NOME";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next())
            {
                String pergunta = rs.getString("pergunta");
                int score = rs.getInt("Score");
                String eixo = rs.getString("NOME");
                diagnosticos.add(new Diagnosis(score,eixo,pergunta));
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"" + e.getMessage(),"Erro",0);
        }
        return diagnosticos;
    }
   public int performInsertDiagnostico(ArrayList<Evaluation> avaliacoes , String empresa ) throws SQLException {

       String sqlInsert = "INSERT INTO DIAGNOSTICO (IDEMPRESA , DATACRIACAO ) "+" VALUES ( " + empresa + " , CURRENT_TIMESTAMP  " +")";
       int toReturn = 0;
       con = DriverManager.getConnection(url);
       con.setAutoCommit(false);
       PreparedStatement ps = con.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
       stm = con.createStatement();
       try
       {
           ps.execute();
           ResultSet rs = ps.getGeneratedKeys();
           int iddiagnostico = -1;
           while (rs.next()) {
                iddiagnostico = rs.getInt(1);
           }

           for (int i = 0; i<avaliacoes.size(); i++)
           {
               sqlInsert = "INSERT INTO  avaliacao (idpergunta,iddiagnostico, score ) "+" VALUES ( " +avaliacoes.get(i).idPergunta+ " , "+ iddiagnostico + " , " + avaliacoes.get(i).score+" )";
               toReturn = stm.executeUpdate(sqlInsert);
           }
        con.commit();
       }
       catch(Exception e){
           e.printStackTrace();
           stm.close();
           con.close();
           return toReturn;
       }

       return toReturn;
    }

    public void query(){
        try
        {
            ResultSet rs = stm.executeQuery("SELECT * FROM CONSULTOR");
            //ResultSet rs = stm.executeQuery("SELECT MAX(DATACRIACAO) as data FROM DIAGNOSTICO WHERE DIAGNOSTICO.IDEMPRESA = 1");
            JOptionPane.showMessageDialog(null, "Empresa" );
            while (rs.next())
            {
                //int id = rs.getInt("ID");
                //String login = rs.getString("LOGIN");
               // String senha = rs.getString("SENHA");
                //String nome = rs.getString("NOME");
                JOptionPane.showMessageDialog(null, "NOME: " + rs.getString("NOME") + " SENHA: " + rs.getString("SENHA") +" LOGIN: " + rs.getString("LOGIN"));

            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,"" + e.getMessage(),"Erro",0);
        }

    }
}
