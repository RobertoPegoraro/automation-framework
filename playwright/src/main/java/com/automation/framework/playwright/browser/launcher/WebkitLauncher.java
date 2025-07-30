package com.automation.framework.playwright.browser.launcher;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class WebkitLauncher implements BrowserLauncher {

    @Override
    public Browser launch(Playwright playwright, boolean headless) {

        return playwright.webkit().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(headless)
        );
    }
}
