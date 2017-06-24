package com.example.shayla.wetleak;

/**
 * Created by Shayla on 5/8/2017.
 */

public class Caretaker {

    private String username;
    private String password;
    private String cname;
    private String title;
    private String email;

    public Caretaker() {
    }
    public Caretaker(String username, String password, String cname, String title, String email) {
        this.username = username;
        this.password = password;
        this.cname = cname;
        this.title = title;
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

