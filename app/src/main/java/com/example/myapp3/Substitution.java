package com.example.myapp3;

import java.util.ArrayList;
import java.util.List;

public class Substitution {
    private static final List<String> days;
    private static final List<String> times;

    static {
        days = new ArrayList<>();
        days.add("ПОНЕДЕЛЬНИК");
        days.add("ВТОРНИК");
        days.add("СРЕДА");
        days.add("ЧЕТВЕРГ");
        days.add("ПЯТНИЦА");
        days.add("СУББОТА");
        times = new ArrayList<>();
        times.add("8.00-9.35");
        times.add("9.50-11.25");
        times.add("11.55-13.30");
        times.add("13.45-15.20");
        times.add("15.50-17.25");
        times.add("17.50-19.15");
    }

    public static String getDay(int pos) {
        return days.get(pos);
    }

    public static String getTime(int pos) {
        return times.get(pos);
    }
}
