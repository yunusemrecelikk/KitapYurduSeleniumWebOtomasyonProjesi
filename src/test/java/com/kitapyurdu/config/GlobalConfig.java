package com.kitapyurdu.config;

import java.util.ArrayList;
import java.util.List;

public class GlobalConfig {
    private final String websiteUrl = "https://www.kitapyurdu.com/";
    public String getWebsiteUrl() {
        return this.websiteUrl;
    }
    public static List<Integer> favProducts = new ArrayList<>();

}
