package com.kitapyurdu.tests;

import com.kitapyurdu.actions.LogoutAction;
import com.kitapyurdu.driver.BaseTest;
import com.kitapyurdu.pages.BasketPage;
import com.kitapyurdu.pages.LoginPage;
import com.kitapyurdu.pages.PointsCatalogue;
import com.kitapyurdu.pages.ProductSearchAndFavorite;
import com.oracle.webservices.internal.api.message.BasePropertySet;
import org.junit.Test;

public class LoginTest extends BaseTest {

    @Test
    public void StartTest() {
        LoginPage loginPage = new LoginPage();
        ProductSearchAndFavorite productSearchAndFavorite = new ProductSearchAndFavorite();
        PointsCatalogue pointsCatalogue = new PointsCatalogue();
        BasketPage basketPage = new BasketPage();
        LogoutAction logoutAction = new LogoutAction();

        loginPage.Login();
        loginPage.LoginCheck();
        productSearchAndFavorite.Search();
        productSearchAndFavorite.ScrollAndAddToFavorite();
        pointsCatalogue.OpenPointsCatalogue();
        pointsCatalogue.OpenTurkKlasikleri();
        pointsCatalogue.RandomAddToBasket();
        pointsCatalogue.removeFromFavorites();

        basketPage.checkIsBasketVisible();
        basketPage.increaseQuantityAndPurchase();

        logoutAction.logout();



    }

}
