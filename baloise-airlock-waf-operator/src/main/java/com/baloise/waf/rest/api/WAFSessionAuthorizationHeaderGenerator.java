package com.baloise.waf.rest.api;

import org.eclipse.microprofile.config.ConfigProvider;

public class WAFSessionAuthorizationHeaderGenerator {

    private static String API_KEY_PROPERTY = "airlock.waf.apikey.write";

    public static String generateWAFAuthorizationHeader() {
        String apiKey = ConfigProvider.getConfig().getValue(API_KEY_PROPERTY, String.class);
        return "Bearer " + apiKey;
    }
}
