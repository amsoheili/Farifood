package ir.ac.kntu.Markets;

import ir.ac.kntu.Logic.OrderStatus;
import ir.ac.kntu.Logic.RestaurantType;
import ir.ac.kntu.Logic.ScannerWrapper;
import ir.ac.kntu.Product.Food;
import ir.ac.kntu.Product.Product;
import ir.ac.kntu.Users.Customer;
import ir.ac.kntu.Users.User;

import java.util.ArrayList;

public class Restaurant extends Market {
    private RestaurantType restaurantType;

    Restaurant() { }

    public Restaurant(String name,String address,int openTime,int closeTime,int deliveryMultiplicity,RestaurantType restaurantType){
        super(name, address, openTime, closeTime, deliveryMultiplicity);
        this.restaurantType = restaurantType;
    }

    public Restaurant(String name,String address,int openTime,int closeTime,int deliveryMultiplicity,RestaurantType restaurantType,int score){
        this(name,address,openTime,closeTime,deliveryMultiplicity,restaurantType);
        setScore(score);

    }

    @Override
    public void addProduct(ArrayList<Product> products){
        System.out.println("Name: ");
        String tempName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Price: ");
        double tempPrice = ScannerWrapper.getInstance().nextDouble();
        System.out.println("Cook time (hour): ");
        double tempCookTime = ScannerWrapper.getInstance().nextDouble();
        products.add(new Food(tempName,tempPrice,tempCookTime));
        getProducts().add(new Food(tempName,tempPrice,tempCookTime));
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
    public void orderProduct(ArrayList<Order> orders, User user){
        System.out.println("Which food ? ");
        showFoodMenu();
        int orderChoice = ScannerWrapper.getInstance().nextInt();
        if (getDeliveries().size() == 0){
            System.out.println("There are no deliveries yet !!");
        }else{
            System.out.println("Select the delivery : ");
            showDeliveries();
            int deliveryChoice = ScannerWrapper.getInstance().nextInt();
            getDeliveries().get(deliveryChoice).getOrders().add(new Order(getProducts().get(orderChoice), OrderStatus.PROCESSING));
        }
        getOrders().add(new Order(getProducts().get(orderChoice), OrderStatus.PROCESSING));
        orders.add(new Order(getProducts().get(orderChoice), OrderStatus.PROCESSING));
        ((Customer)user).getOrders().add(new Order(getProducts().get(orderChoice), OrderStatus.PROCESSING));
        System.out.println("Done");
    }



}
