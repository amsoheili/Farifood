package ir.ac.kntu;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;

public class OrderDuty {

    OrderDuty() {
    }

    public static void orderSth(ArrayList<Market> markets, ArrayList<Order> orders,int marketCode,User user){
        switch (marketCode){
            case 1:
                ArrayList<Market> restaurants = new ArrayList<>();
                for (Market market:markets){
                    if (market instanceof Restaurant){
                        restaurants.add((Restaurant) market);
                    }
                }
                orderSthHandler(restaurants,orders,marketCode,user);
                break;
            case 2:
                ArrayList<Market> superMarkets = new ArrayList<>();
                for (Market market:markets){
                    if (market instanceof SuperMarket){
                        superMarkets.add((Restaurant) market);
                    }
                }
                orderSthHandler(superMarkets,orders,marketCode,user);
                break;
            case 3:
                //orderFruit
                ArrayList<Market> groceryStores = new ArrayList<>();
                for (Market market:markets){
                    if (market instanceof GroceryStore){
                        groceryStores.add((Restaurant) market);
                    }
                }
                orderSthHandler(groceryStores,orders,marketCode,user);
                break;
            default:
                return;
        }
    }
    public static void orderSthHandler(ArrayList<Market> markets, ArrayList<Order> orders, int marketCode,User user) {
        if (markets == null) {
            System.out.println("There are no active markets.");
            return;
        } else {
            System.out.println("Which market ?");
            showMarkets(markets,true,marketCode);
        }
        int choseMarket = ScannerWrapper.getInstance().nextInt();
        if (markets.get(choseMarket).getProducts().size() == 0) {
            System.out.println("product list is empty.");
            return;
        } else {
            markets.get(choseMarket).orderProduct(orders,user);
        }
    }


    public static void showMarkets(ArrayList<Market> markets,boolean timeConsider,int marketCode) {
        System.out.println("How do you want to display the " +
                MarketCode.getMarket(marketCode).toString().toLowerCase(Locale.ROOT) + "'s ?" +
                "\n1) Ascending score" +
                "\n2) Descending score" +
                "\n3) Ascending number of comments" +
                "\n4) Descending number of comments" +
                "\n5) Editor's pick");
        showMarketsHandler(markets,timeConsider,marketCode);
        switch (marketCode){
            case 1:
                showRestaurants(markets,timeConsider);
                break;
            case 2:
                showSuperMarkets(markets,timeConsider);
                break;
            case 3:
                showGroceryStore(markets,timeConsider);
                break;
        }
    }

    public static void showRestaurants(ArrayList<Market> markets,boolean timeConsider){
        if (timeConsider == true){
            for (int i = 0; i < markets.size(); i++) {
                if ( LocalTime.now().getHour() >= markets.get(i).getOpenTime() &&
                        LocalTime.now().getHour() <= markets.get(i).getCloseTime() &&
                        markets.get(i) instanceof Restaurant){
                    System.out.println(i + ")" + markets.get(i).getName());
                }

            }
        }else{
            for (int i = 0; i < markets.size(); i++) {
                if (markets.get(i) instanceof Restaurant){
                    System.out.println(i + ")" + markets.get(i).getName());
                }
            }
        }
    }

    public static void showSuperMarkets(ArrayList<Market> markets,boolean timeConsider){
        if (timeConsider == true){
            for (int i = 0; i < markets.size(); i++) {
                if ( LocalTime.now().getHour() >= markets.get(i).getOpenTime() &&
                        LocalTime.now().getHour() <= markets.get(i).getCloseTime() &&
                        markets.get(i) instanceof SuperMarket){
                    System.out.println(i + ")" + markets.get(i).getName());
                }

            }
        }else{
            for (int i = 0; i < markets.size(); i++) {
                if (markets.get(i) instanceof SuperMarket){
                    System.out.println(i + ")" + markets.get(i).getName());
                }
            }
        }
    }

    public static void showGroceryStore(ArrayList<Market> markets,boolean timeConsider) {
        if (timeConsider == true) {
            for (int i = 0; i < markets.size(); i++) {
                if (LocalTime.now().getHour() >= markets.get(i).getOpenTime() &&
                        LocalTime.now().getHour() <= markets.get(i).getCloseTime() &&
                        markets.get(i) instanceof GroceryStore) {
                    System.out.println(i + ")" + markets.get(i).getName());
                }

            }
        } else {
            for (int i = 0; i < markets.size(); i++) {
                if (markets.get(i) instanceof GroceryStore) {
                    System.out.println(i + ")" + markets.get(i).getName());
                }
            }

        }
    }

    private static void showMarketsHandler(ArrayList<Market> markets,boolean timeConsider,int marketCode) {
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice) {
            case 1:
                showAscendingScore(markets);
                break;
            case 2:
                showDescendingScore(markets);
                break;
            case 3:
                showAscendingNumOfComments(markets);
                break;
            case 4:
                showDescendingNumOfComments(markets);
                break;
            case 5:
                //showEditorPick(markets,marketCode);
                System.out.println("And the main list is : ");
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                showMarkets(markets,timeConsider,marketCode);
                break;
        }
        //showRestaurants();
    }

    private static void showAscendingScore(ArrayList<Market> markets) {
        for (int i = 0; i < markets.size() - 1; i++) {
            for (int j = 0; j < markets.size() - i - 1; j++) {
                if (markets.get(i).getScore() > markets.get(i + 1).getScore()) {
                    Market temp = markets.get(i + 1);
                    markets.set(i + 1, markets.get(i));
                    markets.set(i, temp);
                }
            }
        }
    }

    private static void showDescendingScore(ArrayList<Market> markets) {
        for (int i = 0; i < markets.size() - 1; i++) {
            for (int j = 0; j < markets.size() - i - 1; j++) {
                if (markets.get(i).getScore() < markets.get(i + 1).getScore()) {
                    Market temp = markets.get(i + 1);
                    markets.set(i + 1, markets.get(i));
                    markets.set(i, temp);
                }
            }
        }
    }

    private static void showAscendingNumOfComments(ArrayList<Market> markets) {
        for (int i = 0; i < markets.size() - 1; i++) {
            for (int j = 0; j < markets.size() - i - 1; j++) {
                if (markets.get(i).getComments().size() > markets.get(i + 1).getComments().size()) {
                    Market temp = markets.get(i + 1);
                    markets.set(i + 1, markets.get(i));
                    markets.set(i, temp);
                }
            }
        }
    }

    private static void showDescendingNumOfComments(ArrayList<Market> markets) {
        for (int i = 0; i < markets.size() - 1; i++) {
            for (int j = 0; j < markets.size() - i - 1; j++) {
                if (markets.get(i).getScore() < markets.get(i + 1).getScore()) {
                    Market temp = markets.get(i + 1);
                    markets.set(i + 1, markets.get(i));
                    markets.set(i, temp);
                }
            }
        }
    }

//    private static void showEditorPick(ArrayList<Market> markets,int marketCode) {
//        int x = LocalTime.now().getMinute() % markets.size();
//        for (int i=0;i<markets.size();i++){
//            if (  )
//        }
//        System.out.println("Our suggestion is : " + x + ")" + markets.get(x).getName());
//    }

    public static void filterOrders(ArrayList<Order> orders,ArrayList<Market> markets,ArrayList<Comment> comments) {
        if (orders == null) {
            System.out.println("There are no order");
            return;
        }
        System.out.println("How do you want to filter the orders ?" +
                "\n1) " + OrderStatus.PROCESSING +
                "\n2) " + OrderStatus.SENDING +
                "\n3) " + OrderStatus.DELIVERED +
                "\n4) Exit");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice) {
            case 1:
                showFilteredOrders(OrderStatus.PROCESSING,orders);
                break;
            case 2:
                showFilteredOrders(OrderStatus.SENDING,orders);
                break;
            case 3:
                deliveredOrdersMenu(orders, markets,comments);
                break;
            case 4:
                return;
            default:
                filterOrders(orders, markets,comments);
        }
    }

    private static void showFilteredOrders(OrderStatus status,ArrayList<Order> orders) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getStatus().equals(status)) {
                System.out.println(i + ")\n" + orders.get(i).toString());
            }
        }
    }

    private static void deliveredOrdersMenu(ArrayList<Order> orders, ArrayList<Market> markets, ArrayList<Comment> comments) {
        if (isThereDeliveredOrder(orders)) {
            showFilteredOrders(OrderStatus.DELIVERED,orders);
            System.out.println("Do you want to add comment ?( 1-Yes   2-No)");
            int choice = ScannerWrapper.getInstance().nextInt();
            if (choice == 1) {
                addComment(orders, markets,comments);
            } else {
                System.out.println("Bye!");
            }
        } else {
            System.out.println("There are not any delivered order :)");
        }

    }

    private static boolean isThereDeliveredOrder(ArrayList<Order> orders){
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).getStatus().equals(OrderStatus.DELIVERED)){
                return true;
            }
        }
        return false;
    }

    public static void addComment(ArrayList<Order> orders,ArrayList<Market> markets,ArrayList<Comment> comments){
        if(isThereDeliveredOrder(orders)){
            System.out.println("Which one ?");
            showFilteredOrders(OrderStatus.DELIVERED,orders);
            int choice = ScannerWrapper.getInstance().nextInt();
            findMarket(orders.get(choice), markets).addComment(orders.get(choice).getProduct().getName(),comments);
        }else{
            System.out.println("There are not any delivered order :)");
        }
    }

    private static Market findMarket(Order order,ArrayList<Market> markets){
        for(int i = 0; i< markets.size(); i++){
            for(int j = 0; j< markets.get(i).getOrders().size(); j++){
                if(markets.get(i).getOrders().get(j).equals(order)){
                    return markets.get(i);
                }
            }
        }
        return null;
    }

    public static void setOrderStatus(ArrayList<Market> markets,Manager manager){
        if(markets.size() == 0){
            System.out.println("There are no active market.");
            return;
        }
        System.out.println("Choose a market to change it's orders :");
        showMarkets(markets,false,1);
        int choice = ScannerWrapper.getInstance().nextInt();
        if(markets.get(choice).getOrders().size() == 0){
            System.out.println("orders is empty.");
            return;
        }else{
            markets.get(choice).setOrderStatus(manager);
        }
    }

    public static void addScore(ArrayList<Order> orders,ArrayList<Market> markets,ArrayList<Comment> comments){
        if(isThereDeliveredOrder(orders)){
            System.out.println("Which one ? ");
            showFilteredOrders(OrderStatus.DELIVERED,orders);
            int choice = ScannerWrapper.getInstance().nextInt();
            findMarket(orders.get(choice),markets).addScore();
            orders.get(choice).getProduct().addScore();
        }else {
            System.out.println("There are not any delivered order :)");
        }
    }

    public static void showOrders(ArrayList<Order> orders){
        if(orders.size() == 0){
            System.out.println("There are no orders yet !!");
            return;
        }
        for(int i=0;i<orders.size();i++){
            System.out.println(i + ")" + orders.get(i).getProduct());
        }
    }

    public static void setOrderFood(ArrayList<Market> markets,int marketCode){
        System.out.println("Which market do you want to change it's order's food ?");
        showMarkets(markets,false,marketCode);
        int choice = ScannerWrapper.getInstance().nextInt();
        setOrderFoodHandler(choice,markets);
    }

    public static void setOrderFoodHandler(int marketChoice, ArrayList<Market> markets){
        if(markets.get(marketChoice).getOrders().size() == 0){
            System.out.println("There are no orders for this market !!");
            return;
        }
        System.out.println("Which order do you want to change it's product ? ");
        markets.get(marketChoice).showOrders();
        int orderChoice = ScannerWrapper.getInstance().nextInt();
        System.out.println("What do you want to change it into ? ");
        markets.get(marketChoice).showProducts();
        int productChoice = ScannerWrapper.getInstance().nextInt();
        markets.get(marketChoice).getOrder(orderChoice).setProduct(markets.get(marketChoice).
                getProducts().get(productChoice));
        System.out.println("<<<< Done >>>>");
    }
}