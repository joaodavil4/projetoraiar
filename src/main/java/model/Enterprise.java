package model;

import java.util.Date;

public class Enterprise {
    private String name;
    private String phone;
    private String email;
    private String site;
    private Date lifetime;
    private Date registrationDate;

    public Enterprise(String name, String phone, String email, String site, Date lifetime, Date registrationDate) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.site = site;
        this.lifetime = lifetime;
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
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

    public Date getLifetime() {
        return lifetime;
    }

    public void setLifetime(Date lifetime) {
        this.lifetime = lifetime;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String toFriendlyString(){
        return "Nome: " + getName() +
                "\n Telefone: " + getPhone() +
                "\n Email: " + getEmail() +
                "\n Site: " + getSite() +
                "\n Data Fundação: " + getLifetime() +
                "\n Data de Cadastro: " + getRegistrationDate();
    }
}
