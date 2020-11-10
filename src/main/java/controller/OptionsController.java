package controller;

import db.Database;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class OptionsController {
    private static OptionsController instance;

    public static OptionsController getInstance(){
        if (instance == null){
            instance = new OptionsController();
        }
        return instance;
    }

    public void newConsultant(){
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o seu login...");
        String login = in.nextLine();
        System.out.println("Digite a sua senha...");
        String password = in.nextLine();
        System.out.println("Digite o seu nome...");
        String name = in.nextLine();
        System.out.println("Digite a sua função...");
        String role = in.nextLine();
        System.out.println("Digite o seu setor...");
        String sector = in.nextLine();
        Consultant consultant = new Consultant(login, password, name, role, sector);

        try {
            Database.getInstance()
                    .performInsertAll(consultant.getTABLE_NAME(),consultant.toString()
                    );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateConsultant() throws SQLException {
        ArrayList<String> consultants = Database.getInstance().SelectConsultant();
        System.out.println("-- Consultores --");
        for(int i = 0; i < consultants.size(); i++)
        {
            System.out.println(consultants.get(i).split(",")[0] + " - " + consultants.get(i).split(",")[1]);
        }
        System.out.println("-- --  -- --");
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o numero do Registro que deseja alterar...");
        String idConsultants= in.next();
        Consultant consultantoriginal =  Database.getInstance().getConsultant(idConsultants);
        Consultant consultantalterado = new Consultant(consultantoriginal.getLogin(), consultantoriginal.getPassword(), consultantoriginal.getName(), consultantoriginal.getRole(), consultantoriginal.getSector());
        int opcao = 0;
        do{
            System.out.println("-- Dados do Consultor --");
            System.out.println("[1]Nome: " + consultantalterado.getName() + "  [2]Setor: " + consultantalterado.getSector() + " [3]Cargo:" + consultantalterado.getRole());
            System.out.println("-- -- -- --");
            System.out.println("Digite o número do campo que deseja alterar ou 0 para sair: " );
            opcao = Integer.parseInt(in.next());
            switch (opcao){
                case 1:
                    System.out.println("Digite o novo valor:");
                    in.nextLine();
                    consultantalterado.setName(in.nextLine());
                    break;
                case 2:
                    System.out.println("Digite o novo valor:");
                    in.nextLine();
                    consultantalterado.setSector(in.nextLine());
                    break;
                case 3:
                    System.out.println("Digite o novo valor:");
                    in.nextLine();
                    consultantalterado.setRole(in.nextLine());
                    break;
                default:
                    break;
            }
        }while(opcao != 0);
        if(!consultantalterado.getRole().equals(consultantoriginal.getRole()) || !consultantalterado.getSector().equals(consultantoriginal.getSector()) || !consultantalterado.getName().equals(consultantoriginal.getName()))
        {
            Database.getInstance().UpdateConsultant(consultantalterado, idConsultants);
        }
    }
    public void newDiagnosis() throws SQLException {
       ArrayList<String> enterprises = Database.getInstance().SelectEnterprise();
        System.out.println("-- EMPRESAS --");
        for(int i = 0; i < enterprises.size(); i++)
        {
            System.out.println(enterprises.get(i).split(",")[0] + " - " + enterprises.get(i).split(",")[1]);
        }
        System.out.println("-- --  -- --");
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o numero da Empresa...");
        String idEnterprise = in.next();
        ArrayList<String> perguntas = Database.getInstance().SelectPerguntas();
        ArrayList<Evaluation> avaliacoes = new ArrayList<Evaluation>();
        System.out.println("Porfavor preencha o Score de cada pergunta:");
        for (int i = 0; i< perguntas.size();i++) {
            System.out.println(perguntas.get(i).split(";")[1]);
            int score = in.nextInt();
            avaliacoes.add(new Evaluation(score, Integer.parseInt(perguntas.get(i).split(";")[0]), 0));
        }
        try {
           Database.getInstance().performInsertDiagnostico(avaliacoes, idEnterprise);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void getDiagnosis() throws SQLException {
        ArrayList<String> enterprises = Database.getInstance().SelectEnterprise();
        System.out.println("-- EMPRESAS --");
        for(int i = 0; i < enterprises.size(); i++)
        {
            System.out.println(enterprises.get(i).split(",")[0] + " - " + enterprises.get(i).split(",")[1]);
        }
        System.out.println("-- --  -- --");
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o numero da Empresa...");
        String idEnterprise = in.next();
        ArrayList<Diagnosis> diagnostico = Database.getInstance().SelectDiagnosis(idEnterprise);
        String eixo= "";
        for(int i = 0; i < diagnostico.size(); i++)
        {
            if(eixo.isEmpty()){
                System.out.println("-----Eixo - " + diagnostico.get(i).getEixo() + " -----");
                System.out.println("SCORE - PERGUNTA");
                System.out.println("0" + diagnostico.get(i).getScore() + "    - " + diagnostico.get(i).getPergunta());
                eixo = diagnostico.get(i).getEixo();
            }
            else if (eixo.equals(diagnostico.get(i).getEixo())){
                System.out.println("0" + diagnostico.get(i).getScore() + "    - " + diagnostico.get(i).getPergunta());
            }
            else if(!eixo.equals(diagnostico.get(i).getEixo()))
            {
                System.out.println("-----Eixo - " + diagnostico.get(i).getEixo() + " -----");
                System.out.println("SCORE - PERGUNTA");
                System.out.println("0" + diagnostico.get(i).getScore() + "    - " + diagnostico.get(i).getPergunta());
                eixo = diagnostico.get(i).getEixo();
            }
        }
    }

    public void getEnterprise() throws SQLException {
        ArrayList<String> enterprises = Database.getInstance().SelectEnterprise();
        System.out.println("-- EMPRESAS --");
        for(int i = 0; i < enterprises.size(); i++)
        {
            System.out.println(enterprises.get(i).split(",")[0] + " - " + enterprises.get(i).split(",")[1]);
        }

        System.out.println("-- --  -- --");
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o numero da Empresa...");
        String idEnterprise = in.next();

        Enterprise actualEnterprise = Database.getInstance().selectEnterprise(idEnterprise);
        System.out.println("---------------- " + actualEnterprise.getName() + " -------------------");
        System.out.println(actualEnterprise.toFriendlyString());

    }


    public void newEntrepreneur(){
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o seu login...");
        String login = in.next();
        System.out.println("Digite a sua senha...");
        String password = in.next();
        System.out.println("Digite o seu nome...");
        String name = in.next();
        System.out.println("Digite a sua função...");
        String role = in.next();
        System.out.println("Digite o seu setor...");
        String sector = in.next();
        System.out.println("Digite o seu email...");
        String email = in.next();
        System.out.println("Digite o seu telefone...");
        String phone = in.next();
        System.out.println("Digite a sua empresa...");
        String enterprise = in.next();
        String buscaEmpresa = "";
        String [] CampoWhere = new  String[1];
        CampoWhere[0] = "NOME";
        String [] ValorWhere = new  String[1];
        ValorWhere[0] = "'"+enterprise+"'";
        try{
        buscaEmpresa = Database.getInstance()
                    .SelectWhereEnterprise("EMPRESA", CampoWhere, ValorWhere);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(buscaEmpresa.split(",")[0].equals("Sucesso")) {
            Entrepreneur entrepreneur = new Entrepreneur(login, password, name, role, phone, email, buscaEmpresa.split(",")[1], sector);

            try {
                Database.getInstance()
                        .performInsert(entrepreneur.database_fields(), entrepreneur.toString(), entrepreneur.getTABLE_NAME() );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void login() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o seu login...");
        String login = in.next();
        System.out.println("Digite a sua senha...");
        String password = in.next();

        if (Database.getInstance().queryForLogin(login, password)){
            System.out.println("Usuário logado com sucesso!");
        } else {
            System.out.println("Credencias incorretas, tente novamente.");
            login();
        }
    }
}
