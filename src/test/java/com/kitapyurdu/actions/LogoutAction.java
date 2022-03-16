package com.kitapyurdu.actions;

import com.kitapyurdu.methods.Methods;
import org.junit.Assert;
import org.openqa.selenium.By;

public class LogoutAction {
    Methods methods;

    public LogoutAction() {
        methods = new Methods();
    }

    public void logout() {
        try {
            methods.WaitUntilClickable(By.id("content"));
            methods.Scroll(By.xpath("//*[@class='common-sprite']//*"));
            methods.ClickWithJavascript(By.xpath("//*[@id=\"header-top\"]//*[text()=\"Çıkış\"]"));
            String loginButtonText = methods.GetText(By.xpath("//*[@class=\"menu-top-button login\"]//*"));
            Assert.assertEquals("Login button is not visible", "Giriş Yap", loginButtonText);
            methods.LogInsert("Succesfully logged out");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
