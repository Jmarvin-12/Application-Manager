package com.example.applicationmanager;

import java.util.ArrayList;

public class AppsVector implements IApps {

    protected ArrayList<Apps> appsList= getAllApps();

    public static boolean status=true;

    public AppsVector(){
        appsList= getAllApps();
    }

    @Override
    public Apps getAppById(int id) {
        return appsList.get(id);
    }

    @Override
    public void deleteAppById(int id) {
        appsList.remove(id);
    }

    @Override
    public int appSize() {
        return appsList.size();
    }

    @Override
    public boolean status(boolean status) {
        return this.status=status;
    }

    public static ArrayList<Apps> getAllApps(){
        ArrayList<Apps> apps= new ArrayList<Apps>();

        apps.add(new Apps(AppName.YOUTUBE, "com.google.youtube", 38.5, 127.8, status, false));
        apps.add(new Apps(AppName.GOOGLEPLAY, "com.google.playstore", 52.5, 228.4, status, true));
        apps.add(new Apps(AppName.CHROME, "com.google.chrome", 65.3, 205.1, status, false));
        apps.add(new Apps(AppName.FACEBOOK, "com.facebook.orca", 215.6, 302.5, status, false));
        apps.add(new Apps(AppName.TELEFONO, "com.phone.simple", 705.8, 960.52, status, true));

        return apps;
    }


}
