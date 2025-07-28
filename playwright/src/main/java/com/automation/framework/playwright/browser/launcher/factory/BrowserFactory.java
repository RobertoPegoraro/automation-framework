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

        Playwright playwright = Playwright.create();
        Browser browserEnum = getBrowserNaming();
        BrowserLauncher launcher = switch (browserEnum) {
            case FIREFOX -> new FirefoxLauncher();
            case WEBKIT -> new WebkitLauncher();
            case CHROME -> new ChromiumLauncher();
        };

        com.microsoft.playwright.Browser browser = launcher.launch(playwright);
        BrowserContext context = browser.newContext();
        return context.newPage();
    }

    private static Browser getBrowserNaming() {

        //Allow browser via -Dbrowser or via application.properties
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
