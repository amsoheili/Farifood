package ir.ac.kntu;

import java.util.ArrayList;

public class AdminDuty {

    public static void declareAdmin(ArrayList<User> users){
        System.out.println("Enter the name : ");
        String tempUserName = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the username : ");
        String tempPassWord = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter the id : ");
        int tempId = ScannerWrapper.getInstance().nextInt();
        users.add(new Admin(tempId,tempUserName,tempPassWord));
        System.out.println("<<<<< Done >>>>>");
    }

    public static void showAdmins(ArrayList<User> users){
        for (int i=0;i< users.size();i++){
            if (users.get(i) instanceof Admin){
                System.out.println(i + ") " + users.get(i).getUserName());
            }
        }
    }

    public static ArrayList<Admin> findAdmins(ArrayList<User> users){
        ArrayList<Admin> admins = new ArrayList<>();
        for(User user : users){
            if (user instanceof Admin){
                admins.add((Admin) user);
            }
        }
        return admins;
    }

    public static void setDeclaredAdmin(ArrayList<User> users){
        System.out.println("Which one ? ");
        showAdmins(users);
        int choice = ScannerWrapper.getInstance().nextInt();
        setDeclaredAdminHandler((Admin) users.get(choice));
    }

    public static void setDeclaredAdminHandler(Admin admin){
        System.out.println("What do you want to change ?" +
                "\n1) Username" +
                "\n2) Password" +
                "\n3) Id ");
        int choice = ScannerWrapper.getInstance().nextInt();
        switch (choice){
            case 1:
                System.out.println("Enter the new username : ");
                String newUserName = ScannerWrapper.getInstance().nextLine();
                admin.setUserName(newUserName);
                System.out.println("<<<<< Done >>>>>");
                break;
            case 2:
                System.out.println("Enter the new password : ");
                String newPassWord = ScannerWrapper.getInstance().nextLine();
                admin.setPassWord(newPassWord);
                System.out.println("<<<<< Done >>>>>");
                break;
            case 3:
                System.out.println("Enter the new id : ");
                int newId = ScannerWrapper.getInstance().nextInt();
                admin.setId(newId);
                System.out.println("<<<<< Done >>>>>");
                break;
            default:
                System.out.println("Incorrect input !! please try again ");
                setDeclaredAdminHandler(admin);
                break;
        }
    }

}
