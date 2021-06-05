package ir.ac.kntu;

import java.util.HashMap;

public class AdminMenu {
    Admin admin;

    AdminMenu(Admin admin){
        this.admin = admin;
    }

    private HashMap<Integer,MenuOptions> setMenu = new HashMap<>();


    public  boolean start(Manager manager){
        setShowMainMenu();
        return mainMenu(manager);
    }

    public boolean mainMenu(Manager manager){
        System.out.println("What do you want to do?" +
                "\n1) " + MenuOptions.getOption(1) +
                "\n2) " + MenuOptions.getOption(2) +
                "\n3) " + MenuOptions.getOption(3) +
                "\n4) " + MenuOptions.getOption(4) +
                "\n5) EXIT_MARKET");
        return mainMenuHandler(manager);
    }

    public void setShowMainMenu(){
        System.out.println("Enter your wished set to show these options in main menu : " +
                "(for example 23145) ");
        System.out.println(
                "1) Orders Menu" +
                        "\n2) Settings" +
                        "\n3) Delivery Menu" +
                        "\n4) Market Menu" );
        int set = ScannerWrapper.getInstance().nextInt();
        setMenu.put(1,MenuOptions.ORDERS_MENU);
        setMenu.put(2,MenuOptions.SETTINGS);
        setMenu.put(3,MenuOptions.DELIVERY_MENU);
        setMenu.put(4,MenuOptions.MARKET_MENU);
        setMenu.get(set%10).setRate(4);
        set /= 10;
        setMenu.get(set%10).setRate(3);
        set /= 10;
        setMenu.get(set%10).setRate(2);
        set /= 10;
        setMenu.get(set%10).setRate(1);
    }

    public boolean mainMenuHandler(Manager manager){
        int choice = ScannerWrapper.getInstance().nextInt();
        if(choice == MenuOptions.ORDERS_MENU.getRate()){
            ordersMenu(manager);
        }else if(choice == MenuOptions.SETTINGS.getRate()){
            settings(manager);
        }else if(choice == MenuOptions.DELIVERY_MENU.getRate()){
            deliveryMenu(manager);
        }else if(choice == MenuOptions.MARKET_MENU.getRate()){
            marketsMenu(manager);
        }else if(choice == 5){
            return false;
        }else{
            mainMenu(manager);
        }
        return false;
    }

    public void ordersMenu(Manager manager){
        System.out.println("What do you want to do?" +
                "\n1) Order a product" +
                "\n2) filter the orders" +
                "\n3) Set orders' status" +
                "\n4) Add Comment" +
                "\n5) Add Score" +
                "\n6) show ordered foods" +
                "\n7) Set order product" +
                "\n8) Exit");
        ordersMenuHandler(manager);
    }

    public void ordersMenuHandler(Manager manager){
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                orderProductMarket(manager);
                break;
            case 2:
                manager.filterOrders();
                break;
            case 3:
                manager.setOrderStatus();
                break;
            case 4:
                manager.addComment();
                break;
            case 5:
                manager.addScore();
                break;
            case 6:
                manager.showOrders();
                break;
            case 7:
                setOrderProductMarket(manager);
                break;
            case 8:
                mainMenu(manager);
                break;
            default:
                ordersMenu(manager);
                break;
        }
        mainMenu(manager);
    }

    public void orderProductMarket(Manager manager){
        System.out.println("Select the market : ( 1- Restaurant  2- Supermarket  3-Grocery Store )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                manager.orderFoodRestaurant(admin);
                break;
            case 2:
                manager.orderFoodSuperMarket(admin);
                break;
            case 3:
                manager.orderFruitGrocery(admin);
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                orderProductMarket(manager);
                break;
        }
    }

    public void setOrderProductMarket(Manager manager){
        System.out.println("Select the market : ( 1- Restaurant  2- Supermarket  3-Grocery Store )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                manager.setOrderFoodRestaurant();
                break;
            case 2:
                manager.setOrderFoodSuperMarket();
                break;
            case 3:
                manager.setOrderFruit();
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                setOrderProductMarket(manager);
                break;
        }
    }

    public void deliveryMenu(Manager manager){
        System.out.println("What the fact are you interested in ? " +
                "\n1) Declare a new delivery" +
                "\n2) Show deliveries" +
                "\n3) Set declared deliveries" +
                "\n4) show orders of a delivery " +
                "\n5) Exit");
        deliveryMenuHandler(manager);
    }

    public void deliveryMenuHandler(Manager manager){
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                manager.declareDelivery();
                break;
            case 2:
                manager.showDeliveries();
                break;
            case 3:
                manager.setDeclaredDeliveries();
                break;
            case 4:
                manager.showOrdersOfDelivery();
                break;
            case 5:
                mainMenu(manager);
                return;
            default:
                System.out.println("Incorrect input !!! please try again ");
                deliveryMenu(manager);
                break;
        }
        deliveryMenu(manager);
    }

    public void marketsMenu(Manager manager){
        System.out.println("What do you want to do ?" +
                "\n1) Show active markets" +
                "\n2) Declare a new market" +
                "\n3) Edit active markets" +
                "\n4) Add delivery to a market" +
                "\n5) Show the score of a market" +
                "\n6) Show the comments of a market" +
                "\n7) Add a product to the market's menu" +
                "\n8) Show declared products " +
                "\n9) Search market " +
                "\n10) Show comments of a particular product " +
                "\n11) Show best products of a market " +
                "\n12) Show best markets for each product " +
                "\n13) Exit ");
        marketsMenuHandler(manager);
    }

    public void marketsMenuHandler(Manager manager){
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                manager.showMarkets();
                break;
            case 2:
                declareMarket(manager);
                break;
            case 3:
                editMarket(manager);
                break;
            case 4:
                addDelivery(manager);
                break;
            case 5:
                manager.showRestaurantScore();
                break;
            case 6:
                manager.showRestaurantComments();
                break;
            case 7:
                addProduct(manager);
                break;
            case 8:
                manager.showProducts();
                break;
            case 9:
                searchMarket(manager);
                break;
            case 10:
                manager.showCommentOfProduct();
                break;
            case 11:
                showBestProduct(manager);
                break;
            case 12:
                manager.showBestMarketForMentionedProduct();
                break;
            case 13:
                mainMenu(manager);
                return;
            default:
                System.out.println("Incorrect choice ");
                marketsMenu(manager);
                break;
        }
        marketsMenu(manager);
    }

    public void declareMarket(Manager manager){
        System.out.println("Select the market : ( 1- Restaurant  2- Supermarket  3-Grocery Store )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                manager.declareRestaurant();
                break;
            case 2:
                manager.declareSuperMarket();
                break;
            case 3:
                manager.declareGroceryStore();
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                declareMarket(manager);
                break;
        }
    }

    public void editMarket(Manager manager){
        System.out.println("Select the market : ( 1- Restaurant  2- Supermarket  3-Grocery Store )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                manager.editRestaurant();
                break;
            case 2:
                manager.editSuperMarket();
                break;
            case 3:
                manager.editGroceryStore();
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                editMarket(manager);
                break;
        }
    }

    public void addDelivery(Manager manager){
        System.out.println("Select the market : ( 1- Restaurant  2- Supermarket  3-Grocery Store )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                manager.addDeliveryRestaurant();
                break;
            case 2:
                manager.addDeliverySuperMarket();
                break;
            case 3:
                manager.addDeliveryGroceryStore();
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                addDelivery(manager);
                break;
        }
    }

    public void addProduct(Manager manager){
        System.out.println("Select the market : ( 1- Restaurant  2- Supermarket  3-Grocery Store )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                manager.addFoodRestaurant();
                break;
            case 2:
                manager.addFoodSuperMarket();
                break;
            case 3:
                manager.addFruit();
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                addProduct(manager);
                break;
        }
    }

    public void searchMarket(Manager manager){
        System.out.println("Select the market : ( 1- Restaurant  2- Supermarket  3-Grocery Store )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                manager.searchRestaurant();
                break;
            case 2:
                manager.searchSuperMarket();
                break;
            case 3:
                manager.searchGroceryStore();
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                searchMarket(manager);
                break;
        }
    }

    public void showBestProduct(Manager manager){
        System.out.println("Select the market : ( 1- Restaurant  2- Supermarket  3-Grocery Store )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                manager.showBestFoodRestaurant();
                break;
            case 2:
                manager.showBestFoodSuperMarket();
                break;
            case 3:
                manager.showBestFruit();
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                showBestProduct(manager);
                break;
        }
    }

    public void settings(Manager manager){
        System.out.println("What do you want to do?" +
                "\n1) Set main menu's order" +
                "\n2) Customers settings" +
                "\n3) Exit");
        settingsMenuHandler(manager);
    }

    public void settingsMenuHandler(Manager manager){
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                setShowMainMenu();
                break;
            case 2:
                customersMenu(manager);
                break;
            case 3:
                mainMenu(manager);
                return;
            default:
                System.out.println("incorrect input !! please try again ");
                settings(manager);
                break;
        }
        settings(manager);
    }

    public void customersMenu(Manager manager){
        System.out.println("What do you want to do?" +
                "\n1) Create a new customer" +
                "\n2) Edit a customer" +
                "\n3) Show all of the customers" +
                "\n4) Exit");
        customersMenuHandler(manager);
    }

    public void customersMenuHandler(Manager manager){
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                manager.createCustomer();
                break;
            case 2:
                manager.editCustomer();
                break;
            case 3:
                manager.showCustomers();
                break;
            case 4:
                return;
            default:
                System.out.println("incorrect input !! please try again ");
                customersMenu(manager);
                break;
        }
        customersMenu(manager);
    }

}
