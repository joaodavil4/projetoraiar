package model;

public class Entrepreneur extends User{
    private int phone;
    private String email;

    public Entrepreneur(String login, String password, String name, String role, int phone, String email) {
        super(login, password, name, role);
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
