package model;

import java.util.Date;

public class Enterprise {
    private String name;
    private String phone;
    private String email;
    private String site;
    private String lifetime;
    private String registrationDate;
    private String IdPrograma;
    private String IdAdvisor;
    private String TABLE_NAME = "EMPRESA";

    public Enterprise(String name, String phone, String email, String site, String lifetime, String registrationDate,String IdPrograma, String IdAdvisor) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.site = site;
        this.lifetime = lifetime;
        this.registrationDate = registrationDate;
        this.IdPrograma = IdPrograma;
        this.IdAdvisor = IdAdvisor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdPrograma() {
        return IdPrograma;
    }

    public void setIdPrograma(String IdPrograma) {
        this.IdPrograma = IdPrograma;
    }

    public String getIdAdvisor() {
        return IdPrograma;
    }

    public void setIdAdvisor(String IdAdvisor) {
        this.IdAdvisor = IdAdvisor;
    }


    public String getPhone() {
        return phone;
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLifetime() {
        return lifetime;
    }

    public void setLifetime(String lifetime) {
        this.lifetime = lifetime;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }


    public String toFriendlyString(){
        return "Nome: " + getName() +
                "\n Telefone: " + getPhone() +
                "\n Email: " + getEmail() +
                "\n Site: " + getSite() +
                "\n Data Fundação: " + getLifetime() +
                "\n Data de Cadastro: " + getRegistrationDate()+
                 "\n Código Programa: " + getIdPrograma()+
                 "\n Código Advisor: " + getIdAdvisor();
    }

    @Override
    public String toString() {
        return "'" + getName() + "','" + getPhone() + "','"+ getEmail() + "','" + getSite()
                + "'," + getRegistrationDate()+ "," + getLifetime() + "," + getIdPrograma() + "," + getIdAdvisor();
    }

    public String database_fields(){
        return " NOME , TELEFONE, EMAIL , SITE, ANOFUNDACAO , DATACADASTRO, IDPROGRAMA , IDADVISOR";
    }
}
