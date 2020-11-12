package controller;

import db.Database;
import model.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class OptionsController {
    private static OptionsController instance;
    private User actualUser;

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

    public void updateEnterprise() throws SQLException {
        ArrayList<String> enterprise = Database.getInstance().SelectEnterprise();
        System.out.println("-- Empresa --");
        for(int i = 0; i < enterprise.size(); i++)
        {
            System.out.println(enterprise.get(i).split(",")[0] + " - " + enterprise.get(i).split(",")[1]);
        }
        System.out.println("-- --  -- --");
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o numero do Registro que deseja alterar...");
        String idEmpresa = in.next();
        Enterprise empresaOriginal =  Database.getInstance().getEnterprise(idEmpresa);
        Enterprise empresaAlterado = new Enterprise(empresaOriginal.getName(), empresaOriginal.getPhone(), empresaOriginal.getEmail(),empresaOriginal.getSite() ,empresaOriginal.getLifetime(),empresaOriginal.getRegistrationDate(),empresaOriginal.getIdPrograma(), empresaOriginal.getIdAdvisor());
        empresaAlterado.setName(empresaOriginal.getName());
        empresaAlterado.setIdPrograma(empresaOriginal.getIdPrograma());
        empresaAlterado.setIdAdvisor(empresaOriginal.getIdAdvisor());
        empresaAlterado.setPhone(empresaOriginal.getPhone());
        empresaAlterado.setEmail(empresaOriginal.getEmail());
        empresaAlterado.setSite(empresaOriginal.getSite());

        int opcao = 0;
        do{
            System.out.println("-- Dados do Empresa --");
            System.out.println("[1]Nome: " + empresaAlterado.getName() + "  [2]Telefone: " + empresaAlterado.getPhone() + " [3]E-mail:" + empresaAlterado.getEmail() + " [4]Site:" + empresaAlterado.getSite());
            System.out.println("-- -- -- --");
            System.out.println("Digite o número do campo que deseja alterar ou 0 para sair: " );
            opcao = Integer.parseInt(in.next());
            switch (opcao){
                case 1:
                    System.out.println("Digite o novo valor:");
                    in.nextLine();
                    empresaAlterado.setName(in.nextLine());
                    Database.getInstance().UpdateEnterprise(empresaAlterado, idEmpresa);
                    break;
                case 2:
                    System.out.println("Digite o novo valor:");
                    in.nextLine();
                    empresaAlterado.setPhone(in.nextLine());
                    Database.getInstance().UpdateEnterprise(empresaAlterado, idEmpresa);
                    break;
                case 3:
                    System.out.println("Digite o novo valor:");
                    in.nextLine();
                    empresaAlterado.setEmail(in.nextLine());
                    Database.getInstance().UpdateEnterprise(empresaAlterado, idEmpresa);
                    break;
                case 4:
                    System.out.println("Digite o novo valor:");
                    in.nextLine();
                    empresaAlterado.setSite(in.nextLine());
                    Database.getInstance().UpdateEnterprise(empresaAlterado, idEmpresa);
                    break;

                default:
                    break;
            }
        }while(opcao != 0);

    }
    public void updateEntrepreneur() throws SQLException {
        ArrayList<String> Entrepreneur = Database.getInstance().SelectEntrepreneur();
        System.out.println("-- Empreendedores --");
        for(int i = 0; i < Entrepreneur.size(); i++)
        {
            System.out.println(Entrepreneur.get(i).split(",")[0] + " - " + Entrepreneur.get(i).split(",")[1]);
        }
        System.out.println("-- --  -- --");
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o numero do Registro que deseja alterar...");
        String idEmpreendedor= in.next();
        Entrepreneur empreendedorOrignal =  Database.getInstance().getEntrepreneur(idEmpreendedor);
        Entrepreneur empreendedoralterado = new Entrepreneur(empreendedorOrignal.getLogin(), empreendedorOrignal.getPassword(), empreendedorOrignal.getName(),empreendedorOrignal.getRole() ,empreendedorOrignal.getPhone(),empreendedorOrignal.getEmail(),empreendedorOrignal.getEnterprise(), empreendedorOrignal.getSector());
        empreendedoralterado.setName(empreendedorOrignal.getName());
        empreendedoralterado.setSector(empreendedorOrignal.getSector());
        empreendedoralterado.setRole(empreendedorOrignal.getRole());
        empreendedoralterado.setPhone(empreendedorOrignal.getPhone());
        empreendedoralterado.setEmail(empreendedorOrignal.getEmail());
        int opcao = 0;
        do{
            System.out.println("-- Dados do Empreendedor --");
            System.out.println("[1]Nome: " + empreendedoralterado.getName() + "  [2]Setor: " + empreendedoralterado.getSector() + " [3]Cargo:" + empreendedoralterado.getRole() + " [4]Telefone:" + empreendedoralterado.getPhone()+ " [5]E-mail:" + empreendedoralterado.getEmail());
            System.out.println("-- -- -- --");
            System.out.println("Digite o número do campo que deseja alterar ou 0 para sair: " );
            opcao = Integer.parseInt(in.next());
            switch (opcao){
                case 1:
                    System.out.println("Digite o novo valor:");
                    in.nextLine();
                    empreendedoralterado.setName(in.nextLine());
                    Database.getInstance().UpdateEntrepreneur(empreendedoralterado, idEmpreendedor);
                    break;
                case 2:
                    System.out.println("Digite o novo valor:");
                    in.nextLine();
                    empreendedoralterado.setSector(in.nextLine());
                    Database.getInstance().UpdateEntrepreneur(empreendedoralterado, idEmpreendedor);
                    break;
                case 3:
                    System.out.println("Digite o novo valor:");
                    in.nextLine();
                    empreendedoralterado.setRole(in.nextLine());
                    Database.getInstance().UpdateEntrepreneur(empreendedoralterado, idEmpreendedor);
                    break;
                case 4:
                    System.out.println("Digite o novo valor:");
                    in.nextLine();
                    empreendedoralterado.setPhone(in.nextLine());
                    Database.getInstance().UpdateEntrepreneur(empreendedoralterado, idEmpreendedor);
                    break;
                case 5:
                    System.out.println("Digite o novo valor:");
                    in.nextLine();
                    empreendedoralterado.setEmail(in.nextLine());
                    Database.getInstance().UpdateEntrepreneur(empreendedoralterado, idEmpreendedor);
                    break;
                default:
                    break;
            }
        }while(opcao != 0);



    }

    public void menuAnalise() throws SQLException {
        ArrayList<String> enterprises = Database.getInstance().SelectEnterprise();
        System.out.println("* - Análise Geral");
        System.out.println("-- EMPRESAS --");

        for(int i = 0; i < enterprises.size(); i++)
        {
            System.out.println(enterprises.get(i).split(",")[0] + " - " + enterprises.get(i).split(",")[1]);
        }

        System.out.println("-- --  -- --");
        Scanner in = new Scanner(System.in);
        System.out.println("Digite a empresa ou análise...");
        String idOption = in.nextLine();

        if (idOption.equals("*")){
            System.out.println("Análise Comparativa do Eixo");
        } else {
            System.out.println("1 - Análise Comparativa do Eixo");
            System.out.println("2 - Análise Histórica do Eixo");
            System.out.println("3 - Análise Específica do Eixo");
        }

        String analiseOption = in.nextLine();

        switch (analiseOption){
            case "1":
                break;
            case "2":
                break;
            case"3":
                break;
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

    public void newEnterprise() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("Digite o seu Nome...");

        String nome = in.nextLine();

        System.out.println("Digite a sua Telefone...");
        String telefone = in.next();
        System.out.println("Digite o seu E-mail...");
        String email = in.next();
        System.out.println("Digite a sua Site...");
        String site = in.next();
        System.out.println("Digite o seu Ano Fundação...");
        String AnoFundacao = in.next();
        AnoFundacao = AnoFundacao;
        String[] splitAnoFund = AnoFundacao.split("/");
        AnoFundacao = "#"+splitAnoFund[1]+"/"+splitAnoFund[0]+"/"+splitAnoFund[2]+"#";
        System.out.println("Digite o seu Data Cadastro...");
        String DataCadastro = in.next();
        splitAnoFund = DataCadastro.split("/");
        DataCadastro = "#"+splitAnoFund[1]+"/"+splitAnoFund[0]+"/"+splitAnoFund[2]+"#";

        System.out.println("Digite o seu programa...");
        String programa = in.next();
        String buscarPrograma = "";
        String [] CampoWhere = new  String[1];
        CampoWhere[0] = "NOME";
        String [] ValorWhere = new  String[1];
        ValorWhere[0] = "'"+programa+"'";
        try{
            buscarPrograma = Database.getInstance()
                    .SelectWhereEnterprise("PROGRAMA", CampoWhere, ValorWhere);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        in.nextLine();
        System.out.println("Digite a sua Advisor...");
        String advisor = in.nextLine();

        String buscaradvisor = "";
        String [] CampoAdvisorWhere = new  String[1];
        CampoWhere[0] = "NOME";
        String [] ValorWhereAdvisor = new  String[1];
        ValorWhere[0] = "'"+advisor+"'";


        try{
            buscaradvisor = Database.getInstance()
                    .SelectWhereEnterprise("Consultor", CampoWhere, ValorWhere);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(buscaradvisor.split(",")[0].equals("Sucesso")) {
            Enterprise enterprise = new Enterprise(nome, telefone, email, site, AnoFundacao, DataCadastro,buscarPrograma.split(",")[1] ,buscaradvisor.split(",")[1]);

            try {
                Database.getInstance()
                        .performInsert(enterprise.database_fields(), enterprise.toString(), enterprise.getTABLE_NAME() );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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

    public void login() throws SQLException {
        Scanner in = new Scanner(System.in);
        String loginOpt = "";

        if (actualUser == null){
            System.out.println("Escolha... \n1- Consultor \n2- Emprendedor");
            if (in.nextInt() == 1){
                loginOpt = "Consultor";
            } else{
                loginOpt = "Empreendedor";
            }
        }

        System.out.println("Digite o seu login...");
        String login = in.next();
        System.out.println("Digite a sua senha...");
        String password = in.next();

        if (Database.getInstance().queryForLogin(login, password, loginOpt)){
            System.out.println("Usuário logado com sucesso!");
        } else {
            System.out.println("Credencias incorretas, tente novamente.");
            login();
        }
    }

    public User getUser(){
        return actualUser;
    }
}
