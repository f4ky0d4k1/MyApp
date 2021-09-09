package ru.sfedu.schedule.network;

import android.app.Application;

public class App extends Application {
    private NetworkService networkService;
    @Override
    public void onCreate() {
        super.onCreate();
        networkService = new NetworkService();
    }
    public NetworkService getNetworkService() {
        return networkService;
    }
}