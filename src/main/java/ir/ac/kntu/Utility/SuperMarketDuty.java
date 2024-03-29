package ir.ac.kntu.Utility;

import ir.ac.kntu.*;
import ir.ac.kntu.Logic.ScannerWrapper;
import ir.ac.kntu.Markets.Delivery;
import ir.ac.kntu.Markets.Market;
import ir.ac.kntu.Markets.SuperMarket;
import ir.ac.kntu.Product.Product;
import ir.ac.kntu.Users.MarketBoss;

import java.util.ArrayList;

public class SuperMarketDuty {

    public static void declareSuperMarket(Manager manager) {
        System.out.println("Declaring SuperMarket:");
        System.out.println("Name: ");
        String tempName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Address: ");
        String tempAddress = ScannerWrapper.getInstance().nextLine();
        System.out.println("Open time(Hour): ");
        int tempOpenTime = ScannerWrapper.getInstance().nextInt();
        System.out.println("Close time(Hour): ");
        int tempCloseTime = ScannerWrapper.getInstance().nextInt();
        System.out.println("Delivery multiplicity: ");
        int tempDeliveryMultiplicity = ScannerWrapper.getInstance().nextInt();
        System.out.println("Enter the max number of orders in each period");
        int maxOrderPerEachPeriod = ScannerWrapper.getInstance().nextInt();
        if (manager.getMarkets().contains(new SuperMarket(tempName, tempAddress, tempOpenTime,
                tempCloseTime, tempDeliveryMultiplicity,maxOrderPerEachPeriod))) {
            System.out.println("This supermarket has been declared before !!");
            return;
        } else {
            SuperMarket tempSuperMarket = new SuperMarket(tempName, tempAddress, tempOpenTime,
                    tempCloseTime, tempDeliveryMultiplicity,maxOrderPerEachPeriod);
            tempSuperMarket.setMarketBoss(declareSuperMarketBoss(manager));
            manager.getMarkets().add(tempSuperMarket);
            System.out.println("<<<<<< Done >>>>>>>");
        }
    }

    public static MarketBoss declareSuperMarketBoss(Manager manager){
        System.out.println("Declaring the super market boss" +
                "\nEnter the username : ");
        String tempUserName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the password : ");
        String tempPassWord = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the id : ");
        int tempId = ScannerWrapper.getInstance().nextInt();
        manager.getUsers().add(new MarketBoss(tempId,tempUserName,tempPassWord));
        return new MarketBoss(tempId,tempUserName,tempPassWord);
    }

    public static void editSuperMarket(ArrayList<Market> markets){
        if(markets.size() == 0){
            System.out.println("There are no active supermarket !!");
            return;
        }
        System.out.println("Which restaurant do you want to edit ?");
        OrderDuty.showMarkets(markets,false,2);
        int choice = ScannerWrapper.getInstance().nextInt();
        editSuperMarketMenuHandler(choice,markets);
    }

    public static void editSuperMarketMenuHandler(int superMarketCode, ArrayList<Market> markets){
        System.out.println("Which part do you want to edit ?" +
                "\n1) Name" +
                "\n2) Address" +
                "\n3) Open time" +
                "\n4) Close time" +
                "\n5) Delivery Multiplicity" +
                "\n6) Exit");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                System.out.println("Enter the new name:");
                String newName = ScannerWrapper.getInstance().nextLine();
                markets.get(superMarketCode).setName(newName);
                System.out.println("SuperMarket Name changed .");
                break;
            case 2:
                System.out.println("Enter the new Address:");
                String newAddress = ScannerWrapper.getInstance().nextLine();
                markets.get(superMarketCode).setAddress(newAddress);
                System.out.println("SuperMarket Address changed .");
                break;
            case 3:
                System.out.println("Enter the new open time:");
                double newOpenTime = ScannerWrapper.getInstance().nextDouble();
                markets.get(superMarketCode).setOpenTime(newOpenTime);
                System.out.println("SuperMarket Open time changed .");
                break;
            case 4:
                System.out.println("Enter the new close time:");
                double newCloseTime = ScannerWrapper.getInstance().nextDouble();
                markets.get(superMarketCode).setCloseTime(newCloseTime);
                System.out.println("SuperMarket Close time changed .");
                break;
            case 5:
                System.out.println("Enter the new Delivery Multiplicity:");
                int newDeliveryMultiplicity = ScannerWrapper.getInstance().nextInt();
                markets.get(superMarketCode).setDeliveryMultiplicity(newDeliveryMultiplicity);
                System.out.println("SuperMarket Delivery Multiplicity changed .");
                break;
            case 6:
                return;
            default:
                System.out.println("Incorrect input !! try again ");
                editSuperMarketMenuHandler(superMarketCode,markets);
                break;
        }
    }

    public static void showSuperMarketScore(ArrayList<Market> markets){
        if(markets == null){
            System.out.println("There are no active supermarket !!");
            return;
        }
        System.out.println("Select the supermarket to show it's Score");
        OrderDuty.showMarkets(markets,false,2);
        int choice = ScannerWrapper.getInstance().nextInt();
        System.out.println(markets.get(choice).getScore());
    }

    public static void showSuperMarketComments(ArrayList<Market> markets){
        if(markets == null){
            System.out.println("There are no active supermarket !!");
            return;
        }
        System.out.println("Select the supermarket to show it's Score");
        OrderDuty.showMarkets(markets,false,2);
        int choice = ScannerWrapper.getInstance().nextInt();
        if(markets.get(choice).getComments().size() == 0){
            System.out.println("There are no comments yet !!");
            return;
        }
        markets.get(choice).showComments();
    }

    public static void addDelivery(ArrayList<Market> markets,ArrayList<Delivery> deliveries){
        if(markets.size() == 0){
            System.out.println("There are no declared restaurant !!");
            return;
        }
        OrderDuty.showMarkets(markets,false,2);
        int resCode = ScannerWrapper.getInstance().nextInt();
        System.out.println("Active deliveries : ");
        RestaurantDuty.showAvailableDeliveries(deliveries);
        int delCode = ScannerWrapper.getInstance().nextInt();
        markets.get(resCode).addDelivery(deliveries.get(delCode));
        System.out.println("<<<< Done >>>>");
    }

    public static void addFood(ArrayList<Market> markets,ArrayList<Product> products){
        if(markets.size() == 0){
            System.out.println("There are no active supermarket !!");
            return;
        }
        System.out.println("Select the supermarket to add a food to it's products");
        OrderDuty.showMarkets(markets,false,2);
        int choice = ScannerWrapper.getInstance().nextInt();
        markets.get(choice).addProduct(products);
        System.out.println("<<<< Done >>>>");
    }

    public static void showBestFood(ArrayList<Market> markets){
        ArrayList<SuperMarket> superMarkets = findSuperMarkets(markets);
        if(superMarkets.size() == 0){
            System.out.println("There are no active super market !!");
            return;
        }
        System.out.println("Which one ?");
        OrderDuty.showMarkets(markets,false,2);
        int choice = ScannerWrapper.getInstance().nextInt();
        showBestFoodsOfSuperMarket((SuperMarket) markets.get(choice));
    }

    public static ArrayList<SuperMarket> findSuperMarkets(ArrayList<Market> markets){
        ArrayList<SuperMarket> superMarkets = new ArrayList<>();
        for (int i=0;i< markets.size();i++){
            if (markets.get(i) instanceof SuperMarket){
                superMarkets.add(((SuperMarket) markets.get(i)));
            }
        }
        return superMarkets;
    }

    public static void showBestFoodsOfSuperMarket(SuperMarket superMarket){
        for (int i = 0;i < superMarket.getProducts().size();i++){
            for(int j = 0;j < superMarket.getProducts().size() - 1 - i;j++){
                if ( superMarket.getProducts().get(i).getScore() < superMarket.getProducts().get(i+1).getScore() ){
                    Product tmp = superMarket.getProducts().get(i+1);
                    superMarket.getProducts().set(i+1,superMarket.getProducts().get(i));
                    superMarket.getProducts().set(i,tmp);
                }
            }
        }
        superMarket.showProducts();
    }
}
