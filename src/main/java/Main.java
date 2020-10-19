import controller.OptionsController;
import db.Database;

import java.sql.SQLException;
import java.util.Scanner;

public class Main{
    public static void main (String[] args) throws SQLException {
        Database.getInstance().connect();

        Scanner in = new Scanner(System.in);
        String opt;

        OptionsController.getInstance().login();

        do {
            exibeMenu();
            opt = in.nextLine();

            switch (opt){
                case "1":
                    OptionsController.getInstance().newConsultant();

            }
        } while (!opt.equals("0"));

        Database.getInstance().disconect();
        System.exit(0);
    }

    public static void exibeMenu(){
        System.out.println("---------------------------------------");
        System.out.println("Opções disponíveis");
        System.out.println("1) Cadastro de consultor");
        System.out.println("*) Sair");

    }

}