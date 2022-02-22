package com.kitapyurdu.driver;

public enum BrowserOptions {
    DISABLENOTIFICATIONS("--disable-notification"),
    DISABLEGPU("--disable-gpu"),
    NOSANDBOX("--no-sandbox"),
    DISABLEPLUGINS("disable-plugins"),
    DISABLEPOPUPBLOCKING("disable-popup-blocking"),
    IGNORECERTIFICATEERRORS("ignore-certificate-errors"),
    DISABLETRANSLATE("disable-translate");

    private String options;
    BrowserOptions(String s) {
        this.options = s;
    }

    public String getOptions() {
        return options;
    }
}
