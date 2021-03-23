package org.timofeev.google.calc.test;

import org.timofeev.google.calc.test.pages.GoogleCalcPage;
import org.timofeev.google.calc.test.pages.GoogleStartPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.timofeev.google.calc.test.provider.WebDriverProvider;
import org.timofeev.google.calc.test.provider.WebDriverProviderFactory;

/**
 * Basic suite for testing google calculator.
 * Inherit from this class and pass the required factory to the constructor to run tests with different web drivers
 */
public abstract class AbstractGoogleCalcTest {

    private final WebDriverProviderFactory providerFactory;

    private WebDriverProvider driverProvider;
    private GoogleCalcPage googleCalcPage;

    public AbstractGoogleCalcTest(WebDriverProviderFactory providerFactory) {
        this.providerFactory = providerFactory;
    }

    @Before
    public void beforeTest() {
        driverProvider = providerFactory.createWebDriverProvider();
        GoogleStartPage googleStartPage = new GoogleStartPage(driverProvider);

        googleStartPage.navigate();
        googleCalcPage = googleStartPage.toCalculator();
    }

    @After
    public void afterTests() {
        driverProvider.getDriver().quit();
    }

    /**
     * Предусловие: Открыть браузер
     * • Открыть страницу http://google.com
     * • В поисковую строку ввести слово “Калькулятор”
     * • Нажать на кнопку поиска
     * • В открывшемся калькуляторе посчитать результат выражения
     * (1 + 2) × 3 - 40 ÷ 5
     * Ожидаемый результат:
     * • в строке памяти (строка над результатом) отображается ранее введенная
     * формула «(1 + 2) × 3 - 40 ÷ 5 =»
     * • в строке результата отображается «1»
     * Постусловие: Закрыть браузер
     */
    @Test
    public void validExpressionTest() {
        String expression = "(1 + 2) × 3 - 40 ÷ 5";
        testExpression(expression, "1", expression);
    }

    /**
     * Предусловие: Открыть браузер
     * • Открыть страницу http://google.com
     * • В поисковую строку ввести слово “Калькулятор”
     * • Нажать на кнопку поиска
     * • В открывшемся калькуляторе посчитать результат выражения 6 ÷ 0
     * Ожидаемый результат:
     * • в строке памяти (строка над результатом) отображается ранее введенная
     * формула «6 ÷ 0 =»
     * • в строке результата отображается «Infinity»
     * Постусловие: Закрыть браузер
     */
    @Test
    public void divisionByZeroTest() {
        String expression = "6 ÷ 0";
        testExpression(expression, "Infinity", expression);
    }

    /**
     * Предусловие: Открыть браузер
     * • Открыть страницу http://google.com
     * • В поисковую строку ввести слово “Калькулятор”
     * • Нажать на кнопку поиска
     * • В открывшемся калькуляторе посчитать результат выражения sin()
     * (вторая скобка не вводится)
     * Ожидаемый результат:
     * • в строке памяти (строка над результатом) отображается ранее введенная
     * формула «sin() =»
     * • в строке результата отображается «Error»
     * Постусловие: Закрыть браузер
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
