package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;

public class SuperMarket extends Market{
    HashMap<Product,Integer> productMultiplicityHashMap;

    SuperMarket(String name,String address,int openTime,int closeTime,int deliveryMultiplicity){
        super(name, address, openTime, closeTime, deliveryMultiplicity);
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
    }

    @Override
    public void orderProduct(ArrayList<Order> orders,User user){
        if (!isThereAvailableProduct()){
            System.out.println("There are no available product");
            return;
        }
        showNumberedProducts();
        int choice = ScannerWrapper.getInstance().nextInt();

    }

    public void showNumberedProducts(){
        System.out.println("Product list : ");
        for (int i=0;i<getProducts().size();i++){
            if (productMultiplicityHashMap.get(getProducts().get(i)) != 0){
                System.out.println(i + ") " + getProducts().get(i).getName());
            }
        }
    }

    public boolean isThereAvailableProduct(){
        for (int i=0;i<getProducts().size();i++){
            if (productMultiplicityHashMap.get(getProducts().get(i)) != 0){
                return true;
            }
        }
        return false;
    }
}
