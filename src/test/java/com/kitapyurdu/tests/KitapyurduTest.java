package com.kitapyurdu.tests;

import com.kitapyurdu.actions.LoginAction;
import com.kitapyurdu.actions.LogoutAction;
import com.kitapyurdu.driver.BaseTest;
import com.kitapyurdu.pages.*;
import org.junit.Test;

public class KitapyurduTest extends BaseTest {

    @Test
    public void StartTest() {
        LoginAction loginAction = new LoginAction();
        SearchPage searchPage = new SearchPage();
        PointsCataloguePage pointsCataloguePage = new PointsCataloguePage();
        TurkKlasikleriPage turkKlasikleriPage = new TurkKlasikleriPage();

        BasketPage basketPage = new BasketPage();
        LogoutAction logoutAction = new LogoutAction();

        loginAction.Login();

        searchPage.Search();
        searchPage.ScrollThenAddFavorites();

        pointsCataloguePage.OpenPointsCatalogue();
        pointsCataloguePage.OpenTurkKlasikleri();

        turkKlasikleriPage.RandomAddToBasket();
        turkKlasikleriPage.openFavorites();
        turkKlasikleriPage.removeFromFavorites();

        basketPage.openBasket();

        basketPage.increaseQuantityAndPurchase();
        logoutAction.logout();
    }
}
