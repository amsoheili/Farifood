package ir.ac.kntu;

import java.util.ArrayList;

public class Identify {
    private Manager manager;

    private ArrayList<User> users;

    Identify(Manager manager){
        this.manager = manager;
        this.users = new ArrayList<>();
    }

    public boolean start(){
        System.out.println("Who are you ?" +
                "\n1) Admin" +
                "\n2) Customer" +
                "\n3) Restaurant boss" +
                "\n4) Exit");
        return startHandler();
    }

    public boolean startHandler(){
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                adminIdentify();
                break;
            case 2:
                //customerIdentify();
                break;
            case 3:
                //resBossIdentify();
                break;
            case 4:
                return false;
            default:
                System.out.println("Incorrect input !! please try again ");
                start();
                break;
        }
        return true;
    }

    public ArrayList getUsers(){
        return users;
    }

    public void adminIdentify(){
        System.out.println("Enter your username: ");
        String tempUserName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter your password: ");
        String tempPassWord = ScannerWrapper.getInstance().nextLine();
        if(verifyAdmin(tempUserName,tempPassWord)){
            //AdminMenu.start();
        }else{
            System.out.println("There are no admin with the given information !!");
        }
    }

    public boolean verifyAdmin(String tempUserName,String tempPassWord){
        for(User user:users){
            if (user instanceof Admin && user.getUserName().equals(tempUserName)
                    && user.getPassWord().equals(tempPassWord)){
                return true;
            }
        }
        return false;
    }

    public void customerIdentify(){
        System.out.println("Enter your username: ");
        String tempUserName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter your password: ");
        String tempPassWord = ScannerWrapper.getInstance().nextLine();
        if(verifyCustomer(tempUserName,tempPassWord)){
            //CustomerMenu.start();
        }else{
            System.out.println("There are no customer with the given information !!");
        }
    }

    public boolean verifyCustomer(String tempUserName,String tempPassWord){
        for(User user:users){
            if (user instanceof Customer && user.getUserName().equals(tempUserName)
                    && user.getPassWord().equals(tempPassWord)){
                return true;
            }
        }
        return false;
    }
}
