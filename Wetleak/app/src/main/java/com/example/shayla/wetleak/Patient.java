package com.example.shayla.wetleak;

/**
 * This class creates a new patient object and sets values and is used by intents to carry patient
 * information from page to page.
 *
 * @author Shayla Moore
 */
public class Patient{

    private String pname;
    private int age;

    public Patient() {}

    public Patient(String pname, int age) {

        this.pname = pname;
        this.age = age;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
