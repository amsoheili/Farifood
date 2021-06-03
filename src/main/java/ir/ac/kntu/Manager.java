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

    public void addRestaurant(Restaurant restaurant){
        markets.add(restaurant);
    }

    public void addCustomers(Customer customer) {
        this.customers.add(customer);
        this.idCustomer.put(customer.getId(),customer);
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

    public HashMap<Integer,Customer> getIdCustomer(){
        return this.idCustomer;
    }

    public ArrayList<Delivery> getDeliveries() {
        return this.deliveries;
    }

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public ArrayList<Food> getFoods() {
        return this.foods;
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

    public void showCommentOfFood(){
        RestaurantDuty.showCommentOfFood(foods,comments);
    }

    public void showBestRestaurantsForMentionedFood(){
        RestaurantDuty.showBestRestaurantsForMentionedFood(markets,products);
    }

    public void showFoods(){
        RestaurantDuty.showFoods(foods);
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

    public int findIndexOrder(Order order){
        for(int i=0;i< orders.size();i++){
            if(orders.get(i).equals(order)){
                return i;
            }
        }
        return 1;
    }

    public void showOrdersOfDelivery(){
        DeliveryDuty.showOrdersOfDelivery(deliveries);
    }
}
