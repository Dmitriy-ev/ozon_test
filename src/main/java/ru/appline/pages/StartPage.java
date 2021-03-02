package ru.appline.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Искать на Ozon']")
    WebElement search;

    public SearchResultPage searchOzon(String name){
        elementToBeVisible(search);
        search.sendKeys(name + Keys.ENTER);
        return app.getSearchResultPage();
    }
}
