package ir.ac.kntu;

import java.util.ArrayList;

public class GroceryStore extends Market{

    GroceryStore(String name,String address,int openTime,int closeTime,int deliveryMultiplicity){
        super(name, address, openTime, closeTime, deliveryMultiplicity);
    }

    @Override
    public void addProduct(ArrayList<Product> products){
        System.out.println("Name: ");
        String tempName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Price: ");
        double tempPrice = ScannerWrapper.getInstance().nextDouble();
        products.add(new Fruit(tempName,tempPrice));
        getProducts().add(new Fruit(tempName,tempPrice));
    }
}
