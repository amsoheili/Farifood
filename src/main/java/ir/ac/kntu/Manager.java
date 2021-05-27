package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.*;

public class Manager {
    private ArrayList<Restaurant> restaurants;
    private ArrayList<Order> orders;
    private ArrayList<Customer> customers;
    private ArrayList<Food> foods;
    private ArrayList<Delivery> deliveries;
    private ArrayList<Comment> comments;
    private HashMap<Integer,Customer> idCustomer;


    public Manager() {
        this.restaurants = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.idCustomer = new HashMap<>();
        this.foods = new ArrayList<>();
        this.deliveries = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    public void addRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
    }

    public void addCustomers(Customer customer) {
        this.customers.add(customer);
        this.idCustomer.put(customer.getId(),customer);
    }

    public ArrayList<Restaurant> getRestaurants(){
        return restaurants;
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

    public void orderFood(){
        OrderDuty.orderFood(restaurants,orders);
    }

    public void showOrders(){
        OrderDuty.showOrders(orders);
    }

    public void showRestaurants() {
        OrderDuty.showRestaurants(restaurants,true);
    }

    public void filterOrders(){
        OrderDuty.filterOrders(orders,restaurants,comments);
    }

    public void addComment(){
        OrderDuty.addComment(orders,restaurants,comments);
    }

    public void addScore(){
        OrderDuty.addScore(orders,restaurants,comments);
    }

    public void setOrderStatus(){
        OrderDuty.setOrderStatus(restaurants,this);
    }

    public void declareRestaurant() {
        RestaurantDuty.declareRestaurant(restaurants);
    }

    public void editRestaurant(){
        RestaurantDuty.editRestaurant(restaurants);
    }

    public void showRestaurantScore(){
        RestaurantDuty.showRestaurantScore(restaurants);
    }

    public void showRestaurantComments(){
        RestaurantDuty.showRestaurantComments(restaurants);
    }

    public void addDelivery(){
        RestaurantDuty.addDelivery(restaurants,deliveries);
    }

    public void addFood(){
        RestaurantDuty.addFood(restaurants,foods);
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

    public void createCustomer(){
        System.out.println("Enter the customer's ID: ");
        int tempId = ScannerWrapper.getInstance().nextInt();
        System.out.println("Enter the username: ");
        String tempUserName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the password: ");
        String tempPassWord = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the customer's address: ");
        String tempAddress = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the customer's phone number: ");
        long tempPhoneNumber = ScannerWrapper.getInstance().nextInt();
        customers.add(new Customer(tempId,tempUserName,tempPassWord,tempPhoneNumber,tempAddress));
        System.out.println("Done");
    }

    public void editCustomer(){
        if(customers.size() == 0){
            System.out.println("There are no customers yet !!");
            return;
        }
        System.out.println("Select the customer that you want to edit: ");
        showCustomers();
        int choice = ScannerWrapper.getInstance().nextInt();
        editCustomerMenuHandler(choice);
    }

    public void editCustomerMenuHandler(int customerCode){
        System.out.println("Which part do you want to change ?" +
                "\n1) Id" +
                "\n2) Username " +
                "\n3) Password" +
                "\n4) Phone number" +
                "\n5) Address" +
                "\n6) Exit");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                System.out.println("Enter the new id: ");
                int newId = ScannerWrapper.getInstance().nextInt();
                customers.get(customerCode).setId(newId);
                break;
            case 2:
                System.out.println("Enter the new username: ");
                String newUserName = ScannerWrapper.getInstance().nextLine();
                customers.get(customerCode).setUserName(newUserName);
                break;
            case 3:
                System.out.println("Enter the new password: ");
                String newPassWord = ScannerWrapper.getInstance().nextLine();
                customers.get(customerCode).setPassWord(newPassWord);
                break;
            case 4:
                System.out.println("Enter the new phone number : ");
                int newPhoneNumber = ScannerWrapper.getInstance().nextInt();
                customers.get(customerCode).setPhoneNumber(newPhoneNumber);
                break;
            case 5:
                System.out.println("Enter the new address: ");
                String newAddress = ScannerWrapper.getInstance().nextLine();
                customers.get(customerCode).setAddress(newAddress);
                break;
            case 6:
                return;
            default:
                editCustomerMenuHandler(customerCode);
                break;
        }
        editCustomerMenuHandler(customerCode);
    }

    public void showCustomers(){
        for(int i=0;i<customers.size();i++){
            System.out.println(i +") "+ customers.get(i).getId());
        }
    }

    public void setOrderFood(){
        OrderDuty.setOrderFood(restaurants);
    }

    public void searchRestaurant(){
        RestaurantDuty.searchRestaurant(restaurants);
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
}
