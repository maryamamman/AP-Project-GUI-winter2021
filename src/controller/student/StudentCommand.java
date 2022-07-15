package controller.student;

import exceptions.IllegalCommandException;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StudentCommand {

    SHOW_FOOD_MENU("show food menu"),
    RETAKE("retake (\\d+) (dinner|breakfast|lunch)"),
    TRANSFER("transfer (\\d+)"),
    CREDIT_ENHANCEMENT("credit enhancement (\\d+)"),
    RESERVE("reserve (\\d+) (dinner|breakfast|lunch) (\\w+)");

    private final Pattern pattern;
    private final String regex;
    StudentCommand(String regex) {
        pattern = Pattern.compile(regex);
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, StudentCommand command) {
        return command.pattern.matcher(input.toLowerCase(Locale.ROOT));
    }

    public static StudentCommand findCommand(String input) {
        for (StudentCommand command : StudentCommand.values()) {
            if(Pattern.matches(command.regex, input))
                return command;
        }
        throw new IllegalCommandException();
    }
}
