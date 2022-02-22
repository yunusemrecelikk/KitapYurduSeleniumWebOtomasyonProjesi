package com.kitapyurdu.pages;

import com.kitapyurdu.config.GlobalConfig;
import com.kitapyurdu.methods.Methods;
import org.openqa.selenium.By;

public class BasketPage {
    Methods methods;

    public BasketPage() {
        methods = new Methods();
    }

    public void checkIsBasketVisible() {
        try {
            boolean isBasketButtonVisible = methods.IsElementVisible(By.id("sprite-cart-icon"));
            if (isBasketButtonVisible) {
                methods.WaitMs(1000);
                methods.Click(By.id("cart-items"));
                methods.Click(By.cssSelector(".checkout.fl > .mg-b-5"));
            } else {
                methods.LogInsert("Basket button is not visible");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void increaseQuantityAndPurchase() {
        try {
            boolean isBasketVisible = methods.IsElementVisible(By.id("cart_module"));
            if (isBasketVisible) {
                methods.SendKeys(By.name("quantity"), "0");
                methods.Click(By.cssSelector(".fa.fa-refresh.green-icon"));
                methods.WaitMs(1000);
                methods.Click(By.cssSelector(".buttons > .right > a"));
                methods.Click(By.xpath("//*[@id='shipping-tabs']//*[2]"));
                methods.SendKeys(By.id("address-firstname-companyname"), "Selenium");
                methods.SendKeys(By.id("address-lastname-title"), "Testing");
                methods.SelectByText(By.id("address-zone-id"), "İstanbul");
                methods.WaitMs(1000);
                methods.SelectByValue(By.id("address-county-id"), "453");
                methods.SendKeys(By.id("district"), "ALEMDAĞ MAH");
                methods.SendKeys(By.id("address-address-text"), "This is my test adress");
                methods.SendKeys(By.id("address-postcode"), "34100");
                methods.SendKeys(By.id("address-telephone"), "3123483160");
                methods.SendKeys(By.id("address-mobile-telephone"), "5531231213");
                methods.Click(By.id("button-checkout-continue"));
                methods.WaitMs(1500);
                methods.Click(By.id("button-checkout-continue"));
                methods.WaitMs(1500);
                methods.SendKeys(By.cssSelector("input#credit-card-owner"), "Testing Selenium");
                methods.SendKeys(By.cssSelector("input#credit_card_number_1"), "5138");
                methods.SendKeys(By.cssSelector("input#credit_card_number_2"), "5138");
                methods.SendKeys(By.cssSelector("input#credit_card_number_3"), "5138");
                methods.SendKeys(By.cssSelector("input#credit_card_number_4"), "5138");
                methods.SelectByText(By.cssSelector("select#credit-card-expire-date-month"), "01");
                methods.SelectByText(By.cssSelector("select#credit-card-expire-date-year"), "2023");
                methods.SendKeys(By.cssSelector("input#credit-card-security-code"), "414");
                methods.WaitMs(500);
                methods.Click(By.cssSelector("#button-checkout-continue"));

                boolean error = methods.IsElementVisible(By.cssSelector(".error"));
                if (error) {
                    String errorMessage = methods.GetText(By.cssSelector(".error"));
                    methods.LogInsert("Error message: " + errorMessage);
                    methods.LogInsert("Failed to purchase, logging out");
                    methods.Click(By.cssSelector(".checkout-logo"));
                }
            } else {
                methods.LogInsert("Basket is not visible");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
