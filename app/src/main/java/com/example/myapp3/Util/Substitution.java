package com.example.myapp3.Util;

import android.content.Context;

import com.example.myapp3.R;

import java.util.ArrayList;
import java.util.List;

public class Substitution {
    private static final List<String> days = new ArrayList<>(6);
    private static final List<String> times = new ArrayList<>();
    private static final List<String> contracts = new ArrayList<>(3);
    private static final List<String> months = new ArrayList<>(12);

    public static void init(Context context) {
        months.add(context.getString(R.string.jan));
        months.add(context.getString(R.string.feb));
        months.add(context.getString(R.string.mar));
        months.add(context.getString(R.string.apr));
        months.add(context.getString(R.string.may));
        months.add(context.getString(R.string.jun));
        months.add(context.getString(R.string.jul));
        months.add(context.getString(R.string.aug));
        months.add(context.getString(R.string.sep));
        months.add(context.getString(R.string.oct));
        months.add(context.getString(R.string.nov));
        months.add(context.getString(R.string.dec));

        days.add(context.getString(R.string.mon));
        days.add(context.getString(R.string.tue));
        days.add(context.getString(R.string.wed));
        days.add(context.getString(R.string.thu));
        days.add(context.getString(R.string.wed));
        days.add(context.getString(R.string.sat));

        times.add(context.getString(R.string.class_1));
        times.add(context.getString(R.string.class_2));
        times.add(context.getString(R.string.class_3));
        times.add(context.getString(R.string.class_4));
        times.add(context.getString(R.string.class_5));
        times.add(context.getString(R.string.class_6));

        contracts.add(context.getString(R.string.degree_bachelor));
        contracts.add(context.getString(R.string.degree_specialist));
        contracts.add(context.getString(R.string.degree_masters));
    }

    public static String getMonth(int pos) {
        return months.get(pos);
    }

    public static String getDay(int pos) {
        return days.get(pos);
    }

    public static String getTime(int pos) {
        return times.get(pos);
    }

    public static String getContractName(int pos) {
        return contracts.get(pos);
    }

    public static int getContractId(String name) {
        return contracts.indexOf(name);
    }

}