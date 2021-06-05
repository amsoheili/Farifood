package ir.ac.kntu;

public class MarketBossMenu {
    Market market;

    public MarketBossMenu(Market market){
        this.market = market;
    }

    public void mainMenu(Manager manager){
        System.out.println("Hello market boss , what do you want to do ? " +
                "\n1) Show my market information" +
                "\n2) Edit my market's information" +
                "\n3) Show market comments" +
                "\n4) Show deliveries" +
                "\n5) Show orders" +
                "\n6) Add products" +
                "\n7) Exit");
        mainMenuHandler(manager);
    }

    public void mainMenuHandler(Manager manager){
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                System.out.println(market.toString());
                break;
            case 2:
                market.editMarket();
                break;
            case 3:
                market.showComments();
                break;
            case 4:
                market.showDeliveries();
                break;
            case 5:
                market.showOrders();
                break;
            case 6:
                market.addProduct(manager.getProducts());
                break;
            case 7:
                return;
            default:
                System.out.println("Incorrect input !! please try again ");
                mainMenu(manager);
                break;
        }
        mainMenu(manager);
    }


}
