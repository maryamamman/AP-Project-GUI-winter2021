package model.self;

import java.util.*;

public class FoodHandler {
    public static HashMap<String, Integer> breakfastPrice;
    public static HashMap<String, Integer> lunchPrice;
    public static HashMap<String, Integer> dinnerPrice;
    public static HashMap<Integer, String[]> breakfastTable;
    public static HashMap<Integer, String[]> lunchTable;
    public static HashMap<Integer, String[]> dinnerTable;
    static {
        breakfastPrice = new HashMap<>();
        lunchPrice = new HashMap<>();
        dinnerPrice = new HashMap<>();
        breakfastTable = new HashMap<>();
        lunchTable = new HashMap<>();
        dinnerTable = new HashMap<>();
    }
    public static void addFood(String name, int price, String type) {
        switch (type){
            case "breakfast": breakfastPrice.put(name, price);
            case "lunch": lunchPrice.put(name, price);
            case "dinner": dinnerPrice.put(name, price);
        }
    }
    public static void setFood(int day, String name) {
        ArrayList<String> breakfastNames = new ArrayList<>(breakfastPrice.keySet());
        ArrayList<String> lunchNames = new ArrayList<>(lunchPrice.keySet());
        ArrayList<String> dinnerNames = new ArrayList<>(dinnerPrice.keySet());
        Random foodChooser = new Random();
        String breakfast1 = breakfastNames.get(foodChooser.nextInt(breakfastNames.size()));
        String breakfast2 = breakfastNames.get(foodChooser.nextInt(breakfastNames.size()));
        breakfastTable.put(day, new String[]{breakfast1, breakfast2});
        String lunch1 = lunchNames.get(foodChooser.nextInt(lunchNames.size()));
        String lunch2 = lunchNames.get(foodChooser.nextInt(lunchNames.size()));
        lunchTable.put(day, new String[]{lunch1, lunch2});
        String dinner1 = dinnerNames.get(foodChooser.nextInt(dinnerNames.size()));
        String dinner2 = dinnerNames.get(foodChooser.nextInt(dinnerNames.size()));
        dinnerTable.put(day, new String[]{dinner1, dinner2});
    }
}
