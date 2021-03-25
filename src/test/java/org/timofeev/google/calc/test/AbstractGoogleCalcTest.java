package org.timofeev.google.calc.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.timofeev.google.calc.test.pages.GoogleCalcPage;
import org.timofeev.google.calc.test.pages.GoogleStartPage;

/**
 * Basic suite for testing google calculator.
 * Inherit from this class and pass the required factory to the constructor to run tests with different web drivers
 */
public abstract class AbstractGoogleCalcTest {

    protected WebDriver driver;

    private final Supplier<WebDriver> driverSupplier;

    private GoogleCalcPage googleCalcPage;

    public AbstractGoogleCalcTest(Supplier<WebDriver> driverSupplier) {
        this.driverSupplier = driverSupplier;
    }

    @Before
    public void beforeTest() {
        driver = driverSupplier.get();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        GoogleStartPage googleStartPage = new GoogleStartPage(driver);

        googleStartPage.navigate();
        googleCalcPage = googleStartPage.toCalculator();
    }

    @After
    public void afterTests() {
        driver.quit();
    }

    /**
     * Case 1. Checking operations with integers
     *
     * Precondition: Open browser
     * • Open the page http://google.com
     * • In the search box, enter the word "Calculator"
     * • Click on the search button
     * • In the opened calculator, calculate the result of the expression
     * (1 + 2) × 3 - 40 ÷ 5
     * Expected Result:
     * • the memory line (line above the result) displays the previously entered
     * formula "(1 + 2) × 3 - 40 ÷ 5 ="
     * • "1" is displayed in the result line
     * Postcondition: Close browser
     */
    @Test
    public void validExpressionTest() {
        String expression = "(1 + 2) × 3 - 40 ÷ 5";
        testExpression(expression, "1", expression);
    }

    /**
     * Case 2. Checking division by zero
     *
     * Precondition: Open browser
     * • Open the page http://google.com
     * • In the search box, enter the word "Calculator"
     * • Click on the search button
     * • In the opened calculator, calculate the result of the expression 6 ÷ 0
     * Expected Result:
     * • the memory line (line above the result) displays the previously entered
     * formula "6 ÷ 0 ="
     * • "Infinity" is displayed in the result line
     * Postcondition: Close browser
     */
    @Test
    public void divisionByZeroTest() {
        String expression = "6 ÷ 0";
        testExpression(expression, "Infinity", expression);
    }

    /**
     * Case 3. Checking an error in the absence of a value
     *
     * Precondition: Open browser
     * • Open the page http://google.com
     * • In the search box, enter the word "Calculator"
     * • Click on the search button
     * • In the opened calculator, calculate the result of the expression sin ()
     * (the second parenthesis is not entered)
     * Expected Result:
     * • the memory line (line above the result) displays the previously entered
     * formula "sin () ="
     * • "Error" is displayed in the result line
     * Postcondition: Close browser
     */
    @Test
    public void emptySinFunctionTest() {
        String expression = "sin";
        testExpression(expression, "Error", expression + "()");
    }

    private void testExpression(String expression, String expectedResult, String expectedFormula) {
        googleCalcPage.clear();
        googleCalcPage.enterExpression(expression);
        String result = googleCalcPage.calculate();

        Assert.assertEquals(expectedResult, result);
        Assert.assertEquals(excludeSeparatorsFromString(expectedFormula), excludeSeparatorsFromString(googleCalcPage.getFormula()));
    }

    private String excludeSeparatorsFromString(String str) {
        return str.replaceAll("\\s", "");
    }
}
