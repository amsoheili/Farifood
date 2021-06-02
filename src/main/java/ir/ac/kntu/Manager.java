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

    public void showCommentOfFood(){
        RestaurantDuty.showCommentOfFood(foods,comments);
    }

    public void showBestFood(){
        RestaurantDuty.showBestFood(restaurants);
    }

    public void showBestRestaurantsForMentionedFood(){
        RestaurantDuty.showBestRestaurantsForMentionedFood(restaurants,foods);
    }

    public void showFoods(){
        RestaurantDuty.showFoods(foods);
    }

    public void declareDelivery(){
        System.out.println("Name : ");
        String tempName = ScannerWrapper.getInstance().nextLine();
        TransportationVehicle tempVehicle = takeDeliveryTransportationVehicle();
        SalaryType tempSalaryType = takeDeliverySalaryType();
        System.out.println("Amount of salary: ");
        double tempSalary = ScannerWrapper.getInstance().nextInt();
        deliveries.add(new Delivery(tempName,tempVehicle,tempSalaryType,tempSalary));
        System.out.println("<<<< Done >>>>");
    }

    public TransportationVehicle takeDeliveryTransportationVehicle(){
        System.out.println("what kind of vehicle does the delivery have ? ( 1-Car   2-Motorcycle )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                return TransportationVehicle.CAR;
            case 2:
                return TransportationVehicle.MOTORCYCLE;
            default:
                System.out.println("Incorrect input !! please try again ");
                takeDeliveryTransportationVehicle();
        }
        return null;
    }

    public SalaryType takeDeliverySalaryType(){
        System.out.println("What kind of salary does the delivery have ? ( 1- Per hour  2- Per order )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                return SalaryType.PERHOUR;
            case 2:
                return SalaryType.PERORDER;
            default:
                System.out.println("Incorrect input !! please try again ");
                takeDeliverySalaryType();
        }
        return null;
    }

    public void showDeliveries(){
        if(deliveries.size() == 0){
            System.out.println("There are no delivery yet.");
            return;
        }
        System.out.println("How do you want to see the deliveries ? ( 1- normal  2-based on descending score )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                for (int i=0;i< deliveries.size();i++){
                    System.out.println(i + ")" + deliveries.get(i).toString());
                }
                break;
            case 2:
                descendingShowDeliveries();
                break;
            default:
                showDeliveries();
                break;
        }
    }

    public void descendingShowDeliveries(){
        for (int i=0;i< deliveries.size();i++){
            for (int j=0;j<deliveries.size() - i - 1;j++){
                if(deliveries.get(i).getScore() < deliveries.get(i+1).getScore()){
                    Delivery tmp = deliveries.get(i);
                    deliveries.set(i,deliveries.get(i+1));
                    deliveries.set(i+1,tmp);
                }
            }
        }
        for (int i=0;i< deliveries.size();i++){
            System.out.println(i + ")" + deliveries.get(i).toString());
        }
    }

    public void setDeclaredDeliveries(){
        if (deliveries.size() == 0){
            System.out.println("There are no delivery yet.");
            return;
        }
        System.out.println("Which delivery do you want to change ? ");
        showDeliveries();
        int choice = ScannerWrapper.getInstance().nextInt();
        setDeclaredDeliveriesHandler(choice);
    }

    public void setDeclaredDeliveriesHandler(int deliveryCode){
        System.out.println("What do you want to change ? " +
                "\n1) Name" +
                "\n2) Vehicle" +
                "\n3) Salary type" +
                "\n4) Salary" +
                "\n5) Score" +
                "\n6) Exit");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                changeDeliveryName(deliveryCode);
                break;
            case 2:
                changeDeliveryVehicle(deliveryCode);
                break;
            case 3:
                changeDeliverySalaryType(deliveryCode);
                break;
            case 4:
                changeDeliverySalary(deliveryCode);
                break;
            case 5:
                changeDeliveryScore(deliveryCode);
                break;
            case 6:
                return;
            default:
                System.out.println("Incorrect input !!! please try again");
                setDeclaredDeliveriesHandler(deliveryCode);
                break;
        }
        setDeclaredDeliveriesHandler(deliveryCode);
    }

    public void changeDeliveryName(int deliveryCode){
        System.out.println("Enter the new name : ");
        String newName = ScannerWrapper.getInstance().nextLine();
        deliveries.get(deliveryCode).setName(newName);
        System.out.println("<<<< Done >>>>");
    }

    public void changeDeliveryVehicle(int deliveryCode){
        System.out.println("What is the delivery's new vehicle ? ( 1-Car  2-Motorcycle )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                deliveries.get(deliveryCode).setVehicle(TransportationVehicle.CAR);
                break;
            case 2:
                deliveries.get(deliveryCode).setVehicle(TransportationVehicle.MOTORCYCLE);
                break;
            default:
                System.out.println("Incorrect input !!! please try again ");
                changeDeliveryVehicle(deliveryCode);
                break;
        }
        System.out.println("<<<< Done >>>>");
    }

    public void changeDeliverySalaryType(int deliveryCode){
        System.out.println("What is the delivery's new salary type ? ( 1- Per hour  2- Per order )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                deliveries.get(deliveryCode).setSalaryType(SalaryType.PERHOUR);
                break;
            case 2:
                deliveries.get(deliveryCode).setSalaryType(SalaryType.PERORDER);
                break;
            default:
                System.out.println("Incorrect input !!! please try again ");
                changeDeliverySalaryType(deliveryCode);
                break;
        }
        System.out.println("<<<< Done >>>>");
    }

    public void changeDeliverySalary(int deliveryCode){
        System.out.println("What is the delivery's new salary ? ");
        double newSalary = ScannerWrapper.getInstance().nextDouble();
        deliveries.get(deliveryCode).setSalary(newSalary);
        System.out.println("<<<< Done >>>>");
    }

    public void changeDeliveryScore(int deliveryCode){
        System.out.println("Current Score : " + deliveries.get(deliveryCode).getScore() +
                "\nWhat is the new Score ? ");
        int newScore = ScannerWrapper.getInstance().nextInt();
        deliveries.get(deliveryCode).setScore(newScore);
        System.out.println("<<<< Done >>>>");
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
        if (deliveries.size() == 0){
            System.out.println("There are no deliveries yet !!");
            return;
        }
        showDeliveries();
        int choice = ScannerWrapper.getInstance().nextInt();
        if (deliveries.get(choice).getOrders().size() == 0){
            System.out.println("Orders list is empty !!");
            return;
        }
        for (int i=0;i<deliveries.get(choice).getOrders().size();i++){
            System.out.println(i + ")" + deliveries.get(choice).getOrders().get(i));
        }
    }
}
