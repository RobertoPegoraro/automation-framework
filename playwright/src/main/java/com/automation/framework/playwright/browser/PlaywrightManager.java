package com.automation.framework.playwright.browser;

import com.microsoft.playwright.Playwright;

public class PlaywrightManager {

    private static Playwright playwright;

    public static Playwright getPlaywright() {
        if (playwright == null) {
            playwright = Playwright.create();
        }
        return playwright;
    }

    public static void close() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
