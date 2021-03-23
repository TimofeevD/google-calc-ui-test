package org.timofeev.google.calc.test.provider;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IEWebDriverProvider implements WebDriverProvider {

    private final WebDriver driver;

    public IEWebDriverProvider() {
        Capabilities capabilities = DesiredCapabilities.internetExplorer();
        driver = new InternetExplorerDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    static {
        System.setProperty(
                "webdriver.ie.driver",
                Thread.currentThread().getContextClassLoader().getResource("IEDriverServer.exe").getFile()
        );
    }

    @Override
    public WebDriver getDriver() {
        return this.driver;
    }
}
