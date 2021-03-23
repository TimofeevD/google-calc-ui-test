package org.timofeev.google.calc.test.pages;

import org.timofeev.google.calc.test.provider.WebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.timofeev.google.calc.test.pages.GoogleCalcPage.SEARCH_PHRASE;

public class GoogleStartPage extends BasePage {

    public static final String URL = "http://www.google.com";

    @FindBy(name = "q")
    private WebElement searchElement;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    public GoogleStartPage(WebDriverProvider driverProvider) {
        super(driverProvider);
        PageFactory.initElements(driverProvider.getDriver(), this);
    }

    public void search(String text) {
        searchElement.sendKeys(text);
        searchButton.click();
    }

    public GoogleCalcPage toCalculator() {
        search(SEARCH_PHRASE);
        return new GoogleCalcPage(driverProvider);
    }

    public void navigate() {
        driverProvider.getDriver().navigate().to(URL);
    }
}
