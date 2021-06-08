package ir.ac.kntu.Utility;

import ir.ac.kntu.*;
import ir.ac.kntu.Logic.MarketCode;
import ir.ac.kntu.Logic.OrderStatus;
import ir.ac.kntu.Logic.ScannerWrapper;
import ir.ac.kntu.Markets.*;
import ir.ac.kntu.Users.Comment;
import ir.ac.kntu.Users.Customer;
import ir.ac.kntu.Users.User;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;

public class OrderDuty {

    OrderDuty() {
    }
    public static void orderSth(ArrayList<Market> markets, ArrayList<Order> orders, int marketCode,User user) {
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
                "\n4) Descending number of comments");
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
            default:
                return;
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

    public static void filterOrders(ArrayList<Order> orders,ArrayList<Market> markets,ArrayList<Comment> comments,User user) {
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
                deliveredOrdersMenu(orders, markets,comments,user);
                break;
            case 4:
                return;
            default:
                filterOrders(orders, markets,comments,user);
        }
    }

    private static void showFilteredOrders(OrderStatus status,ArrayList<Order> orders) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getStatus().equals(status)) {
                System.out.println(i + ")\n" + orders.get(i).toString());
            }
        }
    }

    private static void deliveredOrdersMenu(ArrayList<Order> orders, ArrayList<Market> markets, ArrayList<Comment> comments,User user) {
        if (isThereDeliveredOrder(orders)) {
            showFilteredOrders(OrderStatus.DELIVERED,orders);
            System.out.println("Do you want to add comment ?( 1-Yes   2-No)");
            int choice = ScannerWrapper.getInstance().nextInt();
            if (choice == 1) {
                addCommentUser(orders, markets,comments,user);
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

    public static void addCommentUser(ArrayList<Order> orders,ArrayList<Market> markets,ArrayList<Comment> comments,User user){
        if (user instanceof Customer){
            if (isThereDeliveredOrder(((Customer)user).getOrders())){
                System.out.println("Which one ?");
                showFilteredOrders(OrderStatus.DELIVERED,((Customer)user).getOrders());
                int choice = ScannerWrapper.getInstance().nextInt();
                findMarket(((Customer)user).getOrders().get(choice),markets).addComment(((Customer)user).getOrders().get(choice).getProduct().getName(),
                        comments,((Customer)user));
            }    
        }else{
            if(isThereDeliveredOrder(orders)){
            System.out.println("Which one ?");
            showFilteredOrders(OrderStatus.DELIVERED,orders);
            int choice = ScannerWrapper.getInstance().nextInt();
            findMarket(orders.get(choice), markets).addComment(orders.get(choice).getProduct().getName(),comments,user);
        }else{
            System.out.println("There are not any delivered order :)");
        }
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
        showAllMarkets(markets);
        int choice = ScannerWrapper.getInstance().nextInt();
        if(markets.get(choice).getOrders().size() == 0){
            System.out.println("orders is empty.");
            return;
        }else{
            markets.get(choice).setOrderStatus(manager);
        }
    }

    public static void showAllMarkets(ArrayList<Market> markets){
        for (int i=0;i<markets.size();i++){
            System.out.println(i + ") " + markets.get(i).getName());
        }
    }

    public static void addScore(ArrayList<Order> orders,ArrayList<Market> markets,User user){
        if(isThereDeliveredOrder(orders)){
            System.out.println("Which one ? ");
            showFilteredOrders(OrderStatus.DELIVERED,orders);
            int choice = ScannerWrapper.getInstance().nextInt();
            findMarket(orders.get(choice),markets).addScore();
            orders.get(choice).getProduct().addScore();
        }else {
            System.out.println("There are not any delivered order :)");
        }

        if (user instanceof Customer){
            if (isThereDeliveredOrder(((Customer)user).getOrders())){
                System.out.println("Which one ?");
                showFilteredOrders(OrderStatus.DELIVERED,((Customer)user).getOrders());
                int choice = ScannerWrapper.getInstance().nextInt();
                findMarket(((Customer)user).getOrders().get(choice),markets).addScore();
            }
        }else{
            if(isThereDeliveredOrder(orders)){
                System.out.println("Which one ?");
                showFilteredOrders(OrderStatus.DELIVERED,orders);
                int choice = ScannerWrapper.getInstance().nextInt();
                findMarket(orders.get(choice), markets).addScore();
            }else{
                System.out.println("There are not any delivered order :)");
            }
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