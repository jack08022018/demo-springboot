package com.demo.service.staticData;

public final class WelcomeUtil {

    public static String generateWelcome(String name) {
        return String.format("Welcome %s", name);
    }

    private WelcomeUtil() {}
}
