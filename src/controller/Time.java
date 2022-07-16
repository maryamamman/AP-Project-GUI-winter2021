package controller;

public abstract class Time {
    public static int day = 0;
    public static String currentMeal;
    public static int mealNum = 0;
    public final String[] meals = {"breakfast", "lunch", "dinner"};

    public void nextMeal() {
        if (mealNum < 3) mealNum += 1;
        else {
            mealNum = 0;
            day += 1;
        }
        currentMeal = meals[mealNum];
    }

}
