import controller.Controller;
import controller.LoginController;
import model.Database;
import model.user.Admin;

public class Main {

    public static void main(String[] args) {
        Database database = Database.read("database.json");
        Controller controller = new LoginController();
        while (controller != null) {
            controller = controller.run();
            Database.write("database.json", database);
        }
    }
}
