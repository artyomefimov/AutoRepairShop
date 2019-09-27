package com.artyomefimov;

public class Utils {
    public static String timeFormatter(String timeAsString) {
        if (!timeAsString.matches("[0-9][0-9]:[0-9][0-9]:[0-9][0-9]"))
            timeAsString = timeAsString + ":00";
        return timeAsString;
    }
}
