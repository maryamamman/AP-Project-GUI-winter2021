package model.user;

import model.Database;

import java.util.ArrayList;

public class Admin extends User{
    public static ArrayList<Admin> admins;

    static {
        admins = new ArrayList<>();
    }

    public Admin(String username, String password, String name) {
        super(username, password, name);
        admins.add(this);
        Database.write("database.json");
    }

}
