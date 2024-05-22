package com.chat.threading;

public class Data {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String country;
    private String domain;
    private String birth_date;

    public Data(int id, String first_name, String last_name, String email, String gender, String country, String domain, String birth_date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.domain = domain;
        this.birth_date = birth_date;
    }

    public Data() {
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getDomain() {
        return domain;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }
}
