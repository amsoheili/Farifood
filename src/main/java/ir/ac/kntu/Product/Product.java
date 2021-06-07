package ir.ac.kntu.Product;

import ir.ac.kntu.Logic.ScannerWrapper;

import java.util.Objects;

public class Product {
    private String name;
    private double price;
    private int score;

    public Product(){
    }

    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setPrice(double price){

        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void addScore(){
        System.out.println("Rate the food from 1 to 5 : ");
        int score = ScannerWrapper.getInstance().nextInt();
        if (score >= 1 && score <= 5){
            setScore(getScore()+score);
            System.out.println("Thanks for rating the food ");
        }else{
            System.out.println("Incorrect rating !!! please rate again ");
            addScore();
        }
    }

    public void setScore(int score){

        this.score = score;
    }

    public int getScore() {
        return score;
    }


    @Override
    public String toString(){
        return "Name : " +
                this.name +
                "\nPrice : " +
                this.price;
    }

//    @Override
//    public boolean equals(Object o){
//        if (!(o instanceof Product)){
//            return false;
//        }
//        Product p = (Product) o;
//        if (!p.getName().equals(getName())){
//            return false;
//        }
//        if (p.getPrice() != (getPrice())){
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public int hashCode(){
//        return (int)price;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 && getScore() == product.getScore() && Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getScore());
    }
}
