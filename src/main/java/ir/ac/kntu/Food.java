package ir.ac.kntu;

public class Food {
    private String name;
    private double price;
    private double cookTime;
    private int score;

    Food(){}

    Food(String name,double price){
        this.name = name;
        this.price = price;
        this.score = 0;
    }

    Food(String name,double price,double cookTime){
        this.name = name;
        this.price = price;
        this.cookTime = cookTime;
        this.score = 0;
    }
    public void setName(String name){

        this.name=name;
    }

    public void setPrice(double price){

        this.price = price;
    }

    public void setCookTime(double cookTime) {
        this.cookTime = cookTime;
    }

    public void setScore(int score){

        this.score = score;
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

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getCookTime() {
        return cookTime;
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

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Food)){
            return false;
        }
        if(o == null){
            return false;
        }
        Food other = (Food) o;
        if(!other.getName().equals(this.name)){
            return false;
        }
        if(other.getPrice() != this.price){
            return false;
        }
        return true;
    }
}
