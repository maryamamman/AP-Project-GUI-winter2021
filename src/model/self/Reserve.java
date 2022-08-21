package model.self;


import java.util.ArrayList;

public class Reserve {

    public int day;
    public String type;
    public String foodName;
    public boolean eaten;
    public int price;
    public String selfName;
    public int ownerId;

    public Reserve(int day, String type, String foodName, int price, String selfName, int ownerId) {
        this.day = day;
        this.type = type;
        this.foodName = foodName;
        this.price = price;
        this.selfName = selfName;
        this.ownerId = ownerId;
        eaten = false;
    }

    public void setToEaten() {
        eaten = true;
    }
}