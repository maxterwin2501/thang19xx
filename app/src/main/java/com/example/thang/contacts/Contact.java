package com.example.thang.contacts;

public class Contact {
    private boolean isMale;
    private String name;
    private String number;

    public Contact(boolean isMale, String name, String number) {
        this.isMale = isMale;
        this.name = name;
        this.number = number;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

