package ir.ac.kntu.Markets;

import ir.ac.kntu.Logic.OrderStatus;
import ir.ac.kntu.Product.Product;

public class Order {
    private Product product;
    private OrderStatus status;
    private OrderPeriod orderPeriod;

    Order() { }

    public Order(Product product,OrderStatus status){
        this.product = product;
        this.status = status;
        this.orderPeriod = new OrderPeriod();
    }

    public void setOrderPeriod(OrderPeriod orderPeriod) {
        this.orderPeriod = orderPeriod;
    }

    public OrderPeriod getOrderPeriod() {
        return orderPeriod;
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
        return "\nProduct name: " + product.getName() +
                "\nStatus: " + status + "\n" +
                (orderPeriod!=null ? orderPeriod.toString() : "No order period");
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
