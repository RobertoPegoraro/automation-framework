package com.automation.framework.playwright.browser.launcher;

import com.automation.framework.playwright.browser.Browser;

public class BrowserLauncherFactory {

    public static BrowserLauncher getLauncher(Browser browser) {
        return switch (browser) {
            case FIREFOX -> new FirefoxLauncher();
            case WEBKIT -> new WebkitLauncher();
            case CHROME -> new ChromiumLauncher();
        };
    }
}
