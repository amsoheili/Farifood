package ir.ac.kntu.Markets;

import ir.ac.kntu.Logic.OrderStatus;
import ir.ac.kntu.Logic.ScannerWrapper;
import ir.ac.kntu.Product.Food;
import ir.ac.kntu.Product.Product;
import ir.ac.kntu.Users.Customer;
import ir.ac.kntu.Users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class SuperMarket extends Market {
    private HashMap<Product,Integer> productMultiplicityHashMap;
    private ArrayList<OrderPeriod> orderPeriods;
    private int maxOrderPerEachPeriod;

    public SuperMarket(String name,String address,int openTime,int closeTime,int deliveryMultiplicity,int maxOrderPerEachPeriod){
        super(name, address, openTime, closeTime, deliveryMultiplicity);
        this.maxOrderPerEachPeriod = maxOrderPerEachPeriod;
        this.productMultiplicityHashMap = new HashMap<Product,Integer>();
        this.orderPeriods = new ArrayList<>();
        this.orderPeriods = makePeriod();
    }


    @Override
    public void addProduct(ArrayList<Product> products){
        System.out.println("Name: ");
        String tempName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Price: ");
        double tempPrice = ScannerWrapper.getInstance().nextDouble();
        System.out.println("Enter the product multiplicity : ");
        int tempProductMultiplicity = ScannerWrapper.getInstance().nextInt();
        products.add(new Food(tempName,tempPrice,0));
        getProducts().add(new Food(tempName,tempPrice,0));
        productMultiplicityHashMap.put(new Food(tempName,tempPrice,0),tempProductMultiplicity);
        System.out.println("Okeye");
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
            if (productMultiplicityHashMap.get(getProducts().get(i)) != 0 ){
                //System.out.println(getProducts().toString());
                return true;
            }
        }
        return false;
    }

    public void showNumberedProducts(){
        System.out.println("Product list : ");
        for (int i=0;i<getProducts().size();i++){
            if (productMultiplicityHashMap.get(getProducts().get(i)) != 0){
                System.out.println(i + ") " + getProducts().get(i).getName());
            }
        }
    }

    public void orderProductHandler(ArrayList<Order> orders,User user,int productCode){
        System.out.println("Select the sending period : ");
        updateSendingCosts();
        showOrderPeriods();
        int periodChoice = ScannerWrapper.getInstance().nextInt();
        Order order = new Order(getProducts().get(productCode), OrderStatus.PROCESSING);
        orderPeriods.get(periodChoice).setMultiplicity(orderPeriods.get(periodChoice).getMultiplicity()+1);
        order.setOrderPeriod(orderPeriods.get(periodChoice));
        orders.add(order);
        ((Customer)user).getOrders().add(order);
        productMultiplicityHashMap.replace(getProducts().get(productCode),
                productMultiplicityHashMap.get(getProducts().get(productCode)) - 1 );
        getOrders().add(order);
        System.out.println("Your Order is : " + order.toString());
        System.out.println("<<<<< Done >>>>>");
    }

    public void updateSendingCosts(){
        for (int i=0;i<orderPeriods.size();i++){
            if ((!orderPeriods.get(i).isIncreasedCost()) &&
                    (orderPeriods.get(i).getMultiplicity() >= orderPeriods.get(i).getMaxOrder()/2)){
                orderPeriods.get(i).setSendingCost(orderPeriods.get(i).getSendingCost() * 1.5);
                orderPeriods.get(i).setIncreasedCost(true);
            }
        }
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
        for (int i=(int)getOpenTime(); i<mainPeriod;i++){
            periods.add(new OrderPeriod(i,i+1,0,maxOrderPerEachPeriod));
        }
        return periods;
    }

    public HashMap<Product,Integer> getProductMultiplicityHashMap(){
        return productMultiplicityHashMap;
    }
}
