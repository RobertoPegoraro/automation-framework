package com.automation.framework.playwright.test;

import org.testng.annotations.Test;

import com.automation.framework.playwright.base.BasePage;
import com.microsoft.playwright.Page;

public class SampleTest extends BasePage {

    private final Page page = getPage();

    @Test
    public void testHelloWorld() {

        page.navigate("https://www.google.com");
    }

}
