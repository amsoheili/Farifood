package ir.ac.kntu;

public enum OrderStatus {
    PROCESSING(0),SENDING(1),DELIVERED(2);

    private int rate;

    OrderStatus(int rate){
        this.rate = rate;
    }

    public void setRate(int rate){
        this.rate = rate;
    }

    public int getRate(){
        return rate;
    }


}
