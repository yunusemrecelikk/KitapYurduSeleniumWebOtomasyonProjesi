package com.kitapyurdu.actions;

import com.kitapyurdu.methods.Methods;
import org.junit.Assert;
import org.openqa.selenium.By;

public class LoginAction {
    Methods methods;

    public LoginAction() {
        methods = new Methods();
    }

    public void Login() {
        try {
            methods.Click(By.cssSelector(".welcome.fl > .menu-top-button.login > a"));
            methods.SendKeys(By.id("login-email"), "pageg31808@ishop2k.com");
            methods.SendKeys(By.id("login-password"), "1q2w3e4r5t");
            methods.Click(By.cssSelector(".ky-btn.ky-btn-orange.w-100.ky-login-btn"));
            methods.WaitUntilClickable(By.cssSelector(".section"));
            String hesabimText = methods.GetText(By.cssSelector(".section"));
            Assert.assertEquals("Failed to login","Hesabım",hesabimText);
            methods.LogInsert("Succesfully logged in!");
        } catch (Exception e) {
            methods.LogInsert("Error: " + e.getMessage());
        }
    }
}
