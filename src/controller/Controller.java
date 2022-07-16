package controller;

import exceptions.BackException;
import exceptions.ExitException;

import java.util.Scanner;

public abstract class Controller {

    protected static Scanner INPUT = new Scanner(System.in);

    public abstract Controller run();

    protected String getCommand(String string) {
        if (string != null)
            System.out.println(string);
        System.out.print("> ");
        String response = INPUT.nextLine().toLowerCase().replaceAll("( )+", " ").trim();
        switch (response) {
            case "exit" -> throw new ExitException();
            case "back" -> throw new BackException();
            default -> {
                return response;
            }
        }

    }
}
