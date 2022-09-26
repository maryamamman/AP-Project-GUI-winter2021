package model.user;


import model.Database;
import model.self.Reserve;

import java.util.ArrayList;
import java.util.Objects;

public class Student extends User {
    public static ArrayList<Student> students;
    public boolean inDorm;
    public ArrayList<Reserve> reserveList;
    public int id;
    public int wallet;

    static {
        students = new ArrayList<>();
    }

    public Student(String username, String password, String name, int id, boolean inDorm) {
        super(username, password, name);
        this.inDorm = inDorm;
        this.id = id;
        wallet = 0;
        reserveList = new ArrayList<>();
        students.add(this);
        Database.write("database.json");
    }

    public static Student getStudent(int id) {
        for (Student student : students) {
            if (student.id == id)
                return student;
        }
        return null;
    }

    public void deposit(int amount) {
        wallet += amount;
        Database.write("database.json");
    }

    public void reserve(Reserve reserve) {
        reserveList.add(reserve);
        wallet -= reserve.price;
        Database.write("database.json");
    }

    public void retake(Reserve reserve) {
        reserveList.remove(reserve);
        wallet += reserve.price;
        Database.write("database.json");
    }

    public void transfer(Reserve reserve, int toId) {
        reserveList.remove(reserve);
        getStudent(toId).reserveList.add(reserve);
        Database.write("database.json");
    }

    public void selectedFromWaitList(Reserve reserve) {
        wallet += reserve.price;
        Database.write("database.json");
    }

    public void sendToWaitList(Reserve reserve) {
        reserveList.remove(reserve);
        Database.write("database.json");
    }

    public boolean canReserve(String foodType) {
        return (inDorm) || ((!Objects.equals(foodType, "breakfast")) && (!Objects.equals(foodType, "dinner")));
    }

    public boolean hasFood(int day, String type) {
        for (Reserve reserve : reserveList) {
            if (reserve.day == day && reserve.type.equals(type) && !reserve.eaten)
                return true;
        }
        return false;
    }

}