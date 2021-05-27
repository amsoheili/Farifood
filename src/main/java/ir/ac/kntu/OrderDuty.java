package ir.ac.kntu;

import java.time.LocalTime;
import java.util.ArrayList;

public class OrderDuty {

    OrderDuty() {
    }

    public static void orderFood(ArrayList<Restaurant> restaurants, ArrayList<Order> orders) {
        if (restaurants == null) {
            System.out.println("There are no active restaurant.");
            return;
        } else {
            System.out.println("Which restaurant ?");
            showRestaurants(restaurants,true);
        }
        int choseRestaurant = ScannerWrapper.getInstance().nextInt();
        if (restaurants.get(choseRestaurant).getFoodMenu().size() == 0) {
            System.out.println("Food menu is empty.");
            return;
        } else {
            restaurants.get(choseRestaurant).orderFood(orders);
        }
    }

    public static void showRestaurants(ArrayList<Restaurant> restaurants,boolean timeConsider) {
        System.out.println("How do you want to display the restaurants ?" +
                "\n1) Ascending score" +
                "\n2) Descending score" +
                "\n3) Ascending number of comments" +
                "\n4) Descending number of comments" +
                "\n5) Editor's pick");
        showRestaurantsHandler(restaurants,timeConsider);
        if (timeConsider == true){
            for (int i = 0; i < restaurants.size(); i++) {
                if ( LocalTime.now().getHour() >= restaurants.get(i).getOpenTime() &&
                        LocalTime.now().getHour() <= restaurants.get(i).getCloseTime()){
                    System.out.println(i + ")" + restaurants.get(i).getName());
                }
            }
        }else{
            for (int i = 0; i < restaurants.size(); i++) {
                System.out.println(i + ")" + restaurants.get(i).getName());
            }
        }
    }

    private static void showRestaurantsHandler(ArrayList<Restaurant> restaurants,boolean timeConsider) {
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice) {
            case 1:
                showAscendingScore(restaurants);
                break;
            case 2:
                showDescendingScore(restaurants);
                break;
            case 3:
                showAscendingNumOfComments(restaurants);
                break;
            case 4:
                showDescendingNumOfComments(restaurants);
                break;
            case 5:
                showEditorPick(restaurants);
                System.out.println("And the main list is : ");
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                showRestaurants(restaurants,timeConsider);
                break;
        }
        //showRestaurants();
    }

    private static void showAscendingScore(ArrayList<Restaurant> restaurants) {
        for (int i = 0; i < restaurants.size() - 1; i++) {
            for (int j = 0; j < restaurants.size() - i - 1; j++) {
                if (restaurants.get(i).getScore() > restaurants.get(i + 1).getScore()) {
                    Restaurant temp = restaurants.get(i + 1);
                    restaurants.set(i + 1, restaurants.get(i));
                    restaurants.set(i, temp);
                }
            }
        }
    }

    private static void showDescendingScore(ArrayList<Restaurant> restaurants) {
        for (int i = 0; i < restaurants.size() - 1; i++) {
            for (int j = 0; j < restaurants.size() - i - 1; j++) {
                if (restaurants.get(i).getScore() < restaurants.get(i + 1).getScore()) {
                    Restaurant temp = restaurants.get(i + 1);
                    restaurants.set(i + 1, restaurants.get(i));
                    restaurants.set(i, temp);
                }
            }
        }
    }

    private static void showAscendingNumOfComments(ArrayList<Restaurant> restaurants) {
        for (int i = 0; i < restaurants.size() - 1; i++) {
            for (int j = 0; j < restaurants.size() - i - 1; j++) {
                if (restaurants.get(i).getComments().size() > restaurants.get(i + 1).getComments().size()) {
                    Restaurant temp = restaurants.get(i + 1);
                    restaurants.set(i + 1, restaurants.get(i));
                    restaurants.set(i, temp);
                }
            }
        }
    }

    private static void showDescendingNumOfComments(ArrayList<Restaurant> restaurants) {
        for (int i = 0; i < restaurants.size() - 1; i++) {
            for (int j = 0; j < restaurants.size() - i - 1; j++) {
                if (restaurants.get(i).getScore() < restaurants.get(i + 1).getScore()) {
                    Restaurant temp = restaurants.get(i + 1);
                    restaurants.set(i + 1, restaurants.get(i));
                    restaurants.set(i, temp);
                }
            }
        }
    }

    private static void showEditorPick(ArrayList<Restaurant> restaurants) {
        int x = LocalTime.now().getMinute() % restaurants.size();
        System.out.println("Our suggestion is : " + x + ")" + restaurants.get(x).getName());
    }

    public static void filterOrders(ArrayList<Order> orders,ArrayList<Restaurant> restaurants,ArrayList<Comment> comments) {
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
                deliveredOrdersMenu(orders,restaurants,comments);
                break;
            case 4:
                return;
            default:
                filterOrders(orders,restaurants,comments);
        }
    }

    private static void showFilteredOrders(OrderStatus status,ArrayList<Order> orders) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getStatus().equals(status)) {
                System.out.println(i + ")\n" + orders.get(i).toString());
            }
        }
    }

    private static void deliveredOrdersMenu(ArrayList<Order> orders,ArrayList<Restaurant> restaurants,ArrayList<Comment> comments) {
        if (isThereDeliveredOrder(orders)) {
            showFilteredOrders(OrderStatus.DELIVERED,orders);
            System.out.println("Do you want to add comment ?( 1-Yes   2-No)");
            int choice = ScannerWrapper.getInstance().nextInt();
            if (choice == 1) {
                addComment(orders,restaurants,comments);
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

    public static void addComment(ArrayList<Order> orders,ArrayList<Restaurant> restaurants,ArrayList<Comment> comments){
        if(isThereDeliveredOrder(orders)){
            System.out.println("Which one ?");
            showFilteredOrders(OrderStatus.DELIVERED,orders);
            int choice = ScannerWrapper.getInstance().nextInt();
            findRestaurant(orders.get(choice),restaurants).addComment(orders.get(choice).getFood().getName(),comments);
        }else{
            System.out.println("There are not any delivered order :)");
        }
    }

    private static Restaurant findRestaurant(Order order,ArrayList<Restaurant> restaurants){
        for(int i=0;i< restaurants.size();i++){
            for(int j=0;j<restaurants.get(i).getOrders().size();j++){
                if(restaurants.get(i).getOrders().get(j).equals(order)){
                    return restaurants.get(i);
                }
            }
        }
        return null;
    }

    public static void setOrderStatus(ArrayList<Restaurant> restaurants,Manager manager){
        if(restaurants.size() == 0){
            System.out.println("There are no active restaurant.");
            return;
        }
        System.out.println("Choose a restaurant to change it's orders :");
        showRestaurants(restaurants,false);
        int choice = ScannerWrapper.getInstance().nextInt();
        if(restaurants.get(choice).getOrders().size() == 0){
            System.out.println("orders is empty.");
            return;
        }else{
            restaurants.get(choice).setOrderStatus(manager);
        }
    }

    public static void addScore(ArrayList<Order> orders,ArrayList<Restaurant> restaurants,ArrayList<Comment> comments){
        if(isThereDeliveredOrder(orders)){
            System.out.println("Which one ? ");
            showFilteredOrders(OrderStatus.DELIVERED,orders);
            int choice = ScannerWrapper.getInstance().nextInt();
            findRestaurant(orders.get(choice),restaurants).addScore();
            orders.get(choice).getFood().addScore();
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
            System.out.println(i + ")" + orders.get(i).getFood());
        }
    }

    public static void setOrderFood(ArrayList<Restaurant> restaurants){
        System.out.println("Which restaurant do you want to change it's order's food ?");
        showRestaurants(restaurants,false);
        int choice = ScannerWrapper.getInstance().nextInt();
        setOrderFoodHandler(choice,restaurants);
    }

    public static void setOrderFoodHandler(int restaurantCode,ArrayList<Restaurant> restaurants){
        if(restaurants.get(restaurantCode).getOrders().size() == 0){
            System.out.println("There are no orders fo this restaurant !!");
            return;
        }
        System.out.println("Which order do you want to change it's food ? ");
        restaurants.get(restaurantCode).showOrders();
        int orderChoice = ScannerWrapper.getInstance().nextInt();
        System.out.println("What do you want to change it into ? ");
        restaurants.get(restaurantCode).showFoodMenu();
        int foodChoice = ScannerWrapper.getInstance().nextInt();
        restaurants.get(restaurantCode).getOrder(orderChoice).setFood(restaurants.get(restaurantCode).getFoodMenu().get(foodChoice));
        System.out.println("<<<< Done >>>>");
    }
}