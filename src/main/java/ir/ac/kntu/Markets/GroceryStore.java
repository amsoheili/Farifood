package ir.ac.kntu.Markets;

import ir.ac.kntu.Logic.OrderStatus;
import ir.ac.kntu.Logic.ScannerWrapper;
import ir.ac.kntu.Product.Fruit;
import ir.ac.kntu.Product.Product;
import ir.ac.kntu.Users.Customer;
import ir.ac.kntu.Users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class GroceryStore extends Market {
    private HashMap<Product,Integer> productKilogramHashMap;
    private ArrayList<OrderPeriod> orderPeriods;
    private int maxOrderAmountPerEachPeriod;

    public GroceryStore(String name,String address,int openTime,int closeTime,int deliveryMultiplicity,int maxOrderAmountPerEachPeriod){
        super(name, address, openTime, closeTime, deliveryMultiplicity);
        this.productKilogramHashMap = new HashMap<>();
        this.maxOrderAmountPerEachPeriod = maxOrderAmountPerEachPeriod;
        this.orderPeriods = makePeriod();
    }

    @Override
    public void addProduct(ArrayList<Product> products){
        System.out.println("Name: ");
        String tempName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Price: ");
        double tempPrice = ScannerWrapper.getInstance().nextDouble();
        System.out.println("Amount (Kilogram) : ");
        int fruitAmount = ScannerWrapper.getInstance().nextInt();
        products.add(new Fruit(tempName,tempPrice));
        getProducts().add(new Fruit(tempName,tempPrice));
        productKilogramHashMap.put(new Fruit(tempName,tempPrice),fruitAmount);
        System.out.println("<<<<< Done >>>>>");
    }

    @Override
    public void orderProduct(ArrayList<Order> orders, User user){
        if (!isThereAvailableProduct()){
            System.out.println("There are no available product");
            return;
        }
        showNumberedProducts();
        int choice = ScannerWrapper.getInstance().nextInt();
        orderProductHandler(orders,user,choice);
    }

    public boolean isThereAvailableProduct(){
        for (int i=0;i<getProducts().size();i++){
            if (productKilogramHashMap.get(getProducts().get(i)) != 0 ){
                //System.out.println(getProducts().toString());
                return true;
            }
        }
        return false;
    }

    public void showNumberedProducts(){
        System.out.println("Product list : ");
        for (int i=0;i<getProducts().size();i++){
            if (productKilogramHashMap.get(getProducts().get(i)) != 0){
                System.out.println(i + ") " + getProducts().get(i).getName() +
                        ",Amount(kilogram) : " + productKilogramHashMap.get(getProducts().get(i)));
            }
        }
    }

    public void orderProductHandler(ArrayList<Order> orders,User user,int productCode){
        System.out.println("How much do you want ?");
        int fruitAmount = ScannerWrapper.getInstance().nextInt();
        System.out.println("Select the sending period : ");
        showOrderPeriods();
        int periodChoice = ScannerWrapper.getInstance().nextInt();
        Order order = new Order(getProducts().get(productCode), OrderStatus.PROCESSING);
        orderPeriods.get(periodChoice).setMultiplicity(orderPeriods.get(periodChoice).getMultiplicity()+1);
        order.setOrderPeriod(orderPeriods.get(periodChoice));
        orders.add(order);
        ((Customer)user).getOrders().add(order);
        productKilogramHashMap.replace(getProducts().get(productCode),
                productKilogramHashMap.get(getProducts().get(productCode)) - fruitAmount );
        getOrders().add(order);
        System.out.println("Your Order is : " + order.toString());
        System.out.println("<<<<< Done >>>>>");
    }

    public void showOrderPeriods(){
        for (int i=0;i<orderPeriods.size();i++){
            if (orderPeriods.get(i).getMultiplicity() < orderPeriods.get(i).getMaxOrder()){
                System.out.println(i + ") " + orderPeriods.get(i).toString());
            }
        }
    }

    public ArrayList<OrderPeriod> makePeriod(){
        ArrayList<OrderPeriod> periods = new ArrayList<>();
        int mainPeriod = (int)getCloseTime() - (int)getOpenTime();
        for (int i=(int)getOpenTime(); i<mainPeriod-1;i+=2){
            periods.add(new OrderPeriod(i,i+2,0,maxOrderAmountPerEachPeriod));
        }
        return periods;
    }

    public HashMap<Product, Integer> getProductKilogramHashMap() {
        return productKilogramHashMap;
    }
}
