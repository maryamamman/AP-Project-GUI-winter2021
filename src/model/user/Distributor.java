package model.user;

import controllerAndView.Time;
import model.Database;
import model.self.Reserve;
import model.self.Self;

import java.util.ArrayList;

public class Distributor extends User {
    public static ArrayList<Distributor> distributors;

    static {
        distributors = new ArrayList<>();
    }

    public String selfName;

    public Distributor(String username, String password, String name, String selfName) {
        super(username, password, name);
        this.selfName = selfName;
        distributors.add(this);
        Database.write("database.json");
    }

    public String showDemand(int id) {
        Student student = Student.getStudent(id);
        if (student != null && student.hasFood(Time.day, Time.currentMeal())) {
            String demand = "";
            String self = selfName;
            switch (Time.currentMeal()) {
                case "breakfast" -> demand = Self.selves.get(self).breakfastStudents.get(Time.day).get(id);
                case "lunch" -> demand = Self.selves.get(self).lunchStudents.get(Time.day).get(id);
                case "dinner" -> demand = Self.selves.get(self).dinnerStudents.get(Time.day).get(id);
            }
            return demand;
        }
        return null;
    }

    public void giveFood(int id) {
        Student student = Student.getStudent(id);
        for (Reserve reserve : student.reserveList) {
            if (reserve.day == Time.day && reserve.type.equals(Time.currentMeal())) {
                reserve.setToEaten();
            }
        }
        Database.write("database.json");
    }
}