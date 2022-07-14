package controller;

import controller.admin.AdminController;
import controller.distributor.DistributorController;
import controller.student.StudentController;
import model.user.Admin;
import model.user.Distributor;
import model.user.Student;
import model.user.User;

public class LoginController extends Controller{

    @Override
    public Controller run() {
        Controller controller = this;
        System.out.println("LOGIN MENU");
        String username = getCommand("username: ");
        String password = getCommand("password: ");
        User matchedUser = User.search(username, password);
        if (matchedUser == null){
            System.out.println("wrong username or password, try again!");
        }
        else {
            if (matchedUser instanceof Student) {
                controller = new StudentController((Student) matchedUser);
            }
            else if (matchedUser instanceof Distributor) {
                controller = new DistributorController((Distributor) matchedUser);
            }
            else if (matchedUser instanceof Admin) {
                controller = new AdminController((Admin) matchedUser);
            }
        }
        return controller;
    }
}
