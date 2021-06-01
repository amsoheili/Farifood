package ir.ac.kntu;

public class Food extends Product{
    private double cookTime;
    private int score;

    Food(){}

    Food(String name,double price){
        super(name, price);
        this.score = 0;
    }

    Food(String name,double price,double cookTime){
        super(name, price);
        this.cookTime = cookTime;
        this.score = 0;
    }

    public void setCookTime(double cookTime) {
        this.cookTime = cookTime;
    }

    public double getCookTime() {
        return cookTime;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Food)){
            return false;
        }
        if(o == null){
            return false;
        }
        Food other = (Food) o;
        if(!other.getName().equals(getName())){
            return false;
        }
        if(other.getPrice() != getPrice()){
            return false;
        }
        return true;
    }
}
