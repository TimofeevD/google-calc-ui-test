package org.timofeev.google.calc.test.provider;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeWebDriverProvider implements WebDriverProvider {

    private final WebDriver driver;

    public ChromeWebDriverProvider() {
        Capabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    static {
        System.setProperty(
                "webdriver.chrome.driver",
                Thread.currentThread().getContextClassLoader().getResource("chromedriver.exe").getFile()
        );
    }

    @Override
    public WebDriver getDriver() {
        return this.driver;
    }
}
