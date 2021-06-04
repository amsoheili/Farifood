package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.*;

public class Manager {
    private ArrayList<Market> markets;
    private ArrayList<Order> orders;
    private ArrayList<Customer> customers;
    private ArrayList<Product> products;
    private ArrayList<Delivery> deliveries;
    private ArrayList<Comment> comments;
    private HashMap<Integer,Customer> idCustomer;


    public Manager() {
        this.markets = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.idCustomer = new HashMap<>();
        this.products = new ArrayList<>();
        this.deliveries = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    public ArrayList<Market> getMarkets(){
        return markets;
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Delivery> getDeliveries() {
        return this.deliveries;
    }

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public void orderFoodRestaurant(){
        OrderDuty.orderSth(markets,orders,1);
    }

    public void orderFoodSuperMarket(){
        OrderDuty.orderSth(markets,orders,2);
    }

    public void orderFoodGrocery(){
        OrderDuty.orderSth(markets,orders,3);
    }

    public void showOrders(){
        OrderDuty.showOrders(orders);
    }

    public void showRestaurants() {
        OrderDuty.showMarkets(markets,true,1);
    }

    public void showMarkets(){
        if (markets.size() == 0){
            System.out.println("There are no declared markets ");
        }
        for (int i=0;i< markets.size();i++){
            System.out.println(i + ") " + markets.get(i).getName());
        }
    }

    public void filterOrders(){
        OrderDuty.filterOrders(orders,markets,comments);
    }

    public void addComment(){
        OrderDuty.addComment(orders,markets,comments);
    }

    public void addScore(){
        OrderDuty.addScore(orders,markets,comments);
    }

    public void setOrderStatus(){
        OrderDuty.setOrderStatus(markets,this);
    }

    public void declareRestaurant() {
        RestaurantDuty.declareRestaurant(markets);
    }

    public void declareSuperMarket(){
        SuperMarketDuty.declareSuperMarket(markets);
    }

    public void declareGroceryStore(){
        GroceryStoreDuty.declareGroceryStore(markets);
    }

    public void editRestaurant(){
        RestaurantDuty.editRestaurant(markets);
    }

    public void editSuperMarket(){
        SuperMarketDuty.editSuperMarket(markets);
    }

    public void editGroceryStore(){
        GroceryStoreDuty.editGroceryStore(markets);
    }

    public void showRestaurantScore(){
        RestaurantDuty.showRestaurantScore(markets);
    }

    public void showSuperMarketScore(){SuperMarketDuty.showSuperMarketScore(markets);}

    public void showGroceryStoreScore(){GroceryStoreDuty.showGroceryStoreScore(markets);}

    public void showRestaurantComments(){
        RestaurantDuty.showRestaurantComments(markets);
    }

    public void showSuperMarketComments(){
        SuperMarketDuty.showSuperMarketComments(markets);
    }

    public void showGroceryStoreComments(){
        GroceryStoreDuty.showGroceryStoreComments(markets);
    }

    public void addDeliveryRestaurant(){
        RestaurantDuty.addDelivery(markets,deliveries);
    }

    public void addDeliverySuperMarket(){
        SuperMarketDuty.addDelivery(markets,deliveries);
    }

    public void addDeliveryGroceryStore(){
        GroceryStoreDuty.addDelivery(markets,deliveries);
    }

    public void addFoodRestaurant(){
        RestaurantDuty.addFood(markets,products);
    }

    public void addFoodSuperMarket(){
        SuperMarketDuty.addFood(markets,products);
    }

    public void addFruit(){
        GroceryStoreDuty.addFruit(markets,products);
    }

    public void createCustomer(){
        CustomerDuty.createCustomer(customers);
    }

    public void editCustomer(){
        CustomerDuty.editCustomer(customers);
    }

    public void showCustomers(){
        CustomerDuty.showCustomers(customers);
    }

    public void setOrderFoodRestaurant(){
        OrderDuty.setOrderFood(markets,1);
    }

    public void setOrderFoodSuperMarket(){
        OrderDuty.setOrderFood(markets,2);
    }

    public void setOrderFruit(){
        OrderDuty.setOrderFood(markets,3);
    }

    public void searchRestaurant(){
        Search search = new Search(markets);
        search.start(1);
    }

    public void searchSuperMarket(){
        Search search = new Search(markets);
        search.start(2);
    }

    public void searchGroceryStore(){
        Search search = new Search(markets);
        search.start(3);
    }

    public void showBestFoodRestaurant(){
        RestaurantDuty.showBestFood(markets);
    }

    public void showBestFoodSuperMarket(){
        SuperMarketDuty.showBestFood(markets);
    }

    public void showBestFruit(){
        GroceryStoreDuty.showBestFruit(markets);
    }

    public void showCommentOfProduct(){
        if(products.size() == 0){
            System.out.println("There are no products yet ");
            return;
        }

        if(comments.size() == 0){
            System.out.println("There are no comments yet ");
            return;
        }

        System.out.println("Which product ? ");
        showProducts();
        int choice = ScannerWrapper.getInstance().nextInt();
        for (int i=0;i< comments.size();i++){
            if(comments.get(i).getSubject().equals(products.get(choice).getName())){
                System.out.println( i + ")" + comments.get(i).getText());
            }
        }
    }

    public void showBestMarketForMentionedProduct(){
        if(markets.size() == 0){
            System.out.println("There are no active market !!");
            return;
        }

        if(products.size() == 0){
            System.out.println("There are no products yet ");
            return;
        }

        System.out.println("Select the product : ");
        showProducts();
        int choice = ScannerWrapper.getInstance().nextInt();
        ArrayList<Market> newMarkets = new ArrayList<>();
        HashMap<Market,Integer> marketIndex = new HashMap<>();
        for (int i = 0;i < markets.size();i++){
            for (int j=0;j<markets.get(i).getProducts().size();j++){
                if ( markets.get(i).getProducts().get(j).getName().equals(products.get(choice).getName())){
                    newMarkets.add(markets.get(i));
                    marketIndex.put(markets.get(i),j);
                }
            }
        }

        for (int i=0;i< newMarkets.size();i++){
            for (int j=0;j<newMarkets.size() - 1 - i;j++){
                if ( newMarkets.get(i).getProducts().get(marketIndex.get(newMarkets.get(i))).getScore() <
                        newMarkets.get(i+1).getProducts().get(marketIndex.get(newMarkets.get(i+1))).getScore() ){
                    Market tmp = newMarkets.get(i);
                    newMarkets.set(i,newMarkets.get(i+1));
                    newMarkets.set(i+1,tmp);
                }
            }
        }
        for (int i=0;i<newMarkets.size();i++){
            System.out.println(i + ")" + newMarkets.get(i).getName());
        }
    }

    public void showProducts(){
        if (products.size() == 0){
            System.out.println("There are no products yet !!");
            return;
        }
        for (int i=0;i< products.size();i++){
            System.out.println(i + ") " + products.get(i).getName());
        }
    }

    public void declareDelivery(){
        DeliveryDuty.declareDelivery(deliveries);
    }

    public void showDeliveries(){
        DeliveryDuty.showDeliveries(deliveries);
    }

    public void setDeclaredDeliveries(){
        DeliveryDuty.setDeclaredDeliveries(deliveries);
    }

    public void showOrdersOfDelivery(){
        DeliveryDuty.showOrdersOfDelivery(deliveries);
    }

    public int findIndexOrder(Order order){
        for(int i=0;i< orders.size();i++){
            if(orders.get(i).equals(order)){
                return i;
            }
        }
        return 1;
    }
}
