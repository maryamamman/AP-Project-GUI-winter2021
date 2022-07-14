package model.self;

import java.util.HashMap;

public class Self {
    public static HashMap<String, Self> selves;
    public HashMap<Integer, HashMap<String, Integer>> breakfastStudents;
    public HashMap<Integer, HashMap<String, Integer>> lunchStudents;
    public HashMap<Integer, HashMap<String, Integer>> dinnerStudents;

    public Self(String name) {
        selves.put(name, this);
        breakfastStudents = new HashMap<>();
        lunchStudents = new HashMap<>();
        dinnerStudents = new HashMap<>();
    }

    public void reserveFood(int day, String type, int foodNum, String id) {
        switch (type) {
            case "breakfast" -> {
                if (!breakfastStudents.containsKey(day))
                    breakfastStudents.put(day, new HashMap<>());
                breakfastStudents.get(day).put(id, foodNum);
            }
            case "lunch" -> {
                if (!lunchStudents.containsKey(day))
                    lunchStudents.put(day, new HashMap<>());
                lunchStudents.get(day).put(id, foodNum);
            }
            case "dinner" -> {
                if (!dinnerStudents.containsKey(day))
                    dinnerStudents.put(day, new HashMap<>());
                dinnerStudents.get(day).put(id, foodNum);
            }
        }
    }
}