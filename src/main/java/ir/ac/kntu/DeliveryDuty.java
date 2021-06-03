package ir.ac.kntu;

import java.util.ArrayList;

public class DeliveryDuty {

    public static void declareDelivery(ArrayList<Delivery> deliveries){
        System.out.println("Name : ");
        String tempName = ScannerWrapper.getInstance().nextLine();
        TransportationVehicle tempVehicle = takeDeliveryTransportationVehicle();
        SalaryType tempSalaryType = takeDeliverySalaryType();
        System.out.println("Amount of salary: ");
        double tempSalary = ScannerWrapper.getInstance().nextInt();
        deliveries.add(new Delivery(tempName,tempVehicle,tempSalaryType,tempSalary));
        System.out.println("<<<< Done >>>>");
    }

    public static TransportationVehicle takeDeliveryTransportationVehicle(){
        System.out.println("what kind of vehicle does the delivery have ? ( 1-Car   2-Motorcycle )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                return TransportationVehicle.CAR;
            case 2:
                return TransportationVehicle.MOTORCYCLE;
            default:
                System.out.println("Incorrect input !! please try again ");
                takeDeliveryTransportationVehicle();
        }
        return null;
    }

    public static SalaryType takeDeliverySalaryType(){
        System.out.println("What kind of salary does the delivery have ? ( 1- Per hour  2- Per order )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                return SalaryType.PERHOUR;
            case 2:
                return SalaryType.PERORDER;
            default:
                System.out.println("Incorrect input !! please try again ");
                takeDeliverySalaryType();
        }
        return null;
    }

    public static void showDeliveries(ArrayList<Delivery> deliveries){
        if(deliveries.size() == 0){
            System.out.println("There are no delivery yet.");
            return;
        }
        System.out.println("How do you want to see the deliveries ? ( 1- normal  2-based on descending score )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                for (int i=0;i< deliveries.size();i++){
                    System.out.println(i + ")" + deliveries.get(i).toString());
                }
                break;
            case 2:
                descendingShowDeliveries(deliveries);
                break;
            default:
                showDeliveries(deliveries);
                break;
        }
    }

    public static void descendingShowDeliveries(ArrayList<Delivery> deliveries){
        for (int i=0;i< deliveries.size();i++){
            for (int j=0;j<deliveries.size() - i - 1;j++){
                if(deliveries.get(i).getScore() < deliveries.get(i+1).getScore()){
                    Delivery tmp = deliveries.get(i);
                    deliveries.set(i,deliveries.get(i+1));
                    deliveries.set(i+1,tmp);
                }
            }
        }
        for (int i=0;i< deliveries.size();i++){
            System.out.println(i + ")" + deliveries.get(i).toString());
        }
    }

    public static void setDeclaredDeliveries(ArrayList<Delivery> deliveries){
        if (deliveries.size() == 0){
            System.out.println("There are no delivery yet.");
            return;
        }
        System.out.println("Which delivery do you want to change ? ");
        showDeliveries(deliveries);
        int choice = ScannerWrapper.getInstance().nextInt();
        setDeclaredDeliveriesHandler(choice,deliveries);
    }

    public static void setDeclaredDeliveriesHandler(int deliveryCode,ArrayList<Delivery> deliveries){
        System.out.println("What do you want to change ? " +
                "\n1) Name" +
                "\n2) Vehicle" +
                "\n3) Salary type" +
                "\n4) Salary" +
                "\n5) Score" +
                "\n6) Exit");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                changeDeliveryName(deliveryCode,deliveries);
                break;
            case 2:
                changeDeliveryVehicle(deliveryCode,deliveries);
                break;
            case 3:
                changeDeliverySalaryType(deliveryCode,deliveries);
                break;
            case 4:
                changeDeliverySalary(deliveryCode,deliveries);
                break;
            case 5:
                changeDeliveryScore(deliveryCode,deliveries);
                break;
            case 6:
                return;
            default:
                System.out.println("Incorrect input !!! please try again");
                setDeclaredDeliveriesHandler(deliveryCode,deliveries);
                break;
        }
        setDeclaredDeliveriesHandler(deliveryCode,deliveries);
    }

    public static void changeDeliveryName(int deliveryCode,ArrayList<Delivery> deliveries){
        System.out.println("Enter the new name : ");
        String newName = ScannerWrapper.getInstance().nextLine();
        deliveries.get(deliveryCode).setName(newName);
        System.out.println("<<<< Done >>>>");
    }

    public static void changeDeliveryVehicle(int deliveryCode,ArrayList<Delivery> deliveries){
        System.out.println("What is the delivery's new vehicle ? ( 1-Car  2-Motorcycle )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                deliveries.get(deliveryCode).setVehicle(TransportationVehicle.CAR);
                break;
            case 2:
                deliveries.get(deliveryCode).setVehicle(TransportationVehicle.MOTORCYCLE);
                break;
            default:
                System.out.println("Incorrect input !!! please try again ");
                changeDeliveryVehicle(deliveryCode,deliveries);
                break;
        }
        System.out.println("<<<< Done >>>>");
    }

    public static void changeDeliverySalaryType(int deliveryCode,ArrayList<Delivery> deliveries){
        System.out.println("What is the delivery's new salary type ? ( 1- Per hour  2- Per order )");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                deliveries.get(deliveryCode).setSalaryType(SalaryType.PERHOUR);
                break;
            case 2:
                deliveries.get(deliveryCode).setSalaryType(SalaryType.PERORDER);
                break;
            default:
                System.out.println("Incorrect input !!! please try again ");
                changeDeliverySalaryType(deliveryCode,deliveries);
                break;
        }
        System.out.println("<<<< Done >>>>");
    }

    public static void changeDeliverySalary(int deliveryCode,ArrayList<Delivery> deliveries){
        System.out.println("What is the delivery's new salary ? ");
        double newSalary = ScannerWrapper.getInstance().nextDouble();
        deliveries.get(deliveryCode).setSalary(newSalary);
        System.out.println("<<<< Done >>>>");
    }

    public static void changeDeliveryScore(int deliveryCode,ArrayList<Delivery> deliveries){
        System.out.println("Current Score : " + deliveries.get(deliveryCode).getScore() +
                "\nWhat is the new Score ? ");
        int newScore = ScannerWrapper.getInstance().nextInt();
        deliveries.get(deliveryCode).setScore(newScore);
        System.out.println("<<<< Done >>>>");
    }

    public static void showOrdersOfDelivery(ArrayList<Delivery> deliveries){
        if (deliveries.size() == 0){
            System.out.println("There are no deliveries yet !!");
            return;
        }
        showDeliveries(deliveries);
        int choice = ScannerWrapper.getInstance().nextInt();
        if (deliveries.get(choice).getOrders().size() == 0){
            System.out.println("Orders list is empty !!");
            return;
        }
        for (int i=0;i<deliveries.get(choice).getOrders().size();i++){
            System.out.println(i + ")" + deliveries.get(choice).getOrders().get(i));
        }
    }
}
