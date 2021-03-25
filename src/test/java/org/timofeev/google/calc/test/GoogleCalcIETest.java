package org.timofeev.google.calc.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Will run all 'calculator' tests from the parent class with the IE driver
 */
public class GoogleCalcIETest extends AbstractGoogleCalcTest {
    public GoogleCalcIETest() {
        super(InternetExplorerDriver::new);
        WebDriverManager.iedriver().driverVersion("3.150.1").arch32().setup();
    }
}
