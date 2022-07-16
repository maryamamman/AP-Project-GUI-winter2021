package controller.admin;

import controller.Controller;
import controller.LoginController;
import controller.UserController;
import exceptions.BackException;
import exceptions.ExitException;
import exceptions.IllegalCommandException;
import model.self.FoodHandler;
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
                    case NEW_ADMIN -> newAdmin(matcher.group(1), matcher.group(2), matcher.group(3));
                    case NEW_DISTRIBUTOR -> newDistributor(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
                    case NEW_STUDENT -> newStudent(matcher.group(1), matcher.group(2), matcher.group(3), Integer.parseInt(matcher.group(4)), matcher.group(5));
                    case REPORT -> report();
                    case NEW_FOOD -> newFood(matcher.group(1), Integer.parseInt(matcher.group(2)), matcher.group(3));
                    case SET_FOOD -> setFood(Integer.parseInt(matcher.group(1)), matcher.group(2), matcher.group(3));
                    case NEXT_MEAL -> nextMeal();
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

    private void nextMeal() {
    }

    private void setFood(int day, String name, String type) {
        FoodHandler.setFood(day, name, type);
    }

    private void newFood(String name, int price, String type) {
        FoodHandler.addFood(name, price, type);
    }

    private void newAdmin(String username, String password, String name) {
        Admin admin = new Admin(username, password, name);
    }

    private void newDistributor(String username, String password, String name, String selfName) {
        Distributor distributor = new Distributor(username, password, name, selfName);
    }

    private void newStudent(String username, String password, String name, int id, String inDorm) {
        Student student = new Student(username, password, name, id, Objects.equals(inDorm, "yes"));
    }

    private void report() {
    }


}
