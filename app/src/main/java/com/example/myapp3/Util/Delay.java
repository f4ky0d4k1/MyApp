package com.example.myapp3.Util;

import android.os.Handler;

public class Delay extends Thread {

    Handler handler;
    int delay;

    Delay (Handler handler, int delay) {
        this.handler = handler;
        this.delay = delay;
    }

    @Override
    public void run() {
        delay(delay);
        handler.sendEmptyMessage(0);
    }
    //Задержка в мс
    private void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
