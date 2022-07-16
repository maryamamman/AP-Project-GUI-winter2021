package controller.distributor;

import controller.Controller;
import controller.LoginController;
import controller.Time;
import controller.UserController;
import exceptions.BackException;
import exceptions.ExitException;
import exceptions.IllegalCommandException;
import model.self.Self;
import model.user.Distributor;

import java.util.regex.Matcher;

public class DistributorController extends UserController {
    Distributor distributor;

    public DistributorController(Distributor distributor) {
        this.distributor = distributor;
    }

    @Override
    public Controller run() {
        Controller controller = this;
        try {
            String input = getCommand("distributor command");
            DistributorCommand distributorCommand = DistributorCommand.findCommand(input);
            Matcher matcher = DistributorCommand.getMatcher(input, distributorCommand);
            if (matcher.find())
                if (distributorCommand == DistributorCommand.CHECK_DEMAND) {
                    checkDemand(Integer.parseInt(matcher.group(1)));
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

    private void checkDemand(int id) {
        switch (Time.currentMeal()){
            case "breakfast" -> System.out.println(Self.selves.get(distributor.selfName).breakfastStudents.get(Time.day).get(id));
            case "lunch" -> System.out.println(Self.selves.get(distributor.selfName).lunchStudents.get(Time.day).get(id));
            case "dinner" -> System.out.println(Self.selves.get(distributor.selfName).dinnerStudents.get(Time.day).get(id));
        }
    }
}
