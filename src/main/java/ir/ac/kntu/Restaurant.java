package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant extends Market{
    private String name;
    private String address;
    private ArrayList<Order> orders;
    private int score=5;
    private double openTime;
    private double closeTime;
    private ArrayList<Comment> comments;
    private int deliveryMultiplicity;
    private ArrayList<Delivery> deliveries;
    private RestaurantType restaurantType;

    Restaurant() { }

    Restaurant(String name,String address,int openTime,int closeTime,int deliveryMultiplicity,RestaurantType restaurantType){
        super(name, address, openTime, closeTime, deliveryMultiplicity);
        this.restaurantType = restaurantType;
    }

    Restaurant(String name,String address,int openTime,int closeTime,int deliveryMultiplicity,RestaurantType restaurantType,int score){
        this(name,address,openTime,closeTime,deliveryMultiplicity,restaurantType);
        this.score = score;

    }

    public void addFood(Product food){
        getProducts().add(food);
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public void showFoodMenu(){
        for(int i=0;i<getProducts().size();i++){
            System.out.println(i + ")\n" + getProducts().get(i));
        }
    }

    @Override
    public void orderProduct(ArrayList<Order> orders){
        System.out.println("Which food ? ");
        showFoodMenu();
        int orderChoice = ScannerWrapper.getInstance().nextInt();
        if (deliveries.size() == 0){
            System.out.println("There are no deliveries yet !!");
        }else{
            System.out.println("Select the delivery : ");
            showDeliveries();
            int deliveryChoice = ScannerWrapper.getInstance().nextInt();
            deliveries.get(deliveryChoice).getOrders().add(new Order(getProducts().get(orderChoice), OrderStatus.PROCESSING));
        }
        this.orders.add(new Order(getProducts().get(orderChoice), OrderStatus.PROCESSING));
        orders.add(new Order(getProducts().get(orderChoice), OrderStatus.PROCESSING));
        System.out.println("Done");
    }



}
