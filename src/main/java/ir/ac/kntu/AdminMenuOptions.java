package ir.ac.kntu;

public enum AdminMenuOptions {
    ORDERS_MENU(1),SETTINGS(2),DELIVERY_MENU(3),RESTAURANT_MENU(4);

    private int rate;

    AdminMenuOptions(int rate){
        this.rate = rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getRate(){
        return this.rate;
    }

    public static MenuOptions getOption(int rate){

        if( MenuOptions.ORDERS_MENU.getRate() == rate){
            return MenuOptions.ORDERS_MENU;
        }else if(MenuOptions.SETTINGS.getRate() == rate){
            return MenuOptions.SETTINGS;
        }else if(MenuOptions.DELIVERY_MENU.getRate() == rate){
            return MenuOptions.DELIVERY_MENU;
        }else if(MenuOptions.RESTAURANT_MENU.getRate() == rate){
            return MenuOptions.RESTAURANT_MENU;
        }else{
            return null;
        }

    }
}
