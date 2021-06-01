package ir.ac.kntu;

import java.util.Objects;

public class Order {
    private Product product;
    private OrderStatus status;

    Order() { }

    Order(Product product,OrderStatus status){
        this.product = product;
        this.status = status;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString(){
        return "\nfood name: " + product.getName() +
                "\nStatus: " + status;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Order)){
            return false;
        }
        Order other = (Order) o;
        if(!other.getProduct().equals(this.getProduct())){
            return false;
        }
        if(!other.getStatus().equals(this.getStatus())){
            return false;
        }
        return  true;
    }
}
