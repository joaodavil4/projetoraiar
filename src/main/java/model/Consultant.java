package model;

public class Consultant extends User{
    private String sector;
    private String TABLE_NAME = "CONSULTOR";

    public Consultant(int id, String login, String name, String role, String sector) {
        super(id, login, name, role);
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
        return getLogin() + "," + getName() + "," + getRole()
                + "," + getSector();
    }
}
