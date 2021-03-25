package org.timofeev.google.calc.test.pages;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCalcPage extends BasePage {

    public static final String SEARCH_PHRASE = "Calculator";

    @FindBy(xpath = "//div[text()='1']")
    private WebElement one;
    @FindBy(xpath = "//div[text()='2']")
    private WebElement two;
    @FindBy(xpath = "//div[text()='3']")
    private WebElement three;
    @FindBy(xpath = "//div[text()='4']")
    private WebElement four;
    @FindBy(xpath = "//div[text()='5']")
    private WebElement five;
    @FindBy(xpath = "//div[text()='6']")
    private WebElement six;
    @FindBy(xpath = "//div[text()='7']")
    private WebElement seven;
    @FindBy(xpath = "//div[text()='8']")
    private WebElement eight;
    @FindBy(xpath = "//div[text()='9']")
    private WebElement nine;
    @FindBy(xpath = "//div[text()='0']")
    private WebElement zero;

    @FindBy(xpath = "//div[text()='+']")
    private WebElement plus;
    @FindBy(xpath = "//div[text()='−']")
    private WebElement minus;
    @FindBy(xpath = "//div[text()='÷']")
    private WebElement divide;
    @FindBy(xpath = "//div[text()='×']")
    private WebElement multiply;

    @FindBy(xpath = "//div[text()='(']")
    private WebElement leftBracket;
    @FindBy(xpath = "//div[text()=')']")
    private WebElement rightBracket;

    @FindBy(xpath = "//div[text()=\"sin\"]")
    private WebElement sin;

    @FindBy(xpath = "//div[text()='=']")
    private static WebElement equal;

    @FindBy(xpath = "//div[text()=\"CE\"]")
    private static WebElement ce;

    @FindBy(xpath = "//div[text()=\"AC\"]")
    private static WebElement ac;

    @FindBy(id = "cwos")
    private static WebElement result;
    @FindBy(className = "vUGUtc")
    private static WebElement formula;

    private final Map<String, Runnable> clickMethodByValue = Map.ofEntries(
            Map.entry("1", () -> one.click()),
            Map.entry("2", () -> two.click()),
            Map.entry("3", () -> three.click()),
            Map.entry("4", () -> four.click()),
            Map.entry("5", () -> five.click()),
            Map.entry("6", () -> six.click()),
            Map.entry("7", () -> seven.click()),
            Map.entry("8", () -> eight.click()),
            Map.entry("9", () -> nine.click()),
            Map.entry("0", () -> zero.click()),
            Map.entry("+", () -> plus.click()),
            Map.entry("-", () -> minus.click()),
            Map.entry("÷", () -> divide.click()),
            Map.entry("/", () -> divide.click()),
            Map.entry("×", () -> multiply.click()),
            Map.entry("*", () -> multiply.click()),
            Map.entry("(", () -> leftBracket.click()),
            Map.entry(")", () -> rightBracket.click()),
            Map.entry("s", () -> sin.click()));

    public GoogleCalcPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterExpression(String expression) {
        String modExpression = expression.replace("sin", "s"); //todo also replace cos, tg ... etc
        Stream.of(modExpression.split(""))
                .filter(value -> !" ".equals(value))
                .forEach(value -> clickMethodByValue.get(value).run());
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

