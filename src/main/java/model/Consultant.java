package model;

public class Consultant extends User{
    private String sector;

    public Consultant(int id, String login, String name, String role, String sector) {
        super(id, login, name, role);
        this.sector = sector;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
