package com.kitapyurdu.pages;

import com.kitapyurdu.methods.Methods;
import org.junit.Assert;
import org.openqa.selenium.By;

public class BasketPage {
    Methods methods;

    public BasketPage() {
        methods = new Methods();
    }

    public void openBasket() {
        try {
            methods.Click(By.id("cart"));
            methods.WaitUntilClickable(By.id("js-cart"));
            String basketText = methods.GetText(By.id("js-cart"));
            Assert.assertEquals("Can not open basket", "Sepete Git", basketText);
            methods.Click(By.id("js-cart"));
            methods.WaitUntilClickable(By.cssSelector("#cart_module > h1"));
            String basketInsideText = methods.GetText(By.cssSelector("#cart_module > h1"));
            Assert.assertEquals("Failed to open basket", "Sepetim", basketInsideText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void increaseQuantityAndPurchase() {
        try {
            methods.ClearInput(By.name("quantity"));
            methods.SendKeys(By.name("quantity"), "2");

            String quantity = methods.GetValue(By.name("quantity"));
            Assert.assertEquals("Quantity did not increase as defined", "2", quantity);
            methods.Click(By.cssSelector(".fa.fa-refresh.green-icon"));

            methods.WaitUntilClickable(By.cssSelector("#cart_module > h1"));
            methods.Click(By.cssSelector(".buttons > .right > a"));

            methods.WaitUntilClickable(By.id("shipping-address-header"));
            String addressText = methods.GetText(By.id("shipping-address-header"));
            Assert.assertEquals("Failed to open choose adresses", "2 Kargo Adresi", addressText);

            methods.Click(By.xpath("//*[@id='shipping-tabs']//*[text()=\"Yeni bir adres kullanmak istiyorum.\"]"));
            methods.WaitUntilClickable(By.id("address-firstname-companyname"));

            methods.SendKeys(By.id("address-firstname-companyname"), "Selenium");
            String firstnameText = methods.GetAttribute(By.id("address-firstname-companyname"), "value");
            Assert.assertEquals("Not correct firstname", "Selenium", firstnameText);

            methods.SendKeys(By.id("address-lastname-title"), "Testing");
            String lastnameText = methods.GetAttribute(By.id("address-lastname-title"), "value");
            Assert.assertEquals("Not correct lastname", "Testing", lastnameText);

            methods.SelectByText(By.id("address-zone-id"), "İstanbul");
            String citySelect = methods.GetSelectedOptionText(By.id("address-zone-id"));
            Assert.assertEquals("Not correct city selected", "İstanbul", citySelect);

            methods.SelectByValue(By.id("address-county-id"), "453"); // 453 = ÇEKMEKÖY
            String districtSelect = methods.GetSelectedOptionText(By.id("address-county-id"));
            Assert.assertEquals("Not correct district selected", "ÇEKMEKÖY", districtSelect);

            methods.SendKeys(By.id("district"), "ALEMDAĞ MAH");
            String streetText = methods.GetAttribute(By.id("district"), "value");
            Assert.assertEquals("Not correct street information", "ALEMDAĞ MAH", streetText);

            methods.SendKeys(By.id("address-address-text"), "This is my test adress");
            String addressInputText = methods.GetAttribute(By.id("address-address-text"), "value");
            Assert.assertEquals("Not correct address information", "This is my test adress", addressInputText);

            methods.SendKeys(By.id("address-postcode"), "34100");
            String postcodeText = methods.GetAttribute(By.id("address-postcode"), "value");
            Assert.assertEquals("Not correct postcode information", "34100", postcodeText);

            methods.SendKeys(By.id("address-telephone"), "3123483160");
            String telephoneText = methods.GetAttribute(By.id("address-telephone"), "value");
            Assert.assertEquals("Not correct phone number information", "3123483160", telephoneText);

            methods.SendKeys(By.id("address-mobile-telephone"), "5531231213");
            String phonenumberText = methods.GetAttribute(By.id("address-mobile-telephone"), "value");
            Assert.assertEquals("Not correct phone number information", "5531231213", phonenumberText);

            methods.Click(By.id("button-checkout-continue"));
            methods.WaitUntilClickable(By.id("tabs"));
            methods.Click(By.id("button-checkout-continue"));
            methods.WaitUntilClickable(By.id("credit-card-owner"));

            methods.SendKeys(By.cssSelector("input#credit-card-owner"), "Testing Selenium");
            String cardOwnerName = methods.GetAttribute(By.cssSelector("input#credit-card-owner"), "value");
            Assert.assertEquals("Not correct card owner firstname", "Testing Selenium", cardOwnerName);

            methods.SendKeys(By.cssSelector("input#credit_card_number_1"), "5138");
            String cardNumber = methods.GetAttribute(By.cssSelector("input#credit_card_number_1"), "value");
            Assert.assertEquals("Not correct card owner first 4 digit", "5138", cardNumber);

            methods.SendKeys(By.cssSelector("input#credit_card_number_2"), "5138");
            String cardNumber2 = methods.GetAttribute(By.cssSelector("input#credit_card_number_2"), "value");
            Assert.assertEquals("Not correct card owner first 4 mid digit", "5138", cardNumber2);

            methods.SendKeys(By.cssSelector("input#credit_card_number_3"), "5138");
            String cardNumber3 = methods.GetAttribute(By.cssSelector("input#credit_card_number_3"), "value");
            Assert.assertEquals("Not correct card owner last 4 mid digit", "5138", cardNumber3);

            methods.SendKeys(By.cssSelector("input#credit_card_number_4"), "5138");
            String cardNumber4 = methods.GetAttribute(By.cssSelector("input#credit_card_number_4"), "value");
            Assert.assertEquals("Not correct card owner last 4 digit", "5138", cardNumber4);

            methods.SelectByText(By.cssSelector("select#credit-card-expire-date-month"), "01");
            String cardExpireMonth = methods.GetSelectedOptionText(By.cssSelector("select#credit-card-expire-date-month"));
            Assert.assertEquals("Not correct card expire month selected", "01", cardExpireMonth);

            methods.SelectByText(By.cssSelector("select#credit-card-expire-date-year"), "2023");
            String cardExpireYear = methods.GetSelectedOptionText(By.cssSelector("select#credit-card-expire-date-year"));
            Assert.assertEquals("Not correct card expire year selected", "2023", cardExpireYear);

            methods.SendKeys(By.cssSelector("input#credit-card-security-code"), "414");
            String cardCode = methods.GetAttribute(By.cssSelector("input#credit-card-security-code"), "value");
            Assert.assertEquals("Not correct card code", "414", cardCode);

            methods.WaitUntilClickable(By.id("button-checkout-continue"));
            methods.Click(By.id("button-checkout-continue"));

            boolean error = methods.IsElementVisible(By.cssSelector(".error"));
            if (error) {
                String errorMessage = methods.GetText(By.cssSelector(".error"));
                methods.LogInsert("Error message: " + errorMessage);
                methods.LogInsert("Failed to purchase, logging out");
                methods.Click(By.cssSelector(".checkout-logo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
