package com.automation.framework.playwright.browser.launcher.factory;

import com.automation.framework.playwright.browser.Browser;
import com.automation.framework.playwright.browser.PlaywrightManager;
import com.automation.framework.playwright.browser.launcher.BrowserLauncherFactory;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class PageFactory {

    public static class Builder {

        private Browser browser;

        private boolean headless = true;

        public Builder setBrowser(Browser browser) {

            this.browser = browser;
            return this;
        }

        public Builder setHeadless(boolean headless) {

            this.headless = headless;
            return this;
        }

        public Page build() {

            var playwright = PlaywrightManager.getPlaywright();
            var launcher = BrowserLauncherFactory.getLauncher(browser);
            var pwBrowser = launcher.launch(playwright, headless);
            BrowserContext context = pwBrowser.newContext();
            return context.newPage();
        }
    }
}
