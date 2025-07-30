package com.automation.framework.playwright.browser.launcher.factory;

import com.automation.framework.config.ConfigBootstrap;
import com.automation.framework.playwright.browser.Browser;
import com.automation.framework.playwright.browser.launcher.BrowserLauncher;
import com.automation.framework.playwright.browser.launcher.ChromiumLauncher;
import com.automation.framework.playwright.browser.launcher.FirefoxLauncher;
import com.automation.framework.playwright.browser.launcher.WebkitLauncher;
import com.automation.framework.playwright.config.ConfigMapping;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {

    public static Page createPage() {

        Browser browserEnum = getBrowserNaming();
        return new PageFactory.Builder()
                .setBrowser(browserEnum)
                .build();
    }

    private static Browser getBrowserNaming() {

        String browserFromSystem = System.getProperty("browser");
        if (browserFromSystem != null && !browserFromSystem.isBlank()) {
            try {
                return Browser.valueOf(browserFromSystem.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid browser name passed via -Dbrowser: " + browserFromSystem);
            }
        }
        ConfigMapping config = ConfigBootstrap.load(ConfigMapping.class);
        return Browser.valueOf(config.defaultBrowser().toUpperCase());
    }
}
