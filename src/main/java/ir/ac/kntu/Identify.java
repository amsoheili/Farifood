package ir.ac.kntu;

import ir.ac.kntu.Logic.ScannerWrapper;
import ir.ac.kntu.Markets.Market;
import ir.ac.kntu.Menu.AdminMenu;
import ir.ac.kntu.Menu.CustomerMenu;
import ir.ac.kntu.Menu.MarketBossMenu;
import ir.ac.kntu.Users.Admin;
import ir.ac.kntu.Users.Customer;
import ir.ac.kntu.Users.MarketBoss;
import ir.ac.kntu.Users.User;
import ir.ac.kntu.Utility.AdminDuty;

import java.util.ArrayList;

public class Identify {
    private Manager manager;

    private ArrayList<User> users;

    Identify(Manager manager){
        this.manager = manager;
        this.users = new ArrayList<>();
        manager.setUsers(users);
    }

    public boolean start(){
        System.out.println("Who are you ?" +
                "\n1) Admin" +
                "\n2) Customer" +
                "\n3) Market boss" +
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
        Admin currentAdmin = findAdmin(tempUserName,tempPassWord);
        if(currentAdmin != null){
            adminHandler(currentAdmin);
        }else{
            System.out.println("There are no admin with the given information !!");
        }
    }

    public Admin findAdmin(String tempUserName,String tempPassWord){
        for(User user:users){
            if (user instanceof Admin && user.getUserName().equals(tempUserName)
                    && user.getPassWord().equals(tempPassWord)){
                return (Admin) user;
            }
        }
        return null;
    }

    public void adminHandler(Admin currentAdmin){
        System.out.println("As an admin , select what do you want " +
                "\n1) Admin stuff" +
                "\n2) Market stuff" +
                "\n3) Show users" +
                "\n4) Exit");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                adminStuffMenu(currentAdmin);
                break;
            case 2:
                AdminMenu adminMenu = new AdminMenu(currentAdmin);
                adminMenu.start(manager);
                break;
            case 3:
                showUsers();
                break;
            case 4:
                start();
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                adminHandler(currentAdmin);
                break;
        }
    }

    public void showUsers(){
        for (int i=0;i<users.size();i++){
            System.out.println(i + ") " + users.get(i).getUserName());
        }
    }

    public void adminStuffMenu(Admin currentAdmin){
        System.out.println("What do you want ? " +
                "\n1) Declare a new admin" +
                "\n2) Show all of the admins" +
                "\n3) Set an admin" +
                "\n4) Exit");
                adminStuffHandler(manager,currentAdmin);
    }

    public void adminStuffHandler(Manager manager,Admin currentAdmin){
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
                adminHandler(currentAdmin);
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                adminStuffMenu(currentAdmin);
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
        MarketBoss tempMarketBoss = recognizeMarketBoss(tempUserName,tempPassWord);
        if(tempMarketBoss != null){
            MarketBossMenu marketBossMenu = new MarketBossMenu(findBossMarket(tempMarketBoss));
            marketBossMenu.mainMenu(manager);
        }else{
            System.out.println("There are no boss with the given information !!");
        }
    }

    public MarketBoss recognizeMarketBoss(String tempUserName,String tempPassWord){
        for (User user:users){
            if (user instanceof MarketBoss && user.getUserName().equals(tempUserName)
                    && user.getPassWord().equals(tempPassWord)){
                return (MarketBoss) user;
            }
        }
        return null;
    }

    public Market findBossMarket(MarketBoss marketBoss){
        for (int i=0;i<manager.getMarkets().size();i++){
            if (manager.getMarkets().get(i).getMarketBoss().equals(marketBoss)){
                return manager.getMarkets().get(i);
            }
        }
        return null;
    }
}
