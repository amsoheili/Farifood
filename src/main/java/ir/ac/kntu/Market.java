package ir.ac.kntu;

import java.util.ArrayList;

public class Market {
    private MarketBoss marketBoss;
    private String name;
    private String address;
    private ArrayList<Order> orders;
    private int score=5;
    private double openTime;
    private double closeTime;
    private ArrayList<Comment> comments;
    private int deliveryMultiplicity;
    private ArrayList<Delivery> deliveries;
    private ArrayList<Product> products;

    Market(){
        products = new ArrayList<>();
        orders = new ArrayList<>();
        comments = new ArrayList<>();
        deliveries = new ArrayList<>();
    }

    Market(String name,String address,int openTime,int closeTime,int deliveryMultiplicity){
        this.name = name;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.deliveryMultiplicity = deliveryMultiplicity;
        products = new ArrayList<>();
        orders = new ArrayList<>();
        comments = new ArrayList<>();
        deliveries = new ArrayList<>();
    }

    public void setMarketBoss(MarketBoss marketBoss) {
        this.marketBoss = marketBoss;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
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

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void addComment(String foodName,ArrayList<Comment> comments){
        System.out.println("Write your main text: ");
        String commentText = ScannerWrapper.getInstance().nextLine();
        this.comments.add(new Comment(foodName,commentText));
        comments.add(new Comment(foodName,commentText));
        System.out.println("Done !!");
    }

    public void addScore(){
        System.out.println("Rate us from 1 to 5 : ");
        int choice = ScannerWrapper.getInstance().nextInt();
        if(choice>=1 && choice<=5){
            setScore(getScore()+choice);
            System.out.println("Thanks for rating .");
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

    public MarketBoss getMarketBoss() {
        return marketBoss;
    }

    public ArrayList<Delivery> getDeliveries(){
        return deliveries;
    }

    public String getName() {
        return name;
    }

    public String getAddress(){
        return this.address;
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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void showOrders(){
        for(int i=0;i<orders.size();i++){
            System.out.println(i +")\n" + orders.get(i).toString());
        }
    }

    public void showDeliveries(){
        for (int i=0;i<deliveries.size();i++){
            System.out.println(i + ")" + deliveries.get(i).getName());
        }
    }

    public void orderProduct(ArrayList<Order> orders,User user){
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

    public void showProducts(){
        for (int i=0;i< products.size();i++){
            System.out.println(i + ") " + products.get(i).getName());
        }
    }

    public void editMarket(){
        System.out.println("Which one do you want to change ? " +
                "\n1) Name" +
                "\n2) Address" +
                "\n3) Open time" +
                "\n4) Close time" );
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                System.out.println("Enter the new name : ");
                String newName = ScannerWrapper.getInstance().nextLine();
                setName(newName);
                System.out.println("<<<<< Done >>>>>");
                break;
            case 2:
                System.out.println("Enter the new Address:");
                String newAddress = ScannerWrapper.getInstance().nextLine();
                setAddress(newAddress);
                System.out.println("Market Address changed .");
                break;
            case 3:
                System.out.println("Enter the new open time:");
                double newOpenTime = ScannerWrapper.getInstance().nextDouble();
                setOpenTime(newOpenTime);
                System.out.println("Market Open time changed .");
                break;
            case 4:
                System.out.println("Enter the new close time:");
                double newCloseTime = ScannerWrapper.getInstance().nextDouble();
                setCloseTime(newCloseTime);
                System.out.println("Market Close time changed .");
                break;
            default:
                System.out.println("Incorrect input !! try again ");
                editMarket();
                break;
        }
    }

    public void addProduct(ArrayList<Product> products){

    }

    @Override
    public String toString(){
        return "name: " + name + "\n" +
                "Address: " + address + "\n" +
                "score: " + String.valueOf(score) + "\n" +
                "open time: " + String.valueOf(openTime) + "\n" +
                "close time: " + String.valueOf(closeTime) + "\n" +
                "Number of deliveris: " + String.valueOf(deliveryMultiplicity);

    }
}
