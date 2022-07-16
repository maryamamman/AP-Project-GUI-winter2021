package model.self;

import model.user.Student;

public class Reserve {

    public int day;
    public String type;
    public String foodName;
    public boolean eaten;
    public int price;

    public Reserve(int day, String type, String foodName, int price) {
        this.day = day;
        this.type = type;
        this.foodName = foodName;
        this.price = price;
        eaten = false;
    }

    public void setToEaten() {
        eaten = true;

    }
}
