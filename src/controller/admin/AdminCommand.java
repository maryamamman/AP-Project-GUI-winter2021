package controller.admin;

import exceptions.IllegalCommandException;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum AdminCommand {

    NEW_STUDENT("new student ([a-zA-Z0-9_]+) ([a-zA-Z0-9_]+) (\\w+) (\\d+) (yes|no)"),
    NEW_DISTRIBUTOR("new distributor ([a-zA-Z0-9_]+) ([a-zA-Z0-9_]+) (\\w+) ([a-zA-Z0-9_]+)"),
    NEW_ADMIN("new admin ([a-zA-Z0-9_]+) ([a-zA-Z0-9_]+) (\\w+)"),
    NEW_FOOD("new food (\\w+) (\\d+) (dinner|breakfast|lunch)"),
    SET_FOOD("set food (\\w+) (\\w+) (\\d+) (dinner|breakfast|lunch)"),
    RESERVE_REPORT("reserve report (\\d+)"),
    REPORT("report"),
    NEXT_MEAL("next meal");

    private final Pattern pattern;
    private final String regex;

    AdminCommand(String regex) {
        pattern = Pattern.compile(regex);
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, AdminCommand command) {
        return command.pattern.matcher(input.toLowerCase(Locale.ROOT));
    }

    public static AdminCommand findCommand(String input) {
        for (AdminCommand command : AdminCommand.values()) {
            if (Pattern.matches(command.regex, input))
                return command;
        }
        throw new IllegalCommandException();
    }
}
