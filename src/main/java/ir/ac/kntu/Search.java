package ir.ac.kntu;

import java.util.ArrayList;

public class Search {
    private ArrayList<Market> markets;

    Search(){

    }

    Search(ArrayList<Market> markets){
        this.markets = markets;
    }

    public void start(int marketCode){
        switch (marketCode){
            case 1:
                //searchRestaurant();
                break;
            case 2:
                //searchSuperMarket();
                break;
            case 3:
                //searchGroceryStore();
                break;
            default:
                return;
        }
    }

}
