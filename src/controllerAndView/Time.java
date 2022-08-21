package controllerAndView;

public class Time {
    public static int day;
    public static boolean holiday = true;
    public static int mealNum;
    public static final String[] MEALS = {"breakfast", "lunch", "dinner"};

    public static void nextMeal() {
        if (mealNum == 2)
            nextDay();
        mealNum = (mealNum + 1) % 3;
    }

    private static void nextDay() {
        day += 1;
        holiday = day % 7 == 0;
    }

    private void hall() {

    }

    public static String currentMeal() {
        return MEALS[mealNum];
    }
}