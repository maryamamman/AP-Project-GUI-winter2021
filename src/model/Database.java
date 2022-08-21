package model;

import com.google.gson.Gson;
import controllerAndView.Time;
import model.self.FoodHandler;
import model.self.Self;
import model.user.Admin;
import model.user.Distributor;
import model.user.Student;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    final private ArrayList<Student> students = new ArrayList<>();
    final private ArrayList<Admin> admins = new ArrayList<>();
    final private ArrayList<Distributor> distributors = new ArrayList<>();
    final private HashMap<String, Self> selves = new HashMap<>();
    final private HashMap<String, Integer> breakfastPrice = new HashMap<>();
    final private HashMap<String, Integer> lunchPrice = new HashMap<>();
    final private HashMap<String, Integer> dinnerPrice = new HashMap<>();
    final private HashMap<Integer, String[]> breakfastTable = new HashMap<>();
    final private HashMap<Integer, String[]> lunchTable = new HashMap<>();
    final private HashMap<Integer, String[]> dinnerTable = new HashMap<>();
    public static Database database = new Database();
    private int day;
    private int mealNum;


    public Database() {
        /*students = new ArrayList<>();
        admins = new ArrayList<>();
        distributors = new ArrayList<>();
        selves = new HashMap<>();
        breakfastPrice = new HashMap<>();
        lunchPrice = new HashMap<>();
        dinnerPrice = new HashMap<>();
        breakfastTable = new HashMap<>();
        lunchTable = new HashMap<>();
        dinnerTable = new HashMap<>();*/
        //Admin firstAdmin = new Admin("admin", "admin", "admin");
        //admins.add(firstAdmin);
    }

    public void parse() {
        Student.students = this.students;
        Distributor.distributors = this.distributors;
        Admin.admins = this.admins;
        Self.selves = this.selves;
        FoodHandler.breakfastPrice = this.breakfastPrice;
        FoodHandler.lunchPrice = this.lunchPrice;
        FoodHandler.dinnerPrice = this.dinnerPrice;
        FoodHandler.breakfastTable = this.breakfastTable;
        FoodHandler.lunchTable = this.lunchTable;
        FoodHandler.dinnerTable = this.dinnerTable;
        Time.day = this.day;
        Time.mealNum = this.mealNum;
    }

    public static void write(String fileName) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(database, writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(String fileName) {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader(fileName)) {
            database = gson.fromJson(fileReader, Database.class);
            database.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}