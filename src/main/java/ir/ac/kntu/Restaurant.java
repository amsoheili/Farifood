package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant extends Market{
    private String name;
    private String address;
    private ArrayList<Food> foodMenu;
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
        this.name = name;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.deliveryMultiplicity = deliveryMultiplicity;
        this.restaurantType = restaurantType;
        this.orders = new ArrayList<>();
        this.foodMenu = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.deliveries = new ArrayList<>();
    }

    Restaurant(String name,String address,int openTime,int closeTime,int deliveryMultiplicity,RestaurantType restaurantType,int score){
        this.name = name;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.deliveryMultiplicity = deliveryMultiplicity;
        this.restaurantType = restaurantType;
        this.score = score;
        this.orders = new ArrayList<>();
        this.foodMenu = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.deliveries = new ArrayList<>();
    }

    public void setFoodMenu(ArrayList<Food> foodMenu){
        this.foodMenu=foodMenu;
    }

    public void addFood(Food food){
        this.foodMenu.add(food);
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public ArrayList<Food> getFoodMenu(){
        return this.foodMenu;
    }

    public void showFoodMenu(){
        for(int i=0;i<foodMenu.size();i++){
            System.out.println(i + ")\n" + foodMenu.get(i));
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
            deliveries.get(deliveryChoice).getOrders().add(new Order(foodMenu.get(orderChoice), OrderStatus.PROCESSING));
        }
        this.orders.add(new Order(foodMenu.get(orderChoice), OrderStatus.PROCESSING));
        orders.add(new Order(foodMenu.get(orderChoice), OrderStatus.PROCESSING));
        System.out.println("Done");
    }

    public void setOrderStatus(Manager manager){
        if(orders.size() == 0){
            System.out.println("Orders is empty .");
            return;
        }
        System.out.println("Which one ?");
        showOrders();
        int choice = ScannerWrapper.getInstance().nextInt();
        System.out.println("The order is this:"+orders.get(choice).toString());
        System.out.println("What do you want change it's status into ?" +
                "\n1) " + OrderStatus.PROCESSING +
                "\n2) " + OrderStatus.SENDING +
                "\n3) " + OrderStatus.DELIVERED);
        int choice1 = ScannerWrapper.getInstance().nextInt();
        switch (choice1){
            case 1:
                this.orders.get(choice).setStatus(OrderStatus.PROCESSING);
                manager.getOrders().get(manager.findIndexOrder(this.orders.get(choice))).setStatus(OrderStatus.PROCESSING);
                break;
            case 2:
                this.orders.get(choice).setStatus(OrderStatus.SENDING);
                manager.getOrders().get(manager.findIndexOrder(this.orders.get(choice))).setStatus(OrderStatus.SENDING);
                break;
            case 3:
                this.orders.get(choice).setStatus(OrderStatus.DELIVERED);
                manager.getOrders().get(manager.findIndexOrder(this.orders.get(choice))).setStatus(OrderStatus.DELIVERED);
                break;
            default:
                setOrderStatus(manager);
                break;
        }
    }

}
