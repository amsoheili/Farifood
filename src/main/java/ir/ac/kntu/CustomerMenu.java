package ir.ac.kntu;

import java.util.ArrayList;

public class CustomerMenu {
    private Customer customer;

    public CustomerMenu(Customer customer){
        this.customer = customer;
    }

    public void mainMenu(Manager manager){
        System.out.println("Hello customer , what do you want to do ?" +
                "\n1) Order product" +
                "\n2) Show comments" +
                "\n3) Show orders" +
                "\n9) Exit");
        mainMenuHandler(manager);
    }

    public void mainMenuHandler(Manager manager){
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                orderProduct(manager);
                break;
            case 2:
                showComments();
                break;
            case 3:
                showOrders();
                break;
            case 9:
                return;
            default:
                System.out.println("Incorrect input !! please try again ");
                mainMenu(manager);
                break;
        }
    }

    public void orderProduct(Manager manager){
        System.out.println("Select the market : ( 1- Restaurant  2- Supermarket  3-Grocery Store )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                manager.orderFoodRestaurant();
                break;
            case 2:
                manager.orderFoodSuperMarket();
                break;
            case 3:
                manager.orderFoodGrocery();
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                orderProduct(manager);
                break;
        }
    }

    public void showComments(){
        if (customer.getComments().size() == 0){
            System.out.println("There are no comments yet !!");
            return;
        }
        for (int i=0;i<customer.getComments().size();i++){
            System.out.println(i + ") " + customer.getComments().get(i).toString());
        }
    }

    public void showOrders(){
        if (customer.getOrders().size() == 0){
            System.out.println("There are no orders yet !!");
            return;
        }
        for (int i=0;i<customer.getOrders().size();i++){
            System.out.println(i + ") " + customer.getOrders().get(i).toString());
        }
    }
}
