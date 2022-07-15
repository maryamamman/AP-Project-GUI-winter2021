package controller.student;

import controller.Controller;
import controller.LoginController;
import controller.UserController;
import exceptions.BackException;
import exceptions.ExitException;
import exceptions.IllegalCommandException;
import model.user.Student;

import java.util.regex.Matcher;

public class StudentController extends UserController {
    Student student;

    public StudentController(Student student) {
        this.student = student;
    }

    @Override
    public Controller run() {
        Controller controller = this;
        try {
            String input = getCommand(null);
            StudentCommand studentCommand = StudentCommand.findCommand(input);
            Matcher matcher = StudentCommand.getMatcher(input, studentCommand);
            if (matcher.find())
                switch (studentCommand) {
                    case RESERVE -> reserve(Integer.parseInt(matcher.group(1)), matcher.group(2), matcher.group(3));
                    case SHOW_FOOD_MENU -> showFoodMenu();
                    case CREDIT_ENHANCEMENT -> creditEnhancement(Integer.parseInt(matcher.group(1)));
                }

            return null;
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

    private void creditEnhancement(int amount) {
        student.deposit(amount);
    }


    private void showFoodMenu() {

    }


    private void reserve(int day, String type, String foodName) {
            System.out.println(day);
            System.out.println(type);
            System.out.println(foodName);
    }
}