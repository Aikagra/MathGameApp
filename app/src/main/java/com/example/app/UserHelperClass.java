package com.example.app;

public class UserHelperClass {

    String name, email, result;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserHelperClass(String result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
