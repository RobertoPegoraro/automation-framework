package com.automation.framework.playwright.browser.launcher;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

public interface BrowserLauncher {

    Browser launch(Playwright playwright);
}
