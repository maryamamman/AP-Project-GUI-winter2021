package controller.distributor;

import controller.Controller;
import controller.LoginController;
import controller.UserController;
import exceptions.BackException;
import exceptions.ExitException;
import exceptions.IllegalCommandException;
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
                String input = getCommand(null);
                DistributorCommand distributorCommand = DistributorCommand.findCommand(input);
                Matcher matcher = DistributorCommand.getMatcher(input, distributorCommand);
                if (matcher.find())
                    switch (distributorCommand) {
                        case CHECK_DEMAND -> checkDemand();
                        case HAND_OVER -> handOver();
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

    private void handOver() {
    }

    private void checkDemand() {
    }
}
