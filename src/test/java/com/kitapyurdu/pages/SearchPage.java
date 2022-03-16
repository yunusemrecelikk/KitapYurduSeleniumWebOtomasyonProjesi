package com.kitapyurdu.pages;

import com.kitapyurdu.methods.Methods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class SearchPage {
    Methods methods;

    public SearchPage() { methods = new Methods(); }

    public void Search() {
        try {
            methods.WaitUntilClickable(By.id("search-input"));
            methods.SendKeys(By.id("search-input"), "Oyuncak");

            String searchValue = methods.GetAttribute(By.id("search-input"), "value");
            Assert.assertEquals("Failed to write \"Oyuncak\" to search ", "Oyuncak", searchValue);

            methods.Click(By.cssSelector(".common-sprite.button-search"));
            methods.WaitUntilClickable(By.cssSelector(".search-heading-title"));

            String successSearch = methods.GetText(By.cssSelector(".search-heading-title"));
            Assert.assertEquals("Failed to search", "Oyuncak için arama sonuçları", successSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ScrollThenAddFavorites() {
        try {
            Random rnd = new Random();
            List<WebElement> listedProducts = methods.FindElements(By.cssSelector(".product-details"));
            WebElement sevenProduct = listedProducts.get(7);
            methods.ScrollWithAction(sevenProduct);
            HashSet<Integer> selectedProducts = new HashSet<Integer>();
            int lowestRandom = 1;
            int highestRandom = listedProducts.size();
            for (int i = 1; i < 5 ; i++) {
                int selectedProduct = rnd.nextInt(highestRandom-lowestRandom) + lowestRandom;
                if (!selectedProducts.contains(selectedProduct)) {
                    selectedProducts.add(selectedProduct);
                    String chosenProduct = methods.IsItemAlreadyFavorited(By.xpath("//*[" + selectedProduct + "]//*" +
                            "[@class=\"grid_2 alpha omega relative\"]//*[@class=\"in-favorites\"]"));
                    if (chosenProduct.equals("true")) {
                        methods.ClickWithJavascript(By.xpath("//*[" + selectedProduct + "]//*" +
                                "[@class=\"grid_2 alpha omega relative\"]//*[@class=\"in-favorites\"]"));
                        methods.ClickWithJavascript(By.xpath("//*[" + selectedProduct + "]//*" +
                                "[@class=\"grid_2 alpha omega relative\"]//*[@class=\"add-to-favorites\"]"));
                        methods.LogInsert(selectedProduct + "  product number has " +
                                "already been added to favorites. "+ selectedProduct +
                                " product number was removed and re-added");
                    } else if (chosenProduct.equals("false")) {
                        methods.ClickWithJavascript(By.xpath("//*[" + selectedProduct + "]//*" +
                                "[@class=\"grid_2 alpha omega relative\"]//*[@class=\"add-to-favorites\"]"));
                        methods.LogInsert(selectedProduct + " added to favorites");
                    }
                } else {
                    i--;
                    methods.LogInsert(selectedProduct + " already favorited, so skipped and restarted add to favorite section");
                }
            }
            methods.Click(By.cssSelector(".logo-text > a > img"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
