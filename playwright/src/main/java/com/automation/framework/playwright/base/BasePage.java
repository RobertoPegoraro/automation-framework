package com.automation.framework.playwright.base;

import com.automation.framework.playwright.browser.launcher.factory.BrowserFactory;
import com.microsoft.playwright.Page;

public abstract class BasePage {

    protected static Page page = BrowserFactory.createPage();

    public Page getPage() {

        return page;
    }
}
