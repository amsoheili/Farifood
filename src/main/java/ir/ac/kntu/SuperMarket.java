package ir.ac.kntu;

import java.util.ArrayList;

public class SuperMarket extends Market{

    SuperMarket(String name,String address,int openTime,int closeTime,int deliveryMultiplicity){
        super(name, address, openTime, closeTime, deliveryMultiplicity);
    }

    @Override
    public void addProduct(ArrayList<Product> products){
        System.out.println("Name: ");
        String tempName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Price: ");
        double tempPrice = ScannerWrapper.getInstance().nextDouble();
        System.out.println("Cook time (hour): ");
        double tempCookTime = ScannerWrapper.getInstance().nextDouble();
        products.add(new Food(tempName,tempPrice,tempCookTime));
        getProducts().add(new Food(tempName,tempPrice,tempCookTime));
    }
}
