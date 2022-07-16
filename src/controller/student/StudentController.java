package controller.student;

import controller.Controller;
import controller.LoginController;
import controller.UserController;
import exceptions.BackException;
import exceptions.ExitException;
import exceptions.IllegalCommandException;
import model.DataBase;
import model.self.FoodHandler;
import model.self.Self;
import model.self.Reserve;
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
            String input = getCommand("student command");
            StudentCommand studentCommand = StudentCommand.findCommand(input);
            Matcher matcher = StudentCommand.getMatcher(input, studentCommand);
            if (matcher.find())
                switch (studentCommand) {
                    case RESERVE -> reserve(Integer.parseInt(matcher.group(1)), matcher.group(2), matcher.group(3), matcher.group(4));
                    case SHOW_FOOD_MENU -> showFoodMenu();
                    case CREDIT_ENHANCEMENT -> creditEnhancement(Integer.parseInt(matcher.group(1)));
                    case TRANSFER -> transfer(matcher.group(2), Integer.parseInt(matcher.group(2)), matcher.group(3), matcher.group(4), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
                    case RETAKE -> retake(Integer.parseInt(matcher.group(1)), matcher.group(2));
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

    private void retake(int day, String type) {
    }

    private void transfer(String foodName, int day, String type, String selfName, int fromId, int toId) {
        if (!student.hasFood(day, type))
            throw new IllegalCommandException("This food is not reserved!");
        if (!FoodHandler.isAvailable(foodName, day, type))
            throw new IllegalCommandException("This food isn't available in the chosen time.");
        switch (type) {
            case "breakfast" -> {
                Self.selves.get(selfName).breakfastStudents.get(day).remove(fromId);
                Self.selves.get(selfName).breakfastStudents.get(day).put(toId, foodName);
            }
            case "lunch" -> {
                Self.selves.get(selfName).lunchStudents.get(day).remove(fromId);
                Self.selves.get(selfName).lunchStudents.get(day).put(toId, foodName);
            }
            case "dinner" -> {
                Self.selves.get(selfName).dinnerStudents.get(day).remove(fromId);
                Self.selves.get(selfName).dinnerStudents.get(day).put(toId, foodName);
            }
        }
    }

    private void creditEnhancement(int amount) {
        student.deposit(amount);
    }


    private void showFoodMenu() {

    }


    private void reserve(int day, String type, String foodName, String selfName) {
        if (student.hasFood(day, type))
            throw new IllegalCommandException("You've already reserved your food.");
        if (!FoodHandler.isAvailable(foodName, day, type))
            throw new IllegalCommandException("This food isn't available in the chosen time.");
        int price = 0;
        switch (type) {
            case "breakfast" -> price = FoodHandler.breakfastPrice.get(foodName);
            case "lunch" -> price = FoodHandler.lunchPrice.get(foodName);
            case "dinner" -> price = FoodHandler.dinnerPrice.get(foodName);
        }
        if (student.wallet >= price) {
            Reserve reserve = new Reserve(day, type, foodName, price);
            student.reserve(reserve);
        } else throw new IllegalCommandException(
                "You don't have enough money in your account.");
        Self.selves.get(selfName).reserveFood(day, type, foodName, student.id);
    }
}