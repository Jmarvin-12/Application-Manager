package com.example.applicationmanager;

public interface IApps {

    Apps getAppById(int id);
    void deleteAppById(int id);
    int appSize();

    boolean status(boolean b);

}
