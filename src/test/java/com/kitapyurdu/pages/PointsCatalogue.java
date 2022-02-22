package com.kitapyurdu.pages;

import com.kitapyurdu.config.GlobalConfig;
import com.kitapyurdu.methods.Methods;
import org.openqa.selenium.By;

public class PointsCatalogue {
    Methods methods;

    public PointsCatalogue() {
        methods = new Methods();
    }

    public void OpenPointsCatalogue() {
        try {
            methods.Click(By.cssSelector(".lvl1catalog > a"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OpenTurkKlasikleri() {
        try {
            methods.Click(By.cssSelector(":nth-child(4) > a:nth-child(2) > img"));
            methods.Click(By.cssSelector(".sort > select"));
            methods.SelectByText(By.cssSelector(".sort > select"), "Yüksek Oylama");
            methods.Scroll(By.cssSelector(".lvl2.js-bookCr > ul > li:nth-child(3) > span"));
            methods.ClickWithJavascript(By.cssSelector(":nth-child(3) > :nth-child(14) > a"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RandomAddToBasket() {
        try {
            int chance = methods.randomNumber(1,2);
            int selectedBook = methods.randomNumber(1, 15);
            if (chance == 1) {
                methods.Click(By.xpath("//*[@class='box no-padding']//*[2]//*[@class='box no-padding']//*" +
                        "[@class='product-grid jcarousel-skin-opencart']//*[@class='mg-b-10']" +
                        "["+selectedBook+"]//*[@class='image']//*[@class='cover']//*[@class='pr-img-link']"));
            } else {
                methods.Click(By.xpath("//*[@class='box no-padding']//*[1]//*[@class='box no-padding']//*" +
                        "[@class='product-grid jcarousel-skin-opencart']//*[@class='mg-b-10']" +
                        "["+selectedBook+"]//*[@class='image']//*[@class='cover']//*[@class='pr-img-link']"));
            }
            methods.Click(By.id("button-cart"));
            methods.Click(By.cssSelector(".menu.top.my-list"));
            methods.Click(By.linkText("Favorilerim"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeFromFavorites() {
        try {
            int item = GlobalConfig.favProducts.get(2);
            boolean isElementVisible = methods.IsElementVisible(By.id("product-"+ item));
            if (isElementVisible) {
                methods.Scroll(By.id("product-"+ item));
                methods.Click(By.cssSelector("#product-"+ item +" > .grid_2.alpha.omega.relative > .hover-menu >" +
                        " :nth-child(3)"));
                methods.LogInsert(item + " has been removed from favorites");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
