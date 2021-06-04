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
                customerIdentify();
                break;
            case 3:
                marketBossIdentify();
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
            adminHandler();
        }else{
            System.out.println("There are no admin with the given information !!");
        }
    }

    public void adminHandler(){
        System.out.println("As an admin , select what do you want " +
                "\n1) Admin stuff" +
                "\n2) Market stuff" +
                "\n3) Exit");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.start(manager);
                break;
            case 2:
                adminStuffMenu();
                break;
            case 3:
                start();
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                adminHandler();
                break;
        }
    }

    public void adminStuffMenu(){
        System.out.println("What do you want ? " +
                "\n1) Declare a new admin" +
                "\n2) Show all of the admins" +
                "\n3) Set an admin" +
                "\n4) Exit");
                adminStuffHandler(manager);
    }

    public void adminStuffHandler(Manager manager){
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                AdminDuty.declareAdmin(users);
                break;
            case 2:
                AdminDuty.showAdmins(users);
                break;
            case 3:
                AdminDuty.setDeclaredAdmin(users);
                break;
            case 4:
                adminHandler();
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                adminStuffMenu();
                break;
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
        Customer customer = recognizeCustomer(tempUserName,tempPassWord);
        if(customer != null){
            CustomerMenu customerMenu = new CustomerMenu(customer);
            customerMenu.mainMenu(manager);
        }else{
            System.out.println("There are no customer with the given information !!");
        }
    }

    public Customer recognizeCustomer(String tempUserName,String tempPassWord){
        for(User user:users){
            if (user instanceof Customer && user.getUserName().equals(tempUserName)
                    && user.getPassWord().equals(tempPassWord)){
                return (Customer) user;
            }
        }
        return null;
    }

    public void marketBossIdentify(){
        System.out.println("Enter your username: ");
        String tempUserName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter your password: ");
        String tempPassWord = ScannerWrapper.getInstance().nextLine();
        if(verifyMarketBoss(tempUserName,tempPassWord)){
            //MarketBossMenu.start();
        }else{
            System.out.println("There are no restaurant boss with the given information !!");
        }


    }

    public boolean verifyMarketBoss(String tempUserName,String tempPassWord){
        for (User user:users){
            if (user instanceof MarketBoss && user.getUserName().equals(tempUserName)
                    && user.getPassWord().equals(tempPassWord)){
                return true;
            }
        }
        return false;
    }
}
