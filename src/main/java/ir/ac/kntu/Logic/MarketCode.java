package ir.ac.kntu.Logic;

public enum MarketCode {
    RESTAURANT(1),SUPERMARKET(2),GROCERY_STORE(3);

    private int code;

    MarketCode(int code){
        this.code = code;
    }

    public static MarketCode getMarket(int code){
        switch (code){
            case 1:
                return RESTAURANT;
            case 2:
                return SUPERMARKET;
            case 3:
                return GROCERY_STORE;
            default:
                return null;
        }
    }
}
