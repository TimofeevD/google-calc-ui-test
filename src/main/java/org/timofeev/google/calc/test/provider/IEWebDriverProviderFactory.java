package org.timofeev.google.calc.test.provider;

public class IEWebDriverProviderFactory implements WebDriverProviderFactory {
    @Override
    public WebDriverProvider createWebDriverProvider() {
        return new IEWebDriverProvider();
    }
}
