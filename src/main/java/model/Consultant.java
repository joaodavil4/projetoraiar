package model;

public class Consultant extends User{
    private String sector;
    private String TABLE_NAME = "CONSULTOR";

    public Consultant(String login, String password, String name, String role, String sector) {
        super(login, password, name, role);
        this.sector = sector;
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

    @Override
    public String toString() {
        return "'" + getLogin() + "','" + getPassword() + "','"+ getName() + "','" + getSector()
                + "','" + getRole()+"'";
    }
}
