package model.user;


import java.util.ArrayList;

public class Student extends User{
    public static ArrayList<Student> students;
    public boolean inDorm;
    public static ArrayList<Reserve> reserveList;
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
        students.add(this);
    }
    public void deposit(int amount) {
        wallet += amount;
    }

    class Reserve {

        int day;
        String type;
        String foodName;
        boolean eaten;

        public Reserve(int day, String type, String foodName) {
            this.day = day;
            this.type = type;
            this.foodName = foodName;
            eaten = false;
            Student.reserveList.add(this);
        }

        public void setToEaten() {
            eaten = true;

        }
    }
}

