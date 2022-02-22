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
            methods.Scroll(By.xpath("//*[@class='common-sprite'][1]/*"));
            methods.ClickWithJavascript(By.xpath("//*[@id='header-top']//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[4]/*"));
            String loginButtonText = methods.GetText(By.xpath("//*[@id='header-top']//*[1]//*[1]//*[1]/*"));
            Assert.assertEquals("Login button is not visible", "Giriş Yap", loginButtonText);
            methods.LogInsert("Succesfully logged out");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
