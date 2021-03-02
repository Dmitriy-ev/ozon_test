package ru.appline.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.utils.Product;

import java.util.List;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'filter-block')]")
    List<WebElement> filter;

    @FindBy(xpath = "//div[contains(@data-widget,'searchResultsFiltersActive')]//span")
    List<WebElement> textFilter;

    @FindBy(xpath = "//div[@data-widget='searchResultsV2']/div/div")
    List<WebElement> productList;

    @FindBy(xpath = "//a[@data-widget='cart']")
    WebElement cart;

    private WebElement searchBlockFiltr(String name) {
        for (WebElement element : filter) {
            if (element.findElement(By.xpath("./div[@value or @class and count(*)=0]")).getText().contains(name)) {
                return element;
            }
        }
        Assert.fail("Блок не найден");
        return null;
    }

    private void searchTextFilterWait(String nameText) {
        for (WebElement element : textFilter) {
            wait.until(textToBePresent(element, nameText.replaceAll("\\D", "")));
        }
    }

    /**
     * Метод обходит элементы с диапозоном от - до
     */
    public SearchResultPage fillFilterFields(String nameFilter, String nameField, String value) {
        WebElement blockFilter = searchBlockFiltr(nameFilter);
        for (WebElement element : blockFilter.findElements(By.xpath(".//following-sibling::div//p"))) {
            if (element.getText().contains(nameField)) {
                WebElement inputData = element.findElement(By.xpath(".//preceding-sibling::input"));
                scrollWithOffset(inputData, 0, 200);
                inputData.sendKeys(Keys.chord(Keys.CONTROL, "a"), value + Keys.ENTER);
                searchTextFilterWait(nameFilter);
                return this;
            }
        }
        Assert.fail("Не удалось установить диапозон");
        return this;
    }

    /**
     * Метод обходит элементы с чекбоксом
     */
    public SearchResultPage fillFilterCheckBox(String nameFilter) {
        WebElement blockFilter = searchBlockFiltr(nameFilter);
        WebElement checkBox = blockFilter.findElement(By.xpath(".//span"));
        checkBox.click();
        searchTextFilterWait(nameFilter);
        return this;
    }

    /**
     * Метод работает с полями где ставятся галочки
     */
    public SearchResultPage fillMultiFilterCheckBoxBrand(String nameFilter, String... nameCheckBox) {
        WebElement blockFilter = searchBlockFiltr(nameFilter);
        WebElement openAllBrand = blockFilter.findElement(By.xpath(".//following-sibling::div//span[contains(text(),'Посмотреть')]"));
        openAllBrand.click();
        WebElement nameBrand = blockFilter.findElement(By.xpath(".//following-sibling::div//a//span"));
        WebElement searchBrand = blockFilter.findElement(By.xpath(".//following-sibling::div//p//preceding-sibling::input"));
        for (String str : nameCheckBox) {
            //wait.until(ExpectedConditions.stalenessOf(elementName));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//following-sibling::div//p//preceding-sibling::input")));
            searchBrand.click();
            searchBrand.sendKeys(Keys.chord(Keys.CONTROL, "a") + str + Keys.ENTER);
            elementToBeClickable(nameBrand);
            nameBrand.click();
            searchTextFilterWait(str);
        }
        return this;
    }

    /**
     * Так же где есть галочки но нет кнопки посмотреть все
     */
    public SearchResultPage fillMultiFilterCheckBox(String nameFilter, String nameCheckBox) {
        WebElement blockFilter = searchBlockFiltr(nameFilter);
        for (WebElement element : blockFilter.findElements(By.xpath(".//following-sibling::div//*[@href]"))) {
            if (element.getText().contains(nameCheckBox)) {
                WebElement selectFlag = element.findElement(By.xpath(".//span"));
                selectFlag.click();
                searchTextFilterWait(nameFilter);
                return this;
            }
        }
        Assert.fail("Пункт не выбран");
        return this;
    }

    public BasketPage addToBasket(List<Product> list, int size) {
        WebElement buttonClick;
        int count = 0;
        for (int i = 1; i < productList.size(); i += 2) {
            if (count == size) break;
            else {
                if (isElementExist(productList.get(i), ".//button//div[contains(text(), 'В корзину')]")
                        && !isElementExist(productList.get(i), ".//span[contains(text(), 'Express')]")) {
                    buttonClick = productList.get(i).findElement(By.xpath(".//button//div[contains(text(), 'В корзину')]"));
                    scrollWithOffset(buttonClick, 0, -400);
                    elementToBeClickable(buttonClick);
                    buttonClick.click();
                    count++;
                    list.add(new Product(productList.get(i).findElement(By.xpath(".//following-sibling::div//span//font/../../a")).getText()
                            , Integer.parseInt(productList.get(i).findElement(By.xpath(".//div[contains(@class,'fav-button')]/..//preceding-sibling::span")).getText().replaceAll("\\D", ""))));
                }
            }
        }
        wait.until(ExpectedConditions.textToBePresentInElement(cart.findElement(By.xpath(".//preceding-sibling::span")), "8"));
        Assert.assertEquals("Количество товаров в корзине не соответствует действительному",
                cart.findElement(By.xpath(".//preceding-sibling::span")).getAttribute("innerText"), "8");
        cart.click();
        return app.getBasketPage();
    }
}
