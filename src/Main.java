import com.google.gson.Gson;
import controller.Controller;
import controller.LoginController;
import model.DataBase;
import model.user.Admin;

public class Main {

    public static void main(String[] args) {
        Admin admin = new Admin("admin", "admin", "admin");
        Controller controller = new LoginController();
        while (controller != null) {
            controller = controller.run();
        }
    }
}
