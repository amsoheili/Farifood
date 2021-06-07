package ir.ac.kntu.Markets;

public class OrderPeriod {
    private int start;
    private int end;
    private int multiplicity;
    private double sendingCost = 5000;
    private int maxOrder;
    private boolean increasedCost;

    public OrderPeriod(){

    }

    public OrderPeriod(int start,int end,int multiplicity,int maxOrder){
        this.start = start;
        this.end = end;
        this.multiplicity = multiplicity;
        this.maxOrder = maxOrder;
        this.increasedCost = false;
    }

    public int getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(int multiplicity) {
        this.multiplicity = multiplicity;
    }

    public int getMaxOrder() {
        return maxOrder;
    }

    public void setMaxOrder(int maxOrder) {
        this.maxOrder = maxOrder;
    }

    public boolean isIncreasedCost() {
        return increasedCost;
    }

    public double getSendingCost(){
        return sendingCost;
    }

    public void setSendingCost(double sendingCost) {
        this.sendingCost = sendingCost;
    }

    public void setIncreasedCost(boolean increasedCost) {
        this.increasedCost = increasedCost;
    }

    @Override
    public String toString(){
        return "Sending period : \nStart : " + start + ",End : " +
                end + " ,Multiplicity : " + multiplicity +
                " ,Sending Cost : " + sendingCost ;
    }
}
