package org.timofeev.google.calc.test.provider;

public class ChromeWebDriverProviderFactory implements WebDriverProviderFactory {
    @Override
    public WebDriverProvider createWebDriverProvider() {
        return new ChromeWebDriverProvider();
    }
}
