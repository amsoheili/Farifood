package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantDuty {

    public static void declareRestaurant(ArrayList<Market> markets) {
        System.out.println("Declaring restaurant:");
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
        RestaurantType tempRestaurantType = takeRestaurantType();
        if (markets.contains(new Restaurant(tempName, tempAddress, tempOpenTime,
                tempCloseTime, tempDeliveryMultiplicity, tempRestaurantType))) {
            System.out.println("This restaurant has been declared before !!");
            return;
        } else {
            markets.add(new Restaurant(tempName, tempAddress, tempOpenTime, tempCloseTime, tempDeliveryMultiplicity, tempRestaurantType));
            System.out.println("<<<<<< Done >>>>>>>");
        }
    }

    public static RestaurantType takeRestaurantType(){
        System.out.println("Restaurant type ( 1-Economic 2-Medium 3-Luxury ) :");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                return RestaurantType.ECONOMIC;
            case 2:
                return RestaurantType.MEDIUM;
            case 3:
                return RestaurantType.LUXURY;
            default:
                System.out.println("Incorrect input !! try again ");
                takeRestaurantType();
                break;
        }
        return null;
    }

    public static void editRestaurant(ArrayList<Market> markets){
        if(markets.size() == 0){
            System.out.println("There are no active restaurant !!");
            return;
        }
        System.out.println("Which restaurant do you want to edit ?");
        OrderDuty.showMarkets(markets,false,1);
        int choice = ScannerWrapper.getInstance().nextInt();
        editRestaurantMenuHandler(choice,markets);
    }

    public static void editRestaurantMenuHandler(int restaurantCode,ArrayList<Market> markets){
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
                markets.get(restaurantCode).setName(newName);
                System.out.println("Restaurant Name changed .");
                break;
            case 2:
                System.out.println("Enter the new Address:");
                String newAddress = ScannerWrapper.getInstance().nextLine();
                markets.get(restaurantCode).setAddress(newAddress);
                System.out.println("Restaurant Address changed .");
                break;
            case 3:
                System.out.println("Enter the new open time:");
                double newOpenTime = ScannerWrapper.getInstance().nextDouble();
                markets.get(restaurantCode).setOpenTime(newOpenTime);
                System.out.println("Restaurant Open time changed .");
                break;
            case 4:
                System.out.println("Enter the new close time:");
                double newCloseTime = ScannerWrapper.getInstance().nextDouble();
                markets.get(restaurantCode).setCloseTime(newCloseTime);
                System.out.println("Restaurant Close time changed .");
                break;
            case 5:
                System.out.println("Enter the new Delivery Multiplicity:");
                int newDeliveryMultiplicity = ScannerWrapper.getInstance().nextInt();
                markets.get(restaurantCode).setDeliveryMultiplicity(newDeliveryMultiplicity);
                System.out.println("Restaurant Delivery Multiplicity changed .");
                break;
            case 6:
                return;
            default:
                System.out.println("Incorrect input !! try again ");
                editRestaurantMenuHandler(restaurantCode,markets);
                break;
        }
    }

//    public static void showDeliveries(ArrayList<Delivery> deliveries){
//        if(deliveries == null){
//            System.out.println("There are no deliveries yet !!");
//            return;
//        }
//        for(int i=0;i< deliveries.size();i++){
//            System.out.println(i + ")" + deliveries.get(i).getName());
//        }
//    }

    public static void showRestaurantScore(ArrayList<Market> markets){
        if(markets == null){
            System.out.println("There are no active restaurant !!");
            return;
        }
        System.out.println("Select the restaurant to show it's Score");
        OrderDuty.showMarkets(markets,false,1);
        int choice = ScannerWrapper.getInstance().nextInt();
        System.out.println(markets.get(choice).getScore());
    }

    public static void showRestaurantComments(ArrayList<Market> markets){
        if(markets == null){
            System.out.println("There are no active restaurant !!");
            return;
        }
        System.out.println("Select the restaurant to show it's Score");
        OrderDuty.showMarkets(markets,false,1);
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
        OrderDuty.showMarkets(markets,false,1);
        int resCode = ScannerWrapper.getInstance().nextInt();
        System.out.println("Active deliveries : ");
        showAvailableDeliveries(deliveries);
        int delCode = ScannerWrapper.getInstance().nextInt();
        markets.get(resCode).addDelivery(deliveries.get(delCode));
        System.out.println("<<<< Done >>>>");
    }

    public static void showAvailableDeliveries(ArrayList<Delivery> deliveries){
        for (int i=0;i<deliveries.size();i++){
            if(deliveries.get(i).getWorkPlaceNumber() < 2){
                System.out.println(i + ")" + deliveries.get(i).getName());
            }
        }
    }

    public static void addFood(ArrayList<Market> markets,ArrayList<Product> products){
        if(markets.size() == 0){
            System.out.println("There are no active restaurant !!");
            return;
        }
        System.out.println("Select the restaurant to add a food to it's menu");
        OrderDuty.showMarkets(markets,false,1);
        int choice = ScannerWrapper.getInstance().nextInt();
        markets.get(choice).getProducts().add(declareFood(products));
        System.out.println("<<<< Done >>>>");
    }

    public static Food declareFood(ArrayList<Product> products){
        System.out.println("Name: ");
        String tempName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Price: ");
        double tempPrice = ScannerWrapper.getInstance().nextDouble();
        System.out.println("Cook time (hour): ");
        double tempCookTime = ScannerWrapper.getInstance().nextDouble();
        products.add(new Food(tempName,tempPrice,tempCookTime));
        return new Food(tempName,tempPrice,tempCookTime);
    }

    public static void searchRestaurant(ArrayList<Restaurant> restaurants){
        if(restaurants.size() == 0){
            System.out.println("There are no active restaurant ");
            return;
        }
        System.out.println("How do you want to search the restaurant ? ( 1-Based on name  2-Based on type )");
        searchRestaurantHandler(restaurants);
    }

    public static void searchRestaurantHandler(ArrayList<Restaurant> restaurants){
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                searchRestaurantName(restaurants);
                break;
            case 2:
                searchRestaurantType(restaurants);
                break;
            default:
                System.out.println("Incorrect input !!! please try again ");
                searchRestaurant(restaurants);
                break;
        }
    }

    public static void searchRestaurantName(ArrayList<Restaurant> restaurants){
        System.out.println("Enter the name : ");
        String name = ScannerWrapper.getInstance().nextLine();
        for (int i=0;i< restaurants.size();i++){
            if(restaurants.get(i).getName().equals(name)){
                System.out.println(i + ")" + restaurants.get(i).getName());
            }
        }
    }

    public static void searchRestaurantType(ArrayList<Restaurant> restaurants){
        System.out.println("Select the type :" +
                "\n1) " + RestaurantType.ECONOMIC +
                "\n2) " + RestaurantType.MEDIUM +
                "\n3) " + RestaurantType.LUXURY);
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                showFilteredRestaurant(RestaurantType.ECONOMIC,restaurants);
                break;
            case 2:
                showFilteredRestaurant(RestaurantType.MEDIUM,restaurants);
                break;
            case 3:
                showFilteredRestaurant(RestaurantType.LUXURY,restaurants);
                break;
            default:
                System.out.println("Incorrect input !!! please try again ");
                searchRestaurantType(restaurants);
                break;
        }
    }

    public static void showFilteredRestaurant(RestaurantType type,ArrayList<Restaurant> restaurants){
        for (int i = 0;i < restaurants.size();i++){
            if(restaurants.get(i).getRestaurantType().equals(type)){
                System.out.println( i + ")" + restaurants.get(i).getName());
            }
        }
    }

    public static void showBestFood(ArrayList<Restaurant> restaurants){
        if(restaurants.size() == 0){
            System.out.println("There are no active restaurant !!");
            return;
        }
        System.out.println("Which one ?");
        OrderDuty.showRestaurants(restaurants,false);
        int choice = ScannerWrapper.getInstance().nextInt();
        showBestFoodsOfMenu(restaurants.get(choice));
    }

    public static void showBestFoodsOfMenu(Restaurant restaurant){
        for (int i = 0;i < restaurant.getFoodMenu().size();i++){
            for(int j = 0;j < restaurant.getFoodMenu().size() - 1 - i;j++){
                if ( restaurant.getFoodMenu().get(i).getScore() < restaurant.getFoodMenu().get(i+1).getScore() ){
                    Food tmp = restaurant.getFoodMenu().get(i+1);
                    restaurant.getFoodMenu().set(i+1,restaurant.getFoodMenu().get(i));
                    restaurant.getFoodMenu().set(i,tmp);
                }
            }
        }
        restaurant.showFoodMenu();
    }

    public static void showBestRestaurantsForMentionedFood(ArrayList<Restaurant> restaurants,ArrayList<Food> foods){
        if(restaurants.size() == 0){
            System.out.println("There are no active restaurant !!");
            return;
        }

        if(foods.size() == 0){
            System.out.println("There are no foods yet ");
            return;
        }

        System.out.println("Select the food : ");
        showFoods(foods);
        int choice = ScannerWrapper.getInstance().nextInt();
        ArrayList<Restaurant> res = new ArrayList<>();
        HashMap<Restaurant,Integer> resIndex = new HashMap<>();
        for (int i = 0;i < restaurants.size();i++){
            for (int j=0;j<restaurants.get(i).getFoodMenu().size();j++){
                if ( restaurants.get(i).getFoodMenu().get(j).getName().equals(foods.get(choice).getName())){
                    res.add(restaurants.get(i));
                    resIndex.put(restaurants.get(i),j);
                }
            }
        }

        for (int i=0;i< res.size();i++){
            for (int j=0;j<res.size() - 1 - i;j++){
                if ( res.get(i).getFoodMenu().get(resIndex.get(res.get(i))).getScore() <
                        res.get(i+1).getFoodMenu().get(resIndex.get(res.get(i+1))).getScore() ){
                    Restaurant tmp = res.get(i);
                    res.set(i,res.get(i+1));
                    res.set(i+1,tmp);
                }
            }
        }
        for (int i=0;i<res.size();i++){
            System.out.println("A");
            System.out.println(i + ")" + res.get(i).getName());
        }
    }

    public static void showFoods(ArrayList<Food> foods){
        if(foods.size() == 0){
            System.out.println("There are no food yet ");
        }

        for (int i=0;i<foods.size();i++){
            System.out.println(i + ")" + foods.get(i).getName());
        }
    }

    public static void showCommentOfFood(ArrayList<Food> foods,ArrayList<Comment> comments){
        if(foods.size() == 0){
            System.out.println("There are no foods yet ");
            return;
        }

        if(comments.size() == 0){
            System.out.println("There are no comments yet ");
            return;
        }

        System.out.println("Which food ? ");
        showFoods(foods);
        int choice = ScannerWrapper.getInstance().nextInt();

        for (int i=0;i< comments.size();i++){
            if(comments.get(i).getSubject().equals(foods.get(choice).getName())){
                System.out.println( i + ")" + comments.get(i).getText());
            }
        }
    }
}
