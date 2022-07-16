import controller.Controller;
import controller.LoginController;
import controller.Time;
import model.self.Self;
import model.user.Admin;

public class Main {

    public static void main(String[] args) {
        Admin admin = new Admin("admin","admin","admin");
        Controller menu = new LoginController();
        while (menu != null){
            menu =  menu.run();
        }
    }
}
