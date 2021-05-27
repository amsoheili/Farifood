package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant {
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

    public void setRestaurantType(RestaurantType restaurantType) {
        this.restaurantType = restaurantType;
    }

    public void setDeliveries(ArrayList<Delivery> deliveries){
        this.deliveries = deliveries;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setFoodMenu(ArrayList<Food> foodMenu){
        this.foodMenu=foodMenu;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void addFood(Food food){
        this.foodMenu.add(food);
    }

    public void addComment(String foodName,ArrayList<Comment> comments){
        System.out.println("Write your main text: ");
        String commentText = ScannerWrapper.getInstance().nextLine();
        this.comments.add(new Comment(foodName,commentText));
        comments.add(new Comment(foodName,commentText));
        System.out.println("Done !!");
    }

    public void addScore(){
        System.out.println("Rate the restaurant from 1 to 5 : ");
        int choice = ScannerWrapper.getInstance().nextInt();
        if(choice>=1 && choice<=5){
            setScore(getScore()+choice);
            System.out.println("Thanks for rating the restaurant .");
        }else{
            System.out.println("incorrect rate .");
            addScore();
        }
    }

    public void addDelivery(Delivery delivery){
        deliveries.add(delivery);
        delivery.setWorkPlaceNumber(delivery.getWorkPlaceNumber()+1);
    }

    public void setComments(ArrayList<Comment> comments){
        this.comments = comments;
    }

    public void setOpenTime(double openTime){
        this.openTime = openTime;
    }

    public void setCloseTime(double closeTime){
        this.closeTime = closeTime;
    }

    public void showComments(){
        for (int i=0;i<comments.size();i++){
            System.out.println(i + ")\n" + comments.get(i));
        }
    }

    public void setDeliveryMultiplicity(int deliveryMultiplicity) {
        this.deliveryMultiplicity = deliveryMultiplicity;
    }

    public ArrayList<Delivery> getDeliveries(){
        return deliveries;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public String getName() {
        return name;
    }

    public String getAddress(){
        return this.address;
    }

    public ArrayList<Food> getFoodMenu(){
        return this.foodMenu;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public Order getOrder(int orderIndex) {
        return orders.get(orderIndex);
    }

    public int getScore(){
        return this.score;
    }

    public double getOpenTime(){
        return this.openTime;
    }

    public double getCloseTime(){
        return this.closeTime;
    }

    public ArrayList<Comment> getComments(){
        return this.comments;
    }

    public int getDeliveryMultiplicity(){
        return this.deliveryMultiplicity;
    }

    public void showFoodMenu(){
        for(int i=0;i<foodMenu.size();i++){
            System.out.println(i + ")\n" + foodMenu.get(i));
        }
    }

    public void orderFood(ArrayList<Order> orders){
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

    public void showDeliveries(){
        for (int i=0;i<deliveries.size();i++){
            System.out.println(i + ")" + deliveries.get(i).getName());
        }
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


    public void showOrders(){
        for(int i=0;i<orders.size();i++){
            System.out.println(i +")\n" + orders.get(i).toString());
        }
    }


    @Override
    public String toString(){
        return "name: " + name + "\n" +
                "Address: " + address + "\n" +
                "score: " + String.valueOf(score) + "\n" +
                "oprn time: " + String.valueOf(openTime) + "\n" +
                "close time: " + String.valueOf(closeTime) + "\n" +
                "Number of deliveris: " + String.valueOf(deliveryMultiplicity);

    }
}
