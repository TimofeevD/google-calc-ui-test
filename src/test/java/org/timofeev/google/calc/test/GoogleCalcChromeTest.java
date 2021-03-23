package org.timofeev.google.calc.test;

import org.timofeev.google.calc.test.provider.ChromeWebDriverProviderFactory;

/**
 * Will run all 'calculator' tests from the parent class with the Google Chrome driver
 */
public class GoogleCalcChromeTest extends AbstractGoogleCalcTest {
    public GoogleCalcChromeTest() {
        super(new ChromeWebDriverProviderFactory());
    }
}
