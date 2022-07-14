package model.user;

import java.util.ArrayList;

public class Distributor extends User{
    public static ArrayList<Distributor> distributors;
    static {
        distributors = new ArrayList<>();
    }
    public String selfName;
    public Distributor(String username, String password, String name, String selfName) {
        super(username, password, name);
        this.selfName = selfName;
        distributors.add(this);
    }
}