package ir.ac.kntu.Logic;

import ir.ac.kntu.Logic.RestaurantType;
import ir.ac.kntu.Logic.ScannerWrapper;
import ir.ac.kntu.Markets.GroceryStore;
import ir.ac.kntu.Markets.Market;
import ir.ac.kntu.Markets.Restaurant;
import ir.ac.kntu.Markets.SuperMarket;

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
                searchRestaurant();
                break;
            case 2:
                searchSuperMarket();
                break;
            case 3:
                searchGroceryStore();
                break;
            default:
                return;
        }
    }

    public void searchRestaurant(){
        if(markets.size() == 0){
            System.out.println("There are no active restaurant ");
            return;
        }
        System.out.println("How do you want to search the restaurant ? ( 1-Based on name  2-Based on type )");
        searchRestaurantHandler();
    }

    public void searchRestaurantHandler(){
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                searchRestaurantName();
                break;
            case 2:
                searchRestaurantType();
                break;
            default:
                System.out.println("Incorrect input !!! please try again ");
                searchRestaurant();
                break;
        }
    }

    public void searchRestaurantName(){
        ArrayList<Restaurant> restaurants = findRestaurants();
        System.out.println("Enter the name : ");
        String name = ScannerWrapper.getInstance().nextLine();
        for (int i=0;i< restaurants.size();i++){
            if(restaurants.get(i).getName().equals(name)){
                System.out.println(i + ")" + restaurants.get(i).getName());
            }
        }
    }

    public void searchRestaurantType(){
        System.out.println("Select the type :" +
                "\n1) " + RestaurantType.ECONOMIC +
                "\n2) " + RestaurantType.MEDIUM +
                "\n3) " + RestaurantType.LUXURY);
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                showFilteredRestaurant(RestaurantType.ECONOMIC);
                break;
            case 2:
                showFilteredRestaurant(RestaurantType.MEDIUM);
                break;
            case 3:
                showFilteredRestaurant(RestaurantType.LUXURY);
                break;
            default:
                System.out.println("Incorrect input !!! please try again ");
                searchRestaurantType();
                break;
        }
    }

    public void showFilteredRestaurant(RestaurantType type){
        ArrayList<Restaurant> restaurants = findRestaurants();
        for (int i = 0;i < restaurants.size();i++){
            if(restaurants.get(i).getRestaurantType().equals(type)){
                System.out.println( i + ")" + restaurants.get(i).getName());
            }
        }
    }

    public ArrayList<Restaurant> findRestaurants(){
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        for (int i=0;i< markets.size();i++){
            if (markets.get(i) instanceof Restaurant){
                restaurants.add((Restaurant) markets.get(i));
            }
        }
        return restaurants;
    }

    public void searchSuperMarket(){
        ArrayList<SuperMarket> superMarkets = findSuperMarkets();
        if (superMarkets.size() == 0){
            System.out.println("There are no active super market !!");
            return;
        }
        System.out.println("Enter the name of the SuperMarket : ");
        String tempName = ScannerWrapper.getInstance().nextLine();
        for (int i=0;i< superMarkets.size();i++){
            if (superMarkets.get(i).getName().equals(tempName)){
                System.out.println(i + ") " + superMarkets.get(i).getName());
            }
        }
    }

    public ArrayList<SuperMarket> findSuperMarkets(){
        ArrayList<SuperMarket> superMarkets = new ArrayList<>();
        for (int i=0;i< markets.size();i++){
            if (markets.get(i) instanceof SuperMarket){
                superMarkets.add((SuperMarket) markets.get(i));
            }
        }
        return superMarkets;
    }

    public void searchGroceryStore(){
        ArrayList<GroceryStore> groceryStores = findGroceryStores();
        if (groceryStores.size() == 0){
            System.out.println("There are no active grocery store !!");
            return;
        }
        System.out.println("Enter the name of the grocery store : ");
        String tempName = ScannerWrapper.getInstance().nextLine();
        for (int i=0;i< groceryStores.size();i++){
            if (groceryStores.get(i).getName().equals(tempName)){
                System.out.println(i + ") " + groceryStores.get(i).getName());
            }
        }
    }

    public ArrayList<GroceryStore> findGroceryStores(){
        ArrayList<GroceryStore> groceryStores = new ArrayList<>();
        for (int i=0;i< markets.size();i++){
            if (markets.get(i) instanceof GroceryStore){
                groceryStores.add(((GroceryStore) markets.get(i)));
            }
        }
        return groceryStores;
    }
}
