package ir.ac.kntu;

import javax.sound.midi.MidiEvent;

public class Main {
    public static void main(String[] args) {
        //admin username:admin
        //admin password:admin

        Manager manager = new Manager();

        Identify identify = new Identify(manager);
        createRestaurants(manager, identify);
        createSuperMarket(manager,identify);
        createGroceryStore(manager,identify);
        createUsers(manager,identify);

        while (true){
            if(!identify.start()) {
                System.out.println("Thanks for using our services .");
                break;
            }
        }
    }

    public static void createRestaurants(Manager manager,Identify identify){
        Restaurant res1 = new Restaurant("res1","address",9,21,2,RestaurantType.ECONOMIC,10);
        Restaurant res2 = new Restaurant("res2","address2",9,22,2,RestaurantType.MEDIUM,5);
        Restaurant res3 = new Restaurant("res3","address3",9,24,2,RestaurantType.LUXURY,7);
        MarketBoss resBoss1 = new MarketBoss(1,"resBoss1","resBoss1");
        MarketBoss resBoss2 = new MarketBoss(2,"resBoss2","resBoss2");
        MarketBoss resBoss3 = new MarketBoss(3,"resBoss3","resBoss3");
        Delivery del1 = new Delivery("Del1",TransportationVehicle.MOTORCYCLE,SalaryType.PERORDER,10);
        Delivery del2 = new Delivery("Del2",TransportationVehicle.CAR,SalaryType.PERHOUR,20);
        Food food1 = new Food("res1 makhsoos ",30);
        Food food2 = new Food("res2 makhsoos",10);
        Food food3 = new Food("res3 makhsoos",20);
        Order order1 = new Order(food1,OrderStatus.PROCESSING);
        res1.setMarketBoss(resBoss1);
        res2.setMarketBoss(resBoss2);
        res3.setMarketBoss(resBoss3);
        res1.getProducts().add(food1);
        res2.getProducts().add(food2);
        res3.getProducts().add(food3);
        res1.addOrder(order1);
        res1.addDelivery(del1);
        res2.addDelivery(del1);
        identify.getUsers().add(resBoss1);
        identify.getUsers().add(resBoss2);
        identify.getUsers().add(resBoss3);
        manager.getProducts().add(food1);
        manager.getProducts().add(food2);
        manager.getProducts().add(food3);
        manager.getMarkets().add(res1);
        manager.getMarkets().add(res2);
        manager.getMarkets().add(res3);
        manager.addOrder(order1);
        manager.getDeliveries().add(del1);
        manager.getDeliveries().add(del2);
    }

    public static void createUsers(Manager manager,Identify identify){
        Admin admin = new Admin(1380,"admin","admin");

        Customer cus1 = new Customer(1,"cus1","123",1234,"cus1");
        Customer cus2 = new Customer(2,"cus2","123",1234,"cus2");

        identify.getUsers().add(admin);
        identify.getUsers().add(cus1);
        identify.getUsers().add(cus2);
    }

    public static void createSuperMarket(Manager manager,Identify identify){
        SuperMarket sup1 = new SuperMarket("sup1","sup1",0,24,2,2);
        MarketBoss supBoss1 = new MarketBoss(1,"supBoss1","supBoss1");
        sup1.setMarketBoss(supBoss1);
        identify.getUsers().add(supBoss1);
        manager.getMarkets().add(sup1);
    }

    public static void createGroceryStore(Manager manager,Identify identify){
        GroceryStore gro1 = new GroceryStore("gro1","gro1",0,24,1,5);
        MarketBoss groBoss1 = new MarketBoss(1,"groBoss1","groBoss1");
        gro1.setMarketBoss(groBoss1);
        identify.getUsers().add(groBoss1);
        manager.getMarkets().add(gro1);
    }
}