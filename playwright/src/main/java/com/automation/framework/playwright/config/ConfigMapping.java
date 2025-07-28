package com.automation.framework.playwright.config;

import io.smallrye.config.WithName;

public interface ConfigMapping {

    @WithName("default-browser")
    String defaultBrowser();
}
