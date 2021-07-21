package com.example.myapp3.Util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp3.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Util {

    public static String PERSONAL_FILE = "Personal";
    public static String LAST_QUERY = "Last";

//    public static void activateHint(TextView hint, int rText, int delay) {
//        hint.setText(rText);
//        hint.setVisibility(View.VISIBLE);
//        hint.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                hint.setVisibility(View.INVISIBLE);
//            }
//        }, delay);
//    }
//
//    public static void activateHint(TextView hint, String text, int color, int delay) {
//        Runnable post = new Runnable() {
//            @Override
//            public void run() {
//                hint.setVisibility(View.INVISIBLE);
//            }
//        };
//        hint.removeCallbacks(post);
//        hint.setText(text);
//        hint.setTextColor(color);
//        hint.setVisibility(View.VISIBLE);
//        hint.postDelayed(post, delay);
//    }

    public static String checkText(Context context, String string, int patternId, int symbolsId, int minLength, int maxLength) {
        if (string.length() < minLength)
            return context.getString(R.string.string_too_short, minLength);
        if (string.length() > maxLength)
            return context.getString(R.string.string_too_long, maxLength);
        Scanner scanner = new Scanner(string);
        String check = scanner.findInLine(context.getString(patternId));
        if (check != null)
            return context.getString(R.string.invalid_symbols, context.getString(symbolsId));
        return context.getString(R.string.OK);
    }

    public static boolean save(Context context, Serializable object) {
        return save(context, object, "");
    }

    public static boolean save(Context context, Serializable object, String name) {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(context.getCacheDir().getAbsolutePath() + object.getClass().getSimpleName() + name));
            stream.writeObject(object);
            stream.close();
            Toast.makeText(context, object.getClass().getSimpleName() + name + " saving successfully", Toast.LENGTH_SHORT).show();
            return true;
        } catch (IOException e) {
            Log.e("save(" + object.getClass().getSimpleName() + ")", e.getMessage());
            Toast.makeText(context, object.getClass().getSimpleName() + name + " saving error", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static Object open(Context context, String objectName) {
        return open(context, objectName, "");
    }

    public static Object open(Context context, String objectName, String name) {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(context.getCacheDir().getAbsolutePath() + objectName + name));
            Object object = stream.readObject();
            stream.close();
            Toast.makeText(context, objectName + name + " opening successfully", Toast.LENGTH_SHORT).show();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            Log.e("open(" + objectName + name + ")", e.getMessage());
            Toast.makeText(context, objectName + name + " opening error", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public static boolean delete(Context context, String objectName) {
        return delete(context, objectName, "");
    }

    public static boolean delete(Context context, String objectName, String name) {
        File file = new File(context.getCacheDir().getAbsolutePath() + objectName + name);
        if (file.delete()) {
            Toast.makeText(context, objectName + name + " opening successfully", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Log.e("delete(" + objectName + name + ")", "error");
            Toast.makeText(context, objectName + name + " deleting error", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
