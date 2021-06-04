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
                "\n9) Exit");
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
                showComments();
                break;
            case 4:
                showMarketDeliveries();
                break;
            case 5:
                showOrders();
                break;
            case 6:
                addProducts();
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                mainMenu(manager);
                break;
        }
    }

    public void showComments(){
        if (market.getComments().size() == 0){
            System.out.println("There are no comments yet !! ");
            return;
        }
        for (int i=0;i<market.getComments().size();i++){
            System.out.println(i + ") " + market.getComments().get(i).toString());
        }
    }

}
