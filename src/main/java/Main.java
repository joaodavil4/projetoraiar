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
                    break;
                case "2":
                    OptionsController.getInstance().newEntrepreneur();
                    break;
                case "3":
                    OptionsController.getInstance().getDiagnosis();
                    break;
                case "4":
                    OptionsController.getInstance().getEnterprise();
                    break;
                case "5":
                    OptionsController.getInstance().newDiagnosis();
                    break;
                case "6":
                    OptionsController.getInstance().updateConsultant();
                    break;
                default:
                    opt = "0";
                    break;

            }
        } while (!opt.equals("0"));

        Database.getInstance().disconect();
        System.exit(0);
    }

    public static void exibeMenu(){
        System.out.println("---------------------------------------");
        System.out.println("Opções disponíveis");
        System.out.println("1) Cadastro de Consultor");
        System.out.println("2) Cadastro de Empreendedor");
        System.out.println("3) Consultar Diagnóstico");
        System.out.println("4) Consultar Empresa");
        System.out.println("5) Cadastro de Diagnóstico");
        System.out.println("6) Edição Consultor");
        System.out.println("0) Sair");

    }

}