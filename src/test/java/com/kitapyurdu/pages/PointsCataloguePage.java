package com.kitapyurdu.pages;

import com.kitapyurdu.methods.Methods;
import org.junit.Assert;
import org.openqa.selenium.By;

public class PointsCataloguePage {
    Methods methods;

    public PointsCataloguePage() {
        methods = new Methods();
    }

    public void OpenPointsCatalogue() {
        try {
            methods.Click(By.cssSelector(".lvl1catalog"));
            methods.WaitUntilClickable(By.cssSelector(".landing-header"));
            methods.Scroll(By.cssSelector(".landing-header"));
            methods.WaitUntilClickable(By.cssSelector(".all-point-products"));
            String catalogueText = methods.GetText(By.cssSelector(".all-point-products"));
            Assert.assertEquals("Points Catalogue Page did not open successfully", "Tüm Ürünleri Listeleyin", catalogueText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OpenTurkKlasikleri() {
        try {
            methods.Click(By.cssSelector("img[title*=\"Puan Kataloğundaki Türk Klasikleri\"]"));
            methods.WaitUntilClickable(By.cssSelector(".grid_9"));

//            methods.Click(By.cssSelector(".sort > select"));
            methods.SelectByText(By.cssSelector(".sort > select"), "Yüksek Oylama");
            methods.WaitUntilClickable(By.cssSelector(".grid_9"));
            methods.Scroll(By.xpath("//*[@class=\"has-open-menu\"]//*[@class=\"mn-strong common-sprite\"][text()=\"Tüm Kitaplar\"]"));
            methods.WaitUntilClickable(By.xpath("//*[@class=\"mn-icon icon-angleRight\"][text()=\"Hobi\"]"));
            methods.Click(By.xpath("//*[@class=\"mn-icon icon-angleRight\"][text()=\"Hobi\"]"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
