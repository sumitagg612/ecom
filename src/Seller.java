package ecom;

import java.util.Scanner;

class Seller extends ecom.User implements ecom.Options {

    private String name;
    private String contact;
    private String myAddress;
    private int no_of_ratings;
    private double rating;


    Seller(String userId, String password, String name, String contact, String myAddress) {
        super(userId, password);
        this.name = name;
        this.contact = contact;
        this.myAddress = myAddress;
        this.no_of_ratings = 1;
        this.rating = 5.0;
    }

    void setRating(String rating) {
        this.rating = (((this.rating) * (this.no_of_ratings) + Double.parseDouble(rating)) / ++this.no_of_ratings);
    }

    private void addProduct() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Product ID");
        String productId = in.nextLine();
        System.out.println("Enter Product Name");
        String productName = in.nextLine();
        System.out.println("Enter Market Price");
        String marketPrice = in.nextLine();
        System.out.println("Enter Discount ");
        String discount = in.nextLine();
        System.out.println("Enter Stock ");
        String stock = in.nextLine();
        System.out.println("Enter Product Details ");
        String[] productDetails = new String[1];
        productDetails[0] = " ";
        productDetails[0] = in.nextLine();


        ecom.Catalog.addProductToList(new ecom.Product(productId, productName, marketPrice, discount, stock, productDetails), this);
    }

    private void changeStock() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Product ID");
        String productId = in.nextLine();

        ecom.Product product = ecom.Catalog.searchById(productId, this.getUserId());

        if (product != null) {
            System.out.println("Enter New Stock Size");
            String stock = in.nextLine();
            product.setStock(stock);
        } else {
            System.out.println("Wrong Product Id");
        }

    }

    private void changeProductInfo() {

    }

    private String getBuyerDetails(String buyerId) {
        ecom.Buyer buyer = (ecom.Buyer) ecom.Authentication.getUser(buyerId);
        if (buyer != null) {
            return buyer.getDetails();
        }
        return "No such buyer exist";
    }

    private void rateBuyer(String buyerId, String rating) {
        ecom.Buyer buyer = ecom.Order.buyerInOrder(buyerId, this.getUserId());
        if (buyer != null) {
            buyer.setRating(rating);
        } else {
            System.out.println("You had already rate the Seller or you had not buy from this Seller yet");
        }
    }

    private void changeInfo(String password, String name, String contact, String myAddress) {
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.myAddress = myAddress;
    }


    String getDetails() {
        return ("{ Name :" + this.name + " Contact : " + this.contact +
                " Rating : " + this.rating + " Address : " + this.myAddress + " }\n");
    }


    @Override
    public int showOptions() {
        System.out.println("Enter 0 for Logout\n" +
                "1 for Add new Product \n " +
                "2 for Change Stock \n " +
                "3 for Change Product Info \n " +
                "4 for Get Buyer Details \n " +
                "5 for Rate Buyer \n " +
                "6 for Change Info \n " +
                "7 for Delete Account ");

        return 7;
    }

    @Override
    public boolean processOptions(String string) {
        Scanner in = new Scanner(System.in);
        switch (string) {
            case "1": {
                addProduct();
                break;
            }

            case "2": {
                changeStock();

            }

            case "3": {
                changeProductInfo();

            }

            case "4": {
                System.out.println("Enter Buyer Id");
                System.out.println(getBuyerDetails(in.nextLine()));
            }

            case "5": {
                System.out.println("Enter Buyer Id");
                String buyerId = in.nextLine();
                System.out.println("Enter Your Rating [Max{5.0)]");
                rateBuyer(buyerId, in.nextLine());
                break;
            }

            case "6": {
                System.out.println("Enter What to change \n" +
                        "1 for Password\n" +
                        "2 for Name\n" +
                        "3 for Contact\n" +
                        "4 for Address\n");
                switch (in.nextLine()) {
                    case "1": {
                        System.out.println("Enter New Password \n");
                        changeInfo(in.nextLine(), this.name, this.contact, this.myAddress);
                        break;
                    }

                    case "2": {
                        System.out.println("Enter New Name\n");
                        changeInfo(this.password, in.nextLine(), this.contact, this.myAddress);
                        break;
                    }

                    case "3": {
                        System.out.println("Enter New Contact \n");
                        changeInfo(this.password, this.name, in.nextLine(), this.myAddress);
                        break;

                    }

                    case "4": {
                        System.out.println("Enter New Password \n");
                        changeInfo(this.password, this.name, this.contact, in.nextLine());
                        break;

                    }
                    default:
                        break;
                }
            }

            case "7": {
                if (deleteAccount()) {
                    System.out.println("Account Deleted Successfully");
                    return true;
                } else {
                    System.out.println("No Such User exist");
                }
                break;
            }

            default:
                break;


        }
        return false;
    }

}