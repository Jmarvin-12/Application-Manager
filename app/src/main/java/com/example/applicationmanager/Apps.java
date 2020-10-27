package com.example.applicationmanager;

public class Apps {

    private AppName appName;
    private String packageName;
    private Double apkSize;
    private Double dataSize;
    private boolean unable;
    private boolean sistem;

    public Apps(){
    }

    public Apps(AppName appName, String packageName, Double apkSize, Double dataSize, boolean unable, boolean sistem) {
        this.appName = appName;
        this.packageName = packageName;
        this.apkSize = apkSize;
        this.dataSize = dataSize;
        this.unable= unable;
        this.sistem= sistem;
    }

    public AppName getAppName() {
        return appName;
    }

    public void setAppName(AppName appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Double getApkSize() {
        return apkSize;
    }

    public void setApkSize(Double apkSize) {
        this.apkSize = apkSize;
    }

    public Double getDataSize() {
        return dataSize;
    }

    public void setDataSize(Double dataSize) {
        this.dataSize = dataSize;
    }

    public boolean isUnable() {
        return unable;
    }

    public void setUnable(boolean unable) {
        this.unable = unable;
    }

    public boolean isSistem() {
        return sistem;
    }

    public void setSistem(boolean sistem) {
        this.sistem = sistem;
    }
}
