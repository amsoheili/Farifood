package ir.ac.kntu;

import java.util.HashMap;

public class AdminMenu {

    private static HashMap<Integer,MenuOptions> setMenu = new HashMap<>();

    public static boolean start(Manager manager){
        setShowMainMenu();
        return mainMenu(manager);
    }

    public static boolean mainMenu(Manager manager){
        System.out.println("What do you want to do?" +
                "\n1) " + MenuOptions.getOption(1) +
                "\n2) " + MenuOptions.getOption(2) +
                "\n3) " + MenuOptions.getOption(3) +
                "\n4) " + MenuOptions.getOption(4) +
                "\n5) EXIT_THE_PROGRAM");
        return mainMenuHandler(manager);
    }

    public static void setShowMainMenu(){
        System.out.println("Enter your wished set to show these options in main menu : " +
                "(for example 2314) ");
        System.out.println(
                "1) Orders Menu" +
                        "\n2) Settings" +
                        "\n3) Delivery Menu" +
                        "\n4) Restaurant Menu");
        int set = ScannerWrapper.getInstance().nextInt();
        setMenu.put(1,MenuOptions.ORDERS_MENU);
        setMenu.put(2,MenuOptions.SETTINGS);
        setMenu.put(3,MenuOptions.DELIVERY_MENU);
        setMenu.put(4,MenuOptions.RESTAURANT_MENU);
        setMenu.get(set%10).setRate(4);
        set /= 10;
        setMenu.get(set%10).setRate(3);
        set /= 10;
        setMenu.get(set%10).setRate(2);
        set /= 10;
        setMenu.get(set%10).setRate(1);
    }

    public static boolean mainMenuHandler(Manager manager){
        return true;
    }
}
