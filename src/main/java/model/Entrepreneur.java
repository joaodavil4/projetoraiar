package model;

public class Entrepreneur extends User{
    private int phone;
    private String email;

    public Entrepreneur(int id, String login, String name, String role, int phone, String email) {
        super(id, login, name, role);
        this.phone = phone;
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
