package controller.admin;

import controller.Controller;
import controller.LoginController;
import controller.UserController;
import exceptions.BackException;
import exceptions.ExitException;
import exceptions.IllegalCommandException;
import model.user.Admin;

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
                    case ADD_ADMIN -> addAdmin();
                    case ADD_DISTRIBUTOR -> addDistributor();
                    case ADD_STUDENT -> addStudent();
                    case SHOW_DEMANDS -> showInformation();
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

    private void showInformation() {
    }

    private void addStudent() {

    }

    private void addDistributor() {

    }

    private void addAdmin() {

    }

}
