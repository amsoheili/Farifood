package ir.ac.kntu;

import java.util.ArrayList;

public class Market {
    private String name;
    private String address;
    private ArrayList<Order> orders;
    private int score=5;
    private double openTime;
    private double closeTime;
    private ArrayList<Comment> comments;
    private int deliveryMultiplicity;
    private ArrayList<Delivery> deliveries;


    Market(){
        orders = new ArrayList<>();
        comments = new ArrayList<>();
        deliveries = new ArrayList<>();
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

    public void orderProduct(ArrayList<Order> orders){
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
