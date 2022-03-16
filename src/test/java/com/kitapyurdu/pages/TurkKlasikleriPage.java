package com.kitapyurdu.pages;

import com.kitapyurdu.methods.Methods;
import org.junit.Assert;
import org.openqa.selenium.By;

public class TurkKlasikleriPage {
    Methods methods;

    public TurkKlasikleriPage() {
        methods = new Methods();
    }

    public void RandomAddToBasket() {
        try {
            int chance = methods.randomNumber(1,2);
            int selectedBook = methods.randomNumber(1, 15);
            String bookName;
            if (chance == 1) {
                bookName = methods.GetAttribute(By.xpath("//*[@class='box no-padding']//*[2]//*[@class='box no-padding']//*" +
                        "[@class='product-grid jcarousel-skin-opencart']//*[@class='mg-b-10']" +
                        "["+selectedBook+"]//*[@class='image']//*[@class='cover']//*[@class='pr-img-link']//*[1]"), "alt");
                methods.Click(By.xpath("//*[@class='box no-padding']//*[2]//*[@class='box no-padding']//*" +
                        "[@class='product-grid jcarousel-skin-opencart']//*[@class='mg-b-10']" +
                        "["+selectedBook+"]//*[@class='image']//*[@class='cover']//*[@class='pr-img-link']"));
            } else {
                bookName = methods.GetAttribute(By.xpath("//*[@class='box no-padding']//*[1]//*[@class='box no-padding']//*" +
                        "[@class='product-grid jcarousel-skin-opencart']//*[@class='mg-b-10']" +
                        "["+selectedBook+"]//*[@class='image']//*[@class='cover']//*[@class='pr-img-link']//*"), "alt");
                methods.Click(By.xpath("//*[@class='box no-padding']//*[1]//*[@class='box no-padding']//*" +
                        "[@class='product-grid jcarousel-skin-opencart']//*[@class='mg-b-10']" +
                        "["+selectedBook+"]//*[@class='image']//*[@class='cover']//*[@class='pr-img-link']"));
            }
            methods.WaitUntilClickable(By.id("js-book-cover"));
            String currentBookName = methods.GetAttribute(By.id("js-book-cover"), "alt");
            Assert.assertEquals("Not correct book", bookName, currentBookName);

            methods.Click(By.id("button-cart"));
            methods.WaitUntilClickable(By.cssSelector(".add-to-cart.btn-ripple.btn-green"));
            String successAdd = methods.GetText(By.id("button-cart"));
            Assert.assertEquals("Failed to add to basket", "Sepetinizde", successAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void openFavorites() {
        try {
            methods.Click(By.cssSelector(".menu.top.my-list"));
            methods.Click(By.linkText("Favorilerim"));
            methods.WaitUntilClickable(By.cssSelector(".section.mg-b-30"));
            String favoritesTitle = methods.GetText(By.cssSelector(".section.mg-b-30"));
            Assert.assertEquals("Failed to open favorites", "Favorilerim", favoritesTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFromFavorites() {
        try {
            methods.WaitUntilClickable(By.xpath("//*[@class=\"product-cr\"][3]"));
            methods.Scroll(By.xpath("//*[@class=\"product-cr\"][3]"));
            String productId = methods.GetAttribute(By.xpath("//*[@class=\"product-cr\"][3]"), "id");
            methods.Click(By.cssSelector("#"+ productId +" > .grid_2.alpha.omega.relative" +
                    " > .hover-menu > a > .fa.fa-heart-o"));
            Assert.assertTrue(methods.IsElementVisible(By.cssSelector("#notification > .success")));
            methods.LogInsert(productId + " has been removed from favorites");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
