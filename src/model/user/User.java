package model.user;

public abstract class User {
    public String username;
    public String password;
    public String name;

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
    public boolean loginCheck(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    public static User search(String username, String password) {
        for (Student student :
                Student.students) {
            if (student.loginCheck(username, password))
                return student;
        }
        for (Distributor distributor :
                Distributor.distributors) {
            if (distributor.loginCheck(username, password))
                return distributor;
        }
        for (Admin admin :
                Admin.admins) {
            if (admin.loginCheck(username, password))
                return admin;
        }
        return null;
    }
}