import controller.OptionsController;
import db.Database;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Main{
    public static void main (String[] args) throws SQLException, ParseException {
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
                    OptionsController.getInstance().newEnterprise();
                    break;
                case "4":
                    OptionsController.getInstance().getDiagnosis();
                    break;
                case "5":
                    OptionsController.getInstance().getEnterprise();
                    break;
                case "6":
                    OptionsController.getInstance().newDiagnosis();
                    break;
                case "7":
                    OptionsController.getInstance().updateConsultant();
                    break;
                case "8":
                    OptionsController.getInstance().updateEntrepreneur();
                    break;
                case "9":
                    OptionsController.getInstance().updateEnterprise();
                    break;
                case "10":
                    OptionsController.getInstance().menuAnalise();
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
        System.out.println("3) Cadastro de Empresa");
        System.out.println("4) Consultar Diagnóstico");
        System.out.println("5) Consultar Empresa");
        System.out.println("6) Cadastro de Diagnóstico");
        System.out.println("7) Edição Consultor");
        System.out.println("8) Edição Empreendedor");
        System.out.println("9) Edição Empresa");
        System.out.println("10) Análise Avançada");
        System.out.println("0) Sair");

    }

}