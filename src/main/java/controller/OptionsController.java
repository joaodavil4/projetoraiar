package controller;

import db.Database;
import model.Consultant;
import model.Enterprise;
import model.Entrepreneur;

import java.sql.SQLException;
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
        String login = in.next();
        System.out.println("Digite a sua senha...");
        String password = in.next();
        System.out.println("Digite o seu nome...");
        String name = in.next();
        System.out.println("Digite a sua função...");
        String role = in.next();
        System.out.println("Digite o seu setor...");
        String sector = in.next();
        Consultant consultant = new Consultant(login, password, name, role, sector);

        try {
            Database.getInstance()
                    .performInsertAll(consultant.getTABLE_NAME(),consultant.toString()
                    );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    public void login(){
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
