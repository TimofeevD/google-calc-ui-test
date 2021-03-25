package org.timofeev.google.calc.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Will run all 'calculator' tests from the parent class with the Google Chrome driver
 */
public class GoogleCalcChromeTest extends AbstractGoogleCalcTest {
    public GoogleCalcChromeTest() {
        super(ChromeDriver::new);
        WebDriverManager.chromiumdriver().setup();
    }
}
