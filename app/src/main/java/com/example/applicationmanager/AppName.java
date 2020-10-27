package com.example.applicationmanager;

public enum AppName {
    YOUTUBE ("Youtube", R.drawable.icons8_youtube),
    GOOGLEPLAY ("Google Play",  R.drawable.icons8_google_play),
    CHROME ("Chrome",  R.drawable.icons8_chrome),
    FACEBOOK ("Facebook", R.drawable.baseline_facebook_24),
    TELEFONO ("Teléfono", R.drawable.twotone_extension_24);

    private final String appName;
    private final int recurso;

    AppName(String appName, int recurso) {
        this.appName= appName;
        this.recurso= recurso;
    }

    public String getAppName() {
        return appName;
    }

    public int getRecurso() {
        return recurso;
    }
}
