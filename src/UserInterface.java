package ecom;

import java.util.Scanner;

class UserInterface {

    void userInterface() {
        ecom.User currentUser = null;
        Scanner in = new Scanner(System.in);
        loop:
        while (true) {
            System.out.println("Enter 1 for Login and 2 for SignUp");
            String choice = in.nextLine();
            System.out.println("Enter User Id");
            String userId = in.nextLine();
            System.out.println("Enter Password ");
            String password = in.nextLine();
            switch (choice) {
                case "1": {
                    currentUser = ecom.Authentication.login(userId, password);
                    if (currentUser == null) {
                        System.out.println("Incorrect Credentials");
                        continue loop;
                    }
                    break;
                }

                case "2": {
                    System.out.println("Enter \n1 for Buyer \n 2 for Seller");
                    String role = in.nextLine();
                    System.out.println("Enter Name");
                    String name = in.nextLine();
                    System.out.println("Enter Contact");
                    String contact = in.nextLine();
                    System.out.println("Enter Address");
                    String myAddress = in.nextLine();
                    currentUser = ecom.Authentication.signUp(Integer.parseInt(role), userId, password, name, contact, myAddress);
                    if (currentUser == null) {
                        System.out.println("No such User can be created");
                        continue loop;
                    }

                    break;
                }

                default: {
                    System.out.println("Please Retry");
                    continue loop;
                }
            }
            if (currentUser != null) {

                while (true) {
                    int no_of_options = currentUser.showOptions();
                    String option = in.nextLine();
                    if (option.equals("0")) {
                        continue loop;
                    } else if (Integer.parseInt(option) >= 1 && Integer.parseInt(option) <= no_of_options) {
                        if (currentUser.processOptions(option)) {
                            continue loop;
                        }
                    }
                }
            }
        }
    }
}