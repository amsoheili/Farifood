package ir.ac.kntu;

import java.util.Objects;

public class Order {
    private Food food;
    private OrderStatus status;

    Order() { }

    Order(Food food,OrderStatus status){
        this.food = food;
        this.status = status;
    }
    public void setFood(Food foodName) {
        this.food = foodName;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Food getFood() {
        return food;
    }

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString(){
        return "\nfood name: " + food.getName() +
                "\nStatus: " + status;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Order)){
            return false;
        }
        Order other = (Order) o;
        if(!other.getFood().equals(this.getFood())){
            return false;
        }
        if(!other.getStatus().equals(this.getStatus())){
            return false;
        }
        return  true;
    }
}
