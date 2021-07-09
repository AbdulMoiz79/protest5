package model;

public class AccountModel {
    public boolean login(String username,String password){
        return username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin");

    }
}
