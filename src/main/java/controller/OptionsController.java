package controller;

import db.Database;
import model.Consultant;

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
        System.out.println("Digite o seu nome...");
        String name = in.next();
        System.out.println("Digite a sua função...");
        String role = in.next();
        System.out.println("Digite o seu setor...");
        String sector = in.next();
        Consultant consultant = new Consultant(1,login, name, role,sector);

        try {
            Database.getInstance()
                    .performInsertAll(consultant.getTABLE_NAME(),
                    consultant.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
