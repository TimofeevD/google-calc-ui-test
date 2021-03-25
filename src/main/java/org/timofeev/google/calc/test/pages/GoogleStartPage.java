package org.timofeev.google.calc.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.timofeev.google.calc.test.pages.GoogleCalcPage.SEARCH_PHRASE;

public class GoogleStartPage extends BasePage {

    private static final String URL = "http://www.google.com";

    @FindBy(name = "q")
    private WebElement searchElement;

    public GoogleStartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void search(String text) {
        searchElement.sendKeys(text);
        searchElement.submit();
    }

    public GoogleCalcPage toCalculator() {
        search(SEARCH_PHRASE);
        return new GoogleCalcPage(driver);
    }

    public void navigate() {
        driver.navigate().to(URL);
    }
}
