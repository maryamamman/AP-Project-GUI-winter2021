import controller.Controller;
import controller.LoginController;
import model.user.Admin;

public class Main {

    public static void main(String[] args) {
        Admin admin = new Admin("maryam","123","maryam");
        Controller menu = new LoginController();
        while (menu != null){
            menu =  menu.run();
        }
    }
}
