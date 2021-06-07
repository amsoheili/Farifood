package ir.ac.kntu.Users;

import ir.ac.kntu.Markets.Order;

import java.util.ArrayList;

public class Customer extends User {
    private long phoneNumber;
    private String address;
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Comment> comments = new ArrayList<>();

    Customer(){}

    public Customer(int id,String userName,String passWord,long phoneNumber,String address){
        super(id,userName,passWord);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    public void setPhoneNumber(long phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address){

        this.address = address;
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public long getPhoneNumber() {

        return phoneNumber;
    }

    public String getAddress() {

        return address;
    }

    public ArrayList<Comment> getComments(){
        return comments;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString(){
        return super.toString() +
                "\nPhone number: " + this.phoneNumber +
                "\nAddress: " + this.address;
    }
}
