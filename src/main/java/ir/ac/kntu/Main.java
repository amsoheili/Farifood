package ir.ac.kntu;

public class Main {
    public static void main(String[] args) {
        //admin username:admin
        //admin password:admin

        Manager manager = new Manager();
        Identify identify = new Identify(manager);

        createRestaurants(manager);
        createUsers(manager,identify);

        while (true){
//            System.out.println("Enter the username: ");
//            String userName = ScannerWrapper.getInstance().nextLine();
//            System.out.println("Enter the password: ");
//            String passWord = ScannerWrapper.getInstance().nextLine();
//            if(userName.equals(userName) && passWord.equals(passWord)){
//                if(!Menu.start(manager)) {
//                    System.out.println("Thanks for using our services .");
//                    break;
//                }
//            }
            if(!identify.start()) {
                System.out.println("Thanks for using our services .");
                break;
            }
        }
    }

    public static void createRestaurants(Manager manager){
        Restaurant res1 = new Restaurant("res1","address",9,21,2,RestaurantType.ECONOMIC,10);
        Restaurant res2 = new Restaurant("res2","address2",9,22,2,RestaurantType.MEDIUM,5);
        Restaurant res3 = new Restaurant("res3","address3",9,24,2,RestaurantType.LUXURY,7);
        Delivery del1 = new Delivery("Del1",TransportationVehicle.MOTORCYCLE,SalaryType.PERORDER,10);
        Delivery del2 = new Delivery("Del2",TransportationVehicle.CAR,SalaryType.PERHOUR,20);
        Food food1 = new Food("res1 makhsoos ",30);
        Food food2 = new Food("res2 makhsoos",10);
        Food food3 = new Food("res3 makhsoos",20);
        Order order1 = new Order(food1,OrderStatus.PROCESSING);
        res1.getFoodMenu().add(food1);
        res2.getFoodMenu().add(food2);
        res3.getFoodMenu().add(food3);
        res1.addOrder(order1);
        res1.addDelivery(del1);
        res2.addDelivery(del1);
        manager.getFoods().add(food1);
        manager.getFoods().add(food2);
        manager.getFoods().add(food3);
        manager.addRestaurant(res1);
        manager.addRestaurant(res2);
        manager.addRestaurant(res3);
        manager.addOrder(order1);
        manager.getDeliveries().add(del1);
        manager.getDeliveries().add(del2);
    }

    public static void createUsers(Manager manager,Identify identify){
        Admin admin = new Admin(1380,"admin","admin");

        Customer cus1 = new Customer(1,"cus1","123",1234,"cus1");
        Customer cus2 = new Customer(2,"cus2","123",1234,"cus2");

        MarketBoss resBoss1 = new MarketBoss(1,"resBoss1","123");

        identify.getUsers().add(admin);
        identify.getUsers().add(cus1);
        identify.getUsers().add(cus2);
        identify.getUsers().add(resBoss1);
    }

}