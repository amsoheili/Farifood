package ir.ac.kntu;

import java.util.ArrayList;

public class CustomerDuty {

    public static void createCustomer(ArrayList<User> users){
        System.out.println("Enter the customer's ID: ");
        int tempId = ScannerWrapper.getInstance().nextInt();
        System.out.println("Enter the username: ");
        String tempUserName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the password: ");
        String tempPassWord = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the customer's address: ");
        String tempAddress = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the customer's phone number: ");
        long tempPhoneNumber = ScannerWrapper.getInstance().nextInt();
        users.add(new Customer(tempId,tempUserName,tempPassWord,tempPhoneNumber,tempAddress));
        System.out.println("Done");
    }

    public static void editCustomer(ArrayList<User> users){
        if(findCustomers(users).size() == 0){
            System.out.println("There are no customers yet !!");
            return;
        }
        System.out.println("Select the customer that you want to edit: ");
        showCustomers(users);
        int choice = ScannerWrapper.getInstance().nextInt();
        editCustomerMenuHandler(choice,users);
    }

    public static void editCustomerMenuHandler(int customerCode,ArrayList<User> users){
        System.out.println("Which part do you want to change ?" +
                "\n1) Id" +
                "\n2) Username " +
                "\n3) Password" +
                "\n4) Phone number" +
                "\n5) Address" +
                "\n6) Exit");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                System.out.println("Enter the new id: ");
                int newId = ScannerWrapper.getInstance().nextInt();
                users.get(customerCode).setId(newId);
                break;
            case 2:
                System.out.println("Enter the new username: ");
                String newUserName = ScannerWrapper.getInstance().nextLine();
                users.get(customerCode).setUserName(newUserName);
                break;
            case 3:
                System.out.println("Enter the new password: ");
                String newPassWord = ScannerWrapper.getInstance().nextLine();
                users.get(customerCode).setPassWord(newPassWord);
                break;
            case 4:
                System.out.println("Enter the new phone number : ");
                int newPhoneNumber = ScannerWrapper.getInstance().nextInt();
                ((Customer)users.get(customerCode)).setPhoneNumber(newPhoneNumber);
                break;
            case 5:
                System.out.println("Enter the new address: ");
                String newAddress = ScannerWrapper.getInstance().nextLine();
                ((Customer)users.get(customerCode)).setAddress(newAddress);
                break;
            case 6:
                return;
            default:
                editCustomerMenuHandler(customerCode,users);
                break;
        }
        editCustomerMenuHandler(customerCode,users);
    }

    public static void showCustomers(ArrayList<User> users){
        for(int i=0;i<users.size();i++){
            if (users.get(i) instanceof Customer){
                System.out.println(i +") "+ users.get(i).getUserName());
            }
        }
    }

    public static ArrayList<Customer> findCustomers(ArrayList<User> users){
        ArrayList<Customer> customers = new ArrayList<>();
        for (int i=0;i< users.size();i++){
            if (users.get(i) instanceof Customer){
                customers.add((Customer) users.get(i));
            }
        }
        return customers;
    }
}
