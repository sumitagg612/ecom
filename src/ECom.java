package ecom;

public class ECom {

    public static void main(String[] args) {
        ecom.Authentication.addUser(new ecom.Admin("Sam", "Sam"));
        ecom.UserInterface UI = new ecom.UserInterface();
        UI.userInterface();
    }

}