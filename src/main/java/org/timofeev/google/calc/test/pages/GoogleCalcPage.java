package org.timofeev.google.calc.test.pages;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.timofeev.google.calc.test.provider.WebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCalcPage extends BasePage {

    public static final String SEARCH_PHRASE = "Calculator";

    @FindBy(xpath = "//div[text()='1']")
    public WebElement one;
    @FindBy(xpath = "//div[text()='2']")
    public WebElement two;
    @FindBy(xpath = "//div[text()='3']")
    public WebElement three;
    @FindBy(xpath = "//div[text()='4']")
    public WebElement four;
    @FindBy(xpath = "//div[text()='5']")
    public WebElement five;
    @FindBy(xpath = "//div[text()='6']")
    public WebElement six;
    @FindBy(xpath = "//div[text()='7']")
    public WebElement seven;
    @FindBy(xpath = "//div[text()='8']")
    public WebElement eight;
    @FindBy(xpath = "//div[text()='9']")
    public WebElement nine;
    @FindBy(xpath = "//div[text()='0']")
    public WebElement zero;

    @FindBy(xpath = "//div[text()='+']")
    public WebElement plus;
    @FindBy(xpath = "//div[text()='−']")
    public WebElement minus;
    @FindBy(xpath = "//div[text()='÷']")
    public WebElement divide;
    @FindBy(xpath = "//div[text()='×']")
    public WebElement multiply;

    @FindBy(xpath = "//div[text()='(']")
    public WebElement leftBracket;
    @FindBy(xpath = "//div[text()=')']")
    public WebElement rightBracket;

    @FindBy(xpath = "//div[text()=\"sin\"]")
    public WebElement sin;

    @FindBy(xpath = "//div[text()='=']")
    public static WebElement equal;

    @FindBy(xpath = "//div[text()=\"CE\"]")
    public static WebElement ce;

    @FindBy(xpath = "//div[text()=\"AC\"]")
    public static WebElement ac;

    @FindBy(id = "cwos")
    public static WebElement result;
    @FindBy(className = "vUGUtc")
    public static WebElement formula;

    private final Map<String, Runnable> elementToClickFunctionMap = buildMap();

    private Map<String, Runnable> buildMap() {
        Map<String, Runnable> elementToClickFunctionMap = new HashMap<>();
        elementToClickFunctionMap.put("1", () -> one.click());
        elementToClickFunctionMap.put("2", () -> two.click());
        elementToClickFunctionMap.put("3", () -> three.click());
        elementToClickFunctionMap.put("4", () -> four.click());
        elementToClickFunctionMap.put("5", () -> five.click());
        elementToClickFunctionMap.put("6", () -> six.click());
        elementToClickFunctionMap.put("7", () -> seven.click());
        elementToClickFunctionMap.put("8", () -> eight.click());
        elementToClickFunctionMap.put("9", () -> nine.click());
        elementToClickFunctionMap.put("0", () -> zero.click());
        elementToClickFunctionMap.put("+", () -> plus.click());
        elementToClickFunctionMap.put("-", () -> minus.click());
        elementToClickFunctionMap.put("÷", () -> divide.click());
        elementToClickFunctionMap.put("/", () -> divide.click());
        elementToClickFunctionMap.put("×", () -> multiply.click());
        elementToClickFunctionMap.put("*", () -> multiply.click());
        elementToClickFunctionMap.put("(", () -> leftBracket.click());
        elementToClickFunctionMap.put(")", () -> rightBracket.click());
        elementToClickFunctionMap.put("s", () -> sin.click());
        return elementToClickFunctionMap;
    }

    public GoogleCalcPage(WebDriverProvider driverProvider) {
        super(driverProvider);
        PageFactory.initElements(driverProvider.getDriver(), this);
    }

    public void enterExpression(String expression) {
        String modExpression = expression.replace("sin", "s"); //todo also replace cos, tg ... etc
        Stream.of(modExpression.split(""))
                .filter(value -> !" ".equals(value))
                .forEach(value -> elementToClickFunctionMap.get(value).run());
    }

    public void clear() {
        if (!clickToAC()) {
            ce.click();
        }
    }

    public String calculate() {
        equal.click();
        return result.getText();
    }

    public String getFormula() {
        return formula.getText().replace('=', ' ');
    }

    private boolean clickToAC() {
        try {
            ac.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

