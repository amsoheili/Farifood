package ir.ac.kntu;

import java.util.ArrayList;

public class GroceryStoreDuty {

    public static void declareGroceryStore(Manager manager) {
        System.out.println("Declaring grocery store:");
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
        if (manager.getMarkets().contains(new GroceryStore(tempName, tempAddress, tempOpenTime,
                tempCloseTime, tempDeliveryMultiplicity))) {
            System.out.println("This restaurant has been declared before !!");
            return;
        } else {
            GroceryStore tempGroceryStore = new GroceryStore(tempName, tempAddress, tempOpenTime, tempCloseTime, tempDeliveryMultiplicity);
            tempGroceryStore.setMarketBoss(declareGroceryStoreBoss(manager));
            manager.getMarkets().add(tempGroceryStore);
            System.out.println("<<<<<< Done >>>>>>>");
        }
    }

    public static MarketBoss declareGroceryStoreBoss(Manager manager){
        System.out.println("Enter the name : ");
        String tempUserName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the username : ");
        String tempPassWord = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the id : ");
        int tempId = ScannerWrapper.getInstance().nextInt();
        manager.getUsers().add(new MarketBoss(tempId,tempUserName,tempPassWord));
        return new MarketBoss(tempId,tempUserName,tempPassWord);
    }

    public static void editGroceryStore(ArrayList<Market> markets){
        if(markets.size() == 0){
            System.out.println("There are no active grocery store !!");
            return;
        }
        System.out.println("Which restaurant do you want to edit ?");
        OrderDuty.showMarkets(markets,false,1);
        int choice = ScannerWrapper.getInstance().nextInt();
        editGroceryStoreMenuHandler(choice,markets);
    }

    public static void editGroceryStoreMenuHandler(int groceryStoreCode, ArrayList<Market> markets){
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
                markets.get(groceryStoreCode).setName(newName);
                System.out.println("Grocery Store Name changed .");
                break;
            case 2:
                System.out.println("Enter the new Address:");
                String newAddress = ScannerWrapper.getInstance().nextLine();
                markets.get(groceryStoreCode).setAddress(newAddress);
                System.out.println("Grocery Store Address changed .");
                break;
            case 3:
                System.out.println("Enter the new open time:");
                double newOpenTime = ScannerWrapper.getInstance().nextDouble();
                markets.get(groceryStoreCode).setOpenTime(newOpenTime);
                System.out.println("Grocery Store Open time changed .");
                break;
            case 4:
                System.out.println("Enter the new close time:");
                double newCloseTime = ScannerWrapper.getInstance().nextDouble();
                markets.get(groceryStoreCode).setCloseTime(newCloseTime);
                System.out.println("Grocery Store Close time changed .");
                break;
            case 5:
                System.out.println("Enter the new Delivery Multiplicity:");
                int newDeliveryMultiplicity = ScannerWrapper.getInstance().nextInt();
                markets.get(groceryStoreCode).setDeliveryMultiplicity(newDeliveryMultiplicity);
                System.out.println("Grocery Store Delivery Multiplicity changed .");
                break;
            case 6:
                return;
            default:
                System.out.println("Incorrect input !! try again ");
                editGroceryStoreMenuHandler(groceryStoreCode,markets);
                break;
        }
    }

    public static void showGroceryStoreScore(ArrayList<Market> markets){
        if(markets == null){
            System.out.println("There are no active grocery store !!");
            return;
        }
        System.out.println("Select the grocery to show it's Score");
        OrderDuty.showMarkets(markets,false,3);
        int choice = ScannerWrapper.getInstance().nextInt();
        System.out.println(markets.get(choice).getScore());
    }

    public static void showGroceryStoreComments(ArrayList<Market> markets){
        if(markets == null){
            System.out.println("There are no active grocery store !!");
            return;
        }
        System.out.println("Select the grocery store to show it's Score");
        OrderDuty.showMarkets(markets,false,3);
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
        OrderDuty.showMarkets(markets,false,3);
        int resCode = ScannerWrapper.getInstance().nextInt();
        System.out.println("Active deliveries : ");
        RestaurantDuty.showAvailableDeliveries(deliveries);
        int delCode = ScannerWrapper.getInstance().nextInt();
        markets.get(resCode).addDelivery(deliveries.get(delCode));
        System.out.println("<<<< Done >>>>");
    }

    public static void addFruit(ArrayList<Market> markets,ArrayList<Product> products){
        if(markets.size() == 0){
            System.out.println("There are no active grocery store !!");
            return;
        }
        System.out.println("Select the grocery store to add a food to it's products");
        OrderDuty.showMarkets(markets,false,3);
        int choice = ScannerWrapper.getInstance().nextInt();
        markets.get(choice).addProduct(products);
        System.out.println("<<<< Done >>>>");
    }

    public static void showBestFruit(ArrayList<Market> markets){
        ArrayList<GroceryStore> groceryStores = findGroceryStores(markets);
        if(groceryStores.size() == 0){
            System.out.println("There are no active grocery store !!");
            return;
        }
        System.out.println("Which one ?");
        OrderDuty.showMarkets(markets,false,3);
        int choice = ScannerWrapper.getInstance().nextInt();
        showBestFoodsOfGroceryStore((GroceryStore) markets.get(choice));
    }

    public static ArrayList<GroceryStore> findGroceryStores(ArrayList<Market> markets){
        ArrayList<GroceryStore> groceryStores = new ArrayList<>();
        for (int i=0;i< markets.size();i++){
            if (markets.get(i) instanceof GroceryStore){
                groceryStores.add(((GroceryStore) markets.get(i)));
            }
        }
        return groceryStores;
    }

    public static void showBestFoodsOfGroceryStore(GroceryStore groceryStore){
        for (int i = 0;i < groceryStore.getProducts().size();i++){
            for(int j = 0;j < groceryStore.getProducts().size() - 1 - i;j++){
                if ( groceryStore.getProducts().get(i).getScore() < groceryStore.getProducts().get(i+1).getScore() ){
                    Product tmp = groceryStore.getProducts().get(i+1);
                    groceryStore.getProducts().set(i+1,groceryStore.getProducts().get(i));
                    groceryStore.getProducts().set(i,tmp);
                }
            }
        }
        groceryStore.showProducts();
    }
}
