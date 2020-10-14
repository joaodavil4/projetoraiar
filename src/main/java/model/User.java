package model;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private int id;
    private String login;
    private String name;
    private String role;
    private String password;

    public User(String login, String name, String role, String password) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
