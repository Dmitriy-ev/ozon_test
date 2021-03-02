package ru.appline.pages;

import io.cucumber.java.bs.A;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.utils.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BasketPage extends BasePage {

    @FindBy(xpath = "//div[@data-widget='split']/div//a//span")
    List<WebElement> listNameProductBasket;

    @FindBy(xpath = "//span[contains(text(), 'Ваша корзина')]/following-sibling::span")
    WebElement checkBasket;

    @FindBy(xpath = "//span[contains(text(), 'Удалить выбранные')]")
    WebElement removeButon;

    @FindBy(xpath = "//section//div//button//div[contains(text(),'Удалить')]")
    WebElement confirm;

    @FindBy(xpath = "//h1[contains(text(),'Корзина пуста')]")
    WebElement checkRemove;


    public BasketPage checkProductNameBasket(List<Product> listProduct) {
        for (WebElement nameBasketProduct : listNameProductBasket) {
            boolean flag = true;
            while (flag) {
                flag = false;
                for (int i = 0; i < listProduct.size(); i++) {
                    if (nameBasketProduct.getText().equals(listProduct.get(i).getName())) {
                        flag = true;
                        Assert.assertEquals("Продукты не равны", nameBasketProduct.getText(), listProduct.get(i).getName());
                        break;
                    }
                }
                flag = false;
            }
        }
        return this;
    }

    public BasketPage removeAllAndcheckBasket(List<Product> list){
        Assert.assertTrue("Количество товаров в корзине не верно", checkBasket.getText().contains(String.valueOf(list.size())));
        elementToBeClickable(removeButon);
        removeButon.click();
        elementToBeClickable(confirm);
        confirm.click();
        wait.until(ExpectedConditions.not(textToBePresent(confirm,"Удалить")));
        Assert.assertTrue("Корзина не пуста", wait.until(ExpectedConditions.textToBePresentInElement(checkRemove,"Корзина пуста")));
        return this;
    }


}
