package com.kitapyurdu.pages;

import com.kitapyurdu.config.GlobalConfig;
import com.kitapyurdu.methods.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class ProductSearchAndFavorite {
    Methods methods;
    GlobalConfig config = new GlobalConfig();

    public ProductSearchAndFavorite() {
        methods = new Methods();
    }

    public void Search() {
        try {
            methods.SendKeys(By.id("search-input"), "Oyuncak");
            methods.Click(By.cssSelector(".common-sprite.button-search"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ScrollAndAddToFavorite() {
        try {
            Random random = new Random();
            List<WebElement> products = methods.FindElements(By.cssSelector(".product-details"));
            WebElement sevenProduct = products.get(7);
            methods.ScrollWithAction(sevenProduct);

            int successCount = 1;
            for (int i = 5; i < products.size(); i++) {
                if (successCount > 4 ) {
                    break;
                }
                int lowest = 1;
                int highest = products.size();
                int selectedNumber = random.nextInt(highest-lowest) + lowest;
                String chosenItem = methods.IsItemAlreadyFavorited(By.xpath("//*[" + selectedNumber + "]//*" +
                        "[@class=\"grid_2 alpha omega relative\"]//*[@class=\"in-favorites\"]"));
                if (chosenItem.equals("true")) {
                    methods.ClickWithJavascript(By.xpath("//*[" + selectedNumber + "]//*" +
                            "[@class=\"grid_2 alpha omega relative\"]//*[@class=\"in-favorites\"]"));
                    methods.ClickWithJavascript(By.xpath("//*[" + selectedNumber + "]//*" +
                            "[@class=\"grid_2 alpha omega relative\"]//*[@class=\"add-to-favorites\"]"));
                    methods.LogInsert(selectedNumber + "  product number has " +
                            "already been added to favorites. "+ selectedNumber +
                            " product number was removed and re-added");
                } else if (chosenItem.equals("false")) {
                    methods.ClickWithJavascript(By.xpath("//*[" + selectedNumber + "]//*" +
                            "[@class=\"grid_2 alpha omega relative\"]//*[@class=\"add-to-favorites\"]"));
                    methods.LogInsert(selectedNumber + " added to favorites");
                }
                String itemAttribute = methods.GetAttribute(By.xpath("//*[" + selectedNumber + "]//*" +
                        "[@class=\"grid_2 alpha omega relative\"]//*[@class=\"in-favorites\"]"), "onclick");
                String itemProductId = itemAttribute.replaceFirst("removeFromFavorites", " ");
                itemProductId = itemProductId.replace("(", "");
                itemProductId = itemProductId.replace(")", "");
                itemProductId = itemProductId.replace(";", "");
                itemProductId = itemProductId.replace(" ", "");

                int ItemProductIdInteger = Integer.parseInt(itemProductId);
                GlobalConfig.favProducts.add(ItemProductIdInteger);
                successCount++;
            }
            methods.Click(By.cssSelector(".logo-text > a > img"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
