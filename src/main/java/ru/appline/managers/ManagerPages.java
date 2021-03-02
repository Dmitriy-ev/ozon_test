package ru.appline.managers;

import ru.appline.pages.BasketPage;
import ru.appline.pages.SearchResultPage;
import ru.appline.pages.StartPage;

public class ManagerPages {

    private static ManagerPages managerPages;
    StartPage startPage;
    SearchResultPage searchResultPage;
    BasketPage basketPage;

    private ManagerPages() {
    }


    public static ManagerPages getManagerPages() {
        if (managerPages == null) {
            managerPages = new ManagerPages();
        }
        return managerPages;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public SearchResultPage getSearchResultPage(){
        if(searchResultPage == null){
            searchResultPage = new SearchResultPage();
        }
        return searchResultPage;
    }

    public BasketPage getBasketPage(){
        if(basketPage == null){
            basketPage = new BasketPage();
        }
        return basketPage;
    }
}
