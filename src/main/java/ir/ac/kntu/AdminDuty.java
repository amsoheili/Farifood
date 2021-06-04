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

    ublic static void showAdmins(ArrayList<User> users){
        ArrayList<Admin> admins = findAdmins(users);
        for (int i=0;i< admins.size();i++){
            System.out.println(i + ") " + admins.get(i).getUserName());
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
        ArrayList<Admin> admins = findAdmins(users);
        System.out.println("Which one ? ");

    }
}
