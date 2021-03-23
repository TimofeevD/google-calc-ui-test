package org.timofeev.google.calc.test.pages;

import org.timofeev.google.calc.test.provider.WebDriverProvider;

public abstract class BasePage {

    protected WebDriverProvider driverProvider;

    protected BasePage(WebDriverProvider driverProvider) {
        this.driverProvider = driverProvider;
    }
}
