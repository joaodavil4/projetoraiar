package model;

public class  Entrepreneur extends User{
    private String phone;
    private String email;
    private String TABLE_NAME = "EMPREENDEDOR";
    private String sector;
    private String enterprise;
    public Entrepreneur(String login, String password, String name, String role, String phone, String email, String enterprise, String sector) {
        super(login, password, name, role);
        this.phone = phone;
        this.email = email;
        this.sector = sector;
        this.enterprise = enterprise;
    }
    public Entrepreneur(String login,String password, String name,String role)
    {
        super(login, password, name, role);

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

    public String getTABLE_NAME(){
        return TABLE_NAME;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getEnterprise() {
        return enterprise;
    }

    @Override
    public String toString() {
        return "'" + getLogin() + "','" + getPassword() + "','"+ getName() + "','" + getSector()
                + "','" + getRole()+ "','" + getPhone() + "','" + getEmail() + "'," + getEnterprise();
    }

    public String database_fields(){
        return " LOGIN , CARGO, SENHA , SETOR, NOME , TELEFONE, EMAIL , IDEMPRESA";
    }
}
