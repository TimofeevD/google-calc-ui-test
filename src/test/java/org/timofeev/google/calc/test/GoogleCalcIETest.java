package org.timofeev.google.calc.test;

import org.timofeev.google.calc.test.provider.IEWebDriverProviderFactory;

/**
 * Will run all 'calculator' tests from the parent class with the IE driver
 */
public class GoogleCalcIETest extends AbstractGoogleCalcTest {
    public GoogleCalcIETest() {
        super(new IEWebDriverProviderFactory());
    }
}
