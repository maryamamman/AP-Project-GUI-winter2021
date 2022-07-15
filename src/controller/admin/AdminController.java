package controller.admin;

import controller.Controller;
import controller.LoginController;
import controller.UserController;
import exceptions.BackException;
import exceptions.ExitException;
import exceptions.IllegalCommandException;
import model.user.Admin;
import model.user.Distributor;
import model.user.Student;

import java.util.Objects;
import java.util.regex.Matcher;

public class AdminController extends UserController {
    Admin admin;

    public AdminController(Admin admin) {
        this.admin = admin;
    }

    @Override
    public Controller run() {
        Controller controller = this;
        try {
            String input = getCommand("command");
            AdminCommand adminCommand = AdminCommand.findCommand(input);
            Matcher matcher = AdminCommand.getMatcher(input, adminCommand);
            if (matcher.find())
                switch (adminCommand) {
                    case ADD_ADMIN -> addAdmin(matcher.group(1), matcher.group(2), matcher.group(3));
                    case ADD_DISTRIBUTOR -> addDistributor(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
                    case ADD_STUDENT -> addStudent(matcher.group(1), matcher.group(2), matcher.group(3), Integer.parseInt(matcher.group(4)), matcher.group(5));
                    case SHOW_DEMANDS -> showDemands();
                }

        } catch (
                BackException e) {
            controller = new LoginController();
        } catch (
                ExitException e) {
            controller = null;
        } catch (
                IllegalCommandException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return controller;

    }

    private void addAdmin(String username, String password, String name) {
        Admin admin = new Admin(username, password, name);
        System.out.println("Done!");
    }

    private void addDistributor(String username, String password, String name, String selfName) {
        Distributor distributor = new Distributor(username, password, name, selfName);
        System.out.println("Done!");
    }

    private void addStudent(String username, String password, String name, int id, String inDorm) {
        Student student = new Student(username, password, name, id, Objects.equals(inDorm, "yes"));
        System.out.println("Done!");
    }

    private void showDemands() {
    }


}
