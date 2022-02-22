package com.kitapyurdu.pages;

import com.kitapyurdu.config.GlobalConfig;
import com.kitapyurdu.methods.Methods;
import org.junit.Assert;
import org.openqa.selenium.By;

public class LoginPage {
    Methods methods;
    GlobalConfig config = new GlobalConfig();

    public LoginPage() {
        methods = new Methods();
    }

    public void Login() {
        try {
            methods.Click(By.cssSelector(".welcome.fl > .menu-top-button.login > a"));
            methods.SendKeys(By.id("login-email"), "pageg31808@ishop2k.com");
            methods.SendKeys(By.id("login-password"), "1q2w3e4r5t");
            methods.Click(By.cssSelector(".ky-btn.ky-btn-orange.w-100.ky-login-btn"));
            Assert.assertTrue(methods.IsElementVisible(By.cssSelector(".section")));
            methods.LogInsert("Succesfully logged in!");
        } catch (Exception e) {
            methods.LogInsert("Error: " + e.getMessage());
        }
    }

    public void LoginCheck() {
        try {
            String text = methods.GetText(By.cssSelector(".section"));
            Assert.assertEquals("Expected text is not same with actual text", "Hesabım", text);
            methods.LogInsert("Expected text matched actual text");
        } catch (Exception e) {
            methods.LogInsert("Error: " + e);
        }
    }
}
